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

@WebServlet(value = "/room/show")
public class RoomShowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        //ServletContext application = request.getServletContext();

        String roomid = request.getParameter("roomid");

        int id = 0;
        if (roomid!=null){
            id = Integer.parseInt(roomid);
        }

        RoomService roomService = new RoomServiceImpl();

        session.setAttribute("theChosenRoom",roomService.getRoom(id));

        //推荐的游戏的列表reRoom
        List<Room> rooms = roomService.getRoomList(roomService.getRoom(id).getRoomtype().getTypeid(),0);

        session.setAttribute("reRoom", rooms);

        out.print("<script>location='/room_detail.jsp'</script>");

        /*String json = JSON.toJSONString(RoomService.getRoom(id));
        out.print(json);*/

    }
}
