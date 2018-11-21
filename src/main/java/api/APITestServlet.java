package api;

import date.CaseResult;
import date.ResultEnum;
import listen.ExtentTestNGIReporterListener;
import org.testng.IReporter;
import org.testng.TestNG;
import utils.JSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

/**
 * Created by zhuran on 2018/11/21 0021
 */
public class APITestServlet extends HttpServlet {
    private int pass;
    private int fail;
    private int skip;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        TestNG testNG = new TestNG();
        PrintWriter out = response.getWriter();
        try {
            testNG.setTestClasses(new Class[]{Class.forName(request.getParameter("case"))});
            testNG.run();
            Set<IReporter> reporterSet = testNG.getReporters();
            for (IReporter next : reporterSet) {
                if(next instanceof ExtentTestNGIReporterListener){
                   pass = ((ExtentTestNGIReporterListener) next).getContext().getPassedTests().size();
                   fail = ((ExtentTestNGIReporterListener) next).getContext().getFailedTests().size();
                   skip = ((ExtentTestNGIReporterListener) next).getContext().getSkippedTests().size();
                }
            }
            response.setContentType("text/html");
            out.println(JSONUtils.make(ResultEnum.SUCCESSCODE,ResultEnum.SUCCESS,CaseResult.makeCaseResult(pass,fail,skip)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println(JSONUtils.make(ResultEnum.ERRORCODE,ResultEnum.ERROR,e));
        }
    }
}
