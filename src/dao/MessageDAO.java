package dao;



import entity.Message;
import entity.MessageType;
import entity.Room;
import entity.User;
import java.util.List;

public interface MessageDAO {
    //消息列表的主要作用是用来查询消息，插入消息，但一般来说，消息的查询是多条查询的

    int getTotalCount(User user,Room room,MessageType type,String pass,String now);

    Message queryMessage(int messageid);
    Message queryMessageManager(int userid);

    //查询全部消息
    List<Message> queryMessage();
    List<Message> queryMessagePage(int currentPage);

    //按照消息类型查询消息
    List<Message> queryMessage(MessageType messageType);

    //按照消息发出的时间来查询消息
    List<Message> queryMessage(String pass,String now);

    //按照用户来查询消息
    List<Message> queryMessage(User user,int currentPage);

    //用户按时间查询
    List<Message> queryMessage(User user,String pass,String now);

    List<Message> queryMessage(int roomid,int count);

    List<Message> queryMessageList();


    //基本上，这个方法可以完全够用
    List<Message> queryMessage(User user,Room room,  MessageType messageType, String pass, String now,int currentPage);


    //插入消息
    boolean insertMessage(Message message);

    boolean deleteMessage(int messageid);
}
