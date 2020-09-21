package ui;

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

public class monitorin {
	WebMethod wm = new WebMethod("chrome");
	WebDriver driver = wm.driver;

	@Test
	// 实时监控点击完成，检查页面
	public void monitorin_test001() {
		wm.openPage();
		Login.loginWeb(wm, "admin", "123456");
		Element.飞行记录_飞行记录菜单(wm).click();
		Element.实时监控_实时监控菜单(wm).click();
		Tools.sleep(2);
		String urlString = wm.driver.getCurrentUrl();
		Checkpoint.isTrue(urlString.contains("#/wrap/monitor"));

	}

	@Test
	// 实时监控点击完成，点击取消
	public void monitorin_test002() {
		String yq = driver.findElement(By.xpath("//*[@id=\"tableBox\"]/div/div[3]/table/tbody/tr[1]/td[1]/div"))
				.getText();
		Element.实时监控_完成(wm).click();
		Element.实时监控_取消1(wm).click();
		Tools.sleep(2);
		String sj = driver.findElement(By.xpath("//*[@id=\"tableBox\"]/div/div[3]/table/tbody/tr[1]/td[1]/div"))
				.getText();
		Checkpoint.Equals(yq, sj);
	}

	@Test
	// 实时监控点击完成，点击确定，检查数据
	public void monitorin_test003() {
		String yq = driver.findElement(By.xpath("//*[@id=\"tableBox\"]/div/div[3]/table/tbody/tr[1]/td[1]/div"))
				.getText();
		Element.实时监控_完成(wm).click();
		Element.实时监控_确定1(wm).click();
		Tools.sleep(2);
		String sj = driver.findElement(By.xpath("//*[@id=\"tableBox\"]/div/div[3]/table/tbody/tr[1]/td[1]/div"))
				.getText();
		Checkpoint.isTrue(!yq.equals(sj));
	}

	@Test
	// 实时监控点击继续开始，检查页面
	public void monitorin_test004() {
		Element.实时监控_继续_开始(wm).click();
		Checkpoint.isDisplay(wm, "实时监控", "结束当前巡航");

	}

	@Test
	// 实时监控点击继续开始，点击结束当前巡航，点击取消
	public void monitorin_test005() {
		Element.实时监控_结束当前巡航(wm).click();
		Element.实时监控_取消2(wm).click();
		Checkpoint.isDisplay(wm, "实时监控", "结束当前巡航");
	}

	@Test
	// 实时监控点击继续开始，点击结束当前巡航，点击确定，检查页面
	public void monitorin_test006() {
		Element.实时监控_结束当前巡航(wm).click();
		Element.实时监控_确定2(wm).click();
		String urlString = wm.driver.getCurrentUrl();
		Checkpoint.isTrue(urlString.contains("#/wrap/monitor"));
	}
	@AfterClass
	public void quit() {
		driver.quit();
	}
}
