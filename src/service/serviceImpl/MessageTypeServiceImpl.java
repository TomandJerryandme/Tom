package service.serviceImpl;

import dao.MessageTypeDAO;
import dao.daoimpl.MessageTypeDAOImpl;
import entity.MessageType;
import service.MessageTypeService;

public class MessageTypeServiceImpl implements MessageTypeService {
    MessageTypeDAO messageTypeDAO = new MessageTypeDAOImpl();
    @Override
    public MessageType getMessageType(int messageTypeid) {
        return messageTypeDAO.queryTypeByID(messageTypeid);
    }
}
