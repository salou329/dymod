package ui;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import action.DBcreate;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class init {
	@Test
	public void intdata() {
		System.out.println("初始化数据成功");
	}

	@BeforeSuite
	public void BeforeClass() {

		DBcreate.del();// 清空数据
		for (int i = 0; i <= 500; i++) {
			//调取create方法，初始数据执行
			DBcreate.create();
			System.err.println(i);
		}
	}

}