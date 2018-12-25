package strategy;

import java.util.Map;

/**
 * Created by zhuran on 2018/12/25 0025
 */
public interface IStrategy {

    void exec(Map<String,String> map);

    boolean assertResult(String result,String check);

}
