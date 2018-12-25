package boot;

import strategy.IStrategy;
import strategy.StrategyFactory;
import verify.IVerify;
import verify.VerifyFactory;

import java.util.Map;

/**
 * Created by zhuran on 2018/12/25 0025
 */
public class BootStrap {

    public static void start(Map<String, String> map) {
        IStrategy strategy = StrategyFactory.createStrategy(map.get("strategyType"));
        strategy.exec(map);
        if (!map.get("optionType").equals("")) {
            IVerify verify = VerifyFactory.createVerify(map.get("optionType"));
            verify.verify(map.get("optionInfo"));
        }
    }
}
