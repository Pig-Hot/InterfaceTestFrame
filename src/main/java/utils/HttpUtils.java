package utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zhuran on 2018/11/13 0013
 * Http请求工具类
 */
public class HttpUtils {

    private HttpUtils(){
        throw new AssertionError();
    }

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String postRequestJson(String url, String jsonString) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new ByteArrayEntity(jsonString.getBytes("UTF-8")));
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

    public static String postRequestKV(String url, String KVString) {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            post.setEntity(new ByteArrayEntity(KVString.getBytes("UTF-8")));
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

    public static String getRequest(String url) {
        String result = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpCilent = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
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

    private static String entityToString(HttpEntity entity) throws IOException {
        String result = null;
        if (entity != null) {
            long lenth = entity.getContentLength();
            if (lenth != -1 && lenth < 2048) {
                result = EntityUtils.toString(entity, "UTF-8");
            } else {
                InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
                CharArrayBuffer buffer = new CharArrayBuffer(2048);
                char[] tmp = new char[1024];
                int l;
                while ((l = reader1.read(tmp)) != -1) {
                    buffer.append(tmp, 0, l);
                }
                result = buffer.toString();
            }
        }
        return result;
    }
}
