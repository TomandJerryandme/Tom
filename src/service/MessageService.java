package service;

import entity.Message;
import entity.MessageType;
import entity.Room;
import entity.User;

import java.util.List;

public interface MessageService {
    //消息类表中最重要的功能是添加消息与查询
    boolean addMessage(Message message);

    List<Message> findMessageByType(MessageType messageType);

    List<Message> findMessageByUser(User user);

    List<Message> findMessageByTime(String pass, String now);

    List<Message> findMessage();

    List<Message> findMessage(User user, String pass, String now);

    List<Message> findMessageByRoom(Room room);

    List<Message> findMessage(User user, Room room);

    List<Message> findMessage(User user, Room room, MessageType messageType);

    List<Message> findMessage(User user, Room room, String pass, String now);

    List<Message> findMessage(User user,MessageType messageType);

    List<Message> findMessage(User user, Room room, MessageType messageType, String pass, String now);

    List<Message> findMessage(int roomid, int count);

    Message findMessage(int messageid);
}
