package service;

import entity.Message;
import entity.MessageType;
import entity.Room;
import entity.User;

import java.util.List;

public interface MessageService {
    //消息类表中最重要的功能是添加消息与查询
    boolean addMessage(Message message);

//    List<Message> findMessage();

    List<Message> findMessage(int currentPage);

    List<Message> findMessage(User user,int currentPage);

    List<Message> findMessageList();

/*
    List<Message> findMessage(String pass, String now);

    List<Message> findMessage(MessageType messageType);

    List<Message> findMessage(User user, String pass, String now);

    List<Message> findMessageByRoom(Room room);

    List<Message> findMessage(User user, Room room);

    List<Message> findMessage(User user, Room room, MessageType messageType);

    List<Message> findMessage(User user, Room room, String pass, String now);

    List<Message> findMessage(User user,MessageType messageType);
*/

    List<Message> findMessage(User user, Room room, MessageType messageType, String pass, String now,int currentPage);

    int getTotalCount(User user, Room room, MessageType messageType, String pass, String now);

    //这个方法不能改
    List<Message> findMessage(int roomid, int count);

    Message findMessageByID(int messageid);

    boolean removeMessage(int messageid);

    Message findMessageManager(int userid);
}
