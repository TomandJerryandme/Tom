package servlet;

import entity.Message;
import service.MessageService;
import service.serviceImpl.MessageServiceImpl;

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
        List<Message> list = messageService.findMessage();

        //保存到request属性范围中
        request.setAttribute("list", list);

        //转发到新闻查询页面中
        request.getRequestDispatcher("/message_query.jsp").forward(request, response);

    }
}
