package servlet;

import entity.Message;
import entity.Room;
import entity.User;
import service.MessageService;
import service.RoomService;
import service.serviceImpl.MessageServiceImpl;
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
import java.util.List;
import java.util.Map;

@WebServlet(value = "/room/chatroom")
public class RoomInServlet extends HttpServlet {
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

        User user = (User) session.getAttribute("user");

        Map<User,Integer> userList = (Map<User, Integer>) application.getAttribute("onlineList");

        String roomid = request.getParameter("roomid");
        int id = 0;
        if (roomid!=null){
            id = Integer.parseInt(roomid);
        }

        userList.replace(user,id);
        application.setAttribute("onlineList",userList);
        RoomService roomService = new RoomServiceImpl();
        Room room = roomService.getRoom(id);
        session.setAttribute("chatroom",room);
        MessageService messageService = new MessageServiceImpl();
        List<Message> list = messageService.findMessage(id,user.getCount());
        session.setAttribute("messageInit",list);
        out.print(true);
    }
}
