package servlet;

import entity.User;
import service.CountService;
import service.UserService;
import service.serviceImpl.CountServiceImpl;
import service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/user/forbidden")
public class ManagerForbidServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");
        //获取out输出对象
        PrintWriter out = response.getWriter();
        //获取session对象
        HttpSession session = request.getSession();
        //设置字符编码
        request.setCharacterEncoding("utf-8");
        //获取servletContext对象
        ServletContext application = request.getServletContext();

        //对用户进行禁言的servlet

        String id = request.getParameter("userid");
        int userid = 0;
        if (id!=null){
            userid = Integer.parseInt(id);
        }

        UserService userService = new UserServiceImpl();
        User user = userService.getUser(userid);

        CountService countService = new CountServiceImpl();

        countService.updateUser(user,10);

        out.print("true");

    }
}
