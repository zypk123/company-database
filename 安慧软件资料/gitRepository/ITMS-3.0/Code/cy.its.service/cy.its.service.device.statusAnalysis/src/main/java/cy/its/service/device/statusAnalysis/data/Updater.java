package cy.its.service.device.statusAnalysis.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataAccess.DbExecuter;
import cy.its.service.common.log.LogManager;
import cy.its.service.device.statusAnalysis.util.FileUtil;
import cy.its.service.device.statusAnalysis.util.Timer;

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

	public void write(T t) {
		synchronized (sync) {
			statusList.add(t);
		}
	}

	public void close() {
		toDbTimer.stop();
		toDataBase();
	}

	private void toDataBase() {
		List<T> lst = null;
		while (ConstValue.BOOL_TRUE) {
			lst = readCache();
			if (lst != null) {
				this.writeDb(lst);
			} else {
				break;
			}
		}
	}

	private void toDataBase1() {
		boolean isOk = fileToDB();
		if (isOk) {
			// 将缓存数据写至数据库, 失败写至本地文件
			List<T> lstLeft = cacheToDB();

			if (lstLeft != null) {
				// 存在写入失败的缓存数据
				cacheToFile(lstLeft);
			}
		} else {
			cacheToFile(null);
		}
	}

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

	private List<T> cacheToDB() {
		while (ConstValue.BOOL_TRUE) {
			List<T> lst = readCache();
			if (lst != null) {
				if (!writeDb(lst)) {
					return lst;
				}
			} else {
				break;
			}
		}
		return null;
	}

	private boolean fileToDB() {
		boolean isOk = ConstValue.BOOL_TRUE;
		try {
			List<File> lstFile = FileUtil.findFiles(directory,
					(f) -> f.isFile() && f.getName().startsWith(this.filePrefix));
			if (lstFile.size() > ConstValue.INT_0) {
				sortFiles(lstFile);
				for (File file : lstFile) {
					List<T> lstT = FileUtil.readFile(file, clazz);
					if (lstT != null && writeDb(lstT)) {
						FileUtil.deleteFile(file);
					} else {
						isOk = ConstValue.BOOL_FALSE;
						break;
					}
				}
			}
		} catch (Throwable e) {
			LogManager.error(e);
			isOk = ConstValue.BOOL_FALSE;
		}

		return isOk;
	}

	private void sortFiles(List<File> lstFile) {
		lstFile.sort(new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				return Integer.valueOf(o1.getName().substring(filePrefix.length()))
						.compareTo(Integer.valueOf(o2.getName().substring(filePrefix.length())));
			}
		});
	}

	private boolean writeDb(List<T> lst) {
		long t0 = System.currentTimeMillis();
		try {
			List<List<Object>> lstParams = new ArrayList<List<Object>>(lst.size());
			for (T t : lst) {
				lstParams.add(getParamters(t));
			}
			
			DbExecuter.batchInsert(sql, lstParams);
			
			LogManager.debug(
					String.format("%s 处理%d条记录成功, 耗时:%d毫秒", this.optName, lst.size(), System.currentTimeMillis() - t0));

			return ConstValue.BOOL_TRUE;
		} catch (Throwable e) {
			LogManager.error(String.format("%s 处理%d条记录失败, 耗时:%d毫秒, 原因:%s", this.optName, lst.size(),
					System.currentTimeMillis() - t0, StringUtil.getErrorDetail(e)));
		}

		return ConstValue.BOOL_FALSE;
	}

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

	private String getFileName() {
		return this.directory + java.io.File.separator + FileUtil.maxFileName(this.directory, this.filePrefix);
	}

	abstract String getSql();

	abstract List<Object> getParamters(T t);
}