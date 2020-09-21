package util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.interactions.touch.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AppMethod extends ControlMethod {

	public AndroidDriver<?> driver;
	DesiredCapabilities cap = new DesiredCapabilities();
	String id = null;
	String version = null;
	HashMap<String, String> info = new HashMap<>();

	public AppMethod(String devices) {
		info = Devicesinfo(devices);
		id = info.get("id");
		version = info.get("version");
		cap.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.7.0");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, id);
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		cap.setCapability(MobileCapabilityType.NO_RESET, true);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 2000);
		cap.setCapability(MobileCapabilityType.APP, Tools.getValue(devices + "_android_app"));
		cap.setCapability("appPackage", Tools.getValue(devices + "_appPackage"));
		cap.setCapability("appActivity", Tools.getValue(devices + "_appActivity"));
		cap.setCapability("appWaitActivity", Tools.getValue(devices + "_appWaitActivity"));
		// cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,
		// AutomationName.ANDROID_UIAUTOMATOR2);
		try {
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			System.out.println("手机连接成功。");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//隐式等待
			driver.unlockDevice();
		} catch (Exception e1) {
			if (e1.toString().contains("Connection")) {
				System.err.println("未启动APPIUM，请检查！");
			} else {
				System.out.println(e1);
				System.err.println("手机连接失败。");
			}
		}
	}

	/**
	 * 文字查找页面对象
	 */
	public MobileElement Element(String objectName) {
		MobileElement element = null;
		try {
			element = (MobileElement) driver.findElementByXPath("//*[@text='" + objectName + "']");
		} catch (Exception e) {
			element = (MobileElement) driver.findElementByXPath("//*[@content-desc='" + objectName + "']");
		}
		return element;
	}

	/**
	 * 页面对象
	 *
	 * @author 史文彬
	 * @param sheet
	 * @param objectName
	 * @return
	 */
	private MobileElement WElement(String sheet, String objectName) {
		String[] locator = null;
		try {
			if (Tools.getValue("repertoryOnDB").equals("no")) {
				ReadExcelUtil ex = new ReadExcelUtil(path, sheet);
				locator = ex.getvalue(objectName);
			} else if (Tools.getValue("repertoryOnDB").equals("yes")) {
//				ReadDbUtil ex = new ReadDbUtil();
//				locator = ex.getvalue(sheet, objectName);
				locator = ReadMongoUtil.getvalue(sheet, objectName);
			}
			switch (locator[1]) {
			case "id":
				return (MobileElement) driver.findElement(By.id(locator[0]));

			case "xpath":
				return (MobileElement) driver.findElement(By.xpath(locator[0]));

			case "name":
				return (MobileElement) driver.findElement(By.name(locator[0]));

			case "classname":
				return (MobileElement) driver.findElement(By.className(locator[0]));

			case "linktext":
				return (MobileElement) driver.findElement(By.linkText(locator[0]));

			case "cssselector":
				return (MobileElement) driver.findElement(By.cssSelector(locator[0]));

			case "partiallinktext":
				return (MobileElement) driver.findElement(By.partialLinkText(locator[0]));

			case "tagname":
				return (MobileElement) driver.findElement(By.tagName(locator[0]));
			case "text":
				return (MobileElement) driver.findElement(By.xpath("//*[contains(@text,'" + locator[0] + "')]"));
			case "desc":
				return (MobileElement) driver
						.findElement(By.xpath("//*[contains(@content-desc,'" + locator[0] + "')]"));
			default:
				System.out.println("对不起，定位对象暂不支持".concat(locator[1]));
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return null;
		}

	}

	public MobileElement Element(String sheet, String objectName) {
		waitElement(sheet, objectName);
		return WElement(sheet, objectName);
	}

	/**
	 * 等待对象
	 */
	public boolean waitElement(String sheet, String objectName) {

		boolean reslut = false;
		for (int second = 1; second <= 100; second++) {
			try {
				WElement(sheet, objectName).isDisplayed();
				// System.out.println("第" + second + "次页面查找【" + objectName + "】成功！");
				reslut = true;
				break;
			} catch (Exception e) {
				// System.out.println("第" + second + "次页面查找【" + objectName + "】失败！");
			}
			this.sleep(100);
		}
		if (reslut) {
			System.out.println(":)找到对象 " + objectName);
		} else {
			System.err.println(":(未找到对象 " + objectName);
		}
		return reslut;

	}

	/**
	 * 滑动屏幕
	 *
	 * @author 史文彬
	 */
	public void move(int x, int y, int x2, int y2) {
		AndroidTouchAction action = new AndroidTouchAction(driver);
		Duration duration = Duration.ofNanos(2000);
		action.press(PointOption.point(x, y)).waitAction(WaitOptions.waitOptions(duration))
				.moveTo(PointOption.point(x2, y2)).release().perform();
	}

	/**
	 * 拖拽对象
	 *
	 * @author 史文彬
	 */
	public void scroll(MobileElement element, int X, int Y) {
		TouchActions action = new TouchActions(driver);
		action.scroll(element, X, Y);
		action.perform();
	}

	/**
	 * 返回弹窗消息是否正确
	 *
	 * @author shiwenbin
	 */
	public boolean message(String message) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement target = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'" + message + "')]")));
			System.out.println("期望提示信息：" + message + "   实际提示信息：" + target.getText());
			return true;
		} catch (Exception e) {
			System.out.println("未找到提示信息：" + message);
			return false;
		}
	}

	/**
	 * 返回按键
	 *
	 * @author 史文彬
	 */
	public void back() {

		driver.navigate().back();
	}

	/**
	 * 坐标点击
	 *
	 * @author 史文彬
	 */
	public void xyClick(int startX, int startY) {
		@SuppressWarnings("rawtypes")
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(startX, startY)).release().perform();
	}
	// 根据对象坐标点击

	@SuppressWarnings("rawtypes")
	public void xyClick(MobileElement element) {
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(element.getCenter().getX(), element.getCenter().getY())).release().perform();
	}

	/**
	 * 键盘输入
	 * 
	 * @param value
	 */
	public void keyboardInput(String value) {
		Tools.ADB("adb shell input text " + value);
	}

	/**
	 * 模拟键盘
	 * 
	 * @param key
	 */
	public void Keyboard(String key) {
		Tools.ADB("adb shell input keyevent " + key);
	}

	/**
	 * 往右滑
	 */
	public void rightMove() {
		int x = driver.manage().window().getSize().getWidth();
		int y = driver.manage().window().getSize().getHeight();
		this.move(10, y / 2, x - 10, y / 2);
	}

	/**
	 * 往左滑
	 */
	public void leftMove() {
		int x = driver.manage().window().getSize().getWidth();
		int y = driver.manage().window().getSize().getHeight();
		this.move(x - 10, y / 2, 10, y / 2);
	}

	/**
	 * 往上滑半屏
	 */
	public void upMove() {
		int x = driver.manage().window().getSize().getWidth();
		int y = driver.manage().window().getSize().getHeight();
		this.move(x / 2, y - 10, x / 2, y / 2);
	}

	/**
	 * 往下滑半屏
	 */
	public void downMove() {
		int x = driver.manage().window().getSize().getWidth();
		int y = driver.manage().window().getSize().getHeight();
		this.move(x / 2, y / 2, x / 2, y - 10);
	}

	/**
	 * 往上慢慢滚屏幕
	 */
	public void slowUpMove() {
		int x = driver.manage().window().getSize().getWidth();
		int y = driver.manage().window().getSize().getHeight();
		this.move(x / 2, y / 2, x / 2, y / 2 - 250);
	}

	/**
	 * 长按
	 * 
	 * @param sheet
	 * @param objectName
	 */
	public void longPress(String sheet, String objectName) {
		TouchActions ta = new TouchActions(driver);
		ta.longPress(Element(sheet, objectName)).perform();
	}

	/**
	 * 判断页面加载完成时间
	 * 
	 * @param element
	 * @return
	 */
	public void openPageTime(String objectName) {
		long startTime = System.currentTimeMillis();
		Date nowTime = new Date(startTime);
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		System.out.println(retStrFormatNowDate);
		for (;;) {
			try {
				if (this.Element(objectName).isDisplayed()) {
					break;
				}
			} catch (Exception e) {
			}
		}
		long endTime = System.currentTimeMillis();
		nowTime = new Date(endTime);
		retStrFormatNowDate = sdFormatter.format(nowTime);
		System.out.println(retStrFormatNowDate);
		long Time = endTime - startTime;
		System.out.println("页面打开时间： " + (Time) + "ms");
	}

	public void openPageTime(String sheet, String objectName) {
		long startTime = System.currentTimeMillis();
		Date nowTime = new Date(startTime);
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		System.out.println(retStrFormatNowDate);
		for (;;) {
			try {
				if (this.Element(sheet, objectName).isDisplayed()) {
					break;
				}
			} catch (Exception e) {
			}
		}
		long endTime = System.currentTimeMillis();
		nowTime = new Date(endTime);
		retStrFormatNowDate = sdFormatter.format(nowTime);
		System.out.println(retStrFormatNowDate);
		long Time = endTime - startTime;
		System.out.println("页面打开时间： " + (Time) + "ms");
	}
/**
 * 截图
 * @return
 */
	public String jietu() {
		File srcFile = driver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("jietu/" + "jietu.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "jietu/" + "jietu.jpg";
	}

}
