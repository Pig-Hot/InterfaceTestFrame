package test;

import utils.SendMailUtils;

import java.io.*;

/**
 * Created by zhuran on 2018/12/6 0006
 */
public class Test {
    public static void main(String[] args) throws IOException {
        String sb = "";
        try {
            InputStream inputStream = new FileInputStream(new File("output/testngReports/jenkins-report.html"));
            byte[] b = new byte[102400];
            int i = 0;
            int index = 0;
            //循环读取每个数据
            while((i=inputStream.read())!=-1){//把读取的数据放到i中
                b[index]=(byte) i;
                index++;
            }
            sb = new String(b);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SendMailUtils.sendMail("zhuran@flashhold.com","接口自动化测试结果",sb,"test-report/index.html");
    }
}
