package servlet;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import entity.Room;
import entity.User;
import service.CountService;
import service.RoomService;
import service.RoomTypeService;
import service.UserService;
import service.serviceImpl.CountServiceImpl;
import service.serviceImpl.RoomServiceImpl;
import service.serviceImpl.RoomTypeServiceImpl;
import service.serviceImpl.UserServiceImpl;
import util.StringUtil;

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
import java.util.Date;

@WebServlet(value = "/user/register")
public class UserRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");

        //获取out输出对象
        PrintWriter out = response.getWriter();

        //获取session对象
        HttpSession session = request.getSession();

        //获取application对象
        ServletContext application = this.getServletContext();

        //设置字符编码
        request.setCharacterEncoding("utf-8");

        //*****************文件上传begin*************************
        SmartUpload smartUpload = null;
        String photo = null;

        try {
            //步骤1---创建上传组件---实例化SmartUpload对象
            smartUpload = new SmartUpload("utf-8");

            //步骤2---初始化上传组件---调用initialize()方法
            smartUpload.initialize(getServletConfig(), request, response);

            //检查---文件大小
            smartUpload.setMaxFileSize(100*1024*1024);

            //检查---文件类型
            smartUpload.setAllowedFilesList("jpg,gif");

            //步骤3---上传文件到服务器的临时内存中---调用upload()方法
            smartUpload.upload();

            photo = smartUpload.getRequest().getParameter("userpic");

            if("0.gif".equals(photo)){   //上传自定义头像

                //步骤4---提取上传文件
                SmartFile smartFile = smartUpload.getFiles().getFile(0);

                //检查---判断是否为空
                //if(smartFile.getSize()==0){
                if(smartFile.isMissing()){
                    out.print("<script>alert('必须选择自定义头像');history.back()</script>");
                    return;
                }

                //步骤5---准备上传文件的存储路径和文件名---保证文件名唯一
                String serverFilePath = this.getServletContext().getRealPath("/image/photo");

                String serverFilename = StringUtil.convertFilename(smartFile.getFileName());

                //步骤6---保存上传文件
                smartFile.saveAs(serverFilePath + "/" + serverFilename);

                //更新photo变量的值
                photo = serverFilename;

                System.out.println("photo========"+serverFilename);
            }


        } catch (SmartUploadException e) {
            e.printStackTrace();
        }catch (SecurityException e) {
            out.print("<script>alert('文件大小不能超过100M,而且类型必须是jpg或gif');history.back()</script>");
            e.printStackTrace();
        }

        //*****************文件上传end*************************



        //接收验证码
        String valcode = smartUpload.getRequest().getParameter("valcode");

        //从session属性范围中取出验证码答案
        String valcodeAnswer = (String) session.getAttribute("valcodeAnswer");

        //检查验证码
        if(!valcode.equalsIgnoreCase(valcodeAnswer)){
            out.print("<script>alert('验证码不正确，请重新输入');history.back()</script>");
            return;
        }

        //接收表单数据
        String username = smartUpload.getRequest().getParameter("username");
        String password = smartUpload.getRequest().getParameter("password");
        String mail = smartUpload.getRequest().getParameter("email");
        String phone = smartUpload.getRequest().getParameter("phone");
        String gender = smartUpload.getRequest().getParameter("gender");
        String question = smartUpload.getRequest().getParameter("question");
        String answer = smartUpload.getRequest().getParameter("answer");


        //多值数据使用getParameterValues方法接收
        //String[] interests = smartUpload.getRequest().getParameterValues("interest");

        //将数组的内容拼接成一个字符串
        /*String interest = "";
        if(interests!=null){

            for (String s : interests) {
                interest += s + " ";
            }

            interest = interest.trim();
        }*/

        //读取注册新用户的默认积分
        //int registerScore = Integer.parseInt(this.getInitParameter("registerScore"));

        //调用业务方法
        UserService userService = new UserServiceImpl();
        CountService countService = new CountServiceImpl();
        //检测用户名是否可用
        if(!userService.checkUsername(username)){   //不可用
            out.print("<script>alert('对不起，用户名已存在，请重新输入');history.back()</script>");
            return;
        }

        //将接收的数据封装成一个user对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(gender);
        user.setEmail(mail);
        user.setPhone(phone);
        user.setRegtime(new Date());
        user.setChatime(new Date());
        user.setUsertype(false);
        user.setQuestion(question);
        user.setAnswer(answer);
        user.setTruename(photo);
        user.setUserpic(photo);


        //调用注册的业务方法
        user = userService.register(user);


        if(user!=null){   //注册成功


            //注册一个用户与管理员的消息房间
            RoomTypeService roomTypeService = new RoomTypeServiceImpl();
            Room room = new Room();
            room.setIntroduce("");
            room.setRoomtype(roomTypeService.getType(1));
            room.setRoomphoto("");
            room.setTruename("");
            room.setType(2);
            room.setUser1(user.getUserid());
            room.setUser2(4);
            room.setRoomname("通知");


            RoomService roomService = new RoomServiceImpl();
            roomService.addRoom(room);


            countService.addCount(user);
            //将结果保存在属性范围中

            session.setAttribute("user", user);


            //更新在线人数  +1
            if(application.getAttribute("onlineCount")==null){  //第一个访客
                application.setAttribute("onlineCount", 1);
            }else{
                int onlineCount = (int) application.getAttribute("onlineCount");
                application.setAttribute("onlineCount", onlineCount + 1);
            }

            //页面跳转  重定向到主页
            //response.sendRedirect("/index.jsp");

            //页面跳转  重定向到注册结果页面
            response.sendRedirect("/user_register_result.jsp");

        }else{    //注册失败

            //弹框后，回到注册页面上
            out.print("<script>alert('注册失败，请检查数据是否输入正确');history.back()</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
