package gov.state.history.test_utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HSGUtils {
	public static final String PROPERTIES_FILE = "test.properties";
	private static Properties defaultProps = null,  props = null;

	public static String getConfigParam(String key) {
		synchronized(HSGUtils.class) {
			if(props == null) {
				createDefaultProps();
				props = new Properties(defaultProps);
				try(FileInputStream in = new FileInputStream(PROPERTIES_FILE)) {
					props.load(in);
				} catch (IOException e) {
					System.err.println("WARNING: config file " + PROPERTIES_FILE + " cannot be read, loading defaults. ("+e.getMessage()+")");
				}
			}
		}
		return props.getProperty(key);
		
	}

	private static void createDefaultProps() {
		defaultProps = new Properties();
		defaultProps.put("base_url", "http://localhost:8080");
		defaultProps.put("app_path", "/exist/apps/hsg-shell");
	}
	
	/**
	 * @return the hsg-shell appliaction URL, e.g.like http://localhost:8080/exist/apps/hsg-shell
	 * without the trailing slash
	 */
	public static String getApplicationUrl() {
		return "" + getConfigParam("base_url") + getConfigParam("app_path");
	}
	
}
