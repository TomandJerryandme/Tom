package dao;

import entity.MessageType;

import java.util.List;

public interface MessageTypeDAO {
    //获得消息种类
    MessageType queryTypeByID(int id);

    MessageType queryTypeByName(String name);
}
