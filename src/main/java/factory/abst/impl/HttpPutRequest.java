package factory.abst.impl;

import factory.abst.AbstractHttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by zhuran on 2018/12/21 0021
 */
public class HttpPutRequest extends AbstractHttpRequest {
    private static final Logger logger = LoggerFactory.getLogger(HttpPutRequest.class);

    public String request(String url, String param, String... head) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut put = new HttpPut(url);
        CloseableHttpResponse response = null;
        try {
            for (String s : head) {
                put.setHeader("Content-Type", s);
            }
            put.setEntity(new ByteArrayEntity(param.getBytes("UTF-8")));
            response = httpClient.execute(put);
            HttpEntity entity = response.getEntity();
            result = entityToString(entity);
            logger.info("Put request succeeds[" + url + "]");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Put request failed[" + url + "]");
        } finally {
            try {
                httpClient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("Put to close the HttpClient connection[" + url + "]");
            }
        }
        return null;
    }
}
