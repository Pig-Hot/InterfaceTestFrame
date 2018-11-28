package factory.abst.impl;

import factory.abst.AbstractHttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by zhuran on 2018/11/23 0023
 */
public class HttpGetRequest extends AbstractHttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(HttpGetRequest.class);

    public String request(String url, String param, String... head) {
        String result = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpCilent = HttpClients.createDefault();
        url = url + '?' + param;
        HttpGet httpGet = new HttpGet(url);
        try {
            for (String s : head) {
                httpGet.setHeader("Content-Type", s);
            }
            response = httpCilent.execute(httpGet);
            result = entityToString(response.getEntity());
            logger.info("GET request succeeds[" + url + "]");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("GET request failed[" + url + "]");
        } finally {
            try {
                httpCilent.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("GET to close the HttpClient connection[" + url + "]");
            }
        }
        return null;
    }
}
