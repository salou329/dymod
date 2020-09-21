package action;

import page.TestData;
import util.Tools;

public final class DBcreate {
	/**
	 * 一套测试数据 独立用户，独立线路，独立任务，独立塔
	 */
	public static void create() {
			String userId=Tools.randomNumber(10);
			String lineid=Tools.randomNumber(10);
			String maid=Tools.randomNumber(10);
			String Towerid=Tools.randomNumber(10);
			String andid=Tools.randomNumber(10);
			TestData.新增user(userId);
			TestData.新增Line(lineid);
			TestData.新增missionAssignments(maid, Towerid,userId);
			TestData.新增Tower(Towerid, lineid);	
			TestData.新增appnodedata(maid, Towerid, andid);
	}
	/**
	 * 清空tower、line、missionAssignments
	 */
	public static void del() {
		TestData.删除Line();
		TestData.删除missionAssignments();
		TestData.删除Tower();
		TestData.删除appnodedata();
		
	}
	
}
