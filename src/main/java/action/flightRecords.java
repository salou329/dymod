package action;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import page.Element;
import util.Tools;
import util.WebMethod;

/**
 * 飞行记录封装方法
 * 
 * @author salou
 *
 */
public final class flightRecords {
	// 封装任务名称下拉框
	public static String clickTaskname(WebMethod wm, String name, boolean Rclick) {
		WebDriver driver = wm.driver;
		Element.飞行记录_选择任务名称(wm).click();
		Tools.sleep(5);// 等待加载，给固定时间了，不加逻辑判断对象了，貌似每次都很慢 ：（
		// 获取下拉框数量，先获得下拉框ul对象，再判断对象下有多少个标签为li的对象给一个list
		WebElement ul = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/ul"));
		List<WebElement> li = ul.findElements(By.tagName("li"));
		int num = li.size();
		System.out.println("下拉框共" + num + "数据");// 打印共多少条数据。
		if (Rclick) {
			// 判断是否需要随机点击
			int Rnum = Tools.randomNumber(1, num);

			WebElement element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/ul/li[" + Rnum + "]"));
			name = element.getText();
			element.click();// 随机点击一条
			System.out.println("已经随机点击第" + Rnum + "条:" + name);
		} else {
			for (int i = 1; i <= num; i++) {
				if (driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/ul/li[" + i + "]/span")).getText()
						.equals(name)) {
					driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/ul/li[" + i + "]")).click();
					break;
				}
			}
			System.out.println("点击成功");

		}
		return name;
	}

	// 封装杆塔下拉框
	public static String clickTower(WebMethod wm, String name, boolean Rclick) {
		WebDriver driver = wm.driver;
		Element.飞行记录_选择杆塔编号(wm).click();
		Tools.sleep(5);// 等待加载，给固定时间了，不加逻辑判断对象了，貌似每次都很慢 ：（

		// 获取下拉框数量，先获得下拉框ul对象，再判断对象下有多少个标签为li的对象给一个list
		WebElement ul = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/ul"));
		List<WebElement> li = ul.findElements(By.tagName("li"));
		int num = li.size();
		System.out.println("下拉框共" + num + "数据");// 打印共多少条数据。
		if (Rclick) {
			// 判断是否需要随机点击
			int Rnum = Tools.randomNumber(1, num);

			WebElement element = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/ul/li[" + Rnum + "]"));
			name = element.getText();
			element.click();// 随机点击一条
			System.out.println("已经随机点击第" + Rnum + "条:" + name);
		} else {
			for (int i = 1; i <= num; i++) {
				if (driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/ul/li[" + i + "]/span")).getText()
						.equals(name)) {
					driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/ul/li[" + i + "]")).click();
					break;
				}
			}
			System.out.println("点击成功");

		}
		return name;
	}
	
	// 封装飞机下拉框
		public static String clickAirplane(WebMethod wm, String name, boolean Rclick) {
			WebDriver driver = wm.driver;
			Element.飞行记录_选择对应飞机(wm).click();
			Tools.sleep(5);// 等待加载，给固定时间了，不加逻辑判断对象了，貌似每次都很慢 ：（

			// 获取下拉框数量，先获得下拉框ul对象，再判断对象下有多少个标签为li的对象给一个list
			WebElement ul = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/ul"));
			List<WebElement> li = ul.findElements(By.tagName("li"));
			int num = li.size();
			System.out.println("下拉框共" + num + "数据");// 打印共多少条数据。
			if (Rclick) {
				// 判断是否需要随机点击
				int Rnum = Tools.randomNumber(1, num);

				WebElement element = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/ul/li[" + Rnum + "]"));
				name = element.getText();
				element.click();// 随机点击一条
				System.out.println("已经随机点击第" + Rnum + "条:" + name);
			} else {
				for (int i = 1; i <= num; i++) {
					if (driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/ul/li[" + i + "]/span")).getText()
							.equals(name)) {
						driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/ul/li[" + i + "]")).click();
						break;
					}
				}
				System.out.println("点击成功");

			}
			return name;
		}

}
