package test;

import listen.ExtentTestNGIReporterListener;
import org.testng.annotations.*;

/*
* TestNG生命周期相关
* */
@Listeners(ExtentTestNGIReporterListener.class)
public class TestNGTest {

    @DataProvider(name = "test")
    public Object[][] dataprovider(){
        return new Object[][]{
                {"hello",1},{"world",2}
        };
    }
    @Test
    @Parameters({"first"})
    public void f1(String first) {
        System.out.println(first);
    }

    @Test(dataProvider = "test")
    public void f2(String s,int i) {
        System.out.println(s + " " + i);
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("before method");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("after method");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("before classs");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("after classs");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("before test");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("after test");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("before Suite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("after Suite");
    }

}