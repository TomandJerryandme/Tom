package servlet;

import entity.Message;
import entity.MessageType;
import entity.Room;
import entity.User;
import service.MessageService;
import service.MessageTypeService;
import service.RoomService;
import service.serviceImpl.MessageServiceImpl;
import service.serviceImpl.MessageTypeServiceImpl;
import service.serviceImpl.RoomServiceImpl;
import vo.MessagePage;
import vo.RoomPage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/message/changePage")
public class MessagePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用于跳页的servlet
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        ServletContext application = this.getServletContext();

        String page1 = request.getParameter("page");
        int page = 0;
        if (page1!=null){
            page = Integer.parseInt(page1);
            System.out.println("page==========="+page);
        }

        //从session中得到查询条件
        User user = (User) session.getAttribute("selectUser");
        Room room = (Room) session.getAttribute("selectRoom");
        System.out.println("房间======="+room);
        String messagetype = (String) session.getAttribute("selectType");

        String begin = (String)session.getAttribute("begintime");

        String end = (String)session.getAttribute("endtime");

        MessageType messageType = null;
        if (!"".equals(messagetype)){

            MessageTypeService messageTypeService = new MessageTypeServiceImpl();
            messageType = messageTypeService.getMessageType(Integer.parseInt(messagetype));
        }

        if (begin==null){
            begin = "";
        }
        if (end==null){
            end = "";
        }
        //得到page数后，对相应的页面数据进行填写到application域中的page里
        MessageService messageService = new MessageServiceImpl();

        User a = (User) session.getAttribute("user");
        if (!a.isUsertype()){
            user = a;
        }

        List<Message> list = messageService.findMessage(user,room,messageType,begin,end,page);
        int count = messageService.getTotalCount(user,room,messageType,begin,end);
        MessagePage messagePage = new MessagePage();
        messagePage.setTotalPage(count%15==0?count/15:(count/15+1));
        messagePage.setTotalCount(count);
        messagePage.setCurrentPage(page);
        messagePage.setPageSize(15);
        messagePage.setDataList(list);


        request.setAttribute("role",(int)session.getAttribute("role"));

        String selectUser = "";
        String selectRoom = "";
        String selectType = "";
        if (user!=null){
            selectUser = user.getUsername();
        }
        if (room!=null){
            selectRoom = room.getRoomid()+"";
        }
        if (messageType!=null){
            selectType = messageType.getTypeid()+"";
            System.out.println("selectType = " + selectType);
        }


        //保存到request属性范围中
        request.setAttribute("messagePage", messagePage);

        //role用来给页面区分是否是管理员
        int role = 0;

        if (a.isUsertype()){
            role = 1;
        }
        request.setAttribute("role",role);

        request.setAttribute("selectUser",selectUser);

        request.setAttribute("selectRoom",selectRoom);
        request.setAttribute("selectType",selectType);
        String regtime = (String) session.getAttribute("regtime");
        request.setAttribute("regtime",regtime);
        request.setAttribute("begintime",begin);
        request.setAttribute("endtime",end);
        request.setAttribute("messagePage",messagePage);
        request.getRequestDispatcher("/message_query.jsp").forward(request,response);
    }
}
