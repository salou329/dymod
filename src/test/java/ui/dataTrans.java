package ui;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import action.Login;
import page.Element;
import util.Checkpoint;
import util.Tools;
import util.WebMethod;

public class dataTrans {
	WebMethod wm = new WebMethod("chrome");
	WebDriver driver = wm.driver;

	@Test
	// 点击数据传输菜单按钮，检查页面元素是否显示
	public void dataTrans_test001_001() {
		wm.openPage();
		Login.loginWeb(wm, "admin", "123456");
		Element.数据传输_数据传输菜单(wm).click();
		boolean result = false;
		// 断言多个对象是否显示
		try {
			Element.数据传输_u盘导出按钮(wm).isDisplayed();
			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);
	}

	@Test
	// 点击数据传输菜单按钮，检查页面元素是否显示
	public void dataTrans_test001_002() {
		boolean result = false;
		// 断言多个对象是否显示
		try {
			Element.数据传输_上传状态(wm).isDisplayed();

			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);
	}

	@Test
	// 点击数据传输菜单按钮，检查页面元素是否显示
	public void dataTrans_test001_003() {
		boolean result = false;
		// 断言多个对象是否显示
		try {

			Element.数据传输_上传记录按钮(wm).isDisplayed();

			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);
	}

	@Test
	// 点击数据传输菜单按钮，检查页面元素是否显示
	public void dataTrans_test001_004() {
		boolean result = false;
		// 断言多个对象是否显示
		try {

			Element.数据传输_任务名称(wm).isDisplayed();

			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);
	}

	@Test
	// 点击数据传输菜单按钮，检查页面元素是否显示
	public void dataTrans_test001_005() {
		boolean result = false;
		// 断言多个对象是否显示
		try {

			Element.数据传输_导出状态(wm).isDisplayed();

			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);
	}

	@Test
	// 点击数据传输菜单按钮，检查页面元素是否显示
	public void dataTrans_test001_006() {
		boolean result = false;
		// 断言多个对象是否显示
		try {
			Element.数据传输_开始日期(wm).isDisplayed();

			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);
	}

	@Test
	// 点击数据传输菜单按钮，检查页面元素是否显示
	public void dataTrans_test001_007() {
		boolean result = false;
		// 断言多个对象是否显示
		try {

			Element.数据传输_批量查询按钮(wm).isDisplayed();

			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);
	}

	@Test
	// 点击数据传输菜单按钮，检查页面元素是否显示
	public void dataTrans_test001_008() {
		boolean result = false;
		// 断言多个对象是否显示
		try {
			Element.数据传输_数据传输菜单(wm).isDisplayed();

			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);
	}

	@Test
	// 点击数据传输菜单按钮，检查页面元素是否显示
	public void dataTrans_test001_009() {
		boolean result = false;
		// 断言多个对象是否显示
		try {
			Element.数据传输_查询按钮(wm).isDisplayed();

			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);
	}

	@Test
	// 点击数据传输菜单按钮，检查页面元素是否显示
	public void dataTrans_test001_010() {
		boolean result = false;
		// 断言多个对象是否显示
		try {

			Element.数据传输_结束日期(wm).isDisplayed();
			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);
	}

	@Test
	// 通过日期查询，检查日期是否正确
	public void dataTrans_test002() {
		Tools.sleep(2);
		// 清空开始时间和结束时间输入框
		Element.数据传输_开始日期(wm).clear();
		Element.数据传输_结束日期(wm).clear();
		Element.数据传输_开始日期(wm).sendKeys(Tools.getSysYear() + "-01-01");
		Element.数据传输_结束日期(wm).sendKeys("2099" + "-12-31");
		Element.数据传输_查询按钮(wm).click();// 查询出当前年所有数据
		Tools.sleep(2);
		// 获取第一条数据的日期，精确查询
		String realdate = driver.findElement(By.xpath(
				"//*[@id=\"app\"]/section/section/div/div[1]/div[1]/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[2]"))
				.getText();
		realdate = realdate.substring(0, 10);

		Element.数据传输_开始日期(wm).clear();
		Element.数据传输_结束日期(wm).clear();
		Element.数据传输_开始日期(wm).sendKeys(realdate);
		Element.数据传输_结束日期(wm).sendKeys(realdate);
		Element.数据传输_查询按钮(wm).click();// 查询
		Tools.sleep(2);
		String testdate = driver.findElement(By.xpath(
				"//*[@id=\"app\"]/section/section/div/div[1]/div[1]/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[2]"))
				.getText();
		testdate = testdate.substring(0, 10);

		Checkpoint.isTrue(realdate.equals(testdate));
	}

	@Test
	// 通过实际上传状态查询
	public void dataTrans_test003() {
		String status = driver.findElement(By.xpath(
				"//*[@id=\"app\"]/section/section/div/div[1]/div[1]/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[7]"))
				.getText();
		;
		System.err.println(status);
		boolean real = action.dataTrans.uploadstauts(wm, status);
		if (real) {
			Element.数据传输_查询按钮(wm).click();// 查询
			Tools.sleep(2);
			String realstatus = driver.findElement(By.xpath(
					"//*[@id=\"app\"]/section/section/div/div[1]/div[1]/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[7]"))
					.getText();
			System.err.println(status + "   " + realstatus);
			Checkpoint.isTrue(status.equals(realstatus));
		} else {
			Checkpoint.isTrue(real);
		}

	}

	@Test
	// 通过实际导出查询
	public void dataTrans_test004() {
		String status = driver.findElement(By.xpath(
				"//*[@id=\"app\"]/section/section/div/div[1]/div[1]/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[8]"))
				.getText();
		;
		System.err.println(status);
		boolean real = action.dataTrans.Downloadstauts(wm, status);
		if (real) {
			Element.数据传输_查询按钮(wm).click();// 查询
			Tools.sleep(2);
			String realstatus = driver.findElement(By.xpath(
					"//*[@id=\"app\"]/section/section/div/div[1]/div[1]/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[8]"))
					.getText();
			System.err.println(status + "   " + realstatus);
			Checkpoint.isTrue(status.equals(realstatus));
		} else {
			Checkpoint.isTrue(real);
		}

	}

	@Test
	// 通过任务名称查询
	public void dataTrans_test005() {
		String name = driver.findElement(By.xpath(
				"//*[@id=\"app\"]/section/section/div/div[1]/div[1]/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[3]"))
				.getText();
		Element.数据传输_任务名称(wm).sendKeys(name);
		Element.数据传输_查询按钮(wm).click();// 查询
		Tools.sleep(2);
		String realname = driver.findElement(By.xpath(
				"//*[@id=\"app\"]/section/section/div/div[1]/div[1]/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[3]"))
				.getText();
		System.err.println(name + "   " + realname);
		Checkpoint.isTrue(name.equals(realname));

	}

	@Test
	// 点击上传记录，跳转上传记录页面
	public void dataTrans_test006() {
		Element.数据传输_上传记录按钮(wm).click();
		String url = driver.getCurrentUrl();
		driver.navigate().back();
		Checkpoint.contains("recordPage?key=upload", url);

	}

	@Test
	// 点击导出记录，跳转导出记录页面
	public void dataTrans_test007() {
		Tools.sleep(1);
		Element.数据传输_导出记录按钮(wm).click();
		Tools.sleep(1);
		String url = driver.getCurrentUrl();
		driver.navigate().back();
		System.out.println(url);
		Checkpoint.contains("recordPage?key=export", url);
	}

	@AfterClass
	public void quit() {
		driver.quit();
	}
}
