package test;

import org.testng.annotations.*;

public class NewTest {

    @Test
    public void f1() {
        System.out.println("test1");
    }

    @Test
    public void f2() {
        System.out.println("test2");
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