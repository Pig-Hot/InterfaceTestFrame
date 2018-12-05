package test;

import factory.IRequest;
import factory.impl.RequestFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelIteratorUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhuran on 2018/12/5 0005
 */
public class WCSQueryInterfaceErrorTestCase {
    private final static Logger logger = LoggerFactory.getLogger(WCSQueryInterfaceErrorTestCase.class);


    @DataProvider(name = "queryTestCase")
    public Iterator<Object[]> getData() throws IOException {
        return new ExcelIteratorUtils("casedata/WCSQueryInterfaceError");
    }

    @Test(dataProvider = "queryTestCase")
    public void queryTestSctrip(Map<String, String> map) {
        IRequest iRequest = new RequestFactoryImpl().create(map.get("apiProtocol"), map.get("apiType"));
        String result = iRequest.request(map.get("apiUrl"), map.get("apiData"), map.get("apiHead").split(","));
        if (!map.get("Expectation").equals("")) {
            Assert.assertEquals(result, map.get("Expectation"));
            logger.info("Expectation:" + map.get("Expectation"));
            logger.info("Actual:" + result);
        }
    }
}
