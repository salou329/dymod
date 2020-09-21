package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadDbUtil {
	
	static String[][] locatorMap = readDB();

	public String[] getvalue(String sheet, String objectname) {
		String[] locator = new String[2];
		for (int i = 0; i < locatorMap.length; i++) {
			if (locatorMap[i][2].equals(sheet) && locatorMap[i][3].equals(objectname)) {
				locator[0] = locatorMap[i][0];
				locator[1] = locatorMap[i][1];
				break;
			}
		}
		if (locator != null || (locator == null && locator.length != 0)) {
			System.out.println("对象名称<" + objectname + ">，定位方式：" + locator[1] + ",值：" + locator[0]);
		}
		return locator;

	}

	public static String[][] readDB() {
		String[][] locatorMap = null;
		int j = 0;
		String pro_name = Tools.getValue("UIlibrary");
		final String DRIVER = "com.mysql.jdbc.Driver";
		final String URL = Tools.getValue("object_jdbc").concat("?useUnicode=true&characterEncoding=utf8");
		final String USERNAME = Tools.getValue("object_name");
		final String PASSWORD = Tools.getValue("object_pwd");
		Connection conn = null; // 每一个Connection对象表示一个数据库连接对象
		Statement stat = null;
		System.out.println("开始加载数据库对象仓库...");
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stat = conn.createStatement();
			String sql = "select count(*) from object where isdelete='0' and pro_name='" + pro_name + "';";// 查询语句
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				j = rs.getInt(1);
			}
			locatorMap = new String[j][4];

			String sql2 = "select * from object where isdelete='0' and pro_name='" + pro_name + "';";// 查询语句
			rs = stat.executeQuery(sql2);
			int i = 0;
			while (rs.next()) {
				locatorMap[i][0] = rs.getString("value");
				locatorMap[i][1] = rs.getString("location");
				locatorMap[i][2] = rs.getString("sheet");
				locatorMap[i][3] = rs.getString("objectName");
				i++;
			}
			rs.close();
			stat.close();
			conn.close();
			System.out.println("加载成功，" + pro_name + "项目共有" + j + "个对象");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print("数据库加载失败！报错：");
			System.out.print(e);
		}

		return locatorMap;
	}

}
