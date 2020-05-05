package servlet;

import entity.Message;
import entity.Room;
import entity.User;
import service.MessageService;
import service.RoomService;
import service.RoomTypeService;
import service.UserService;
import service.serviceImpl.MessageServiceImpl;
import service.serviceImpl.RoomServiceImpl;
import service.serviceImpl.RoomTypeServiceImpl;
import service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletContext;
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

@WebServlet(value = "/room/single")
public class SingleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");
        //获取out输出对象
        PrintWriter out = response.getWriter();
        //获取session对象
        HttpSession session = request.getSession();
        ServletContext application = request.getServletContext();
        //设置字符编码
        request.setCharacterEncoding("utf-8");


        UserService userService = new UserServiceImpl();
        RoomService roomService = new RoomServiceImpl();
        RoomTypeService roomTypeService = new RoomTypeServiceImpl();
        User user1 = (User)session.getAttribute("user");
        User user2 = null;
        String username = request.getParameter("userid");

        System.out.println("username==="+username);

        int userid = 0;
        if (username!=null){
            userid = Integer.parseInt(username);
            user2 = userService.getUser(userid);  //因为用户注册时避免了重名的风险
        }

        System.out.println("user1==="+user1);
        System.out.println("user2==="+user2);

        //不能与自己聊天
        if (user1.getUserid()==user2.getUserid()){
            out.print("AloneTalk");
            return;
        }


        if (!roomService.HasRoom(user1.getUserid(),user2.getUserid())){
            //这两个用户是第一次通话，新建一个用户间通话的房间
            Room room = new Room();
            room.setUser1(user1.getUserid());
            room.setUser2(user2.getUserid());
            room.setType(1);
            room.setRoomname("system");
            room.setTruename("");
            room.setRoomphoto("");
            room.setRoomtype(roomTypeService.getType(7));
            room.setIntroduce("这是"+user1.getUsername()+"与"+user2.getUsername()+"的谈话房间");

            if (roomService.addRoom(room)){
                //房间添加成功
                System.out.println("singleServlet=========房间添加成功");
            }else {
                System.out.println("singleServlet=========房间添加失败");
            }

            Room x = roomService.getRoom(user1.getUserid(),user2.getUserid());
            List<Message> list = new ArrayList<>();
            application.setAttribute("messageInit",list);
            application.setAttribute("nowCount",0); //nowCount表示的是现在的界面上显示了多少条信息
            application.setAttribute("totalCount",0);
            session.setAttribute("chatroom",x);
            //房间添加成功后，进入房间
            out.print(true);

            return;
        }

        //不是第一次通话，直接进入房间
        //获取到房间
        Room object = roomService.getRoom(user1.getUserid(),user2.getUserid());

        session.setAttribute("chatroom",object);
        MessageService messageService = new MessageServiceImpl();
        List<Message> messageList = messageService.findMessage(object.getRoomid(),user1.getCount());    //显示多少信息
        application.setAttribute("messageInit",messageList);
        application.setAttribute("nowCount",messageList.size());
        application.setAttribute("totalCount",messageService.getTotalCount(null,object,null,"",""));
        out.print(true);
    }
}
