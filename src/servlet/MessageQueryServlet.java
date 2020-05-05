package servlet;

import entity.Message;
import entity.User;
import service.MessageService;
import service.MessageTypeService;
import service.serviceImpl.MessageServiceImpl;
import service.serviceImpl.MessageTypeServiceImpl;
import vo.MessagePage;

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

@WebServlet(value = "/message/queryinit")
public class MessageQueryServlet extends HttpServlet {
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

        //调用业务方法
        List<Message> list = new ArrayList<>();

        User user = (User) session.getAttribute("user");

        int role = 0;

        MessagePage messagePage = new MessagePage();
        messagePage.setPageSize(15);
        messagePage.setCurrentPage(1);

        if (user.isUsertype()){
            //如果是管理员用户,则默认查询所有信息
            messagePage.setTotalPage(messageService.getTotalCount(null,null,null,"","")%15==0?messageService.getTotalCount(null,null,null,"","")/15:(messageService.getTotalCount(null,null,null,"","")/15+1));
            messagePage.setTotalCount(messageService.getTotalCount(null,null,null,"",""));
            messagePage.setDataList(messageService.findMessage(1));
            role = 1;
        }else {
            //不是管理员用户，查询该用户发表的全部消息
            //userid,roomid,typeid,pass,now
            messagePage.setDataList(messageService.findMessage(user,null,null,"","",1));
            messagePage.setTotalCount(messageService.getTotalCount(user,null,null,"",""));
            messagePage.setTotalPage(messageService.getTotalCount(user,null,null,"","")%15==0?messageService.getTotalCount(user,null,null,"","")/15:(messageService.getTotalCount(user,null,null,"","")/15+1));
        }
        //保存到request属性范围中
        request.setAttribute("messagePage", messagePage);

        MessageTypeService messageTypeService = new MessageTypeServiceImpl();
        //role用来给页面区分是否是管理员
        request.setAttribute("role",role);
        session.setAttribute("role",role);

        request.setAttribute("selectUser","");
        session.setAttribute("selectUser",null);

        request.setAttribute("selectRoom","");
        session.setAttribute("selectRoom",null);
        request.setAttribute("selectType","");
        session.setAttribute("selectType","");
        request.setAttribute("regtime","全部时间");
        session.setAttribute("regtime","全部时间");
        //使用ajax不能使用该方法跳转
        request.getRequestDispatcher("/message_query.jsp").forward(request,response);

    }
}
