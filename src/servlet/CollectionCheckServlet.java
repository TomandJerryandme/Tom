package servlet;

import entity.Collection;
import entity.User;
import service.CollectionService;
import service.serviceImpl.CollectionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/collection/check")
public class CollectionCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String roomid = request.getParameter("roomid");
        int id = 0;
        if (roomid!=null){
            id = Integer.parseInt(roomid);
        }

        User user = (User)session.getAttribute("user");
        CollectionService collectionService = new CollectionServiceImpl();
        if (collectionService.hasCollection(user.getUserid(),id)){
            out.print("yes");
        }else {
            out.print("no");
        }
    }
}
