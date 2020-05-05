package servlet;

import entity.Message;
import entity.Room;
import entity.User;
import service.MessageService;
import service.MessageTypeService;
import service.RoomService;
import service.serviceImpl.MessageServiceImpl;
import service.serviceImpl.MessageTypeServiceImpl;
import service.serviceImpl.RoomServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/user/release")
public class UserReleaseServlet extends HttpServlet {
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

        //得到请求后，在相应的房间填入请求信息
        MessageService messageService = new MessageServiceImpl();
        RoomService roomService = new RoomServiceImpl();
        MessageTypeService messageTypeService = new MessageTypeServiceImpl();


        User user = (User) session.getAttribute("user");
        //找到该房间
        Room room = roomService.getRoom(4,user.getUserid());

        Message message = new Message();

        message.setTime(new Date());
        message.setContent(user.getUsername()+"请求解除禁言");
        message.setMessageType(messageTypeService.getMessageType(1));
        message.setRoom(room);
        message.setUser(user);
        messageService.addMessage(message);
        Message temp = messageService.findMessageManager(user.getUserid());
        List<Message> messageList = (List<Message>) application.getAttribute("releaseList");
        messageList.add(temp);
        application.setAttribute("releaseList",messageList);
        System.out.println("ReleaseServlet=======请求成功");
        out.print("true");
    }
}
