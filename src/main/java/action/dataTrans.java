package action;

import org.openqa.selenium.By;

import page.Element;
import util.Tools;
import util.WebMethod;

/**
 * 数据传输封装方法
 * 
 * @author salou
 *
 */
public final class dataTrans {

	// 上传状态
	public static boolean uploadstauts(WebMethod wm, String status) {
		Element.数据传输_上传状态(wm).click();
		Tools.sleep(2);
		switch (status) {
		case "全部":
			wm.driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/ul/li[1]")).click();
			break;
		case "已上传":
			wm.driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/ul/li[2]")).click();
			break;
		case "未上传":
			wm.driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/ul/li[3]")).click();
			break;
		case "上传失败":
			wm.driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/ul/li[4]")).click();
			break;
		default:
			System.out.println("未知的上传状态：" + status);
			return false;
		}
		return true;
	}

	// 上传状态
	public static boolean Downloadstauts(WebMethod wm, String status) {
		Element.数据传输_导出状态(wm).click();
		Tools.sleep(2);
		switch (status) {
		case "全部":
			wm.driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/ul/li[1]")).click();
			break;
		case "已导出":
			wm.driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/ul/li[2]")).click();
			break;
		case "未导出":
			wm.driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/ul/li[3]")).click();
			break;
		case "导出失败":
			wm.driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[1]/ul/li[4]")).click();
			break;
		default:
			System.out.println("未知的导出状态：" + status);
			return false;
		}
		return true;
	}
}
