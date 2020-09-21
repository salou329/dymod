package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author 史文彬 HttpClient工具类
 */
public final class HttpUtil {

	/**
	 * get请求
	 *
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		HashMap<String, String> header = new HashMap<>();
		return doGet(url, header);
	}
	public static String doGet(String url, Map<?, ?> headers) {

		try {
			HttpClient client = HttpClientBuilder.create().build();// 获取DefaultHttpClient请求
			// 发送get请求
			HttpGet request = new HttpGet(url);
			for (Object key : headers.keySet()) {
				String value = (String) headers.get(key);
				request.setHeader(key.toString(), value);
			}
			HttpResponse response = client.execute(request);

			/**
			 * 请求发送成功，并得到响应*
			 */
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/**
				 * 读取服务器返回过来的json字符串数据*
				 */
				String strResult = EntityUtils.toString(response.getEntity());
				return strResult;
			}
		} catch (IOException e) {
			if (e.toString().contains("sohu")) {
				System.err.println("网速过慢或不通，请检查网络！");
			} else {
				e.printStackTrace();
			}
		}

		return null;

	}
	/**
	 * post请求(用于key-value格式的参数)
	 *
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String doPost(String url, Map<?, ?> params, Map<?, ?> headers) {
		BufferedReader in = null;
		try {
			// 定义HttpClient
			HttpClient client = HttpClientBuilder.create().build();// 获取DefaultHttpClient请求
			// 实例化HTTP方法
			HttpPost request = new HttpPost();
			request.setURI(new URI(url));
			for (Object key : headers.keySet()) {
				String value = (String) headers.get(key);
				request.setHeader(key.toString(), value);
			}

			// 设置参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Iterator<?> iter = params.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String value = String.valueOf(params.get(name));
				nvps.add(new BasicNameValuePair(name, value));

				// System.out.println(name +"-"+value);
			}
			request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) { // 请求成功
				in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				while ((line = in.readLine()) != null) {
					sb.append(line + NL);
				}

				in.close();
				return sb.toString();
			} else { //
				// System.out.println("状态码：" + code);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public static String doPost(String url, Map<?, ?> params) {
		HashMap<String, String> header = new HashMap<>();
		return doPost(url, params, header);
	}

	/**
	 * post请求（用于请求json格式的参数）
	 *
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	public static String doPost(String url, String params, Map<?, ?> headers) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);// 创建httpPost
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		for (Object key : headers.keySet()) {
			String value = (String) headers.get(key);
			httpPost.setHeader(key.toString(), value);
		}
		String charSet = "UTF-8";
		StringEntity entity = new StringEntity(params, charSet);
		httpPost.setEntity(entity);
		CloseableHttpResponse response = null;

		try {

			response = httpclient.execute(httpPost);
			StatusLine status = response.getStatusLine();
			int state = status.getStatusCode();
			if (state == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				String jsonString = EntityUtils.toString(responseEntity);
				System.out.println(jsonString);
				return jsonString;
			} else {
				System.out.println("返回" + state + "(" + url + ")");
			}
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String doPost(String url, String params) {
		try {
			return doPost(url, params, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * 获取json值
	 * 
	 * @param json
	 * @param parm
	 * @return
	 */
	public static String GetJsonValue(String json, String... parm) {
		JSONObject jsonObject = JSON.parseObject(json);
		try {
			for (String value : parm) {
				json = jsonObject.get(value).toString();
				jsonObject = JSON.parseObject(json);
			}
		} catch (Exception e) {
		}
		return json;
	}
	
}
