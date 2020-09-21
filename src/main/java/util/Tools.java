package util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.jdbc.ScriptRunner;

public final class Tools {

	static Properties props = System.getProperties();
	static String os = props.getProperty("os.name");

	/**
	 * 休息几秒
	 * 
	 * @param i
	 */
	public static void sleep(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {

		}
	}

	/**
	 * 获取网络当前北京时间 格式：2019-07-24 13:59:29
	 * 
	 * @author 史文彬
	 *
	 */
	public static String time() {
		String json = HttpUtil.doGet("http://quan.suning.com/getSysTime.do");
		return HttpUtil.GetJsonValue(json, "sysTime2");
	}

	/**
	 * 设备系统占用率
	 * 
	 * @param pack
	 * @return
	 */
	public static int sysInfo(String pack) {
		try {
			String time = time();
			System.err.println("-------" + time + "-------");
			String meminfo = Tools.ADB("adb shell dumpsys meminfo");
			double totalMem = Integer.parseInt(Tools.sideFind(meminfo, "Total RAM:", " kB").trim());
			double usedMem = Integer.parseInt(Tools.sideFind(meminfo, " Used RAM:", " kB").trim());
			System.err.println("内存：" + (int) usedMem / 1024 + "MB/" + (int) totalMem / 1024 + "MB");
			double percent = (usedMem / totalMem * 100);
			System.err.println("mem占用率：" + percent + "%");
			String cpuinfo = Tools.ADB("adb shell dumpsys cpuinfo");
			String appcpu = Tools.sideFind(cpuinfo, "/" + pack + ":", "/");
			System.err.println("cpu占用率：" + appcpu);
			System.err.println("当前已用wlan流量："
					+ (Integer.parseInt(Tools.ADB("adb shell cat /sys/class/net/wlan0/statistics/rx_bytes"))
							+ Integer.parseInt(Tools.ADB("adb shell cat /sys/class/net/wlan0/statistics/tx_bytes")))
							/ 1024
					+ "KB");
			System.err.println("---------------------------------");
			return Integer.parseInt(Tools.sideFind(time, " ", ":"));
		} catch (Exception e) {

			System.err.println(e);
			return 0;
		}

	}

	/**
	 * 获取网络当前美东时间 格式：2019-07-24 01:59:00
	 * 
	 * @author 史文彬
	 *
	 */
	public static String ESTtime() {
		String json = HttpUtil.doGet("http://worldclockapi.com/api/json/est/now");
		return HttpUtil.GetJsonValue(json, "currentDateTime").replace("T", " ").replace("-04", "");
	}

	/**
	 * 执行命令行并返回结果
	 * 
	 * @author 史文彬
	 * @param cmd
	 * @return
	 */
	public static String cmdLine(String cmd) {
		String line = "";
		String message = null;
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader reader;
			try (InputStream is = p.getInputStream()) {
				reader = new BufferedReader(new InputStreamReader(is));
				// line = reader.readLine();
				while ((message = reader.readLine()) != null) {
					line = line + "\n" + message;
				}
				p.waitFor();
			}
			reader.close();
			p.destroy();
		} catch (IOException | InterruptedException e) {
			System.out.println("执行命令行失败！" + "\n" + e);
		}
		return line.trim();
	}

	/**
	 * 执行adb
	 * 
	 * @param cmd
	 * @return
	 */
	public static String ADB(String cmd) {
		String line = "";
		String adb = Tools.getValue("adb");
		line = cmdLine(adb + cmd);
		return line.trim();
	}

	/**
	 * 获取默认配置文件参数值
	 *
	 * @author 史文彬
	 * @param value 参数值
	 * @return
	 */
	public static String getValue(String value) {
		Properties p = new Properties();
		// config.properties目录
		try {
			InputStream ips = new FileInputStream("src/main/java/resources/config.properties");
			BufferedReader bf = new BufferedReader(new InputStreamReader(ips));
			p.load(bf);
			value = p.getProperty(value);

		} catch (IOException e) {
			System.out.println("获取配置文件失败，请检查配置文件");
		}
		// 返回
		return value;

	}

	/**
	 * 获取默认配置文件参数值
	 *
	 * @author 史文彬
	 * @param value 参数值
	 * @return
	 */
	public static String getJmeter(String value) {
		Properties p = new Properties();
		// config.properties目录
		try {
			InputStream ips = new FileInputStream("src/main/java/resources/jmeter.properties");
			BufferedReader bf = new BufferedReader(new InputStreamReader(ips));
			p.load(bf);

			if (Auth.authenticate()) {
				value = p.getProperty(value);
			} else {
				value = null;
			}
		} catch (IOException e) {
			System.out.println("获取配置文件失败，请检查配置文件");
		}
		// 返回
		return value;

	}

	/**
	 * 指定范围内随机数
	 * 
	 * @author shiwenbin
	 * @param max
	 * @param min
	 * @return
	 */
	public static int randomNumber(int min, int max) {
		Random random = new Random();
		if (min > max) {
			int temp;
			temp = max;
			max = min;
			min = temp;
		}
		return random.nextInt(max) % (max - min + 1) + min;
	}

	/**
	 * 指定长度数字
	 * 
	 * @param len
	 * @return
	 */
	public static String randomNumber(int len) {

		return RandomStringUtils.randomNumeric(len);
	}

	/**
	 * 指定长度随机字母
	 * 
	 * @author shiwenbin
	 * @param len
	 * @return
	 */
	public static String randomString(int len) {

		String str = RandomStringUtils.random(len, "abcdefghijklmnopqrstuvwxyz");
		return str;
	}

	/**
	 * 查找ip
	 * 
	 * @return
	 */
	public static String FindIP() {
		String ip = null;
		String adress = null;
		try {
			String value = HttpUtil.doGet("http://pv.sohu.com/cityjson?ie=utf-8");
			int max = value.length();
			value = value.substring(19, max - 1);
			adress = HttpUtil.GetJsonValue(value, "cname");
			ip = HttpUtil.GetJsonValue(value, "cip");
			System.out.println("IP:" + ip + "(" + fanyi(adress) + ")");
		} catch (Exception e) {
			System.err.println("未查询到您所在IP");
		}
		return ip;
	}

	/**
	 * 翻译
	 * 
	 * @param value
	 * @return
	 */
	public static String fanyi(String txt) {
		String value = txt;
		try {
			value = HttpUtil.doGet("http://fanyi.youdao.com/translate?&doctype=json&type=AUTO&i=" + value);
			// System.out.println(value);
			value = HttpUtil.GetJsonValue(value, "translateResult");
			int max = value.length();
			value = value.substring(2, max - 2);
			value = HttpUtil.GetJsonValue(value, "tgt");
		} catch (Exception e) {
			value = txt;
		}
		return value;
	}

	/**
	 * 随机中文名
	 * 
	 * @return
	 */
	public static String ChineseName() {
		String surname = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤";
		surname = RandomStringUtils.random(1, surname);
		String middlename = "精忠报国维持正义文昌德茂广远泽长吉善余庆继世传家恩思绍前人志长昭青永世谋居心中繁茂庆千秋世连德茂广元泽长";
		middlename = RandomStringUtils.random(1, middlename);
		String name = "君不见走马川行雪海边平沙莽莽黄入天轮台九月风夜吼一川碎石大如斗随风满地石乱走匈奴草黄马正肥金山西见烟尘飞汉家大将西出师将军金甲夜不脱半夜军行戈相拨风头如刀面如割马毛带雪汗气蒸五花连钱旋作冰幕中草檄砚水凝虏骑闻之应胆慑料知短兵不敢接车师西门伫献捷";
		name = RandomStringUtils.random(1, name);
		return surname + middlename + name;
	}

	/**
	 * 随机手机号
	 * 
	 * @return
	 */
	public static String getTelephone() {
		String telFirst[] = { "134", "135", "136", "137", "138", "139", "147", "148", "150", "151", "152", "157", "158",
				"159", "165", "172", "178", "182", "183", "184", "187", "188", "198", "130", "131", "132", "145", "146",
				"155", "156", "166", "171", "175", "176", "185", "186", "133", "149", "153", "173", "174", "177", "180",
				"181", "189", "191", "199", "170" };
		int first = randomNumber(0, telFirst.length - 1);
		String num = telFirst[first] + randomNumber(8);
		return num;
	}

	/**
	 * 随机一个邮箱
	 * 
	 * @return
	 */
	public static String getEmail() {
		String[] mail = { "qq", "163", "gmail", "hotmail", "yahoo", "sohu" };

		return randomString(9) + "@" + mail[Tools.randomNumber(0, mail.length)] + ".com";
	}

	/**
	 * 发送telegram消息到指定群组
	 * 
	 * @param message
	 */
	public static Boolean sendTelegram(String message, String... id) {
		boolean auth = false;
		String token = "878333787:AAFBEm90jJ9o8trVbeViffiz_owkjNsuDdU";
		HashMap<String, String> params = new HashMap<>();
		if (id.length > 0) {
			params.put("chat_id", id[0]);
		} else {
			String group = Tools.getValue("bug");
			params.put("chat_id", group);
		}

		params.put("text", message);
		try {
			HttpUtil.doPost("https://api.telegram.org/bot" + token + "/sendMessage", params);
			auth = true;
		} catch (Exception e) {
			System.err.println("发送Telegram失败！不存在的用户或组，请检查config文件的bug字段是否配置正确。");
		}
		return auth;
	}

	/**
	 * 截取图片
	 * 
	 * @param path
	 * @param x
	 * @param y
	 * @param cutW
	 * @param cutH
	 */
	public static void cutPic(String path, int x, int y, int cutW, int cutH) {
		try {
			// path = "jietu/yzm.png";
			// int x = 11, y = 20, cutW = 280, cutH = 280;
			BufferedImage image = ImageIO.read(new File(path));
			// 截取图片
			// System.out.println("分辨率："+image.getWidth()+"*"+image.getHeight());

			Rectangle rect = new Rectangle(x, y, cutW, cutH);
			BufferedImage areaImage = image.getSubimage(rect.x, rect.y, rect.width, rect.height);
			// 新建一个40*40的Image
			BufferedImage buffImg = new BufferedImage(118, 50, BufferedImage.TYPE_INT_RGB);
			buffImg.getGraphics().drawImage(areaImage.getScaledInstance(118, 50, java.awt.Image.SCALE_SMOOTH), 0, 0,
					null);

			ImageIO.write(buffImg, "png", new File("jietu/new_pic.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 字符串左右边界查找指定数据
	 * 
	 * @param str
	 * @param left
	 * @param right
	 * @return
	 */
	public static String sideFind(String str, String left, String right) {
		int x = 0;
		int y = 0;
		if (left.equals("")) {
			x = 0;// 如果left是""，则从头开始截取
		} else {
			x = str.indexOf(left) + left.length();// 字符串开始位置，出现的位置+字符长度算出开始截取的位置
		}
		str = str.substring(x);// 截取掉左边的
		if (right.equals("")) {
			y = str.length();// 如果right是""，则截取到最后
		} else {
			y = str.indexOf(right);// 查到右边界位置
		}
		return str.substring(0, y);// 截取掉右边的返回
	}

	/**
	 * 返回一个图片流
	 * 
	 * @return
	 */
	public static InputStream pic(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				return inputStream;
			}
		} catch (IOException e) {
			System.out.println("获取网络图片出现异常，图片路径为：" + url);
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * unicode转中文
	 * 
	 * @param unicode
	 * @return
	 */
	public static String unicodeToCn(String unicode) {
		/** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格 */
		String[] strs = unicode.split("\\\\u");
		String returnStr = "";
		// 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
		for (int i = 1; i < strs.length; i++) {
			returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
		}
		return returnStr;
	}

	/**
	 * 调用jmeter发送接口请求
	 * 
	 * @return
	 */
	public static void SendJmeter(String Path) {
		String scriptPath = Tools.getJmeter(Path);
		String jmeterpath = Tools.getValue("jmeter目录");
		String cmd = "sh " + jmeterpath + "jmeter -n -t " + scriptPath + " -Jthread=1";
		if (os.contains("Windows")) {
			cmd = jmeterpath + "jmeter -n -t " + scriptPath + " -Jthread=1";
		}
		cmdLine(cmd);
	}

	/**
	 * 执行sql文件
	 * 
	 * @param sqlFilePath
	 */
	public static void execSqlFileByMysql(String sqlFilePath) {

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://" + Tools.getValue("jdbc") + "?useUnicode=true&characterEncoding=utf8";

		Connection conn = null;
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, getValue("jdbcname"), getValue("jdbcpwd"));
//          设置不自动提交
			conn.setAutoCommit(false);

			ScriptRunner runner = new ScriptRunner(conn);
//          设置不自动提交
			runner.setAutoCommit(false);
			/*
			 * setStopOnError参数作用：遇见错误是否停止；
			 * （1）false，遇见错误不会停止，会继续执行，会打印异常信息，并不会抛出异常，当前方法无法捕捉异常无法进行回滚操作，无法保证在一个事务内执行；
			 * （2）true，遇见错误会停止执行，打印并抛出异常，捕捉异常，并进行回滚，保证在一个事务内执行；
			 */
			runner.setStopOnError(true);

			/*
			 * 按照那种方式执行 方式一：true则获取整个脚本并执行； 方式二：false则按照自定义的分隔符每行执行；
			 */
			runner.setSendFullScript(false);

//          定义命令间的分隔符
			runner.setDelimiter(";");
			runner.setFullLineDelimiter(false);

//          设置是否输出日志，null不输出日志，不设置自动将日志输出到控制台
			runner.setLogWriter(null);

//          如果又多个sql文件，可以写多个runner.runScript(xxx),

			runner.runScript(new InputStreamReader(new FileInputStream(sqlFilePath), "utf-8"));
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				if (conn != null) {
					conn = null;
				}
			}
		}

	}

	/**
	 * 获取当前年份
	 */
	public static String getSysYear() {
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		return year;
	}

}
