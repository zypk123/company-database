package cy.its.service.device.statusAnalysis.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.log.LogManager;

public class FileUtil {
	public static boolean createDirectory(String dir) {
		File file = new File(dir);
		return file.mkdirs();
	}

	public static List<File> findFiles(String dir, Predicate<File> filter) {
		File folder = new File(dir);
		File[] files = folder.listFiles();
		if (files != null) {
			List<File> lst = new ArrayList<File>(files.length);
			for (File file : files) {
				if (filter != null) {
					if (filter.test(file)) {
						lst.add(file);
					}
				}
			}
			return lst;
		}
		return new ArrayList<File>(ConstValue.INT_0);
	}

	public static boolean deleteFile(File file) {
		try {
			return file.delete();
		} catch (Exception e) {
			return ConstValue.BOOL_FALSE;
		}
	}

	public static <T> List<T> readFile(File file, Class<T> clazz) {
		FileInputStream stream = null;
		InputStreamReader streamReader = null;
		BufferedReader buffReader = null;
		try {
			stream = new FileInputStream(file);
			streamReader = new InputStreamReader(stream, "UTF-8");
			buffReader = new BufferedReader(streamReader);

			String lineTxt = null;
			List<T> lstCache = new ArrayList<T>();
			while ((lineTxt = buffReader.readLine()) != null) {
				if (!StringUtil.isNullOrEmpty(lineTxt.trim())) {
					lstCache.add(JsonUtil.parseObject(lineTxt, clazz));
				}
			}
			return lstCache;

		} catch (Throwable e) {
			LogManager.error(e);
		} finally {
			try {
				if (buffReader != null) {
					buffReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (streamReader != null) {
					streamReader.close();
				}
			} catch (IOException e) {
				// todo:
				e.printStackTrace();
			}

			try {
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return null;
	}

	static String lineSeparator = System.lineSeparator();

	public static<T> void writeFile(List<T> lstLine, File f) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			fos = new FileOutputStream(f, true);
			osw = new OutputStreamWriter(fos, "UTF-8");
			bw = new BufferedWriter(osw);
			for (T t : lstLine) {
				try {
					bw.write(JsonUtil.serialize(t) + lineSeparator);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (osw != null) {
					osw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static String maxFileName(String dir, String prefix) {
		File folder = new File(dir);
		File[] files = folder.listFiles();
		int index = Integer.MIN_VALUE;
		if (files != null) {
			String fName;
			int i;
			for (File file : files) {
				fName = file.getName();
				if (fName.startsWith(prefix)) {
					i = Integer.valueOf(fName.substring(prefix.length()));

					if (i > index) {
						index = i;
					}
				}
			}
		}

		if (index == Integer.MIN_VALUE) {
			index = ConstValue.INT_0;
		} else {
			index = index + ConstValue.INT_1;
		}

		return prefix + index;
	}

}
