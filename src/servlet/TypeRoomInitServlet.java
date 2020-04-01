package servlet;

import entity.Room;
import service.RoomService;
import service.serviceImpl.RoomServiceImpl;
import vo.RoomPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/typeroom/init")
public class TypeRoomInitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //个别类型的游戏的初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String gametypeid = request.getParameter("typeid");
        int typeid = 0;
        if (gametypeid!=null){
            typeid = Integer.parseInt(gametypeid);
        }


        //组合业务对象
        RoomService roomService = new RoomServiceImpl();

        RoomPage roomPage = new RoomPage();
        roomPage.setCurrentPage(1);
        roomPage.setPageSize(8);
        roomPage.setTotalPage(roomService.getTotalCount(typeid));
        roomPage.setTotalCount(roomService.getTotalCount(typeid));
        roomPage.setDataList(roomService.getRoomList(typeid,1,8));
        //调用业务方法


        //在application属性范围中保存新闻类型列表
        this.getServletContext().setAttribute("typeRoomList", roomPage);

        //System.out.println(this.getServletContext().getAttribute("typeGameList"));

        System.out.println("房间列表加载成功。。。size=" + roomPage.getDataList().size());

        out.print("<script>location='/room_"+typeid+".jsp'</script>");
    }
}
