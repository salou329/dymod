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
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import action.DBcreate;
import action.Login;
import page.Element;
import util.Checkpoint;
import util.Tools;
import util.WebMethod;

public class lineManagement {
	WebMethod wm = new WebMethod("chrome");
	WebDriver driver = wm.driver;
	
  @Test
  //登陆默认进如线路管理页面
  public void lineManagement_test001() {
	  wm.openPage();
		Login.loginWeb(wm, "admin", "123456");
		Checkpoint.isDisplay(wm, "线路管理", "线路管理菜单");

	  
  }
  
  @Test
  //登陆默认进如线路管理页面
  public void lineManagement_test001_001() {

		Checkpoint.isDisplay(wm, "线路管理", "管理班组");
	  
  }
  
  @Test
  //切换到其他菜单，再切换回线路管理
  public void lineManagement_test002() {
		Element.数据传输_数据传输菜单(wm).click();
		Element.线路管理_线路管理菜单(wm).click();
		Tools.sleep(2);
		Checkpoint.isDisplay(wm, "线路管理", "管理班组");
	  
  }
  
  @Test
  //线路管理，通过班组筛选
  public void lineManagement_test003() {
		Element.线路管理_管理班组(wm).click();
		Tools.sleep(2);
		String sj=driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[1]/div/div/label[1]")).getText();
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[1]/div/div/label[1]")).click();
		Tools.sleep(2);
		//点击筛选按钮
		wm.Element("线路管理", "筛选").click();
		
		Tools.sleep(2);
		String yq=driver.findElement(By.xpath("//*[@id=\"app\"]/section/section/div/div[1]/div/div[2]/div/div[3]/table/tbody/tr/td[2]")).getText();
	  Checkpoint.Equals(yq, sj);
  }
  
  @Test
  //点击线路，展示详情
  public void lineManagement_test004() {
		driver.findElement(By.xpath("//*[@id=\"app\"]/section/section/div/div[1]/div/div[2]/div/div[3]/table/tbody/tr/td[2]")).click();
		Tools.sleep(2);
		driver.findElement(By.xpath("//*[@id=\"app\"]/section/section/div/div[1]/div[2]/div[2]/div/div[3]/table/tbody/tr/td[1]")).click();
  }
  
	@AfterClass
	public void quit() {
		driver.quit();
	}
  
}
