package util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class jmeterTools {

	// 返回一个指定长度的字符串
	public static String randomString(int len) {
		String str = RandomStringUtils.random(len, "abcdefghijklmnopqrstuvwxyz");
		return str;
	}

	// 返回一个email
	public static String getEmail() {
		String[] mail = { "qq", "163", "gmail", "hotmail", "yahoo", "sohu" };
		return randomString(9) + "@" + mail[randomNumber(0, mail.length)] + ".com";
	}

	// 返回一个中文名
	public static String ChineseName() {
		String surname = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤";
		surname = RandomStringUtils.random(1, surname);
		String middlename = "精忠报国维持正义文昌德茂广远泽长吉善余庆继世传家恩思绍前人志长昭青永世谋居心中繁茂庆千秋世连德茂广元泽长";
		middlename = RandomStringUtils.random(1, middlename);
		String name = "君不见走马川行雪海边平沙莽莽黄入天轮台九月风夜吼一川碎石大如斗随风满地石乱走匈奴草黄马正肥金山西见烟尘飞汉家大将西出师将军金甲夜不脱半夜军行戈相拨风头如刀面如割马毛带雪汗气蒸五花连钱旋作冰幕中草檄砚水凝虏骑闻之应胆慑料知短兵不敢接车师西门伫献捷";
		name = RandomStringUtils.random(1, name);
		return surname + middlename + name;
	}

	// 返回一个手机号
	public static String getTelephone() {
		String telFirst[] = { "134", "135", "136", "137", "138", "139", "147", "148", "150", "151", "152", "157", "158",
				"159", "165", "172", "178", "182", "183", "184", "187", "188", "198", "130", "131", "132", "145", "146",
				"155", "156", "166", "171", "175", "176", "185", "186", "133", "149", "153", "173", "174", "177", "180",
				"181", "189", "191", "199", "170" };
		int first = randomNumber(0, telFirst.length - 1);
		String num = telFirst[first] + randomNumber(8);
		return num;
	}

	// 返回一个指定长度的数字
	public static String randomNumber(int len) {
		return RandomStringUtils.randomNumeric(len);
	}

	// 指定范围内随机数
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

	// md5加密
	public static String getMD5Str(String str) {
		byte[] digest = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			digest = md5.digest(str.getBytes("utf-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 16是表示转换为16进制数
		String md5Str = new BigInteger(1, digest).toString(16);
		return md5Str;
	}

}
