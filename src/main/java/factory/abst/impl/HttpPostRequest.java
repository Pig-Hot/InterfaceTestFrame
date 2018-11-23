package factory.abst.impl;

import factory.abst.AbstractHttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by zhuran on 2018/11/23 0023
 */
public class HttpPostRequest extends AbstractHttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(HttpPostRequest.class);

    public String request(String url, String param, String... head) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            for (String s : head) {
                post.setHeader("Content-Type", s);
            }
            post.setEntity(new ByteArrayEntity(param.getBytes("UTF-8")));
            response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            result = entityToString(entity);
            logger.info("POST request succeeds[" + url + "]");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("POST request failed[" + url + "]");
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

