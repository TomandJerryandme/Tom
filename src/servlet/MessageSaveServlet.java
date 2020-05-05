package servlet;

import entity.*;
import service.*;
import service.serviceImpl.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/message/save")
public class MessageSaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //保存聊天消息的servlet,该servlet一次只能由一个用户来调用
        //在调用了服务器后，再调用该servlet存储信息
        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");
        //获取out输出对象
        PrintWriter out = response.getWriter();
        //获取session对象
        HttpSession session = request.getSession();

        ServletContext application = request.getServletContext();
        //设置字符编码
        request.setCharacterEncoding("utf-8");

        System.out.println("MessageServlet====================");

        String content = request.getParameter("content");
        String picname = request.getParameter("picId");
        System.out.println("MessageServlet===picname-"+picname);
        int type = 1;
        int picId = 0;
        if (picname!=null){
            picId = Integer.parseInt(picname);
            EmojiService emojiService = new EmojiServiceImpl();
            content = emojiService.getEmoji(picId).getEmojiname();
            System.out.println("图片名：===="+content);
            type = 2;
        }

        if (content==null){
            //用户未发送信息
            out.print(false);
            return;
        }

        //屏蔽词的替换
        WordService wordService = new WordServiceImpl();
        List<Word> wordList = wordService.findWords();
        User user = (User)session.getAttribute("user");
        CountService countService = new CountServiceImpl();

        //是否能取到屏蔽字列表
        for (Word word : wordList) {
            if (content.contains(word.getWordContent())){
                //包含了屏蔽字，无法发送该信息
                countService.updateCount(user);
                //如果在聊天室内发送违禁词超过10次，系统强制禁言，无法发送信息，发送信息按钮与加入对话按钮无法使用
                out.print(false);
            }
            //content = content.replace(word.getWordContent(),"***");
        }
        

        Room room = (Room)session.getAttribute("chatroom");
        MessageTypeService messageTypeService = new MessageTypeServiceImpl();

        Message message = new Message();
        message.setUser(user);
        message.setRoom(room);
        message.setMessageType(messageTypeService.getMessageType(type));
        message.setContent(content);
        message.setTime(new Date());

        System.out.println(message);
        MessageService messageService = new MessageServiceImpl();
        if (messageService.addMessage(message)){

            int total = (int)application.getAttribute("totalMessage");

            application.setAttribute("totalMessage",total+1);
            //数据插入成功
            System.out.println("数据插入成功");

            //该段程序属于单线程
            //将该信息保存在session域中
//            List<Message> list = (List<Message>) application.getAttribute("messageInit");
            application.removeAttribute("messageInit");
            List<Message> list = (List<Message>) messageService.findMessage(room.getRoomid(),user.getCount());
            application.setAttribute("messageInit",list);

            //将messageList里的消息显示到显示区域
            //重新加载一下room_show.jsp界面

            out.print(user.getUsername());
        }else {
            //数据插入失败
            System.out.println("数据插入失败");
        }

    }
}
