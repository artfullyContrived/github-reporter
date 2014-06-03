package com.demandcube.githubflow.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.google.common.io.Closeables;

public class PropertyUtils {

	private static final Logger logger = Logger.getLogger(PropertyUtils.class);
	private Properties properties;
	private String filename;

	public PropertyUtils(String filename) {
		this.filename = filename;
		properties = getPropertyFile(filename);
	}

	public static Properties getPropertyFile(String filename) {

		Properties prop = new Properties();
		InputStream inputStream = null;

		String path;
		try {
			path = System.getProperty("user.dir")
					+ System.getProperty("file.separator") + filename;
			logger.debug("PATH: " + path);
			inputStream = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			try {
				URL urlpath = prop.getClass().getResource(filename);
				inputStream = new FileInputStream(urlpath.getPath());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		}
		try {
			if (inputStream != null) {
				prop.load(inputStream);

			}
		} catch (IOException IOE) {
			IOE.printStackTrace();
		} finally {
			Closeables.closeQuietly(inputStream);
		}
		// logger.debug("Got These Props: " + prop.entrySet());
		return prop;
	}

	public boolean updateProperty(String key, String value) {

		properties.setProperty(key, value);
		String path = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + filename;
		logger.debug("PATH: " + path);
		try {
			properties.store(new FileOutputStream(path), null);
		} catch (FileNotFoundException e) {
			logger.error(e, e);
		} catch (IOException e) {
			logger.error(e, e);
		}
		return false;
	}

	public String getProperty(String string) {
		return properties.getProperty(string);
	}

	public Properties getProperties() {

		return properties;
	}
}
