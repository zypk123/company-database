package cy.its.service.device.statusChecker.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataAccess.DbExecuter;
import cy.its.service.common.dataAccess.DiscardedDbError;
import cy.its.service.common.log.LogManager;
import cy.its.service.device.statusChecker.util.FileUtil;
import cy.its.service.device.statusChecker.util.Timer;

abstract class Updater<T> {

	List<T> statusList = new ArrayList<T>();

	private Object sync = new Object();

	Timer toDbTimer;

	boolean isStop = ConstValue.BOOL_FALSE;

	String sql;

	String optName;

	Class<T> clazz;

	String filePrefix;

	String directory;

	public Updater(String optName, String optEnName, Class<T> clazz) {
		this.optName = optName;
		this.filePrefix = optEnName;
		this.clazz = clazz;
		this.sql = getSql();
		this.directory = System.getProperty("user.dir") + java.io.File.separator + optEnName;
		FileUtil.createDirectory(directory);

		toDbTimer = new Timer(() -> {
			try {
				this.toDataBase1();
			} catch (Throwable e) {
				LogManager.error(this.optName + " 失败:" + StringUtil.getErrorDetail(e));
			}
		} , 5000, 5000);
		toDbTimer.start();
	}

	/**
	 * 数据写缓存
	 * @param t
	 */
	public void write(T t) {
		synchronized (sync) {
			statusList.add(t);
		}
	}

	/**
	 * 停止处理
	 */
	public void close() {
		toDbTimer.stop();
		toDataBase1();
	}

	/**
	 * 数据持久化处理
	 */
	private void toDataBase1() {
		// 扫描文件写到数据库
		boolean isOk = fileToDB();
		if (isOk) {
			// 文件处理完毕
			// 将缓存数据写至数据库, 失败写至本地文件
			List<T> lstLeft = cacheToDB();

			if (lstLeft != null) {
				// 存在写入失败的缓存数据
				// 将入库失败的以及排到后面未入库的数据写入文件
				cacheToFile(lstLeft);
			}
		} else {
			// 存在未处理完成的文件
			// 将缓存数据按顺序存储到文件中
			cacheToFile(null);
		}
	}

	/**
	 * 缓存到文件
	 * @param lstLeft
	 */
	private void cacheToFile(List<T> lstLeft) {
		try {
			String fileName = getFileName();
			File file = new File(fileName);
			if (lstLeft != null) {
				FileUtil.writeFile(lstLeft, file);
			}

			while (ConstValue.BOOL_TRUE) {
				lstLeft = readCache();
				if (lstLeft != null) {
					FileUtil.writeFile(lstLeft, file);
				} else {
					break;
				}
			}
		} catch (Throwable e) {
			LogManager.error(e);
		}
	}

	/**
	 * 缓存到数据库
	 * @return
	 */
	private List<T> cacheToDB() {
		// 不入库的数据存储集合
		List<T> lstLeft = null; 
		while (ConstValue.BOOL_TRUE) {
			// 读取缓存
			List<T> lst = readCache();
			if (lst != null) {
				// 检查不入库的数据存储集合是否创建
				if(lstLeft != null){
					// 已创建: 表明之前已存在未入库成功的数据
					// 保存该数据至不入库的数据存储集合，不再做入库处理
					lstLeft.addAll(lst);
					continue;
				}
				// 写库
				writeDb(lst);
				// 判断是否存在入库未成功且等待下次入库的数据
				if(lst.size() > ConstValue.INT_0) {
					// 存在：保存至不入库的数据存储集合
					if(lstLeft == null){
						lstLeft = new ArrayList<T>();
					}
					lstLeft.addAll(lst);
				}
			} else {
				// 缓存中无数据，停止处理
				break;
			}
		}
		// 返回缓存中读取到的、未写入库的且等待下次处理的数据集合
		return lstLeft;
	}

	/**
	 * 文件到数据库
	 * @return
	 */
	private boolean fileToDB() {
		// 默认：文件处理完成
		boolean isOk = ConstValue.BOOL_TRUE;
		try {
			// 扫描文件
			List<File> lstFile = FileUtil.findFiles(directory,
					(f) -> f.isFile() && f.getName().startsWith(this.filePrefix));
			if (lstFile.size() > ConstValue.INT_0) {
				// 文件名依次排序
				sortFiles(lstFile);				
				for (File file : lstFile) {
					// 读取文件
					List<T> lstT = FileUtil.readFile(file, clazz);
					if (lstT != null) {
						// 数据入库
						writeDb(lstT);
						// 判断数据是否全部入库
						if (lstT.size() == ConstValue.INT_0) {
							// 数据全部入库
							// 删除数据对应的文件
							FileUtil.deleteFile(file);
							// 继续处理下个文件
							continue;
						} else {
							// 数据未全部入库
							// 删除旧文件
							FileUtil.deleteFile(file);
							// 将未处理完的数据写入同旧文件同名的文件中
							FileUtil.writeFile(lstT, file);
						}
					}

					// 文件未处理完成:退出处理
					isOk = ConstValue.BOOL_FALSE;
					break;
				}
			}
		} catch (Throwable e) {
			LogManager.error(e);
			isOk = ConstValue.BOOL_FALSE;
		}

		return isOk;
	}

	/**
	 * 文件名排序
	 * @param lstFile
	 */
	private void sortFiles(List<File> lstFile) {
		lstFile.sort(new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				return Integer.valueOf(o1.getName().substring(filePrefix.length()))
						.compareTo(Integer.valueOf(o2.getName().substring(filePrefix.length())));
			}
		});
	}

	static String FMT1 = "%s 批量处理%d条记录成功, 耗时:%d毫秒";
	static String FMT2 = "%s 批量处理%d条记录失败, 耗时:%d毫秒, 将改为单条处理,  原因:%s";
	
	/**
	 * 入库处理
	 * @param lst
	 */
	private void writeDb(List<T> lst) {
		long t0 = System.currentTimeMillis();
		try {
			// 记录批量入库
			DbExecuter.batchInsert(sql, lst.stream().map(t->getParamters(t)).collect(Collectors.toList()));
			LogManager.debug(String.format(FMT1, this.optName, lst.size(), System.currentTimeMillis() - t0));
			// 批量入库成功
			lst.clear();
		} catch (Throwable e) {
			// 批量入库失败
			LogManager.error(String.format(FMT2, this.optName, lst.size(),
					System.currentTimeMillis() - t0, StringUtil.getErrorDetail(e)));
			// 改为单条处理
			int size = lst.size();
			for (int i = ConstValue.INT_0; i < size; i++) {
				try {
					// 单条入库处理
					DbExecuter.singleInsert(sql, getParamters(lst.get(ConstValue.INT_0)));
					// 单条入库成功
					lst.remove(ConstValue.INT_0);
				} catch (Throwable e2) {
					// 单条入库出错
					if (DiscardedDbError.isDiscardedDataErrorExcludeUniqueConstraint(e2)) {
						// 入库错误 为 可丢弃的错误：继续下一步处理
						lst.remove(ConstValue.INT_0);
					} else {
						// 入库错误 为 不可丢弃：不继续处理记录
						break;
					}
				}
			}
		}
	}

	/**
	 * 缓存读取处理
	 * @return
	 */
	private List<T> readCache() {
		synchronized (sync) {
			if (statusList.size() > ConstValue.INT_0) {
				List<T> lst = new ArrayList<T>(statusList.size());
				lst.addAll(statusList);
				statusList.clear();
				return lst;
			}
		}

		return null;
	}

	/**
	 * 生成文件名
	 * @return
	 */
	private String getFileName() {
		return this.directory + java.io.File.separator + FileUtil.maxFileName(this.directory, this.filePrefix);
	}

	abstract String getSql();

	abstract List<Object> getParamters(T t);
}