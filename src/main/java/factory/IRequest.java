package factory;

/**
 * Created by zhuran on 2018/11/23 0023
 */
public interface IRequest {
    String request(String url, String param, String... var);
}
