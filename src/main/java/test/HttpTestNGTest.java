package test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.SendMailUtils;

import java.io.IOException;

/**
 * Created by zhuran on 2018/11/30 0030
 * 脚本基础写法
 */
public class HttpTestNGTest {
    private int x;
    private int y;
    private boolean flag = false;

    //数据准备
    @BeforeTest
    public void before() {
        x = 1;
        y = 2;
    }

    //执行测试
    @Test
    public void test() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String param = "x=" + x + "&" + "y=" + y;
        HttpPost post = new HttpPost("http://localhost:8080/countSerlvet");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        CloseableHttpResponse response = null;
        String result = null;
        try {
            post.setEntity(new ByteArrayEntity(param.getBytes("UTF-8")));
            response = httpClient.execute(post);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                assert response != null;
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(result);
        Assert.assertEquals(result, "3");
        flag = true;
    }

    //发送报告之类的后续操作
    @AfterTest
    public void report() {
        SendMailUtils.sendMail("zhuran@flashhold.com", "测试", this.getClass().getName() + "：" + flag);
    }
}
