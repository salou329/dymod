package util;

import java.util.HashMap;

public final class Global {

	private static HashMap<String, String> str;
	static {
		config();
		
	}
	private static void config() {
		str = new HashMap<>();
	}

	public static String get(String key) {
		if (str.get(key) == null) {
			System.out.println(key + "不存在");

		}
		return str.get(key);

	}

	public static void set(String key, String value) {
		
		str.put(key, value);
		
	}
}
