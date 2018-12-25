package factory.impl;

import factory.IRequest;
import factory.IRequestFactory;

/**
 * Created by zhuran on 2018/11/23 0023
 */
public class RequestFactoryImpl implements IRequestFactory {
    private IRequest o;

    public IRequest create(String protocol, String type) {
        try {
            Class clazz = Class.forName("factory.abst.impl." + protocol + type + "Request");
            o = (IRequest) clazz.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return o;
    }
}
