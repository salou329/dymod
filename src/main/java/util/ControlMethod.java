package util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;

public class ControlMethod {

	Properties props = System.getProperties();
	String path = "src/main/java/page/" + Tools.getValue("UIlibrary") + ".xlsx";
	// 构造函数
	String os = props.getProperty("os.name");
	String usr = props.getProperty("user.name");

	public ControlMethod() {
		if (Tools.getValue("log").equals("1")) {
			try {
				System.setOut(new PrintStream(new FileOutputStream("src/main/java/page/log.txt")));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println(usr + ",您好！");
		System.out.println(Tools.time());
		Tools.FindIP();
		System.out.println("您正在使用的操作系统:" + os);
		if (Tools.getValue("repertoryOnDB").equals("no")) {
			System.out.println("对象仓库:本地对象仓库");
		} else if (Tools.getValue("repertoryOnDB").equals("yes")) {
			System.out.println("对象仓库:数据库对象仓库");
		} else {
			System.out.println("配置文件中未配置对象仓库位置，请检查 config.properties 。");
		}

		ReadExcelUtil excelUtil = new ReadExcelUtil("src/main/java/page/global.xlsx", "data");
		int j = excelUtil.excel_get_rows();
		if (j == 1) {
			System.out.println("未配置全局参数");
		} else {
			System.out.println("正在加载全局参数，请稍后...");
			try {
				for (int i = 1; i <= j; i++) {
					Global.set(excelUtil.getCellDataasstring(i, 0), excelUtil.getCellDataasstring(i, 1));
				}
				System.out.println("加载全局参数完成!");
			} catch (Exception e) {
				System.err.println("×加载全局参数失败!");
			}
		}
	}

	/**
	 * 强制等待
	 *
	 * @author 史文彬
	 * @param i
	 * @return
	 */
	public void sleep(int i) {

		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			System.out.println("等待超时！");
		}

	}

	/**
	 * 随机等待
	 * 
	 * @param max 秒不是毫秒
	 */
	public void randomSleep(int max) {
		this.sleep(Tools.randomNumber(0, max) * 1000);
	}

	/**
	 * h5
	 * 
	 * @return
	 */
	public ChromeOptions h5() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-infobars");
		Map<String, Object> deviceMetrics = new HashMap<String, Object>();
		deviceMetrics.put("width", 360);
		deviceMetrics.put("height", 640);
		deviceMetrics.put("pixelRatio", 3.0);
		Map<String, Object> mobileEmulation = new HashMap<String, Object>();
		mobileEmulation.put("deviceMetrics", deviceMetrics);
		mobileEmulation.put("userAgent",
				"Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
		options.setExperimentalOption("mobileEmulation", mobileEmulation);
		return options;
	}

	/**
	 * 超级等待
	 *
	 * @author 史文彬
	 */
	public void supersleep(WebElement element) {

		for (int second = 0; second <= 10; second++) {
			try {
				if (element.isDisplayed()) {
					break;
				}
			} catch (Exception e) {
				this.sleep(1000);
			}

		}
	}

	public void supersleep(MobileElement element) {

		for (int second = 0; second <= 10; second++) {
			try {
				if (element.isDisplayed()) {
					break;
				}
			} catch (Exception e) {
				this.sleep(1000);
			}

		}
	}

	/**
	 * 切换到webview
	 * 
	 * @param driver
	 */
	public void switchtoWebview(MobileDriver<?> driver) {
		driver.getContextHandles().forEach((handle) -> {
			if (handle.contains("WEBVIEW")) {
				driver.context(handle);
			} else {
				System.out.println("没有发现'WEBVIEW'");
				System.out.println("handle内容如下：" + handle);
			}
		});
	}

	/**
	 * 切换回原生
	 * 
	 * @param driver
	 */
	public void swtichtoNativeAPP(MobileDriver<?> driver) {
		driver.context("NATIVE_APP");
	}

	public static void WebSaveToComputer(WebDriver driver) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		String date = df.format(new Date());
		try {
			FileUtils.copyFile(scrFile, new File("test-output/screenshot/" + date + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 返回已连接手机的devices信息
	 * 
	 * @param devices
	 * @return
	 */
	public HashMap<String, String> Devicesinfo(String devices) {
		HashMap<String, String> info = new HashMap<>();
		String id = null, version = null, factory = null, factory_name = null;// 定义设备的id和版本号
		System.out.println("正在连接手机，请稍后...");
		try {
			if (Tools.getValue(devices + "_deviceid").trim().equals("")) {
				Tools.ADB("adb kill-server");
				if (devices.equals("mumu")) {
					Tools.ADB("adb connect 127.0.0.1:7555");
				}
				id = Tools.ADB("adb get-serialno");
				// id=Tools.sideFind(id, "List of devices attached\r\n", " device");
				info.put("id", id);
			} else {
				id = Tools.getValue(devices + "_deviceid");
				info.put("id", id.trim());
			}
			System.out.print("设备名称：");
			System.err.println(id);
			if (Tools.getValue(devices + "_androidversion").trim().equals("")) {
				version = Tools.ADB("adb shell getprop ro.build.version.release").trim();
				factory = Tools.ADB("adb shell getprop ro.product.manufacturer").trim();
				factory_name = Tools.ADB("adb shell getprop ro.product.model").trim();
				info.put("version", version);
				System.out.println("手机品牌：" + factory + "，手机型号：" + factory_name);
			} else {
				version = Tools.getValue(devices + "_androidversion").trim();
				info.put("version", version);
			}
			System.out.print(id + "的版本号：");
			System.err.println(version);
		} catch (Exception e) {
			System.err.println("手机连接失败（如果您连接了多部手机，请使用config文件配置设备信息）。");
		}
		return info;
	}
}
