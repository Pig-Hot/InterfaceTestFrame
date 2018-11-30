package test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by zhuran on 2018/11/30 0030
 * HTTPClient操作相关
 */
public class HttpClientTest {
    public void httpGetRequest(String url) throws IOException {
        //创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个Responese对象
        CloseableHttpResponse response = null;
        //创建一个Request对象
        HttpGet get = new HttpGet(url);
       /*
         POST请求
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new ByteArrayEntity(param.getBytes("UTF-8")));
        */
        //使用HttpClient执行Request拿到response
        response = httpClient.execute(get);
        //处理response
        String result = EntityUtils.toString(response.getEntity(),"UTF-8");
        System.out.println(result);
        //关闭
        httpClient.close();
        response.close();
    }

    public static void main(String[] args) {
        HttpClientTest httpClientTest = new HttpClientTest();
        try {
            httpClientTest.httpGetRequest("https://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
