package com.autotest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG {

    @DataProvider(name = "appuser")
public Object[][] createdata() {
return new Object[][] {
             {"18052001540",  "huading"},
        };
     }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("我整个测试套每个方法前都运行");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("我整个测试套每个方法后都运行");
  }
  
  @BeforeClass
  public void beforeClass() {
	  System.out.println("我在整个class之前运行一次");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("我在整个class之后运行一次");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("我整个测试套所有<test>运行前运行一次");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("我整个测试套所有<test>运行后运行一次");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("整个测试套前运行一次");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("整个测试套后运行一次");
  }
  @Test(dependsOnMethods = { "test" },dataProvider = "appuser")
  public void init(String name,String pwd){
	  System.out.println("test通过才运行init   "+name+"/"+pwd);
  }
  @Test(priority = 1)
  public void test(){
	  System.out.println("运行test"); 
  }
  @Test(priority = 0)
  public void Ng(){
	  System.out.println("test中第一个运行");
  }

}
