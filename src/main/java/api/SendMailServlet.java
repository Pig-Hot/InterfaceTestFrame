package api;

import utils.SendMailUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhuran on 2018/11/19 0019
 */
public class SendMailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        System.out.println(request.getParameter("address") + " " + request.getParameter("title") + " " + request.getParameter("context"));
        SendMailUtils.sendMail(request.getParameter("address"), request.getParameter("title"), request.getParameter("context"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
