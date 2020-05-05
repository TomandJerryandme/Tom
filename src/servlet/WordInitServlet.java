package servlet;

import entity.Room;
import entity.Word;
import service.RoomService;
import service.WordService;
import service.serviceImpl.RoomServiceImpl;
import service.serviceImpl.WordServiceImpl;
import vo.RoomPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.List;

@WebServlet(value = "/word/init",loadOnStartup = 20)
public class WordInitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {

        WordService wordService = new WordServiceImpl();

        List<Word> list = wordService.findWords();

        this.getServletContext().setAttribute("wordlistinit",list);

        System.out.println("违禁词列表加载成功。。。");
    }
}
