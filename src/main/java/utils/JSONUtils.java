package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuran on 2018/11/13 0013
 * 处理各种JSON相关数据工具类
 */
public class JSONUtils {

    private JSONUtils() {
        throw new AssertionError();
    }

    private static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);

    public static JSONObject str2JsonObject(String s) {
        return JSON.parseObject(s);
    }

    public static String jsonObject2Str(JSONObject jo) {
        return JSONObject.toJSONString(jo);
    }

    public static JSONArray str2JsonArray(String s) {
        return JSON.parseArray(s);
    }

    public static List<Object> jsonArray2List(JSONArray ja) {
        return JSONObject.parseArray(ja.toJSONString(), Object.class);
    }

    public static JSONArray list2JsonArray(List<Object> list) {
        return JSONArray.parseArray(JSON.toJSONString(list));
    }

    public static List<Object> str2List(String s) {
        return JSONObject.parseArray(s, Object.class);
    }

    public static Map jsonObject2Map(JSONObject jo) {
        return JSONObject.toJavaObject(jo, Map.class);
    }

    public static JSONObject map2JsonObject(Map map) {
        return JSONObject.parseObject(JSON.toJSONString(map));
    }

    public static String make(Integer errNo, String msg, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errNo", errNo);
        jsonObject.put("errMsg", msg);
        jsonObject.put("data", data);
        return jsonObject.toJSONString();
    }

}
