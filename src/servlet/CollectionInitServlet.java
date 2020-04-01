package servlet;

import entity.Collection;
import entity.Room;
import entity.User;
import service.CollectionService;
import service.RoomService;
import service.serviceImpl.CollectionServiceImpl;
import service.serviceImpl.RoomServiceImpl;
import vo.CollectionPage;
import vo.RoomPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/collection/init",loadOnStartup = 3)
public class CollectionInitServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        //初始化
        CollectionService collectionService = new CollectionServiceImpl();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        //获取该用户所有的收藏
        List<Collection> list = collectionService.getUserCollection(user.getUserid());

        int total = collectionService.getTotalCount(user.getUserid());
        CollectionPage collectionPage = new CollectionPage();
        collectionPage.setCurrentPage(1);
        collectionPage.setPageSize(8);
        collectionPage.setTotalCount(total);
        collectionPage.setDataList(collectionService.getUserCollection(user.getUserid(),1,8));
        collectionPage.setTotalPage(total%8==0 ? total/8:(total/8)+1);

        //在application属性范围中保存列表
        session.setAttribute("collectionList", list);  //在新增收藏的时候会使用该变量
        session.setAttribute("collectionpage",collectionPage);

        //在application属性范围中保存新闻类型列表
//        session.setAttribute("collection", list);
        System.out.println("收藏列表加载成功。。。size=" + list.size());

        out.print(true);
    }
}
