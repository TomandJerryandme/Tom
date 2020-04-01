package servlet;

import entity.Room;
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
import java.util.List;

@WebServlet(value = "/room/query")
public class RoomQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //游戏查询时使用，查到后，放在session域中，最后跳转到游戏查询页面

        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");
        //获取out输出对象
        PrintWriter out = response.getWriter();
        //获取session对象
        HttpSession session = request.getSession();
        //设置字符编码
        request.setCharacterEncoding("utf-8");
        String roomname = request.getParameter("roomname");
        System.out.println("查询字段========="+roomname);

        RoomService roomService = new RoomServiceImpl();

        List<Room> list = roomService.getRoomList(roomname,1,8);

        System.out.println(list);
        session.setAttribute("queryRoom",list);

        int size = list.size();

        out.print(String.valueOf(size));

    }
}
