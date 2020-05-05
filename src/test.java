import entity.*;
import service.*;
import service.serviceImpl.*;
import util.StringUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {
    public static void main(String[] args) {
/*

        RoomTypeService roomTypeService = new RoomTypeServiceImpl();
        MessageTypeService messageTypeService = new MessageTypeServiceImpl();
        UserService userService = new UserServiceImpl();
        RoomService roomService = new RoomServiceImpl();

        Room room = new Room();
        User user = new User();
        MessageType messageType = new MessageType();
        Message message = new Message();
        RoomType roomType = new RoomType();

        roomType = roomTypeService.getType(1);
        messageType= messageTypeService.getMessageType(1);
        System.out.println(roomType.getTypename());
        System.out.println(messageType.getTypename());

        user.setUserid(1);

        room.setRoomid(1);
        room.setRoomname("test");
        room.setRoomtype(roomType);

        user.setUsername("test");
        user.setRegtime(new Date());
        user.setPassword("123456");
        user.setQuestion("a");
        user.setAnswer("a");
        user.setPhone("12312312312");
        user.setEmail("1728703711@qq.com");
        user.setUsertype(false);
        user.setChatime(new Date());
        user.setUserpic("");
        //userService.register(user);
        //System.out.println(roomService.addRoom(room));

        message.setTime(new Date());
        message.setContent("aalksjdf");
        message.setRoom(room);
        message.setUser(user);
        message.setMessageType(messageType);
        System.out.println(message);


        MessageService messageService  = new MessageServiceImpl();

        //messageService.addMessage(message);

        //日期时间的转换

        Calendar c1 = Calendar.getInstance();
        //月数要从零开始
        c1.set(2020,2,27);
        Date date1 = c1.getTime();

        Calendar c2 = Calendar.getInstance();
        c2.set(2020,2,28);


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        System.out.println(userService.getUser(1));
*/


        //
       /* MessageService messageService = new MessageServiceImpl();

        System.out.println(messageService.findMessage(1,10));

        RoomService roomService = new RoomServiceImpl();
        System.out.println(roomService.HasRoom(1,3));*/

        System.out.println(StringUtil.isOK("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$","1728703711qq.com"));
    }
}
