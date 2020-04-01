package servlet;

import entity.User;
import service.UserService;
import service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/user/change")
public class UserChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
        System.out.println("abc");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        UserService userService = new UserServiceImpl();
        //用户可以改变的：用户名，密码，密保问题与答案，头像，电话，邮箱。

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        String count = request.getParameter("count");

        int count1 = 0;


        //用户照片的修改,上传新的图片，用这个图片的路径覆盖原本的路径

        System.out.println(username);

        HttpSession session = request.getSession();

        User changeUser = new User();
        changeUser = (User)session.getAttribute("user");

        if (count!=null){
            count1 = Integer.parseInt(count);
            changeUser.setCount(count1);
        }

        if (username!=null){
            //对用户名进行修改
            changeUser.setUsername(username);
        }

        if (password!=null){
            //对用户密码进行修改
            changeUser.setPassword(password);
        }

        if (email!=null){
            //对邮箱进行修改
            changeUser.setEmail(email);
        }

        if (phone!=null){
            //对用户电话进行修改
            changeUser.setPhone(phone);
        }

        if(question!=null){
            if (answer==null){
                //要修改问题时，答案同时也要进行修改
                System.out.println("问题与答案要同时进行修改");
            }else{
                changeUser.setQuestion(question);
                changeUser.setAnswer(answer);
            }
        }
        if (userService.changeUserMessage(changeUser)){
            out.print(true);
        }
    }
}
