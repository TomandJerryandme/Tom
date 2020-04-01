package dao.daoimpl;

import dao.MessageDAO;
import entity.Message;
import entity.MessageType;
import entity.Room;
import entity.User;
import service.MessageTypeService;
import service.RoomService;
import service.UserService;
import service.serviceImpl.MessageTypeServiceImpl;
import service.serviceImpl.RoomServiceImpl;
import service.serviceImpl.UserServiceImpl;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {

    private JDBCUtil jdbcUtil = JDBCUtil.getInstance();

    @Override
    public Message queryMessage(int messageid) {
        String sql = "select * from graduation_message where messageid = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,messageid);
        try{
            UserService userService = new UserServiceImpl();
            RoomService roomService = new RoomServiceImpl();
            MessageTypeService messageTypeService = new MessageTypeServiceImpl();
            if (rs.next()){
                Message message = new Message();
                message.setMessageid(rs.getInt(1));
                message.setUser(userService.getUser(rs.getInt(2)));
                message.setRoom(roomService.getRoom(rs.getInt(3)));
                message.setMessageType(messageTypeService.getMessageType(rs.getInt(4)));
                message.setContent(rs.getString(5));
                message.setTime(rs.getDate(6));
                return message;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> queryMessage() {
        String sql = "select * from graduation_message";

        ResultSet rs = jdbcUtil.execQuery(sql);
        try {
            List<Message> list = new ArrayList<>();
            UserService userService = new UserServiceImpl();
            RoomService roomService = new RoomServiceImpl();
            MessageTypeService messageTypeService = new MessageTypeServiceImpl();
            while (rs.next()){
                Message order = new Message();
                order.setMessageid(rs.getInt(1));
                order.setUser(userService.getUser(rs.getInt(2)));
                order.setRoom(roomService.getRoom(rs.getInt(3)));
                order.setMessageType(messageTypeService.getMessageType(rs.getInt(4)));
                order.setContent(rs.getString(5));
                order.setTime(rs.getDate(6));
                list.add(order);
            }
            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Message> queryMessage(MessageType messageType) {
        String sql = "select * from graduation_message where typeid = ?";

        ResultSet rs = jdbcUtil.execQuery(sql,messageType.getTypeid());
        try {
            List<Message> list = new ArrayList<>();
            UserService userService = new UserServiceImpl();
            RoomService roomService = new RoomServiceImpl();
            MessageTypeService messageTypeService = new MessageTypeServiceImpl();
            while (rs.next()){
                Message order = new Message();
                order.setMessageid(rs.getInt(1));
                order.setUser(userService.getUser(rs.getInt(2)));
                order.setRoom(roomService.getRoom(rs.getInt(3)));
                order.setMessageType(messageTypeService.getMessageType(rs.getInt(4)));
                order.setContent(rs.getString(5));
                order.setTime(rs.getDate(6));
                list.add(order);
            }
            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> queryMessage(String pass, String now) {
        String sql = "select * from graduation_message where time >= ? and time <= ?";

        ResultSet rs = jdbcUtil.execQuery(sql, pass + " 00:00:00", now + " 23:59:59");
        try {
            List<Message> list = new ArrayList<>();
            UserService userService = new UserServiceImpl();
            RoomService roomService = new RoomServiceImpl();
            MessageTypeService messageTypeService = new MessageTypeServiceImpl();
            while (rs.next()){
                Message order = new Message();
                order.setMessageid(rs.getInt(1));
                order.setUser(userService.getUser(rs.getInt(2)));
                order.setRoom(roomService.getRoom(rs.getInt(3)));
                order.setMessageType(messageTypeService.getMessageType(rs.getInt(4)));
                order.setContent(rs.getString(5));
                order.setTime(rs.getDate(6));
                list.add(order);
            }
            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> queryMessage(User user) {
        String sql = "select * from graduation_message where userid = ?";

        ResultSet rs = jdbcUtil.execQuery(sql,user.getUserid());
        try {
            List<Message> list = new ArrayList<>();
            UserService userService = new UserServiceImpl();
            RoomService roomService = new RoomServiceImpl();
            MessageTypeService messageTypeService = new MessageTypeServiceImpl();
            while (rs.next()){
                Message order = new Message();
                order.setMessageid(rs.getInt(1));
                order.setUser(userService.getUser(rs.getInt(2)));
                order.setRoom(roomService.getRoom(rs.getInt(3)));
                order.setMessageType(messageTypeService.getMessageType(rs.getInt(4)));
                order.setContent(rs.getString(5));
                order.setTime(rs.getDate(6));
                list.add(order);
            }
            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> queryMessage(User user, String pass, String now) {
        String sql = "select * from graduation_message where userid = ? and time >= ? and time <= ?";
        //正式使用用这个

        ResultSet rs = jdbcUtil.execQuery(sql, user.getUserid(),pass + " 00:00:00", now + " 23:59:59");
        //测试用
//        ResultSet rs = jdbcUtil.execQuery(sql, user.getUserid(),pass, now);

        try {
            List<Message> list = new ArrayList<>();
            UserService userService = new UserServiceImpl();
            RoomService roomService = new RoomServiceImpl();
            MessageTypeService messageTypeService = new MessageTypeServiceImpl();
            while (rs.next()){
                Message order = new Message();
                order.setMessageid(rs.getInt(1));
                order.setUser(userService.getUser(rs.getInt(2)));
                order.setRoom(roomService.getRoom(rs.getInt(3)));
                order.setMessageType(messageTypeService.getMessageType(rs.getInt(4)));
                order.setContent(rs.getString(5));
                order.setTime(rs.getDate(6));
                list.add(order);
            }
            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> queryMessage(int roomid, int count) {
        String sql = "select * from graduation_message where roomid = ? ORDER BY time DESC limit 0,?";

        ResultSet rs = jdbcUtil.execQuery(sql,roomid,count);
        try {
            List<Message> list = new ArrayList<>();
            UserService userService = new UserServiceImpl();
            RoomService roomService = new RoomServiceImpl();
            MessageTypeService messageTypeService = new MessageTypeServiceImpl();
            while (rs.next()){
                Message order = new Message();
                order.setMessageid(rs.getInt(1));
                order.setUser(userService.getUser(rs.getInt(2)));
                order.setRoom(roomService.getRoom(rs.getInt(3)));
                order.setMessageType(messageTypeService.getMessageType(rs.getInt(4)));
                order.setContent(rs.getString(5));
                order.setTime(rs.getDate(6));
                list.add(order);
            }
            List<Message> messageList = new ArrayList<>();
            for (int i = list.size()-1; i >= 0; i--) {
                messageList.add(list.get(i));
            }

            return messageList;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> queryMessage(User user,Room room,  MessageType messageType, String pass, String now) {


        String sql = null;
        ResultSet rs = null;

        String userid = "";
        String roomid = "";
        String typeid = "";
        if (user!=null){
//            userid = user.getUserid().toString();
            userid = user.getUserid()+"";
        }
        if (room!= null){
//            roomid = String.valueOf(room.getRoomid());
            roomid = room.getRoomid()+"";
        }
        if (messageType!=null){
            typeid = messageType.getTypeid()+"";
        }

        if("".equals(pass) && "".equals(now)) {
            sql = "select * from graduation_message where userid like ? and roomid like ? and typeid like ?";
            rs = jdbcUtil.execQuery(sql, "%" + userid + "%", "%" + typeid + "%","%" + roomid + "%");
        }else{
            sql = "select * from graduation_message where userid like ? and roomid like ? and typeid like ? and time >= ? and time<=?";
            rs = jdbcUtil.execQuery(sql, "%" + userid + "%", "%" + typeid + "%", "%" + roomid + "%", pass + " 00:00:00", now + " 23:59:59");
        }

        try {
            List<Message> list = new ArrayList<>();
            UserService userService = new UserServiceImpl();
            RoomService roomService = new RoomServiceImpl();
            MessageTypeService messageTypeService = new MessageTypeServiceImpl();
            while (rs.next()){
                Message order = new Message();
                order.setMessageid(rs.getInt(1));
                order.setUser(userService.getUser(rs.getInt(2)));
                order.setRoom(roomService.getRoom(rs.getInt(3)));
                order.setMessageType(messageTypeService.getMessageType(rs.getInt(4)));
                order.setContent(rs.getString(5));
                order.setTime(rs.getDate(6));
                list.add(order);
            }
            return list;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertMessage(Message message) {
        //messageid,userid,roomid,typeid,content,time
        String sql = "insert into graduation_message values(null,?,?,?,?,?)";

        return jdbcUtil.execUpdate(sql,message.getUser().getUserid(),message.getRoom().getRoomid(),message.getMessageType().getTypeid(),message.getContent(),message.getTime());
    }
}
