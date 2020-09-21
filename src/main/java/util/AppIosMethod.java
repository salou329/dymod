package util;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
public class AppIosMethod extends ControlMethod {	
	public IOSDriver<?> driver;
	DesiredCapabilities cap = new DesiredCapabilities();
 
	@SuppressWarnings("rawtypes")
	public AppIosMethod(String devices) {
		cap.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.7.0");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		cap.setCapability(MobileCapabilityType.UDID, Tools.getValue(devices + "_UDID"));
		cap.setCapability(MobileCapabilityType.APP, Tools.getValue(devices + "_ios_app"));
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, Tools.getValue(devices + "_deviceid_ios"));
		cap.setCapability(MobileCapabilityType.NO_RESET, true);
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		cap.setCapability("startIWDP", true);
		cap.setCapability("usePrebuiltWDA", true); // 不再次安装WDA
		// 不涉及编译不需要配置这个
		cap.setCapability("xcodeOrgId", "96NJEQL7Y2");// 开发者签名证书编号id
		cap.setCapability("xcodeSigningId", "iPhone Developer");
		try {
			driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		} catch (MalformedURLException e1) {
			System.out.println("获取iosdirver失败");
		}
	}

	/**
	 * 文字查找页面对象
	 * 
	 * @param objectName
	 * @return
	 */
	public MobileElement Element(String objectName) {
		return (MobileElement) driver.findElement(By.xpath("//*[@text='" + objectName + "']"));

	}

	/**
	 * 页面对象
	 *
	 * @author 史文彬
	 * @param sheet
	 * @param objectName
	 * @return
	 */
	public MobileElement Element(String sheet, String objectName) {
		String[] locator = null;
		if (Tools.getValue("repertoryOnDB").equals("no")) {
			ReadExcelUtil ex = new ReadExcelUtil(path, sheet);
			locator = ex.getvalue(objectName);
		} else if (Tools.getValue("repertoryOnDB").equals("yes")) {
//			ReadDbUtil ex = new ReadDbUtil();
//			locator = ex.getvalue(sheet, objectName);
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
			return (MobileElement) driver.findElement(By.xpath("//*[@text='" + locator[0] + "']"));
		default:
			System.out.println("对不起，定位对象暂不支持".concat(locator[1]));
			return null;
		}

	}

	/**
	 * 等待对象
	 * 
	 * @param sheet
	 * @param objectName
	 */
	public void waitElement(String sheet, String objectName) {

		for (int second = 1; second <= 100; second++) {
			if (Element(sheet, objectName).isDisplayed()) {
				System.out.println("第" + second + "次页面查找【" + objectName + "】成功！");
				break;
			} else {
				System.out.println("第" + second + "次页面查找【" + objectName + "】失败！");

			}
			this.sleep(100);
		}

	}

	/**
	 * 滑动屏幕
	 *
	 * @author 史文彬
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
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
	 * @param element
	 * @param X
	 * @param Y
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
	 */
	public void Keyboard(String value) {
		sleep(3000);
		Actions actions = new Actions(driver);
		actions.sendKeys(value).perform();

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

}
