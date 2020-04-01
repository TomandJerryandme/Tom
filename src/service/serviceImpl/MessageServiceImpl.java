package service.serviceImpl;

import dao.MessageDAO;
import dao.daoimpl.MessageDAOImpl;
import entity.Message;
import entity.MessageType;
import entity.Room;
import entity.User;
import service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    MessageDAO messageDAO = new MessageDAOImpl();

    @Override
    public boolean addMessage(Message message) {
        return messageDAO.insertMessage(message);
    }

    @Override
    public List<Message> findMessageByType(MessageType messageType) {
        return messageDAO.queryMessage(messageType);
    }

    @Override
    public List<Message> findMessageByUser(User user) {
        return messageDAO.queryMessage(user);
    }

    @Override
    public List<Message> findMessageByTime(String pass, String now) {
        return messageDAO.queryMessage(pass, now);
    }

    @Override
    public List<Message> findMessage() {
        return messageDAO.queryMessage();
    }

    @Override
    public List<Message> findMessage(User user, String pass, String now) {
        return messageDAO.queryMessage(user,pass,now);
    }

    @Override
    public List<Message> findMessageByRoom(Room room) {
        return messageDAO.queryMessage(null,room,null,null,null);
    }

    @Override
    public List<Message> findMessage(User user, Room room) {
        return messageDAO.queryMessage(user,room,null,null,null);
    }

    @Override
    public List<Message> findMessage(User user, Room room, MessageType messageType) {
        return messageDAO.queryMessage(user,room,messageType,null,null);
    }

    @Override
    public List<Message> findMessage(User user, Room room, String pass, String now) {
        return messageDAO.queryMessage(user,room,null,pass,now);
    }

    @Override
    public List<Message> findMessage(User user, MessageType messageType) {
        return messageDAO.queryMessage(user,null,messageType,null,null);
    }

    @Override
    public List<Message> findMessage(User user, Room room, MessageType messageType, String pass, String now) {
        return messageDAO.queryMessage(user, room, messageType, pass, now);
    }

    @Override
    public List<Message> findMessage(int roomid, int count) {
        return messageDAO.queryMessage(roomid,count);
    }

    @Override
    public Message findMessage(int messageid) {
        return messageDAO.queryMessage(messageid);
    }
}
