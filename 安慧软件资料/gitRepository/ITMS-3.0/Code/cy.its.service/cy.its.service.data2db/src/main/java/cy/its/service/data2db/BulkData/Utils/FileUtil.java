package cy.its.service.data2db.BulkData.Utils;

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
import cy.its.service.common.log.LogManager;

public class FileUtil {
	public static boolean createDirectory(String dir) {
		File file = new File(dir);
		if(file.exists() || file.isDirectory()){
			return ConstValue.BOOL_TRUE;
		}
		
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

	public static void readFile(List<String> lstCache, File file) {
		lstCache.clear();
		FileInputStream stream = null;
		InputStreamReader streamReader = null;
		BufferedReader buffReader = null;
		try {
			stream = new FileInputStream(file);
			streamReader = new InputStreamReader(stream, "UTF-8");
			buffReader = new BufferedReader(streamReader);

			String lineTxt = null;
			while ((lineTxt = buffReader.readLine()) != null) {
				lstCache.add(lineTxt);
			}
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
				// todo:
				e.printStackTrace();
			}

		}
	}

	static String lineSeparator = System.lineSeparator();

	public static void writeFile(List<String> lstLine, File f) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			fos = new FileOutputStream(f, true);
			osw = new OutputStreamWriter(fos, "UTF-8");
			bw = new BufferedWriter(osw);
			for (String line : lstLine) {
				try {
					bw.write(line + lineSeparator);
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
}