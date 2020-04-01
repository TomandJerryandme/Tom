package dao.daoimpl;

import dao.RoomTypeDAO;
import entity.RoomType;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDAOImpl implements RoomTypeDAO {

    private JDBCUtil jdbcUtil = JDBCUtil.getInstance();

    @Override
    public RoomType getType(int typeid) {
        String sql = "select * from graduation_roomtype where typeid = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,typeid);
        try{
            if (rs.next()){
                RoomType roomType = new RoomType();
                roomType.setTypeid(rs.getInt(1));
                roomType.setTypename(rs.getString(2));
                return roomType;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RoomType> getType() {
        String sql = "select * from graduation_roomtype";
        ResultSet rs = jdbcUtil.execQuery(sql);
        try{
            List<RoomType> list = new ArrayList<>();
            while (rs.next()){
                RoomType roomType = new RoomType();
                roomType.setTypeid(rs.getInt(1));
                roomType.setTypename(rs.getString(2));
                list.add(roomType);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
