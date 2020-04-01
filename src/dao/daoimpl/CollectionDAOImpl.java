package dao.daoimpl;

import dao.CollectionDAO;
import entity.Collection;
import service.RoomService;
import service.UserService;
import service.serviceImpl.RoomServiceImpl;
import service.serviceImpl.UserServiceImpl;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollectionDAOImpl implements CollectionDAO {

    private JDBCUtil jdbcUtil = JDBCUtil.getInstance();


    @Override
    public List<Collection> queryCollectionList(int userid) {
        String sql = "select * from graduation_collection where userid = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,userid);

        try{
            UserService userService = new UserServiceImpl();
            RoomService roomService = new RoomServiceImpl();
            List<Collection> list = new ArrayList<>();

            while (rs.next()){
                Collection collection = new Collection();
                collection.setUser(userService.getUser(rs.getInt(1)));
                collection.setRoom(roomService.getRoom(rs.getInt(2)));
                list.add(collection);
            }
            return list;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Collection> queryCollectionList(int userid, int currentPage, int pageSize) {
        String sql = "select * from graduation_collection where userid = ? limit ?,?";
        ResultSet rs = jdbcUtil.execQuery(sql,userid,(currentPage-1)*pageSize,pageSize);
        try{
            UserService userService = new UserServiceImpl();
            RoomService roomService = new RoomServiceImpl();
            List<Collection> list = new ArrayList<>();

            while (rs.next()){
                Collection collection = new Collection();
                collection.setUser(userService.getUser(rs.getInt(1)));
                collection.setRoom(roomService.getRoom(rs.getInt(2)));
                list.add(collection);
            }
            return list;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getTotalCount(int userid) {
        String sql = "select count(*) from graduation_collection where userid = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,userid);
        try{
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean insertCollection(Collection collection) {
        String sql = "insert into graduation_collection values(?,?)";

        return jdbcUtil.execUpdate(sql,collection.getUser().getUserid(),collection.getRoom().getRoomid());
    }

    @Override
    public Collection getCollection(int userid, int roomid) {
        String sql = "select * from graduation_collection where userid = ? and roomid = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,userid,roomid);
        try{
            UserService userService = new UserServiceImpl();
            RoomService roomService = new RoomServiceImpl();
            if (rs.next()){
                Collection collection = new Collection();
                collection.setUser(userService.getUser(rs.getInt(1)));
                collection.setRoom(roomService.getRoom(rs.getInt(2)));
                return collection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteRoom(int userid, int roomid) {
        String sql = "delete from graduation_collection where userid = ? and roomid = ?";

        return jdbcUtil.execUpdate(sql,userid,roomid);
    }

    @Override
    public boolean deleteRoom(int userid) {
        String sql = "delete from graduation_collection where userid = ?";

        return jdbcUtil.execUpdate(sql,userid);
    }

    @Override
    public boolean hasRoom(int userid, int roomid) {
        String sql = "select * from graduation_collection where userid = ? and roomid = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,userid, roomid);
        try{
            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
