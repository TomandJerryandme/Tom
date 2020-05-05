package servlet;

import entity.Collection;
import entity.RoomType;
import entity.User;
import service.CollectionService;
import service.RoomTypeService;
import service.UserService;
import service.serviceImpl.CollectionServiceImpl;
import service.serviceImpl.RoomTypeServiceImpl;
import service.serviceImpl.UserServiceImpl;
import vo.CollectionPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/user/init",loadOnStartup = 10)
public class UserInitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

        //组合业务对象
        UserService userService = new UserServiceImpl();

        //调用业务方法,查询到所有的注册用户
        List<User> userList = userService.getUserList(0);

        //在application属性范围中保存新闻类型列表
        this.getServletContext().setAttribute("userList", userList);

        System.out.println("用户列表加载成功。size=" + userList.size());
    }
}
