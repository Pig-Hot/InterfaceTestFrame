package api;

import org.testng.TestNG;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhuran on 2018/11/21 0021
 */
public class APITestServlet extends HttpServlet {
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
            response.setContentType("text/html");
            out.println("success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println(e);
        }
    }
}
