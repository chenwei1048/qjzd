package com.qjzd.network.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;


public class ConfigUtil {

	//配置文件的文件名，配置文件位于$BASE/conf下
	private static final String DEFAULT_CONF_FILE = "/Params.properties";

	private static PropertiesConfiguration config = null;

	private static Long lastModified = 0L;
	static {
		try {
			String absolutePath = ConfigUtil.class.getResource(getConfigFile()).getFile();
            System.out.println("*********************"+absolutePath);
            config = new PropertiesConfiguration(absolutePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 判断配置文件是否改动
	 * @return returnValue ：true:改动过 ，false:没有改动过
	 */
	private static boolean isPropertiesModified() {
		boolean returnValue = false;
		File file = new File(ConfigUtil.class.getClassLoader().getResource(DEFAULT_CONF_FILE).getPath());
		if (file.lastModified() > lastModified) {
			System.out.println("修改配置文件" + DEFAULT_CONF_FILE);
			lastModified=file.lastModified();
			returnValue = true;
		}
		return returnValue;
	}



	public static void reload() throws ConfigurationException{
		String absolutePath = ConfigUtil.class.getResource(getConfigFile()).getFile();
		config = new PropertiesConfiguration(absolutePath);
	}

	public static void setValue(String key, String defaultValue){
		config.setProperty(key, defaultValue);
		try {
			config.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	protected static String getConfigFile(){
		return DEFAULT_CONF_FILE;
	}

	public static String getString(String key, String defaultValue) {
		return config.getString(key, defaultValue);
	}

	public static String getString(String key) {

		return config.getString(key, null);
	}

	public static int getInt(String key, int defaultValue) {
		return config.getInt(key, defaultValue);
	}

	public static int getInt(String key) {
		return config.getInt(key, 0);
	}

	public static Long getLong(String key, Long defaultValue) {
		return config.getLong(key, defaultValue);
	}

	public static Long getLong(String key) {
		return config.getLong(key, 0);
	}

	public static String[] getStringArray(String key) {
		return config.getStringArray(key);
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		return config.getBoolean(key, defaultValue);
	}

	public static boolean getBoolean(String key) {
		return config.getBoolean(key, false);
	}

	public static float getFloat(String key, float defaultValue) {
		return config.getFloat(key, defaultValue);
	}

	public static float getFloat(String key) {
		return config.getFloat(key, 0);
	}

	public static String getChinese(String key){

		try {
			InputStream resource = ConfigUtil.class.getClassLoader().getResourceAsStream("Params.properties");
			Properties properties2 = new Properties();
			properties2.load(resource);
			String property  = properties2.getProperty(key);
			return new String(property.getBytes("iso-8859-1"));
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}


	public static void main(String[] args) {
		try {
			InputStream resource = ConfigUtil.class.getClassLoader().getResourceAsStream("Params.properties");
			Properties properties2 = new Properties();
			properties2.load(resource);
			String property = properties2.getProperty("sss");
			System.out.println("----" + new String(property.getBytes("iso-8859-1")));
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}

