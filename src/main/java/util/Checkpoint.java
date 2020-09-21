package util;


import java.util.Properties;

import org.testng.Assert;

public final class Checkpoint {

	static Properties props = System.getProperties();
	static String usr = props.getProperty("user.name");
	/**
	 * 判断两个值是否相等
	 *
	 * @param yq
	 * @param sj
	 */
	public static void Equals(String yq, String sj) {
		try {
			Assert.assertEquals(yq, sj);
		} catch (AssertionError e) {
			//Tools.sendTelegram(usr+"在"+Tools.time()+"发现bug："+Thread.currentThread().getStackTrace()[2].getMethodName()+"//"+e.getMessage());
			System.err.println("-------------断言未通过-------------");
			throw e;
		}
	}

	/**
	 * 判断是否包含预期的值
	 *
	 * @param yq
	 * @param sj
	 */
	public static void contains(String yq, String sj) {
		try {
			Assert.assertTrue(sj.contains(yq));
		} catch (AssertionError e) {
			//Tools.sendTelegram(usr+"在"+Tools.time()+"发现bug："+Thread.currentThread().getStackTrace()[2].getMethodName()+"//"+e.getMessage());
			System.err.println("-------------断言未通过-------------");
			throw e;
		}
		
	}

	/**
	 * 判断是否为true
	 *
	 * @param test
	 */
	public static void isTrue(boolean test) {
		
		try {
			Assert.assertTrue(test);
		} catch (AssertionError e) {
			//Tools.sendTelegram(usr+"在"+Tools.time()+"发现bug："+Thread.currentThread().getStackTrace()[2].getMethodName()+"//"+e.getMessage());
			System.err.println("-------------断言未通过-------------");
			throw e;
		}
	}

	/**
	 * 判断是否为false
	 *
	 * @param test
	 */
	protected static void isFalse(boolean test) {
		try {
			Assert.assertFalse(test);
		} catch (AssertionError e) {
			//Tools.sendTelegram(usr+"在"+Tools.time()+"发现bug："+Thread.currentThread().getStackTrace()[2].getMethodName()+"//"+e.getMessage());
			System.err.println("-------------断言未通过-------------");
			throw e;
		}
	}

	/**
	 * 判断对象是否显示
	 *
	 * @param Element
	 */
	public static void isDisplay(AppMethod driver, String sheet, String objectName) {
		try {
			driver.Element(sheet, objectName).isDisplayed();
			isTrue(true);
		} catch (Exception e) {
			isTrue(false);
		}
	}

	public static void isDisplay(AppIosMethod driver, String sheet, String objectName) {
		try {
			driver.Element(sheet, objectName).isDisplayed();
			isTrue(true);
		} catch (Exception e) {
			isTrue(false);
		}
	}

	public static void isDisplay(WebMethod driver, String sheet, String objectName) {
		try {
			driver.Element(sheet, objectName).isDisplayed();
			isTrue(true);
		} catch (Exception e) {
			isTrue(false);
		}
	}
	
	
}
