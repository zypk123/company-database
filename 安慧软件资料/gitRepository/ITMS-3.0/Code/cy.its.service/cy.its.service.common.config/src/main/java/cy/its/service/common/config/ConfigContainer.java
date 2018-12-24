package cy.its.service.common.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import cy.its.service.common.StringUtil;

class ConfigContainer {

	private File folder;
	private Map<String, String> props;

	ConfigContainer(File folder) {
		if (folder != null) {
			this.folder = folder;
			initProps();
		}
	}

	ConfigContainer(String path) {
		if (!StringUtil.isNullOrEmpty(path)) {
			this.folder = new File(path);
			initProps();
		}
	}

	public String getValue(String propKey) {
		return props.get(propKey);
	}

	void initProps() {
		props = new HashMap<String, String>();
		if (this.folder != null) {
			for (File f : folder.listFiles()) {
				if (f.isFile() && f.getName().toLowerCase().endsWith(".properties")) {
					readProperty(f);
				}
			}
		}
	}

	private void readProperty(File f) {
		InputStream in = null;
		try {
			in = new FileInputStream(f);
			Properties p = new Properties();
			p.load(in);
			p.entrySet().forEach(kv -> {
				props.put(kv.getKey().toString(), kv.getValue().toString());
			});
		} catch (Exception e) {
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
