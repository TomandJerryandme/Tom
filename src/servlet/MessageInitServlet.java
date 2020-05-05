package servlet;

import entity.Message;
import entity.User;
import service.MessageService;
import service.RoomService;
import service.serviceImpl.MessageServiceImpl;
import service.serviceImpl.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/message/init",loadOnStartup = 5)
public class MessageInitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {

        //获取所有请求解除禁言的信息
        MessageService messageService = new MessageServiceImpl();
        List<Message> list = messageService.findMessageList();

        this.getServletContext().setAttribute("releaseList",list);

        //组合业务对象
        RoomService roomService = new RoomServiceImpl();

        Map<User,Integer> onlineList= new HashMap<>();
        this.getServletContext().setAttribute("onlineList", onlineList);

        List<User> userList= new ArrayList<>();
        User user = new User();
        user.setUsername("temp");
        userList.add(user);
        this.getServletContext().setAttribute("onlineUserList",userList);
    }
}
