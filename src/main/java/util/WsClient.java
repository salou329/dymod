package util;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WsClient extends WebSocketClient {
	public String msg="";
    public WsClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake arg0) {
        System.out.println("握手成功");

    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        System.out.println("连接关闭");
    }

    @Override
    public void onError(Exception arg0) {
        System.out.println("发生错误");
    }

    @Override
    public void onMessage(String arg0) {
        System.out.println("收到消息" + arg0);
        msg=arg0;
    }
}
