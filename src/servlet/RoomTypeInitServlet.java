package servlet;

import entity.RoomType;
import service.RoomTypeService;
import service.serviceImpl.RoomTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.List;

@WebServlet(value = "/gametype/init",loadOnStartup = 1)
public class RoomTypeInitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

        //组合业务对象
        RoomTypeService roomTypeService = new RoomTypeServiceImpl();

        //调用业务方法
        List<RoomType> roomTypeList = roomTypeService.getType();

        //在application属性范围中保存新闻类型列表
        this.getServletContext().setAttribute("roomTypeList", roomTypeList);

        System.out.println("聊天室类型列表加载成功。size=" + roomTypeList.size());
    }
}
