package date;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuran on 2018/11/23 0023
 */
public class ResultDate {

    public static HashMap<String, String> getResultDate(HashMap<String, String> map, String name, String date, String rgex) {
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(date);
        while (m.find()) {
            map.put(name, m.group(1));
        }
        return map;
    }

    public static String replaceResultDate(String s,String oldString,String newString){
        return s.replace(oldString,newString);
    }

    public static void main(String[] args) {
        String str ="date=1,${test}=3";
        String rgex = "(?<=date=)(.+?)(?=,)";
        System.out.println(getResultDate(new HashMap<String, String>(),"date",str, rgex).get("date"));
        System.out.println(replaceResultDate(str,"${test}","1"));
    }
}
