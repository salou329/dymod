package action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import page.Element;
import util.Tools;
import util.WebMethod;
/**
 * 登陆封装方法
 * @author salou
 *
 */
public final class Login {
//封装登陆方法
	public static String loginWeb(WebMethod wm, String username, String pwd) {
		WebDriver driver = wm.driver;
		driver.manage().deleteAllCookies();// 清楚所有缓存
		driver.navigate().refresh();//刷新页面，清空可能的数据
		
		// 用户名
		Element.登陆页_用户名(wm).sendKeys(username);
		// 密码
		Element.登陆页_密码(wm).sendKeys(pwd);
		// 登陆按钮
		Element.登陆页_确定(wm).click();
		Tools.sleep(5);//358原则，登陆5秒
		String Message = "";// 初始化提示信息为空
		boolean button = true;
		try {
			Element.登陆页_确定(wm).isDisplayed();// 判断登陆按钮是否还存在，默认存在
		} catch (Exception e) {
			button = false;// 页面查找不到，置状态为不存在
		}

		if (button) {// 判断一下，如果存在，返回登陆失败的提示
			System.out.print("登陆失败，获取失败提示");
			if (Message.equals("")) {
				Message = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div[3]/span")).getText();
			}
				System.out.println(":"+Message);
		} else {// 登陆成功，控制台打印日志，登陆成功
			System.out.println("登陆成功");
		}
		return Message;// 返回提示语，登陆成功返回空字符串
	}

}
