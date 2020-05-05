package servlet;

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

@WebServlet(value = "/room/typeChange")
public class TypePageChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        ServletContext application = this.getServletContext();

        String page1 = request.getParameter("page");
        int page = 0;
        if (page1!=null){
            page = Integer.parseInt(page1);
        }

        int type = (int)session.getAttribute("roomType");

        RoomService roomService = new RoomServiceImpl();
        RoomPage roomPage = (RoomPage) application.getAttribute("typeRoomList");

        roomPage.setCurrentPage(page);
        roomPage.setDataList(roomService.getRoomList(type,page,8,0));

        application.setAttribute("typeRoomList",roomPage);
        out.print("true");
    }
}
