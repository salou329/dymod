package util;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public final class ReadMongoUtil {

	public static String[] getvalue(String sheet, String objectname) {
		String jsonString="";
		String[] locator = new String[2];
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
				.url("http://"+Tools.getValue("mongodb")+"/dbApp/api/mongo/autotest/element/1?query= {\"sheet_objectName\":\""+sheet+"_"+objectname+"\"}")
				.method("POST", body)
				.build();
		try {
			Response response = client.newCall(request).execute();
			jsonString=response.body().string();
		} catch (IOException e) {
			System.out.println("读取mongodb出错");
		}
		locator[1] = Tools.sideFind(jsonString, "location\":\"", "\",\"value\"").trim();
		locator[0] = Tools.sideFind(jsonString, "value\":\"", "\"}],\"fields").trim();

		if (locator != null || (locator == null && locator.length != 0)) {
			System.out.println("对象名称<" + objectname + ">，定位方式：" + locator[1] + ",值：" + locator[0]);
		}
		return locator;

	}

}
