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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import action.DBcreate;
import action.Login;
import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition;

import org.openqa.selenium.WebDriver;

import util.Checkpoint;
import util.Jietu;
import util.WebMethod;

public class login {
	WebMethod wm = new WebMethod("chrome");
	WebDriver driver = wm.driver;

	@Test
	// 正确用户名，正确密码，点击登陆
	public void login_testcase001() {
		wm.openPage();
		String reslut = Login.loginWeb(wm, "admin", "123456");
		Checkpoint.Equals("", reslut);

	}

	@Test
	// 正确用户名，错误密码，点击登陆
	public void login_testcase002() {
		wm.openPage();
		String reslut = Login.loginWeb(wm, "admin", "1");
		Checkpoint.Equals("用户名或密码错误", reslut);

	}

	@Test
	// 未输入用户名，密码，点击登陆
	public void login_testcase003() {
		wm.openPage();
		String reslut = Login.loginWeb(wm, "", "");
		Checkpoint.Equals("用户名或密码不能为空", reslut);

	}

	@Test
	// 未输入用户名，点击登陆
	public void login_testcase004() {
		wm.openPage();
		String reslut = Login.loginWeb(wm, "", "123456");
		Checkpoint.Equals("用户名或密码不能为空", reslut);

	}

	@Test
	// 未输入密码，点击登陆
	public void login_testcase005() {
		wm.openPage();
		String reslut = Login.loginWeb(wm, "admin", "");
		Checkpoint.Equals("用户名或密码不能为空", reslut);

	}

	@Test
	// 错误用户名，密码，点击登陆
	public void login_testcase006() {
		wm.openPage();
		String reslut = Login.loginWeb(wm, "xxxx", "123456");
		Checkpoint.Equals("用户名或密码错误", reslut);

	}

	@Test
	// 登陆页面_检查页面元素
	public void login_testcase007() {
		wm.openPage();

		Checkpoint.isDisplay(wm, "登陆页", "用户名");

	}
	
	@Test
	// 登陆页面_检查页面元素
	public void login_testcase008() {
		
		Checkpoint.isDisplay(wm, "登陆页", "密码");

	}
	
	@Test
	// 登陆页面_检查页面元素
	public void login_testcase009() {

		Checkpoint.isDisplay(wm, "登陆页", "确定");

	}

	@AfterClass
	public void quit() {
		driver.quit();
	}

}
