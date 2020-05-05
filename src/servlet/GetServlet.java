package servlet;

import service.UserService;
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

@WebServlet(value = "/temp/getUsername")
public class GetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");
        //获取out输出对象
        PrintWriter out = response.getWriter();
        //获取session对象
        HttpSession session = request.getSession();
        ServletContext application = request.getServletContext();
        //设置字符编码
        request.setCharacterEncoding("utf-8");

        String userid = request.getParameter("userid");
        int id = 0;
        if (userid!=null){
            id = Integer.parseInt(userid);
        }

        UserService userService = new UserServiceImpl();

        String username = userService.getUser(id).getUsername();

        System.out.println("getServlet:\tusername = "+username);

        out.print(username+"");
    }
}
