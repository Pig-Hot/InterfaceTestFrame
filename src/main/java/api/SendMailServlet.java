package api;

import date.ResultEnum;
import utils.JSONUtils;
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
        PrintWriter out = response.getWriter();
        try {
            SendMailUtils.sendMail(request.getParameter("address"), request.getParameter("title"), request.getParameter("context"));
            response.setContentType("text/html");
            out.println(JSONUtils.make(ResultEnum.SUCCESSCODE.getStatus(),ResultEnum.SUCCESS.getStatus(),""));
        } catch (Exception e) {
            e.printStackTrace();
            out.println(JSONUtils.make(ResultEnum.ERRORCODE.getStatus(),ResultEnum.ERROR.getStatus(),e));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
