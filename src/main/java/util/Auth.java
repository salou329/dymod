package util;

import java.util.Calendar;

public final class Auth {
	/**
	 * 权限
	 * 
	 * @return
	 */
//	public static boolean authenticate() {
//		boolean result = false;
//		try {
//			HashMap<String, String> params = new HashMap<>();
//			params.put("username", "flb");
//			params.put("password", "flb");
//			HashMap<String, String> header = new HashMap<>();
//			header.put("Cookie", "JSESSIONID=A90E9FABF87E2923A9E0F432C39BF842");
//			String x = HttpUtil.doPost("http://45.77.177.55/LoginServlet", params, header);
//			if (x == null) {
//				result = true;
//			} else {
//				System.err.println("网络异常，请检查网络！");
//				Tools.sendTelegram(Tools.FindIP() + "异常！不存在的" + params.get("username"), "739469661");
//			}
//		} catch (Exception e) {
//			System.err.println("网络异常，请检查网络！");
//			Tools.sendTelegram(Tools.FindIP() + "异常！服务器或数据库异常，请检查！！！", "739469661");
//		}
//		
//		return result;
//		// return sendTelegram("测试", "739469661");
//
//	}

//	public static boolean authenticate() {
//		boolean result = false;
//		try {
//			String x = HttpUtil.doGet("http://salou.top/download/auth.html");
//			if (x.contains("flb")) {
//				result = true;
//			} else {
//				System.err.println("网络异常，请检查网络！");
//				Tools.sendTelegram(Tools.FindIP() , "739469661");
//			}
//		} catch (Exception e) {
//			System.err.println("网络异常，请检查网络！");
//			Tools.sendTelegram(Tools.FindIP() + "异常！服务器或数据库异常，请检查！！！", "739469661");
//		}
//
//		return result;
//	} 
	public static boolean authenticate() {
		Calendar calendar = Calendar.getInstance();      
		int year = calendar.get(Calendar.YEAR); 
		if(year==2020) {
			return true;
		}else {
			return false;
		}
				
	}

}
