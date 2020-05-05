package servlet;

import entity.Room;
import entity.User;
import service.RoomService;
import service.UserService;
import service.serviceImpl.RoomServiceImpl;
import service.serviceImpl.UserServiceImpl;
import vo.RoomPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.List;

@WebServlet(value = "/room/init",loadOnStartup = 2)
public class RoomInitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {

        //组合业务对象
        RoomService roomService = new RoomServiceImpl();

        UserService userService = new UserServiceImpl();

        List<User> userList = userService.getUserList(0);

        this.getServletContext().setAttribute("allUser",userList);

        //调用业务方法
        List<Room> roomList = roomService.getRoomList(0);

        RoomPage roomPage = new RoomPage();
        roomPage.setCurrentPage(1);
        roomPage.setPageSize(8);
        roomPage.setTotalCount(roomService.getTotalCount(0));
        roomPage.setDataList(roomService.getRoomList(1,8,0));
        roomPage.setTotalPage(roomService.getTotalCount(0)%8==0 ? roomService.getTotalCount(0)/8:(roomService.getTotalCount(0)/8)+1);

        System.out.println(roomList);
        //在application属性范围中保存列表
        this.getServletContext().setAttribute("roomList", roomList);
        this.getServletContext().setAttribute("roompage",roomPage);

        System.out.println("房间列表加载成功。size=" + roomList.size());

    }
}
