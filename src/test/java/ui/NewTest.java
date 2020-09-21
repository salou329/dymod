package ui;

import org.testng.annotations.Test;

import io.appium.java_client.imagecomparison.OccurrenceMatchingOptions;
import util.OCR;
import util.ReadMongoUtil;
import util.Tools;

public class NewTest {
  @Test
  public void f() {
	  ReadMongoUtil.getvalue("首页", "登陆按钮");
	  OCR.ocr("/Users/salou/Desktop/Jietu20200915-170430.jpg");
  }
}
