package servlet;

import entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/user/logout")
public class UserLoginOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        PrintWriter out = response.getWriter();
        ServletContext application = this.getServletContext();


        List<User> userList = (List<User>) application.getAttribute("onlineUserList");

        boolean flag = false;    //表示用户登录

        for (User user1 : userList) {
            if (user.getUsername().equals(user1.getUsername())){
                //找到了要退出登录的用户
                flag = true;
            }
        }

        if (flag){
            userList.remove(user);
        }
        application.setAttribute("onlineUserList",userList);

        request.getRequestDispatcher("/user_login.jsp").forward(request,response);
    }
}
