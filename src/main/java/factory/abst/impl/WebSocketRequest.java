package factory.abst.impl;

import factory.IRequest;
import factory.abst.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;

/**
 * Created by zhuran on 2018/11/23 0023
 */
public class WebSocketRequest implements IRequest {

    private static Session session;

    private static final Logger logger = LoggerFactory.getLogger(WebSocketRequest.class);

    public String request(String url, String param, String... var) {
        WebSocketRequest client = new WebSocketRequest();
        client.start(url);
        try {
            session.getBasicRemote().sendText(param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void start(String url) {
        WebSocketContainer container = null;
        try {
            container = ContainerProvider.getWebSocketContainer();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(String.valueOf(ex));
        }
        try {
            URI r = URI.create(url);
            assert container != null;
            session = container.connectToServer(Client.class, r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebSocketRequest webSocketRequest = new WebSocketRequest();
        webSocketRequest.request("ws://192.168.25.163:8080/websocket", "12345");
    }
}
