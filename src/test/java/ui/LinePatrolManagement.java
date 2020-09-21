package ui;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import action.Login;
import page.Element;
import util.Checkpoint;
import util.Tools;
import util.WebMethod;

public class LinePatrolManagement {

	WebMethod wm = new WebMethod("chrome");
	WebDriver driver = wm.driver;

	@Test
	// 巡线管理_点击巡线管理菜单栏,检查页面元素是否展示
	public void LinePatrolManagement_testcase001() {
		wm.openPage();
		Login.loginWeb(wm, "admin", "123456");
		Element.巡线管理_巡线管理菜单(wm).click();
		boolean result = false;
		// 断言多个对象是否显示
		try {
			Element.巡线管理_当前任务(wm).isDisplayed();

			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);

	}
	
	@Test
	// 巡线管理_点击巡线管理菜单栏,检查页面元素是否展示
	public void LinePatrolManagement_testcase001_001() {

		boolean result = false;
		// 断言多个对象是否显示
		try {
			Element.巡线管理_历史任务(wm).isDisplayed();
			result = true;
		} catch (Exception e) {
			System.err.println("测试失败");
		}
		Checkpoint.isTrue(result);

	}

	@Test
	// 巡线管理，切换历史任务检查状态
	public void LinePatrolManagement_testcase002() {
		Element.巡线管理_历史任务(wm).click();
		Tools.sleep(2);
		// 检查状态值是不是已完成｜｜已取消
		String textString = driver.findElement(By.xpath("//*[@id=\"mission\"]/div[2]/div[3]/table/tbody/tr[1]/td[8]"))
				.getText();
		if (textString.equals("已完成") || textString.equals("已取消")) {
			Checkpoint.isTrue(true);
		} else {
			Checkpoint.isTrue(false);
		}

	}

	@Test
	// 巡线管理，切换当前任务检查状态
	public void LinePatrolManagement_testcase003() {
		Element.巡线管理_当前任务(wm).click();
		Tools.sleep(2);
		// 检查状态值是不是进行中｜｜已创建
		String textString = driver.findElement(By.xpath("//*[@id=\"mission\"]/div[2]/div[3]/table/tbody/tr[1]/td[8]"))
				.getText();
		if (textString.equals("进行中") || textString.equals("已创建")) {
			Checkpoint.isTrue(true);
		} else {
			Checkpoint.isTrue(false);
		}
	}

	@Test
	// 进行中任务-完成
	public void LinePatrolManagement_testcase004() {
		Element.巡线管理_当前任务(wm).click();
		Tools.sleep(2);
		String yq = "";
		String sj = "";
		int num = 0;
		for (int Row = 1; Row <= 10; Row++) {
			if (driver.findElement(By.xpath("//*[@id=\"mission\"]/div[2]/div[3]/table/tbody/tr[" + Row + "]/td[8]/div"))
					.getText().equals("进行中")) {
				yq = driver
						.findElement(By.xpath("//*[@id=\"mission\"]/div[2]/div[3]/table/tbody/tr[" + Row + "]/td[1]"))
						.getText();
				driver.findElement(
						By.xpath("//*[@id=\"mission\"]/div[2]/div[3]/table/tbody/tr[" + Row + "]/td[9]/div/i[1]"))
						.click();
				driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[2]")).click();
				num = Row;
				break;
			}

		}
		Tools.sleep(2);
		sj = driver.findElement(By.xpath("//*[@id=\"mission\"]/div[2]/div[3]/table/tbody/tr[" + num + "]/td[1]"))
				.getText();
		System.out.println("原任务名称：" + yq + "当前位置任务名称：" + sj);
		Checkpoint.isTrue(!(yq.equals(sj)));
	}

	@Test
	// 已创建任务-取消
	public void LinePatrolManagement_testcase005() {
		Element.巡线管理_当前任务(wm).click();
		Tools.sleep(2);
		String yq = "";
		String sj = "";
		int num = 0;
		for (int Row = 1; Row <= 10; Row++) {
			if (driver.findElement(By.xpath("//*[@id=\"mission\"]/div[2]/div[3]/table/tbody/tr[" + Row + "]/td[8]/div"))
					.getText().equals("已创建")) {
				yq = driver
						.findElement(By.xpath("//*[@id=\"mission\"]/div[2]/div[3]/table/tbody/tr[" + Row + "]/td[1]"))
						.getText();
				driver.findElement(
						By.xpath("//*[@id=\"mission\"]/div[2]/div[3]/table/tbody/tr[" + Row + "]/td[9]/div/i[1]"))
						.click();
				driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[2]")).click();
				num = Row;
				break;
			}

		}
		Tools.sleep(2);
		sj = driver.findElement(By.xpath("//*[@id=\"mission\"]/div[2]/div[3]/table/tbody/tr[" + num + "]/td[1]"))
				.getText();

		System.out.println("原任务名称：" + yq + "当前位置任务名称：" + sj);
		Checkpoint.isTrue(!(yq.equals(sj)));
	}
	@AfterClass
	public void quit() {
		driver.quit();
	}

}
