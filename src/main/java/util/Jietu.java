package util;


public final class Jietu {
 
	public static void AppSaveToSelf() {
		Tools.ADB("adb shell screencap -p /sdcard/"+System.currentTimeMillis()+Tools.randomNumber(5)+".png");
	}
	
	public static String WebSaveToComputerForMAC() {
		String name=System.currentTimeMillis()+Tools.randomNumber(5);
		Tools.cmdLine("screencapture -m jietu/"+name+".png");
		return "jietu/"+name+".png";
	}
	
	public static String WebSaveToComputerForWin() {
		String name=System.currentTimeMillis()+Tools.randomNumber(5);
		Tools.cmdLine("screencapture -m jietu/"+name+".png");
		return "jietu/"+name+".png";
	}

}
