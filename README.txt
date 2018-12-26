api:接口类(用于给外部提供接口)
boot:启动类(再测试case中直接调用即可)
data:数据类(基础数据以及生成数据方法类)
factory:请求工厂(负责执行HTTP相关请求)
listen:监听类(重试,报表等监听类)
strategy:数据处理策略(用于处理业务过程中的数据)
test:接口用例类(接口用例脚本类)
utils:常用工具类(操作数据库,发送邮件,处理JSON相关以及时间等方法类)
verify:检验处理策略(用于处理测试后的额外操作)
test-output/test-report:结果(生成的测试报表)
logs:日志信息(case运行生成)
testcase:接口用例XML文件(通过TestNG执行)

对于使用人员只需编写Excel文件以及一个测试类即可,例如:
    public class Test {

        @DataProvider(name = "test")  //给你的数据命名
        public Iterator<Object[]> getData() throws IOException {
            return new ExcelIteratorUtils("casedata/Test"); //你的Excel文件路径
        }

        @Test(dataProvider = "test")    //使用你的命名数据
        public void add(Map<String, String> map) {
            BootStrap.start(map);   //调用启动类start即可
        }
    }

Excel格式介绍
    apiCaseName:接口用例名称[case1]
    apiUrl:接口URL[http://localhost:1234/add]
    apiProtocol:接口协议[Http]
    apiType:接口类型[Post]
    apiHead:接口请求头部[application/json]
    apiData:接口请求实体[{"x":"1","y":"2"}]
    getResult:需要从本次接口返回值中保存的信息[{"case1data":"data"}]
    Expectation:期望结果[{"errNo":0,"data":"3","errMsg":"0"}]
    strategyType:请求以及返回的数据类型[JSON]
    optionType:请求后额外的校验操作类型[SQL]
    optionInfo:额外操作具体信息[{"user":"root","pw":"","url":"jdbc:mysql://192.168.21.149:3306?useSSL=false&allowMultiQueri
                                es=true","sql":"select state from walle_core.buckets where bucketID = 1","query":"state",
                                "exception":"STATIC"}]
                            
特别说明:
    1.多个apiHead用','隔开
    2.对于JSON类型的返回值,getResult中,可以通过.的方式获取具体值,例如返回信息为{"a":"1","b":{"c":2}},你可通过b.c可以获取到2这个值
      如果结果是一个数组在最后加上|以及下标,然后会将值保存在一个Map中,对于{"data":"b.c"}来说,保存的Map信息就是{data=2},注意key值不能
      重复,除非上一个数据可以被覆盖了
    3.在apiData中如果需要用到之前保存的Map信息,使用$key$的方式,用第二点为例就是$data$,那么在用例运行过程中,会被替换成2
    4.额外SQL操作中'sql'是你的查询sql,'query'是你要检验的内容,'exception'是你期望的结果

扩展:
    框架暂时只支持strategyType为JSON,optionType为SQL的操作,如果不符合你的需求
    1.通过在strategy自己实现IStrategy接口完成其他数据处理
    2.通过在verify自己实现IVerify接口完成其他检验处理
    3.让框架开发者添加对应需求