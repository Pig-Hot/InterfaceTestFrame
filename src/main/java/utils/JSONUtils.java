package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
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

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getResult(String jsonString) {
        return JSON.parseObject(jsonString, Map.class);
    }

    @SuppressWarnings("unchecked")
    public static Object get(String str, String jsonString) {
        Map<?, ?> map = JSON.parseObject(jsonString, Map.class);
        Object object = null;
        int no = -1;
        if (str.contains("|")) {
            String[] as = str.split("\\|");
            str = as[0];
            no = Integer.parseInt(as[1]);
        }
        String[] arrs = str.split("\\.");
        for (int i = 0; i < arrs.length; i++) {
            if (i == 0) {
                object = handle(map, arrs[i]);
            } else {
                if (object instanceof Map) {
                    object = handle((Map<?, ?>) object, arrs[i]);
                } else if (object instanceof List) {
                    object = handle((List<Map<?, ?>>) object, arrs[i]);
                }
            }
        }
        if (no != -1 && object instanceof List && no < ((List) object).size()) {
            object = ((List) object).get(no);
        }
        return object;
    }

    //解析map类型的对象
    private static Object handle(Map<?, ?> map, String key) {
        return map.get(key);
    }

    // 解析list类型的对象
    @SuppressWarnings("unchecked")
    private static Object handle(List<Map<?, ?>> listMap, String key) {
        List<Map<String, Object>> listMaps = new ArrayList<>();
        List<Object> lists = new ArrayList<>();
        listMap.stream().filter(e -> null != e.get(key)).forEach(e -> {
            if (!(e.get(key) instanceof Map<?, ?>) && !(e.get(key) instanceof List<?>)) {
                lists.add(e.get(key));
            } else {
                listMaps.addAll((Collection<? extends Map<String, Object>>) e.get(key));
            }
        });
        return lists.size() > 0 ? lists : listMaps;
    }

    public static String setJsonStr(String jsonString, Map<String, Object> map) {
        String[] arrs = jsonString.split("\\$");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrs.length; i++) {
            if (i % 2 == 1) {
                arrs[i] = String.valueOf(map.get(arrs[i]));
            }
        }
        for (String arr : arrs) {
            sb.append(arr);
        }
        return sb.toString();
    }

    public static String make(String errNo, String msg, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errNo", errNo);
        jsonObject.put("errMsg", msg);
        jsonObject.put("data", data);
        return jsonObject.toJSONString();
    }

}
