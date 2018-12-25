package test;

import boot.BootStrap;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelIteratorUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhuran on 2018/12/5 0005
 */
@Test
public class WCSQueryInterfaceTestCase {

    @DataProvider(name = "queryTestCase")
    public Iterator<Object[]> getData() throws IOException {
        return new ExcelIteratorUtils("casedata/WCSQueryInterface");
    }

    @Test(dataProvider = "queryTestCase")
    public void queryTestSctrip(Map<String, String> map) {
        BootStrap.start(map);
    }

}
