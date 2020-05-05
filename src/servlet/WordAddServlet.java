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

@WebServlet(value = "/word/add")
public class WordAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //添加屏蔽字的servlet
        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");
        //获取out输出对象
        PrintWriter out = response.getWriter();

        ServletContext application = request.getServletContext();
        //获取session对象
        HttpSession session = request.getSession();
        //设置字符编码
        request.setCharacterEncoding("utf-8");

        String word1 = request.getParameter("word1");
        String word2 = request.getParameter("word2");

        WordService wordService = new WordServiceImpl();
        if (!"".equals(word1)){
            //第一个屏蔽词字段不为空,先检测该关键字是否已经存在
            if (wordService.hasWord(word1)){
                //该关键字已经存在，无法进行添加
                out.print("关键字1已经存在");
                return;
            }else {
                if (!"".equals(word2)){
                    //第二个屏蔽词字段不为空
                    if (wordService.hasWord(word2)){
                        //该关键字已经存在，无法进行添加
                        out.print("关键字2已经存在");
                        return;
                    }
                    //将该Word添加到屏蔽词列表中
                    if (wordService.addWord(word1)&&wordService.addWord(word2)){
                        Word a = wordService.findWord(word1);
                        Word b = wordService.findWord(word2);
                        List<Word> list = (List<Word>) application.getAttribute("wordlistinit");
                        list.add(a);
                        list.add(b);
                        application.setAttribute("wordlistinit",list);
                        out.print("<script>alert('屏蔽词添加成功');location='/word_add.jsp'</script>");
                    }else {
                        out.print("<script>alert('屏蔽词添加失败');history.back()</script>");
                    }
                }
            }
            //将该Word添加到屏蔽词列表中
            /*if(roomService.addRoom(room)){
                out.print("<script>alert('房间添加成功');location='/game_add.jsp'</script>");
            }else{
                out.print("<script>alert('房间添加失败');history.back()</script>");
            }*/
        }

        if (!"".equals(word2)){
            //第二个屏蔽词字段不为空
            if (wordService.hasWord(word2)){
                //该关键字已经存在，无法进行添加
                out.print("关键字2已经存在");
                return;
            }
            //将该Word添加到屏蔽词列表中
            if (wordService.addWord(word2)){
                out.print("<script>alert('屏蔽词添加成功');location='/word_add.jsp'</script>");
            }
        }
    }
}
