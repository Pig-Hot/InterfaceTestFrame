package date;

/**
 * Created by zhuran on 2018/11/21 0021
 */
public enum ResultEnum {

    SUCCESS("success"), ERROR("error"), SUCCESSCODE("0"), ERRORCODE("-1");

    private String status;

    ResultEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
