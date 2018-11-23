package factory;

/**
 * Created by zhuran on 2018/11/23 0023
 */
public interface IRequestFactory {
    IRequest create(String protocol,String type);
}
