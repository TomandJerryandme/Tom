package servlet;

import entity.Message;
import entity.User;
import service.MessageService;
import service.serviceImpl.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/user/transfor")
public class UserTransforServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //将跳转到user私聊页面

        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");
        //获取out输出对象
        PrintWriter out = response.getWriter();
        //获取session对象
        HttpSession session = request.getSession();
        //设置字符编码
        request.setCharacterEncoding("utf-8");

        String messageid = request.getParameter("messageid");
        int messageId = 0;
        if (messageid!=null){
            messageId = Integer.parseInt(messageid);
        }
        MessageService messageService = new MessageServiceImpl();
        Message message = messageService.findMessageByID(messageId);

        User objectUser = message.getUser();
        session.setAttribute("objectUser",objectUser);

        out.print(true);
    }
}
