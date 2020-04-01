package servlet;

import service.RoomService;
import service.serviceImpl.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/room/in")
public class ChatRoomServlet extends HttpServlet {
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
        //设置字符编码
        request.setCharacterEncoding("utf-8");

        //将roomid记录在session域中，以便以后提交聊天内容时使用
        String roomid = request.getParameter("roomid");
        int roomId = 0;
        if (roomid!=null){
            roomId = Integer.parseInt(roomid);
        }

        RoomService roomService = new RoomServiceImpl();
        //将聊天室id保存在session域中
        session.setAttribute("chatroom",roomService.getRoom(roomId));

        out.print("true");
    }
}
