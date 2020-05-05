package servlet;



import entity.Emoji;
import entity.User;
import service.CountService;
import service.EmojiService;
import service.UserService;
import service.serviceImpl.CountServiceImpl;
import service.serviceImpl.EmojiServiceImpl;
import service.serviceImpl.UserServiceImpl;
import util.CookieUtil;
import util.EncryptUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(value="/user/login",initParams = @WebInitParam(name="loginScore",value = "1"))
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");

        //设置头信息，告诉浏览器我回送的数据编码是utf-8的,与上面一句话的作用类似
//        response.setHeader("Content-Type", "text/html;charset=UTF-8");

        //获取out输出对象
        PrintWriter out = response.getWriter();

        //获取session对象
        HttpSession session = request.getSession();

        //获取application对象
        ServletContext application = this.getServletContext();

        //设置字符编码
        request.setCharacterEncoding("utf-8");

        //接收表单数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //接收复选框
        String remember = request.getParameter("remember");
        String autoLogin = request.getParameter("autoLogin");

        if(remember!=null){
            //发送cookie
            CookieUtil.addCookie("userinfo", EncryptUtil.encrypt(username + ":" + password), 7 , response);
        }


        //调用业务方法
        UserService userService = new UserServiceImpl();

        User user = userService.login(username, password);

        CountService countService = new CountServiceImpl();

        int legal = 0;


        if(user!=null){   //成功找到该user

            List<User> userList = (List<User>) application.getAttribute("onlineUserList");

            for (User user1 : userList) {
                if ("temp".equals(user1.getUsername())){
                    //是初始化的一个用户，不用管
                }else{
                    if (user1.getUsername().equals(user.getUsername())){
                        //该用户已经登录
                        out.print("<script>alert('该用户已经登录');location='/user_login.jsp'</script>");
                        return;
                    }
                }
            }

            //该用户未登录
            userList.add(user);
            application.setAttribute("onlineUserList",userList);

            if (countService.getCount(user)>=10){
                legal = 1;
            }
            //判断是否需要禁言
            session.setAttribute("legal",legal);


            //加载表情包列表
            EmojiService emojiService = new EmojiServiceImpl();
            List<Emoji> emojiList = emojiService.getEmojiList();
            application.setAttribute("emojiList",emojiList);

            if(autoLogin!=null){
                String ss = EncryptUtil.encrypt(username + ":" + password);
                //发送cookie
                CookieUtil.addCookie("logininfo", ss, 7 , response);
            }

            if (user.isUsertype()){
                //如果是管理员用户，要跳转到对应的页面
                session.setAttribute("user", user);
                /*Map<User,Integer> userList = (Map<User,Integer>) application.getAttribute("onlineList");
                userList.put(user,0);
                application.setAttribute("onlineList",userList);*/
                //管理员用户可以添加room，违禁词，删除房间
                out.print("<script>location='/user_manager_index.jsp'</script>");

                return;
            }

            //将结果保存在属性范围中  request  session  application
            session.setAttribute("user", user);

            //更新在线人数  +1
            if(application.getAttribute("onlineCount")==null){  //第一个访客
                application.setAttribute("onlineCount", 1);
            }else{
                 int onlineCount = (int) application.getAttribute("onlineCount");
                 application.setAttribute("onlineCount", onlineCount + 1);
            }

            String url = "/index.jsp";
            if(session.getAttribute("prevURL")!=null){
                url = (String) session.getAttribute("prevURL");
            }

            response.sendRedirect(url);

        }else{    //登录失败
            out.print("<script>alert('登录失败，请重新登录');location='/user_login.jsp'</script>");

        }

    }
}
