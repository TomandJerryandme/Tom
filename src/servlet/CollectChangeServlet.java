package servlet;

import entity.Collection;
import entity.Room;
import entity.User;
import service.CollectionService;
import service.RoomService;
import service.serviceImpl.CollectionServiceImpl;
import service.serviceImpl.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/room/collect")
public class CollectChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //收藏用servlet
        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");
        //获取out输出对象
        PrintWriter out = response.getWriter();
        //获取session对象
        HttpSession session = request.getSession();
        //设置字符编码
        request.setCharacterEncoding("utf-8");

        System.out.println("执行到collection里了");

        String roomid = request.getParameter("roomid");
        int id = 0;
        if (roomid!=null){
            id = Integer.parseInt(roomid);
        }

        String state = request.getParameter("state");
        RoomService roomService = new RoomServiceImpl();
        User user = (User)session.getAttribute("user");
        Room room = roomService.getRoom(id);
        CollectionService shopCarService = new CollectionServiceImpl();

        if ("sure".equals(state)){
            //将该游戏的信息放在购物车，收藏
            Collection collection = new Collection();
            collection.setRoom(room);
            collection.setUser(user);
            if (shopCarService.addCollection(collection)){
                System.out.println("收藏成功");
                out.print(true);
            }else{
                System.out.println("收藏失败");
                out.print(false);
            }
        }else{
            //取消收藏
            if(shopCarService.removeCollection(user.getUserid(),id)){
                out.print(true);
            }else{
                out.print(false);
            }
        }
    }
}
