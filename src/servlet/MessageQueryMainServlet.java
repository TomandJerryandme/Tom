package servlet;

import entity.Message;
import entity.MessageType;
import entity.Room;
import entity.User;
import service.MessageService;
import service.MessageTypeService;
import service.RoomService;
import service.UserService;
import service.serviceImpl.MessageServiceImpl;
import service.serviceImpl.MessageTypeServiceImpl;
import service.serviceImpl.RoomServiceImpl;
import service.serviceImpl.UserServiceImpl;
import vo.MessagePage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/message/query")
public class MessageQueryMainServlet extends HttpServlet {
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
        //设置字符编码
        request.setCharacterEncoding("utf-8");
        //用户进行信息查询时使用该servlet


        //组合业务对象
        MessageService messageService = new MessageServiceImpl();
        User user = null;
        Room room = null;
        MessageType messageType = null;

        UserService userService = new UserServiceImpl();
        RoomService roomService = new RoomServiceImpl();
        MessageTypeService messageTypeService = new MessageTypeServiceImpl();

        //接收用户名
        String username = request.getParameter("username");
        String messagetype = request.getParameter("messagetype");
        String roomid = request.getParameter("roomid");

        int roomId = 0;
        int messagetypeid;

        //默认值处理
        if(!"".equals(username)){
            user = userService.getUser(username);
            request.removeAttribute("selectUser");
            request.setAttribute("selectUser",user.getUsername());
            session.setAttribute("selectUser",user);
        }

        if (!"".equals(roomid)){
            roomId = Integer.parseInt(roomid);
            room = roomService.getRoom(roomId);
            request.removeAttribute("selectRoom");
            request.setAttribute("selectRoom",room.getRoomname());
            session.setAttribute("selectRoom",room);
        }

        if (!"".equals(messagetype)){
            messagetypeid = Integer.parseInt(messagetype);
            messageType = messageTypeService.getMessageType(messagetypeid);
            request.removeAttribute("selectType");
            request.setAttribute("selectType",messageType.getTypeid()+"");
            session.setAttribute("selectType",messageType.getTypeid());

        }

        //接收注册时间
        String regtime = request.getParameter("time");
        //默认值处理
        if(regtime==null){
            regtime = "全部时间";
            request.setAttribute("regtime","全部时间");
            session.setAttribute("regtime","全部时间");
        }
        String begintime = "";
        String endtime = "";
        if("指定时间".equals(regtime)){
            request.setAttribute("regtime","指定时间");
            session.setAttribute("regtime","指定时间");
            begintime = request.getParameter("begintime");
            endtime = request.getParameter("endtime");
        }

        MessagePage messagePage = new MessagePage();
        messagePage.setCurrentPage(1);
        messagePage.setDataList(messageService.findMessage(user,room,messageType,begintime,endtime,1));
        messagePage.setTotalCount(messageService.getTotalCount(user,room,messageType,begintime,endtime));
        messagePage.setTotalPage(messageService.getTotalCount(user,room,messageType,begintime,endtime)%15==0?messageService.getTotalCount(user,room,messageType,begintime,endtime)/15:(messageService.getTotalCount(user,room,messageType,begintime,endtime)/15+1));
        messagePage.setPageSize(15);


        //在request属性范围中保存查询到的信息列表
        request.setAttribute("messagePage", messagePage);

        //在request属性范围中保存注册时间
        request.setAttribute("regtime", regtime);
        session.setAttribute("regtime",regtime);
        session.setAttribute("begintime",begintime);
        session.setAttribute("endtime",endtime);
        request.setAttribute("begintime", begintime);
        request.setAttribute("endtime", endtime);

        User a = (User)session.getAttribute("user");
        if (a.isUsertype()){
            request.setAttribute("role",1);
        }else{
            request.setAttribute("role",0);
        }

        //转发到查询页面
        request.getRequestDispatcher("/message_query.jsp").forward(request, response);

    }
}
