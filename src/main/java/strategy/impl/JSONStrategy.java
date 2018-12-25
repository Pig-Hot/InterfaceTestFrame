package strategy.impl;

import factory.IRequest;
import factory.impl.RequestFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import strategy.IStrategy;
import utils.JSONUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuran on 2018/12/25 0025
 */
public class JSONStrategy implements IStrategy {

    private static HashMap<String, Object> hashMap = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(JSONStrategy.class);

    @Override
    public void exec(Map<String, String> map) {
        if (map.get("apiData").contains("$")) {
            map.put("apiData", JSONUtils.setJsonStr(map.get("apiData"), hashMap));
        }
        IRequest iRequest = new RequestFactoryImpl().create(map.get("apiProtocol"), map.get("apiType"));
        String result = iRequest.request(map.get("apiUrl"), map.get("apiData"), map.get("apiHead").split(","));
        if (!map.get("getResult").equals("")) {
            Map<String, Object> soMap = JSONUtils.getResult(map.get("getResult"));
            for (String s : soMap.keySet()) {
                soMap.put(s, JSONUtils.get(String.valueOf(soMap.get(s)), result));
            }
            hashMap.putAll(soMap);
        }
        if (!map.get("Expectation").equals("")) {
            logger.info("Expection: " + map.get("Expectation"));
            logger.info("Actual : " + result);
            Assert.assertTrue(assertResult(result,map.get("Expectation")));
        }
    }

    @Override
    public boolean assertResult(String result,String check) {
        return result.equals(check);
    }
}
