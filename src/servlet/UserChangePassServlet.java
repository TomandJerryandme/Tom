package servlet;

import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/user/changepass")
public class UserChangePassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        String answer = request.getParameter("answer");

        if (answer.equals(user.getAnswer())){
            //回答正确，可以修改密码

        }else {
            out.print(false);
        }
    }
}
