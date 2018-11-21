api:接口类(用于给外部提供接口)
date:数据类(基础数据以及生成数据方法类)
listen:监听类(重试,报表等监听类)
test:接口用例类(接口用例脚本类)
utils:常用工具类(Http请求,操作数据库,发送邮件,处理JSON相关以及时间等方法类)
test-output/test-report:结果(生成的测试报表)
logs:日志信息(case运行生成)
testcase:接口用例XML文件(通过TestNG执行)

测试执行的流程(代码层)：
    1.测试执行的入口为TestNG的XML文件/执行@TestNG注解的类或者通过api包提供的接口/前端页面(暂时未开发)等
    2.获取测试用例的脚本，测试用例执行分为测试前(@BeforeTest)，测试中(@Test)，测试后(@AfterTest)
    3.@BeforeTest：获取/解析测试数据，接口关联参数化，Cookie装载等
    4.@Test：初始化httpclient对象，执行请求，获取响应并转换成String等
    5.@AfterTest：jsonpath检查点，对于接口正确/错误请求的逻辑处理
    6.生成在线ExtentReports报告/日志