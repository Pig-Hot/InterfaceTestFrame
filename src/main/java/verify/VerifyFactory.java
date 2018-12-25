package verify;

/**
 * Created by zhuran on 2018/12/25 0025
 */
public class VerifyFactory {

    private static IVerify o;

    public static IVerify createVerify(String type) {
        Class clazz;
        try {
            clazz = Class.forName("verify.impl." + type + "Impl");
            o = (IVerify) clazz.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return o;
    }
}
