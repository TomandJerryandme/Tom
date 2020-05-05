package servlet;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import entity.Room;
import service.RoomService;
import service.RoomTypeService;
import service.serviceImpl.RoomServiceImpl;
import service.serviceImpl.RoomTypeServiceImpl;
import util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/room/add")
public class RoomAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        //*****************文件上传begin*************************
        SmartUpload smartUpload = null;
        String cover = null;
        String truename = null;
        String gamefilename = null;
        double gamesize = 0;
        try {
            //步骤1---创建上传组件---实例化SmartUpload对象
            smartUpload = new SmartUpload("utf-8");

            //步骤2---初始化上传组件---调用initialize()方法
            smartUpload.initialize(getServletConfig(), request, response);

            //检查---文件大小
            smartUpload.setMaxFileSize(10*1024*1024);

            //检查---文件类型
            smartUpload.setAllowedFilesList("jpg,gif");

            //步骤3---上传文件到服务器的临时内存中---调用upload()方法
            smartUpload.upload();

            cover = smartUpload.getRequest().getParameter("cover");

            //if("0.gif".equals(cover)){   //上传自定义头像
                //步骤4---提取上传文件
            SmartFile smartFile = smartUpload.getFiles().getFile(0);
//            SmartFile game = smartUpload.getFiles().getFile(1);


//            gamesize = game.getSize();          //getSize()返回值单位是字节
            truename = smartFile.getFileName();
            //检查---判断是否为空
            //if(smartFile.getSize()==0){
            if(smartFile.isMissing()){
                out.print("<script>alert('必须选择自定义头像');history.back()</script>");
                return;
            }

            //步骤5---准备上传文件的存储路径和文件名---保证文件名唯一

            String serverFilePath = this.getServletContext().getRealPath("/roomphoto");
            File path = new File(serverFilePath);

            if (!path.exists()){
                //如果路径不存在，创建这么一个文件夹
                path.mkdir();
            }
            String serverFilename = StringUtil.convertFilename(smartFile.getFileName());
//            gamefilename = game.getFileName();
            //步骤6---保存上传文件
            smartFile.saveAs(serverFilePath + "/" + truename);
            //更新photo变量的值
            cover = serverFilename;
            //}


        } catch (SmartUploadException e) {
            e.printStackTrace();
        }catch (SecurityException e) {
            out.print("<script>alert('文件大小不能超过100k,而且类型必须是jpg或gif');history.back()</script>");
            e.printStackTrace();
        }

        //*****************文件上传end*************************

        RoomTypeService roomTypeService = new RoomTypeServiceImpl();
        RoomService roomService = new RoomServiceImpl();
        ServletContext application = this.getServletContext();
        String roomname = smartUpload.getRequest().getParameter("roomname");
        String roomIntro = smartUpload.getRequest().getParameter("introduce");
        String type = smartUpload.getRequest().getParameter("roomtype");

        System.out.println("true======="+truename);

        int roomtype = 5;
        if (type!=null) {
            roomtype = Integer.parseInt(type);

        }

        Room room = new Room();
        room.setRoomname(roomname);
        room.setRoomtype(roomTypeService.getType(roomtype));
        room.setIntroduce(roomIntro);
        room.setRoomphoto(cover);
        room.setTruename(truename);
        room.setType(0);
        room.setUser1(0);
        room.setUser2(0);

        if(roomService.addRoom(room)){
            out.print("<script>alert('房间添加成功');location='/room_add.jsp'</script>");
        }else{
            out.print("<script>alert('房间添加失败');history.back()</script>");
        }

        //下面是用来修改session域中的房间列表，因为添加了一个新的房间，在不重启tomcat服务器的情况下，先将房间添加到session中，表示房间已经添加成功
        /*game = gameService.getGame(game.getGamename(),cover);     //游戏重名的时候会错
        System.out.println(game);
        if (gameDownloadService.addGameAddress(game,gamefilename)){
            Game newGame = gameService.getGame(gamename,cover);
            List<Game> list = new ArrayList<>();
            list = (List<Game>)application.getAttribute("gameList");
            list.add(newGame);
            session.setAttribute("gameList",list);
            out.print("<script>alert('游戏添加成功');location='/game_add.jsp'</script>");
        }else{
            out.print("<script>alert('游戏添加失败');history.back()</script>");
        }*/

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
