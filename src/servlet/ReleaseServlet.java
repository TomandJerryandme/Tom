package servlet;

import entity.Message;
import service.CountService;
import service.MessageService;
import service.serviceImpl.CountServiceImpl;
import service.serviceImpl.MessageServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/user/jinyan")
public class ReleaseServlet extends HttpServlet {
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

        //获取参数
        String messageid = request.getParameter("messageid");

        int id = 0;
        if (messageid!=null){
            id = Integer.parseInt(messageid);
        }


        System.out.println("ReleaseServlet================"+messageid);


        CountService countService = new CountServiceImpl();
        MessageService messageService = new MessageServiceImpl();
        Message message = messageService.findMessageByID(id);

        if (messageService.removeMessage(message.getMessageid())){
            //成功删除该条信息
            countService.releaseCount(message.getUser());
            List<Message> list = messageService.findMessageList();

            application.setAttribute("releaseList",list);
        }

        System.out.println("releaseServlet执行完毕");
        out.print("true");
    }
}
