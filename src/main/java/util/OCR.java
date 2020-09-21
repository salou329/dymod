package util;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.activation.MimetypesFileTypeMap;

public class OCR {
	/**
	 * 识别图片
	 * 
	 * @return
	 * 
	 */
	public static String ocr(String pic) {
		String url = "https://zhcn.109876543210.com/api/1.50/?action=upload&language=zhcn,en,&output_format=txt&kernel=2.0&layoutl=1&color=1&direction=1&straightening=1&line=1&pay=0&file_password=";
		String fileName = pic;
		Map<String, String> textMap = new HashMap<String, String>();
		// 普通参数：可以设置多个input的name，value
		textMap.put("name", "ocr.jpg");
		// 文件：设置file的name，路径
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("file", fileName);
		String contentType = "";// image/png
		String id = formUpload(url, textMap, fileMap, contentType);
		System.out.println("开始识别图片:" + pic);
		// System.out.println(id);
		id = Tools.sideFind(id, "id\":\"", "\"}");
		String txtString = "";
		System.out.println("正在识别，请稍后");

		for (int i = 0;; i++) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			txtString = HttpUtil.doGet("https://zhcn.109876543210.com/api/1.0/?action=result&from=109876543210.com&id="
					+ id + "&ajaxtimestamp=1577344700163");
			if (txtString.contains("txt") || i == 50) {
				// 转换成功或者50秒没转换成功，退出。
				break;
			}
			if (txtString.contains("fu_fei")) {
				System.out.println("识别失败，失败原因：免费额度已用完.");
				break;
			}
		}
		// System.out.println(txtString);
		txtString = Tools.sideFind(txtString, "content\":\"", "\",\"pay");
		String newurl = txtString.replace("\\/", "/");
		String result = readFileByUrl(newurl);
		System.out.println("识别结果：" + result);

		return result;

	}

	/**
	 * 上传图片
	 * 
	 * @param urlStr
	 * @param textMap
	 * @param fileMap
	 * @param contentType 没有传入文件类型默认采用application/octet-stream
	 *                    contentType非空采用filename匹配默认的图片类型
	 * @return 返回response数据
	 */
	@SuppressWarnings("rawtypes")
	public static String formUpload(String urlStr, Map<String, String> textMap, Map<String, String> fileMap,
			String contentType) {
		String res = "";
		HttpURLConnection conn = null;
		// boundary就是request头和上传文件内容的分隔符
		String BOUNDARY = "---------------------------123821742118716";
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// text
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
					// System.out.println(inputName+","+inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}
			// file
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();

					// 没有传入文件类型，同时根据文件获取不到类型，默认采用application/octet-stream
					contentType = new MimetypesFileTypeMap().getContentType(file);
					// contentType非空采用filename匹配默认的图片类型
					if (!"".equals(contentType)) {
						if (filename.endsWith(".png")) {
							contentType = "image/png";
						} else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")
								|| filename.endsWith(".jpe")) {
							contentType = "image/jpeg";
						} else if (filename.endsWith(".gif")) {
							contentType = "image/gif";
						} else if (filename.endsWith(".ico")) {
							contentType = "image/image/x-icon";
						}
					}
					if (contentType == null || "".equals(contentType)) {
						contentType = "application/octet-stream";
					}
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					// System.out.println(inputName+","+filename);

					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
					out.write(strBuf.toString().getBytes());
					DataInputStream in = new DataInputStream(new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
			// 读取返回数据
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}

	/**
	 * 从输入流中获取字符串
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static String readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return new String(bos.toByteArray(), "utf-8");
	}

	public static String readFileByUrl(String urlStr) {
		String res = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置超时间为3秒
			conn.setConnectTimeout(3 * 1000);
			// 防止屏蔽程序抓取而返回403错误
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// 得到输入流
			InputStream inputStream = conn.getInputStream();
			res = readInputStream(inputStream);
		} catch (Exception e) {

		}
		return res;
	}
}
