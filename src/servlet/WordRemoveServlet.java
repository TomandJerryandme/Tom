package servlet;

import entity.Word;
import service.WordService;
import service.serviceImpl.WordServiceImpl;

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

        ServletContext application = request.getServletContext();
        //获取session对象
        HttpSession session = request.getSession();
        //设置字符编码
        request.setCharacterEncoding("utf-8");

        String word = request.getParameter("word");

        WordService wordService = new WordServiceImpl();

        if (!"".equals(word)){
            //word不为空
            if (!wordService.hasWord(word)){
                //没有该词
                out.print("<script>alert('没有该屏蔽词');location='/word_remove.jsp'</script>");
            }
            if (wordService.removeWord(word)){
                List<Word> list = (List<Word>) application.getAttribute("wordlistinit");
                list.remove(wordService.findWord(word));
                application.setAttribute("wordlistinit",list);
                out.print("<script>alert('屏蔽词删除成功');location='/word_remove.jsp'</script>");
            }
        }else {
            out.print("<script>alert('输入为空！！！');history.back()</script>");
        }

    }
}
