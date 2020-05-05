package servlet;

import entity.Message;
import entity.Room;
import service.MessageService;
import service.serviceImpl.MessageServiceImpl;

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

@WebServlet(value = "/message/history")
public class MessageHistoryServlet extends HttpServlet {
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

        MessageService messageService = new MessageServiceImpl();

        int count = (int) application.getAttribute("nowCount");
        Room room = (Room) session.getAttribute("chatroom");    //获得当前的房间

        System.out.println("MessageHistory:nowCount==="+count);
        System.out.println("MessageHistory:room==="+room);

        if (count>=messageService.getTotalCount(null,room,null,"","")){
            //已经是全部的消息了。

            out.print("all");
            return;
        }

        List<Message> list = messageService.findMessage(room.getRoomid(),count+10);

        application.setAttribute("nowCount",count+10);
        application.setAttribute("messageInit",list);
        out.print(true);
    }
}
