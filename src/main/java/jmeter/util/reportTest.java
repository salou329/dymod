package jmeter.util;

import java.util.Properties;

import jmeter.pojo.summaryPojo;
import util.ReportForJmx;
import util.Tools;

public final class reportTest {
	public static void print() {
		System.err.println("注意！压测功能暂未适配Windows");
		int xxx = Integer.parseInt(Tools.getValue("压测次数"));
		String jmeterpath = Tools.getValue("jmeter目录");
		String scriptPath = Tools.getValue("脚本目录");
		System.out.println("1、测试策略见脚本" + scriptPath);
		System.out.println("2、最低并发人数：1人");
		System.out.println("3、最高并发人数：" + xxx + "人");
		System.out.println("4、压测次数：" + xxx + "次");
		System.out.println("开始压测，日志打印中...");
		summaryPojo[] summarys = new summaryPojo[xxx];
		ReportForJmx rfj = new ReportForJmx();
		String[] num = new String[summarys.length];
		String[] log = new String[summarys.length];
		for (int i = 0; i < xxx; i = i + 1) {
			int count = i + 1;
			String cmd = "cd " + jmeterpath + " ;sh jmeter -n -t " + scriptPath + " -Jthread=" + count;
			String resultString = execLinux.exec(cmd).toString();

			summarys[i] = parseReport.parsePojo(resultString);

			System.out.println("当" + count + "个用户并发时，系统每秒处理" + summarys[i].getThroughput() + "个请求（TPS）"
					+ "，每个请求的平均响应时间为" + summarys[i].getAvg() + "毫秒（Average Response Time），" + "执行错误率为"
					+ summarys[i].getError() + "%，" + "本次共执行接口请求" + summarys[i].getRequests() + "个");
			if (summarys[i].getError() > 0) {
				log[i] = "异常：系统每秒处理" + summarys[i].getThroughput() + "个请求（TPS）" + "，每个请求的平均响应时间为" + summarys[i].getAvg()
						+ "毫秒（Average Response Time），" + "执行错误率为" + summarys[i].getError() + "%，" + "本次共执行接口请求"
						+ summarys[i].getRequests() + "个";
			} else {
				log[i] = "正常";
			}

			num[i] = String.valueOf(count);
		}

		System.out.println("------吞吐率------");
		String[] tpsvalue = new String[summarys.length];
		String[] name = new String[2];
		name[0] = "并发数";
		name[1] = "吞吐率";
		for (int i = 0; i < xxx; i = i + 1) {

			String tp = String.valueOf(summarys[i].getThroughput());
			System.out.println(tp);
			tpsvalue[i] = tp;
		}

		System.out.println("------平均响应时间------");
		String[] avgvalue = new String[summarys.length];

		name[0] = "并发数";
		name[1] = "平均响应时间";
		for (int i = 0; i < xxx; i = i + 1) {

			String tp = String.valueOf(summarys[i].getAvg());
			System.out.println(tp);
			avgvalue[i] = tp;
		}

		System.out.println("-------错误率------");
		String[] errvalue = new String[summarys.length];

		name[0] = "并发数";
		name[1] = "错误率";
		for (int i = 0; i < xxx; i = i + 1) {

			String tp = String.valueOf(summarys[i].getError());
			System.out.println(tp);
			errvalue[i] = tp + "%";
		}

		String[] title = new String[4];
		title[0] = "吞吐率（个）";
		title[1] = "平均响应时间（毫秒）";
		title[2] = "错误率";
		title[3] = "日志";
		rfj.report(title, num, tpsvalue, avgvalue, errvalue, log);
	}

}
