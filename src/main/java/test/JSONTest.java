package test;

import boot.BootStrap;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelIteratorUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhuran on 2018/12/25 0025
 */
public class JSONTest {

    @DataProvider(name = "test")
    public Iterator<Object[]> getData() throws IOException {
        return new ExcelIteratorUtils("casedata/Test");
    }

    @Test(dataProvider = "test")
    public void add(Map<String, String> map) {
        BootStrap.start(map);
    }
}
