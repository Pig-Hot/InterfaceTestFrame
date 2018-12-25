package strategy.impl;

import factory.IRequest;
import factory.impl.RequestFactoryImpl;
import org.testng.Assert;
import strategy.IStrategy;

import java.util.Map;

/**
 * Created by zhuran on 2018/12/25 0025
 */
public class DefaultStrategy implements IStrategy {

    @Override
    public void exec(Map<String, String> map) {
        IRequest iRequest = new RequestFactoryImpl().create(map.get("apiProtocol"), map.get("apiType"));
        String result = iRequest.request(map.get("apiUrl"), map.get("apiData"), map.get("apiHead").split(","));
        if (!map.get("Expectation").equals("")) {
            Assert.assertTrue(assertResult(result,map.get("Expectation")));
        }
    }

    @Override
    public boolean assertResult(String result,String check) {
        return result.equals(check);
    }
}
