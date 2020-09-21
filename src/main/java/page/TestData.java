package page;


import java.io.IOException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import bsh.This;
import util.Tools;

public final class TestData {
	// static String url = "http://192.168.100.103:1234/document/mongo" //测试环境
	static String url = "http://192.168.3.184:1234/dbApp/document/mongo";// 本地

	/**
	 * 用户
	 * 
	 * @param userId
	 */
	public static void 新增user(String userId) {
		try {
			Unirest.setTimeouts(0, 0);
			HttpResponse<String> response = Unirest.post(url + "/aircraft/users/insert_doc")
					.header("Content-Type", "application/json")
					.header("Cookie",
							"connect.sid=s%3AOVDC6hBcS6CVezUphKP_REWfuo1szKTJ.BduhCaIA30DCznqHk35b9Lu7tyO2ngXlNNAc0zL2djY")
					.body("{\n\t\"objectData\": \"{\\n    \\\"id\\\": \\\"" + userId
							+ "\\\",\\n    \\\"username\\\": \\\"swb\\\",\\n    \\\"nickname\\\": \\\"shiwenbin测试组\\\",\\n    \\\"password\\\": \\\"$2b$10$fX4UnnQddbZjfCUMX3b5YeVF1gJ7KesV63rg8reVsHY1jmYNnnIJ.\\\",\\n    \\\"createdAt\\\": 1599062400,\\n    \\\"updatedAt\\\": 1599062400,\\n    \\\"roleId\\\": \\\"31de10c8-7591-4eac-830f-e6472520fb7c\\\",\\n    \\\"managementTeam\\\": \\\"测试管理团队\\\",\\n    \\\"dropped\\\": false\\n}\"\n}")
					.asString();
			System.out.println(response.getBody());
			Unirest.shutdown();
		} catch (UnirestException | IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 线路管理
	 */
	public static void 新增Line(String lineid) {
		String name = "dy测试数据";
		try {
			Unirest.setTimeouts(0, 0);
			HttpResponse<String> response = Unirest.post(url + "/aircraft/lines/insert_doc")
					.header("Content-Type", "application/json")
					.header("Cookie",
							"connect.sid=s%3AOVDC6hBcS6CVezUphKP_REWfuo1szKTJ.BduhCaIA30DCznqHk35b9Lu7tyO2ngXlNNAc0zL2djY")
					.body("{\n\t\"objectData\": \"{\\n    \\\"id\\\": \\\"" + lineid
							+ "\\\",\\n    \\\"info\\\": {\\n        \\\"managementTeam\\\": \\\"" + name
							+ Tools.randomNumber(5)
							+ "\\\",\\n        \\\"name\\\": \\\""+Tools.ChineseName()+"的任务\\\",\\n        \\\"volLevelInKV\\\": 110,\\n        \\\"createdAt\\\": -1,\\n        \\\"createdBy\\\": \\\"fangtian\\\",\\n        \\\"isDeserted\\\": false\\n    }\\n}\"\n}")
					.asString();
			System.out.println(response.getBody());
			Unirest.shutdown();
		} catch (UnirestException | IOException e) {
			System.out.println(e);
		}
	}

	public static void 新增Tower(String Towerid, String lineid) {
		try {
			Unirest.setTimeouts(0, 0);
			HttpResponse<String> response = Unirest.post(url + "/aircraft/towers/insert_doc")
					.header("Content-Type", "application/json")
					.header("Cookie",
							"connect.sid=s%3AOVDC6hBcS6CVezUphKP_REWfuo1szKTJ.BduhCaIA30DCznqHk35b9Lu7tyO2ngXlNNAc0zL2djY")
					.body("{\n\t\"objectData\": \"{\\n    \\\"id_version\\\": {\\n        \\\"id\\\": \\\"" + Towerid
							+ "\\\",\\n        \\\"version\\\": \\\"v1\\\"\\n    },\\n    \\\"info\\\": {\\n        \\\"category\\\": \\\"耐张\\\",\\n        \\\"gps\\\": {\\n            \\\"lat\\\": 31.91444047,\\n            \\\"lon\\\": 120.59387038\\n        },\\n        \\\"identifier\\\": \\\"测试支线888\\\",\\n        \\\"lineId\\\": \\\""
							+ lineid
							+ "\\\",\\n        \\\"createdAt\\\": 1599062400,\\n        \\\"createdBy\\\": \\\"测试团队\\\",\\n        \\\"isDeserted\\\": false,\\n        \\\"isCollected\\\": false\\n    }\\n}\"\n}")
					.asString();
			System.out.println(response.getBody());
			Unirest.shutdown();
		} catch (UnirestException | IOException e) {
			System.out.println(e);
		}

	}

	public static void 新增missionAssignments(String maid, String Towerid,String userId) {
		try {
			String[] StatusList = { "started", "done" ,"created","canceled"};
			String statu = StatusList[(int) Math.floor(Math.random() * StatusList.length)];
			Unirest.setTimeouts(0, 0);
			HttpResponse<String> response = Unirest.post(url + "/aircraft/missionAssignments/insert_doc")
					.header("Content-Type", "application/json")
					.header("Cookie",
							"connect.sid=s%3AOVDC6hBcS6CVezUphKP_REWfuo1szKTJ.BduhCaIA30DCznqHk35b9Lu7tyO2ngXlNNAc0zL2djY")
					.body("{\n\t\"objectData\": \"{\\n    \\\"id_version\\\": {\\n        \\\"id\\\": \\\"" + maid
							+ "\\\",\\n        \\\"version\\\": \\\"v1\\\"\\n    },\\n    \\\"assignedTowerId\\\": \\\"\\\",\\n    \\\"createdAt\\\": 1654131600000,\\n    \\\"createdBy\\\": \\\"fangtian\\\",\\n    \\\"createdByDevice\\\": \\\"carrier-nj\\\",\\n    \\\"deadline\\\": 1654156140000,\\n    \\\"description\\\": \\\"测试线路 - 001 ~ 004 - 正常巡视\\\",\\n    \\\"marked\\\": false,\\n    \\\"peopleIds\\\": [\\n        \\\""
							+ userId + "\\\"\\n    ],\\n    \\\"status\\\": \\\"" + statu
							+ "\\\",\\n    \\\"statusUpdatedAt\\\": 1597033774365,\\n    \\\"templateIdList\\\": [],\\n    \\\"towerIdList\\\": [\\n        {\\n            \\\"id\\\": \\\""
							+ Towerid
							+ "\\\",\\n            \\\"version\\\": \\\"v1\\\"\\n        }\\n    ],\\n    \\\"updatedAt\\\": 1555401060457,\\n    \\\"hasTrueTowers\\\": false,\\n    \\\"statusInCenter\\\": \\\"started\\\"\\n}\"\n}")
					.asString();
			System.out.println(response.getBody());
			Unirest.shutdown();
		} catch (UnirestException | IOException e) {
			System.out.println(e);
		}

	}

	public static void 新增appnodedata(String maid, String Towerid, String andid) {
		try {
			Unirest.setTimeouts(0, 0);
			HttpResponse<String> response = Unirest
					.post(url + "/aircraft/appNodeData/insert_doc")
					.header("Content-Type", "application/json")
					.body("{\n\t\"objectData\": \"{\\n    \\\"droneId\\\": \\\"dt2\\\",\\n    \\\"ZHD_RTK\\\": {\\n        \\\"lon\\\": 119.94291685513977,\\n        \\\"lat\\\": 31.820757004875148,\\n        \\\"alt\\\": 13.109,\\n        \\\"quality\\\": \\\"rtk\\\",\\n        \\\"timestamp\\\": 1596176226307,\\n        \\\"isCache\\\": false\\n    },\\n    \\\"dji_RTK\\\": {\\n        \\\"alt\\\": 0,\\n        \\\"lat\\\": 31.82076965361365,\\n        \\\"lon\\\": 119.94292763835848\\n    },\\n    \\\"alt\\\": 13.109,\\n    \\\"distance\\\": \\\"1.7387096321902153\\\",\\n    \\\"height\\\": 88,\\n    \\\"chargeRemainingInPercent\\\": \\\"53\\\",\\n    \\\"assignmentId\\\": \\\""+maid+"\\\",\\n    \\\"status\\\": \\\"upload\\\",\\n    \\\"flightId\\\": \\\"804684bc-faa2-4b85-b352-230eb1619a4b\\\",\\n    \\\"towerId\\\": \\\""+Towerid+"\\\",\\n    \\\"statusInfo\\\": \\\"96%\\\",\\n    \\\"id\\\": \\\""+andid+"\\\",\\n    \\\"createdAt\\\": \\\"1596176227228\\\"\\n}\"\n}")
					.asString();
			System.out.println(response.getBody());
			Unirest.shutdown();
			
		} catch (UnirestException | IOException e) {
			System.out.println(e);
		}

	}

	public static void 删除Line() {

		try {
			Unirest.setTimeouts(0, 0);
			HttpResponse<String> response = Unirest.post(url + "/aircraft/lines/mass_delete").asString();
			System.out.println(response.getBody());
		} catch (UnirestException e) {
			System.out.println(e);
		}
	}

	public static void 删除Tower() {
		try {
			Unirest.setTimeouts(0, 0);
			HttpResponse<String> response = Unirest.post(url + "/aircraft/towers/mass_delete").asString();

			System.out.println(response.getBody());
		} catch (UnirestException e) {
			System.out.println(e);
		}

	}

	public static void 删除missionAssignments() {
		try {
			Unirest.setTimeouts(0, 0);
			HttpResponse<String> response = Unirest.post(url + "/aircraft/missionAssignments/mass_delete").asString();
			System.out.println(response.getBody());
		} catch (UnirestException e) {
			System.out.println(e);
		}

	}
	public static void 删除appnodedata() {
		try {
			Unirest.setTimeouts(0, 0);
			HttpResponse<String> response = Unirest.post(url + "/aircraft/appNodeData/mass_delete").asString();
			System.out.println(response.getBody());
		} catch (UnirestException e) {
			System.out.println(e);
		}

	}
}
