package util;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

public class WebMethod extends ControlMethod {


	public WebDriver driver;

	public WebMethod(String conf) {
		// 通过conf选择驱动
		System.out.println("正在连接" + conf + "浏览器，请稍后...");
		try {
			if (os.contains("Windows")) {
				switch (conf) {
				case "firefox":
					System.setProperty("webdriver.firefox.marionette", "src/main/java/resources/geckodriver.exe");
					driver = new FirefoxDriver();
					System.out.println("浏览器连接成功");
					break;
				case "chrome":
					System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver.exe");
					driver = new ChromeDriver();
					System.out.println("浏览器连接成功");
					break;
				case "h5":
					System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver.exe");
					driver = new ChromeDriver(h5());
					System.out.println("浏览器连接成功");
					break;
				default:
					System.err.print("×驱动加载错误，请检查参数是否正确，火狐浏览器：firefox，谷歌浏览器：chrome，WAP浏览器：h5（区分大小写）");
					break;
				}
			} else if (os.contains("Mac")) {
				switch (conf) {
				case "firefox":
					System.setProperty("webdriver.firefox.marionette", "src/main/java/resources/geckodriver");
					// System.out.println(Tools.cmdLine("src/main/java/resources/geckodriver
					// --version"));
					driver = new FirefoxDriver();
					System.out.println("浏览器连接成功");
					break;
				case "chrome":
					System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver");
					// System.out.println(Tools.cmdLine("src/main/java/resources/chromedriver
					// --version"));
//					ChromeOptions options = new ChromeOptions();
//					options.addArguments("user-data-dir=/Users/salou/Library/Application Support/Google/Chrome/Default");
					driver = new ChromeDriver();
					System.out.println("浏览器连接成功");
					break;
				case "safari":
					// System.setProperty("webdriver.chrome.driver",
					// "src/main/java/resources/chromedriver");
					driver = new SafariDriver();
					System.out.println("浏览器连接成功");
					break;
				case "h5":
					System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver");
					// System.out.println(Tools.cmdLine("src/main/java/resources/chromedriver
					// --version"));
					driver = new ChromeDriver(h5());
					System.out.println("浏览器连接成功");
					break;
				default:
					System.err.print("×驱动加载错误，请检查参数是否正确，火狐浏览器：firefox，谷歌浏览器：chrome，苹果浏览器：safari，WAP浏览器：h5（区分大小写）");
					break;
				}
			
		} else if (os.contains("Linux")) {
			switch (conf) {
			case "firefox":
				System.setProperty("webdriver.firefox.marionette", "src/main/java/resources/geckodriver");
				// System.out.println(Tools.cmdLine("src/main/java/resources/geckodriver
				// --version"));
				driver = new FirefoxDriver();
				System.out.println("浏览器连接成功");
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver");
				// System.out.println(Tools.cmdLine("src/main/java/resources/chromedriver
				// --version"));
//					ChromeOptions options = new ChromeOptions();
//					options.addArguments("user-data-dir=/Users/salou/Library/Application Support/Google/Chrome/Default");
				driver = new ChromeDriver();
				System.out.println("浏览器连接成功");
				break;
			case "h5":
				System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver");
				// System.out.println(Tools.cmdLine("src/main/java/resources/chromedriver
				// --version"));
				driver = new ChromeDriver(h5());
				System.out.println("浏览器连接成功");
				break;
			default:
				System.err.print("×驱动加载错误，请检查参数是否正确，火狐浏览器：firefox，谷歌浏览器：chrome，WAP浏览器：h5（区分大小写）");
				break;
			}
		}
	} catch (Exception e) {
			if (e.toString().contains("Develop menu") && conf.equals("safari")) {
				System.err.println("驱动加载错误,请在safari浏览器-开发-允许远程自动化前打勾!");
			} else {
				System.err.println("驱动加载错误,请检测Driver文件是否存在且有可执行权限!");
			}
		}
	}

	/**
	 * 键盘操作
	 * 
	 * @return
	 */
	public Actions keyActions() {
		Actions ac = new Actions(driver);
		return ac;
	}

	/**
	 * 文字查找页面对象
	 */
	public WebElement Elementlink(String objectName) {
		return driver.findElement(By.linkText(objectName));
	}

	public WebElement Element(String objectName) {
		return driver.findElement(By.xpath("//*[@text='" + objectName + "']"));
	}

	/**
	 * 页面对象
	 * 
	 * @author 史文彬
	 * @param sheet
	 * @param objectName
	 * @return
	 */
	private WebElement WElement(String sheet, String objectName) {
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
			return driver.findElement(By.id(locator[0]));

		case "xpath":
			return driver.findElement(By.xpath(locator[0]));

		case "name":
			return driver.findElement(By.name(locator[0]));

		case "classname":
			return driver.findElement(By.className(locator[0]));

		case "linktext":
			return driver.findElement(By.linkText(locator[0]));

		case "cssselector":
			return driver.findElement(By.cssSelector(locator[0]));

		case "partiallinktext":
			return driver.findElement(By.partialLinkText(locator[0]));

		case "tagname":
			return driver.findElement(By.tagName(locator[0]));
		default:
			System.out.println("对不起，定位对象暂不支持".concat(locator[1]));
			return null;
		}

	}

	/**
	 * 页面对象先查找再返回
	 * 
	 * @param sheet
	 * @param objectName
	 * @return
	 */
	public WebElement Element(String sheet, String objectName) {
		waitElement(sheet, objectName);
		return WElement(sheet, objectName);
	}

	/**
	 * 等待对象
	 * 
	 * @param sheet
	 * @param objectName
	 * @param time
	 */
	public boolean waitElement(String sheet, String objectName, int times) {
		boolean reslut = false;
		for (int second = 1; second <= times; second++) {
			try {
				WElement(sheet, objectName).isDisplayed();
				reslut = true;
				times = second;
				break;
			} catch (Exception e) {
				// 没找到再次查找
			}
			this.sleep(1000);
		}
		if (reslut) {
			System.out.println("我耶，经过" + times + "次查找终于找到对象 " + objectName);
		} else {
			System.err.println("我操，经过" + times + "次查找也未找到对象 " + objectName);
		}
		return reslut;
	}

	public boolean waitElement(WebElement element, int times) {
		boolean reslut = false;
		for (int second = 1; second <= times; second++) {
			try {
				element.isDisplayed();
				reslut = true;
				times = second;
				break;
			} catch (Exception e) {
				// 没找到再次查找
			}
			this.sleep(1000);
		}
		return reslut;
	}

	protected void waitElement(String sheet, String objectName) {
		// 默认10秒
		waitElement(sheet, objectName, 11);
	}

	/**
	 * 下拉框选择
	 *
	 * @author 史文彬
	 * @param sheet
	 * @param objectName
	 * @param value
	 */
	public void select(String sheet, String objectName, String value) {
		this.waitElement(sheet, objectName);
		Select s = new Select(this.Element(sheet, objectName));
		s.selectByVisibleText(value);
	}

	/**
	 * 切换到frame框
	 *
	 * @author 史文彬
	 * @param frameName
	 */
	public void switchToFrame(String sheet, String objectName) {

		WebElement object = this.Element(sheet, objectName);
		driver.switchTo().frame(object);
	}

	/**
	 * 切换到主frame框
	 *
	 * @author 史文彬
	 */
	public void switcTodefaultContent() {

		driver.switchTo().defaultContent();
	}

	/**
	 * 切换到上一级frame框
	 *
	 * @author 史文彬
	 */
	public void switcToparentFrame() {

		driver.switchTo().parentFrame();
	}

	/**
	 * 切换窗口
	 * 
	 * @param num
	 */
	public void switchToWindow(int num) {
		Set<String> winHandels = driver.getWindowHandles();
		List<String> it = new ArrayList<String>(winHandels);
		String value = "";
		// System.out.println(it.size());
		System.out.println(it);
		// 不清楚什么逻辑，除了0以外，反正长度减去需要的目标tab才是正确的tab
		if (num == 0) {
			value = it.get(num);
		} else {
			value = it.get(it.size() - num);
		}
		driver.switchTo().window(value);
	}

	/**
	 * 打开网页链接
	 *
	 * @author 史文彬
	 * @param pageUrl
	 */
	// 传url打开指定url，不传url打开配置文件中的url
	public void openPage(String pageUrl) {

		driver.get(pageUrl);
	}

	public void newOpenPage(String pageUrl) {
		excuteJS("window.open('" + pageUrl + "');");
	}

	public void openPage() {
		driver.get(Global.get("url"));
		driver.manage().window().maximize();
	}

	/**
	 * 执行js方法
	 *
	 * @author 史文彬
	 */
	public void excuteJS(String js) {
		((JavascriptExecutor) driver).executeScript(js);
	}

	public void excuteJS(String js, WebElement element) {
		// 例子：js = "arguments[0].value='2018-9-9';"
		((JavascriptExecutor) driver).executeScript(js, element);
	}

	/**
	 * 修改某个对象的属性值
	 * 
	 * @param element
	 * @param property
	 * @param value
	 */
	public void updateElementProperty(WebElement element, String property, String value) {
		String js = "arguments[0]." + property + "='" + value + "';";
		excuteJS(js, element);
	}

	/**
	 * 查找只读日期控件，并输入日期
	 *
	 * @author 史文彬
	 */
	public void inputdate(String sheet, String objectName, String date) {
		this.waitElement(sheet, objectName);
		// String[] locator;
		String js;
		js = "arguments[0].value='" + date + "';";
		((JavascriptExecutor) driver).executeScript(js, this.Element(sheet, objectName));
	}

	/**
	 * 查找控件，去掉只读
	 *
	 * @author 史文彬
	 */
	public void changeReadOnly(String sheet, String objectName) {
		this.waitElement(sheet, objectName);
		String[] locator;
		if (Tools.getValue("repertoryOnDB").equals("no")) {
			ReadExcelUtil ex = new ReadExcelUtil(path, sheet);
			locator = ex.getvalue(objectName);
		} else {
			ReadDbUtil ex = new ReadDbUtil();
			locator = ex.getvalue(sheet, objectName);
		}
		switch (locator[1]) {
		case "id":
			excuteJS("document.getElementById(" + locator[0] + ").readOnly=false");
			break;
		case "name":
			excuteJS("document.getElementByName(" + locator[0] + ").readOnly=false");
			break;
		case "classname":
			excuteJS("document.getElementByclassName(" + locator[0] + ").readOnly=false");
		default:
			System.out.println("对不起，此方法暂不支持".concat(locator[1]));
			break;
		}

	}

	/**
	 * 鼠标点击
	 *
	 * @param sheet
	 * @param objectName
	 */
	public void xyClick(String sheet, String objectName) {
		Actions action = new Actions(driver);
		WebElement element = this.Element(sheet, objectName);
		action.moveToElement(element).click();
	}

	/**
	 * 移动鼠标到对象
	 * 
	 * @param sheet
	 * @param objectName
	 */
	public void moveMouse(String sheet, String objectName) {
		Actions action = new Actions(driver);
		WebElement element = this.Element(sheet, objectName);
		action.moveToElement(element);
	}

	/**
	 * 双击
	 *
	 * @param sheet
	 * @param objectName
	 */
	public void doubleclick(String sheet, String objectName) {
		Actions mouse = new Actions(driver);
		WebElement element = this.Element(sheet, objectName);
		mouse.doubleClick(element).perform();
	}

	/**
	 * 从一个table的单元格中得到文本值
	 *
	 * @param sheet
	 * @param objectName
	 * @param Row
	 * @param Cell
	 * @return 返回单元格的值
	 * @author 史文彬
	 */
	public String getCellValue(String sheet, String objectName, int Row, int Cell) {
		this.waitElement(sheet, objectName);

		// 查找表格
		WebElement table = this.Element(sheet, objectName);
		// 得到table表中所有行对象，并得到所要查询的行对象。
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		WebElement theRow = rows.get(Row);
		List<WebElement> cells;
		WebElement target = null;
		String content = "";
		// 如果标签是<th>
		try {
			if (theRow.findElements(By.tagName("th")).size() > 0) {
				cells = theRow.findElements(By.tagName("th"));
				target = cells.get(Cell);
				content = target.getText();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		// 如果标签是<td>
		try {
			if (theRow.findElements(By.tagName("td")).size() > 0) {
				cells = theRow.findElements(By.tagName("td"));
				target = cells.get(Cell);
				content = target.getText();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return content;
	}

	/**
	 * 点击表格单选框
	 *
	 * @param sheet
	 * @param objectName
	 * @param Row
	 * @param Cell
	 * @return 返回对象
	 * @author 史文彬
	 */
	public WebElement clickCell(String sheet, String objectName, int Row, int Cell) {
		this.waitElement(sheet, objectName);
		// 查找表格
		WebElement table = this.Element(sheet, objectName);
		// 得到table表中所有行对象，并得到所要查询的行对象。
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		WebElement theRow = rows.get(Row);
		List<WebElement> cells;
		WebElement target = null;
		// 如果标签是<th>
		try {
			if (theRow.findElements(By.tagName("th")).size() > 0) {
				cells = theRow.findElements(By.tagName("th"));
				target = cells.get(Cell);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		// 如果标签是<td>
		try {
			if (theRow.findElements(By.tagName("td")).size() > 0) {
				cells = theRow.findElements(By.tagName("td"));
				target = cells.get(Cell);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return target;
	}

	/**
	 * 根据列找对应列的值（返回第一个发现的）
	 * 
	 * @param sheet
	 * @param objectName
	 * @param Cell
	 * @param cellValue
	 * @param goalCell
	 * @return
	 */
	public String cellFcell(String sheet, String objectName, int Cell, String cellValue, int goalCell) {
		try {
			for (int Row = 0;; Row++) {
				if (getCellValue(sheet, objectName, Row, Cell).equals(cellValue)) {
					return getCellValue(sheet, objectName, Row, goalCell);
				}
			}
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 通过2列找对应列的值 （返回第一个发现的）
	 * 
	 * @param sheet
	 * @param objectName
	 * @param Cell
	 * @param cellValue
	 * @param Cell2
	 * @param cell2Value
	 * @param goalCell
	 * @return
	 */
	public String cell2Fcell(String sheet, String objectName, int Cell, String cellValue, int Cell2, String cell2Value,
			int goalCell) {
		try {
			for (int Row = 0;; Row++) {
				if (getCellValue(sheet, objectName, Row, Cell).equals(cellValue)
						&& getCellValue(sheet, objectName, Row, Cell2).equals(cell2Value)) {
					return getCellValue(sheet, objectName, Row, goalCell);
				}
			}
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 通过两列点击第三列，第一个找到的
	 * 
	 * @param sheet
	 * @param objectName
	 * @param Cell
	 * @param cellValue
	 * @param Cell2
	 * @param cell2Value
	 * @param goalCell
	 * @return
	 */
	public void cell2Clickcell(String sheet, String objectName, int Cell, String cellValue, int Cell2,
			String cell2Value, int goalCell) {
		for (int Row = 0;; Row++) {
			if (getCellValue(sheet, objectName, Row, Cell).equals(cellValue)
					&& getCellValue(sheet, objectName, Row, Cell2).equals(cell2Value)) {
				clickCell(sheet, objectName, Row, goalCell).click();
				break;
			}
		}
	}

	/**
	 * 通过两列点击第三列中的某一个按钮，第一个找到的
	 * 
	 * @param sheet
	 * @param objectName
	 * @param Cell
	 * @param cellValue
	 * @param Cell2
	 * @param cell2Value
	 * @param goalCell
	 * @param value
	 */
	public void cell2FindcellToChooseClick(String sheet, String objectName, int Cell, String cellValue, int Cell2,
			String cell2Value, int goalCell, String value) {
		for (int Row = 0;; Row++) {
			if (getCellValue(sheet, objectName, Row, Cell).equals(cellValue)
					&& getCellValue(sheet, objectName, Row, Cell2).equals(cell2Value)) {
				clickCell(sheet, objectName, Row, goalCell).findElement(By.linkText(value)).click();
				break;
			}
		}
	}

	/**
	 * 验证码
	 * 
	 * @param xpath
	 */
	public String GetYZM(String xpath) {
		File srcFile = driver.findElement(By.xpath(xpath)).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("jietu/yzm.png"));
		} catch (IOException e) {
			System.out.println("截图验证码失败");
		}
		return "jietu/yzm.png";
	}
}
