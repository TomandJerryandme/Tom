package servlet;

import entity.Message;
import entity.User;
import service.RoomService;
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

        //组合业务对象
        RoomService roomService = new RoomServiceImpl();

        //调用业务方法
        List<Message> messageList = new ArrayList<>();

        Map<User,Integer> onlineList= new HashMap<>();
        this.getServletContext().setAttribute("onlineList", onlineList);


        System.out.println("信息列表已经创建成功");

        //在application属性范围中保存列表
        this.getServletContext().setAttribute("messageList", messageList);

    }
}
