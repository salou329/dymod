package util;

import java.net.URI;
import org.java_websocket.WebSocket;

public final class WS {
	public static WsClient myClient = null;

	public static void ws() {
		try {
			//System.out.println("444");
			myClient = new WsClient(new URI("ws://98785176.com/income/suning?deviceNo=13218003466"));
			myClient.connect();
			// 判断是否连接成功，未成功后面发送消息时会报错
			while (!myClient.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
				System.out.println("连接中···请稍后");
				Thread.sleep(1000);
				}
			myClient.send("{\"operator\":\"ping\"} ");
			System.out.println(myClient.msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
