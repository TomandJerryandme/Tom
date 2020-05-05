package servlet;

import entity.Message;
import entity.Room;
import entity.User;
import service.CountService;
import service.MessageService;
import service.RoomService;
import service.serviceImpl.CountServiceImpl;
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

        //在该servlet中，来与服务器端程序建立连接

        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");
        //获取out输出对象
        PrintWriter out = response.getWriter();
        //获取session对象
        HttpSession session = request.getSession();
        ServletContext application = request.getServletContext();
        //设置字符编码
        request.setCharacterEncoding("utf-8");

        CountService countService = new CountServiceImpl();
        User user = (User) session.getAttribute("user");

        if (countService.getCount(user)>=10){
            //如果发表超过十次不当言论，系统可以直接对其进行禁言，无法进入聊天室
            out.print("<script>alert('对不起，您发表了超过十次不当言论，您不能进入房间聊天呢！！！您可以请求解除您的禁言')</script>");
            return;
        }

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


        int total = messageService.getTotalCount(null,room,null,"","");

        application.setAttribute("totalMessage",total);
        application.setAttribute("messageInit",list);
        application.setAttribute("nowCount",list.size());
        out.print(true);
    }
}
