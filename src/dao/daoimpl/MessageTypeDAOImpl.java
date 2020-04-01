package dao.daoimpl;

import dao.MessageTypeDAO;
import entity.MessageType;
import entity.RoomType;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageTypeDAOImpl implements MessageTypeDAO {

    private JDBCUtil jdbcUtil = JDBCUtil.getInstance();

    @Override
    public MessageType queryTypeByID(int id) {
        String sql = "select * from graduation_messagetype where typeid = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,id);
        try{
            if (rs.next()){
                MessageType messageType = new MessageType();
                messageType.setTypeid(rs.getInt(1));
                messageType.setTypename(rs.getString(2));
                return messageType;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MessageType queryTypeByName(String name) {
        String sql = "select * from graduation_messagetype where typename = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,name);
        try{
            if (rs.next()){
                MessageType messageType = new MessageType();
                messageType.setTypeid(rs.getInt(1));
                messageType.setTypename(rs.getString(2));
                return messageType;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
