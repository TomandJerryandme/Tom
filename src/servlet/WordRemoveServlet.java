package servlet;

import service.WordService;
import service.serviceImpl.WordServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/word/remove")
public class WordRemoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //删除屏蔽字的servlet
        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");
        //获取out输出对象
        PrintWriter out = response.getWriter();
        //获取session对象
        HttpSession session = request.getSession();
        //设置字符编码
        request.setCharacterEncoding("utf-8");

        String word = request.getParameter("word");

        WordService wordService = new WordServiceImpl();

        if (!"".equals(word)){
            //word不为空
            if (wordService.removeWord(word)){
                out.print("<script>alert('屏蔽词删除成功');location='/word_remove.jsp'</script>");
            }
        }else {
            out.print("<script>alert('输入为空！！！');history.back()</script>");
        }

    }
}
