package strategy;

/**
 * Created by zhuran on 2018/12/25 0025
 */
public class StrategyFactory {

    private static IStrategy o;

    public static IStrategy createStrategy(String type) {
        Class clazz;
        try {
            clazz = Class.forName("strategy.impl." + type + "Strategy");
            o = (IStrategy) clazz.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            try {
                clazz = Class.forName("strategy.impl.DefaultStrategy");
                o = (IStrategy) clazz.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
                e1.printStackTrace();
            }
        }
        return o;
    }
}
