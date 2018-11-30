package test;

import data.ResultData;
import factory.IRequest;
import factory.impl.RequestFactoryImpl;
import listen.ExtentTestNGIReporterListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ExcelIteratorUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhuran on 2018/11/13 0013
 * Demo测试类
 * 运用框架编写用例
 */
@Listeners({ExtentTestNGIReporterListener.class})
public class InterfaceTestDemo {

    private HashMap<String, String> hashMap = new HashMap<String, String>();

    private final static Logger logger = LoggerFactory.getLogger(InterfaceTestDemo.class);

    @DataProvider(name = "test")
    public Iterator<Object[]> getData() throws IOException {
        return new ExcelIteratorUtils("casedata/ApiTestCase");
    }

    @BeforeTest
    public void check() throws Exception {
        String line;
        StringBuilder sb = new StringBuilder();
        Process process = Runtime.getRuntime().exec("ping 127.0.0.1");
        assert process != null;
        InputStream inputStream = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        if(!sb.toString().contains("TTL=")){
            throw new Exception("PING ERROR");
        }
    }

    @Test(dataProvider = "test")
    public void interfaceTest(Map<String, String> map) {
        IRequest iRequest = new RequestFactoryImpl().create(map.get("apiProtocol"), map.get("apiType"));
        if (map.get("apiData").contains("|")) {
            //TODO 如果传参存在入参所需标记 进行处理
            String s = ResultData.getResultString(map.get("apiData"), "(?<=\\|)(.+?)(?=\\|)");
            map.put("apiData", ResultData.replaceResultData(map.get("apiData"), "|" + s + "|", hashMap.get(s)));
        }
        String result = iRequest.request(map.get("apiUrl"), map.get("apiData"), map.get("apiHead").split(","));
        if (!map.get("getResult").equals("")) {
            //TODO 如果接口返回值需要用于下面的接口 进行处理
            hashMap = ResultData.getResultData(hashMap, map.get("getResult"), result, "(?<=\"" + map.get("getResult") + "\":\")(.+?)(?=\")");
        }
        Assert.assertEquals(result, map.get("Expectation"));
        logger.info("Expectation:" + map.get("Expectation"));
        logger.info("Actual:" + result);
    }

}
