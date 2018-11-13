package test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.HttpUtils;
import utils.JDBCUtils;
import utils.JSONUtils;
import utils.TimeUtils;

/**
 * Created by zhuran on 2018/11/13 0013
 */
public class UtilsMethodTest {

    @BeforeTest
    public void testTimeUtils() {
        System.out.println(TimeUtils.getNowTime());
    }

    @Test
    public void testJDBCUtils() {
        System.out.println(JDBCUtils.getInstance("root", "", "jdbc:mysql://192.168.21.238:3306?useSSL=false&allowMultiQueries=true").query("select * from mysql.user"));
    }

    @Test
    public void testJsonUtils() {
        System.out.println((JSONUtils.str2List("[1,2,3]")).get(0));
        Assert.assertEquals(1, 1);
    }

    @AfterTest
    public void testHttpUtils() {
        System.out.println(HttpUtils.getRequest("https://www.baidu.com/s?wd=test"));
    }

}
