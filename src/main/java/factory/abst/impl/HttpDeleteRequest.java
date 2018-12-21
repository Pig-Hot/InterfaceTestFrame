package factory.abst.impl;

import factory.abst.AbstractHttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by zhuran on 2018/12/21 0021
 */
public class HttpDeleteRequest extends AbstractHttpRequest {
    private static final Logger logger = LoggerFactory.getLogger(HttpDeleteRequest.class);

    public String request(String url, String param, String... head) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete delete = new HttpDelete(url);
        CloseableHttpResponse response = null;
        try {
            for (String s : head) {
                delete.setHeader("Content-Type", s);
            }
            response = httpClient.execute(delete);
            HttpEntity entity = response.getEntity();
            result = entityToString(entity);
            logger.info("Delete request succeeds[" + url + "]");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Delete request failed[" + url + "]");
        } finally {
            try {
                httpClient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("Failed to close the HttpClient connection[" + url + "]");
            }
        }
        return null;
    }
}
