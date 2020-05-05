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
/*
    @Override
    public List<Message> findMessage() {
        return messageDAO.queryMessage();
    }*/

    @Override
    public List<Message> findMessage(int currentPage) {
        return messageDAO.queryMessagePage(currentPage);
    }

    @Override
    public List<Message> findMessage(User user,int currentPage) {
        return messageDAO.queryMessage(user,currentPage);
    }

    @Override
    public List<Message> findMessageList() {
        return messageDAO.queryMessageList();
    }

/*
    @Override
    public List<Message> findMessage(String pass, String now) {
        return messageDAO.queryMessage(pass, now);
    }

    @Override
    public List<Message> findMessage(MessageType messageType) {
        return messageDAO.queryMessage(messageType);
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
*/

    @Override
    public List<Message> findMessage(User user, Room room, MessageType messageType, String pass, String now,int currentPage) {
        return messageDAO.queryMessage(user, room, messageType, pass, now,currentPage);
    }

    @Override
    public int getTotalCount(User user, Room room, MessageType messageType, String pass, String now) {
        return messageDAO.getTotalCount(user, room, messageType, pass, now);
    }

    @Override
    public List<Message> findMessage(int roomid, int count) {
        return messageDAO.queryMessage(roomid,count);
    }



    @Override
    public Message findMessageByID(int messageid) {
        return messageDAO.queryMessage(messageid);
    }

    @Override
    public boolean removeMessage(int messageid){
        return messageDAO.deleteMessage(messageid);
    }

    @Override
    public Message findMessageManager(int userid) {
        return messageDAO.queryMessageManager(userid);
    }
}
