package servlet;

import entity.Room;
import service.RoomService;
import service.serviceImpl.RoomServiceImpl;
import vo.RoomPage;

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

@WebServlet(value = "/page/change")
public class RoomPageChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用于跳页的servlet
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        ServletContext application = this.getServletContext();
        //将新的page替代掉原本application中的page

        String page1 = request.getParameter("page");
        int page = 0;
        if (page1!=null){
            page = Integer.parseInt(page1);
        }

        //得到page数后，对相应的页面数据进行填写到application域中的page里
        RoomService roomService = new RoomServiceImpl();

        List<Room> list = roomService.getRoomList(page,8);
        RoomPage roomlist = (RoomPage)application.getAttribute("roompage");
        roomlist.setDataList(list);
        roomlist.setCurrentPage(page);
        application.setAttribute("roompage",roomlist);
        out.print("true");
    }
}
