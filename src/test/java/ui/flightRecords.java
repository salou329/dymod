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
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import action.Login;
import page.Element;
import util.Checkpoint;
import util.Tools;
import util.WebMethod;

public class flightRecords {
	WebMethod wm = new WebMethod("chrome");
	WebDriver driver = wm.driver;

//(dependsOnMethods = { "flightRecords_test001" })
	@Test
	// 飞行记录_点击飞机记录菜单栏,检查页面元素是否展示
	public void flightRecords_test001_001() {
		wm.openPage();
		Login.loginWeb(wm, "admin", "123456");
		Element.飞行记录_飞行记录菜单(wm).click();
		boolean result = false;
		// 断言多个对象是否显示
		try {
			Element.飞行记录_查询(wm).isDisplayed();
			
			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);

	}
	
	@Test
	// 飞行记录_点击飞机记录菜单栏,检查页面元素是否展示
	public void flightRecords_test001_002() {
		boolean result = false;
		// 断言多个对象是否显示
		try {
			
			Element.飞行记录_表单(wm).isDisplayed();
			
			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);

	}
	
	@Test
	// 飞行记录_点击飞机记录菜单栏,检查页面元素是否展示
	public void flightRecords_test001_003() {
		boolean result = false;
		// 断言多个对象是否显示
		try {
			
			Element.飞行记录_导出总飞行记录(wm).isDisplayed();
			
			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);

	}
	
	@Test
	// 飞行记录_点击飞机记录菜单栏,检查页面元素是否展示
	public void flightRecords_test001_004() {
		boolean result = false;
		// 断言多个对象是否显示
		try {
			
			Element.飞行记录_导出飞行记录详情(wm).isDisplayed();
			
			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);

	}
	
	@Test
	// 飞行记录_点击飞机记录菜单栏,检查页面元素是否展示
	public void flightRecords_test001_005() {
		boolean result = false;
		// 断言多个对象是否显示
		try {
			
			Element.飞行记录_开始日期(wm).isDisplayed();
			
			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);

	}
	
	@Test
	// 飞行记录_点击飞机记录菜单栏,检查页面元素是否展示
	public void flightRecords_test001_006() {
		boolean result = false;
		// 断言多个对象是否显示
		try {
			
			Element.飞行记录_结束日期(wm).isDisplayed();
			
			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);

	}
	
	@Test
	// 飞行记录_点击飞机记录菜单栏,检查页面元素是否展示
	public void flightRecords_test001_007() {
		boolean result = false;
		// 断言多个对象是否显示
		try {
			
			Element.飞行记录_选择任务名称(wm).isDisplayed();
			
			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);

	}
	
	@Test
	// 飞行记录_点击飞机记录菜单栏,检查页面元素是否展示
	public void flightRecords_test001_008() {
		boolean result = false;
		// 断言多个对象是否显示
		try {
			
			Element.飞行记录_飞行记录菜单(wm).isDisplayed();
			
			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);

	}
	
	@Test
	// 飞行记录_点击飞机记录菜单栏,检查页面元素是否展示
	public void flightRecords_test001_009() {
		boolean result = false;
		// 断言多个对象是否显示
		try {
			
			Element.飞行记录_选择杆塔编号(wm).isDisplayed();
			
			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);

	}
	
	@Test
	// 飞行记录_点击飞机记录菜单栏,检查页面元素是否展示
	public void flightRecords_test001_010() {
		boolean result = false;
		// 断言多个对象是否显示
		try {
			
			Element.飞行记录_选择对应飞机(wm).isDisplayed();
			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);

	}

	@Test
	// 飞行记录_通过飞行任务名称查询结果是否正确
	public void flightRecords_test002() {
		String name = action.flightRecords.clickTaskname(wm, "", true);// 随机点击一个下拉框数据

		Element.飞行记录_查询(wm).click();// 点击查询
		// 判断查到的对象是不是和查询的一致
		WebElement element = driver.findElement(
				By.xpath("//*[@id=\"app\"]/section/section/div/div/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[2]/div"));
		Tools.sleep(2);// 接口慢need 等待查询

		Checkpoint.Equals(name, element.getText());

	}

	@Test
	// 飞行记录_通过杆塔编号查询结果是否正确
	public void flightRecords_test003() {
		String name = action.flightRecords.clickTower(wm, "", true);// 随机点击一个下拉框数据

		Element.飞行记录_查询(wm).click();// 点击查询
		// 判断查到的对象是不是和查询的一致
		WebElement element = driver.findElement(
				By.xpath("//*[@id=\"app\"]/section/section/div/div/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[4]/div"));
		Tools.sleep(2);// 接口慢need 等待查询

		Checkpoint.Equals(name, element.getText());

	}

	@Test
	// 飞行记录_通过杆塔编号查询结果是否正确
	public void flightRecords_test004() {
		String name = action.flightRecords.clickAirplane(wm, "", true);// 随机点击一个下拉框数据

		Element.飞行记录_查询(wm).click();// 点击查询
		// 判断查到的对象是不是和查询的一致
		WebElement element = driver.findElement(
				By.xpath("//*[@id=\"app\"]/section/section/div/div/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[8]/div"));
		Tools.sleep(2);// 接口慢need 等待查询
		System.out.println(wm.driver.manage().getClass());
		Checkpoint.Equals(name, element.getText());

	}

	@Test
	// //测试飞行记录_备注功能
	public void flightRecords_test005() {
		// wm.driver.navigate().refresh();
		Tools.sleep(1);
		driver.findElement(By.xpath(
				"//*[@id=\"app\"]/section/section/div/div/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[14]/div/button"))
				.click();// 点击第一行的编辑

		driver.findElement(By.xpath("//*[@id=\"app\"]/section/section/div/div/div[4]/div/div[2]/div/textarea"))
				.sendKeys("dymod_test");// 输入测试内容

		driver.findElement(By.xpath("//*[@id=\"app\"]/section/section/div/div/div[4]/div/div[3]/span/button")).click();// 点击确定
		Tools.sleep(2);
		// 获取修改后的值断言
		String result = driver
				.findElement(By.xpath(
						"//*[@id=\"app\"]/section/section/div/div/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[13]"))
				.getText();
		Checkpoint.Equals(result, "dymod_test");

	}

	@Test
	// 飞行记录_通过时间查询结果是否正确
	public void flightRecords_test006() {
		String date = driver
				.findElement(By.xpath(
						"//*[@id=\"app\"]/section/section/div/div/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[1]/div"))
				.getText();// 获取时间
		String dateStart = date.substring(0, 10) + " 00:00:00";// 格式化头
		String dateEnd = "2099" + " 23:59:59";
		;// 格式化尾
		Element.飞行记录_开始日期(wm).sendKeys(dateStart);
		Element.飞行记录_结束日期(wm).sendKeys(dateEnd);

		Element.飞行记录_查询(wm).click();// 点击查询
		// 判断查到的对象是不是和查询的一致
		WebElement element = driver.findElement(
				By.xpath("//*[@id=\"app\"]/section/section/div/div/div[3]/div[1]/div[3]/table/tbody/tr[1]/td[1]/div"));
		Tools.sleep(2);// 接口慢need 等待查询
		Checkpoint.Equals(date, element.getText());

	}

	@Test
	// 飞行记录_导出空数据(导出总飞行记录)，测试是否提示
	public void flightRecords_test007() {
		// wm.driver.navigate().refresh();
		Tools.sleep(1);

		Element.飞行记录_开始日期(wm).sendKeys("2016-03-01 00:00:00");
		Element.飞行记录_结束日期(wm).sendKeys("2016-03-01 00:00:00");

		Element.飞行记录_查询(wm).click();// 点击查询

		// 接口慢need 等待查询
		Element.飞行记录_导出总飞行记录(wm).click();
		Tools.sleep(1);
		Checkpoint.Equals("暂无导出数据", driver.findElement(By.xpath("//div/p")).getText());

	}

	@Test
	// 飞行记录_导出空数据(导出飞行记录详情)，测试是否提示
	public void flightRecords_test008() {
		// wm.driver.navigate().refresh();
		Tools.sleep(1);

		Element.飞行记录_导出飞行记录详情(wm).click();
		Tools.sleep(1);
		Checkpoint.Equals("暂无导出数据", driver.findElement(By.xpath("//div/p")).getText());

	}

	@AfterClass
	public void quit() {
		driver.quit();
	}

}
