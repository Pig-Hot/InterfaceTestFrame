package date;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by zhuran on 2018/11/21 0021
 */
public class CaseResult {

    public static JSONObject makeCaseResult(int pass, int fail, int skip){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pass", pass);
        jsonObject.put("fail",fail);
        jsonObject.put("skip", skip);
        return jsonObject;
    }
}
