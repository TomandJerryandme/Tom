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
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/collection/delete")
public class CollectionDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        //根据传送过来的数据判断是全部删除还是删除一部分

        String state = request.getParameter("state");
        CollectionService collectionService = new CollectionServiceImpl();
        User user = (User)session.getAttribute("user");
        if ("all".equals(state)){
            //删除全部的游戏
            if (collectionService.removeCollection(user.getUserid())){
                out.print("true");  //删除成功
                List<Collection> list = new ArrayList<>();
                session.setAttribute("shopcar",list);
                session.setAttribute("cost",0);
            }else{
                out.print("false");
            }
        }else{
            //删除一部分游戏,因为数据传送的原因，这个servlet也就是全部删除或者只删除一个而调用多次该servlet

        }

    }
}
