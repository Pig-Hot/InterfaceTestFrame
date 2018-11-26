package data;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuran on 2018/11/23 0023
 */
public class ResultData {

    public static HashMap<String, String> getResultData(HashMap<String, String> map, String name, String data, String rgex) {
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(data);
        while (m.find()) {
            map.put(name, m.group(1));
        }
        return map;
    }

    public static String getResultString(String data, String rgex) {
        String s = null;
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(data);
        while (m.find()) {
            s = m.group(1);
        }
        return s;
    }

    public static String replaceResultData(String s,String oldString,String newString){
        s = s.replace(oldString,newString);
        return s;
    }

    public static void main(String[] args) {
//        String str ="date=1,${test}=3";
//        String rgex = "(?<=date=)(.+?)(?=,)";
//        System.out.println(getResultData(new HashMap<String, String>(), "data",str, rgex).get("data"));
//        System.out.println(replaceResultData(str,"${test}","1"));
        String s = ResultData.getResultString("x=|data|&y=2","(?<=\\|)(.+?)(?=\\|)");
        System.out.println(s);
    }
}
