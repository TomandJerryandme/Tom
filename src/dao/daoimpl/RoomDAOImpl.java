package dao.daoimpl;

import dao.RoomDAO;
import entity.Room;
import entity.RoomType;
import service.RoomTypeService;
import service.UserService;
import service.serviceImpl.RoomTypeServiceImpl;
import service.serviceImpl.UserServiceImpl;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInstance();
    private UserService userService = new UserServiceImpl();

    @Override
    public List<Room> queryRoom(int temp) {
        String sql = "select * from graduation_room where type = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,temp);
        try {
            List<Room> list = new ArrayList<>();
            RoomTypeService roomTypeService = new RoomTypeServiceImpl();
            while (rs.next()){
                Room room = new Room();
                room.setRoomid(rs.getInt(1));
                room.setRoomname(rs.getString(2));
                room.setRoomtype(roomTypeService.getType(rs.getInt(3)));
                room.setRoomphoto(rs.getString(4));
                room.setIntroduce(rs.getString(5));
                room.setTruename(rs.getString(6));
                room.setType(rs.getInt(7));
                room.setUser1(rs.getInt(8));
                room.setUser2(rs.getInt(9));
                list.add(room);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Room queryRoomByID(int id) {

        String sql = "select * from graduation_room where roomid = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,id);
        try {
            RoomTypeService roomTypeService = new RoomTypeServiceImpl();
            if (rs.next()){
                Room room = new Room();
                room.setRoomid(rs.getInt(1));
                room.setRoomname(rs.getString(2));
                room.setRoomtype(roomTypeService.getType(rs.getInt(3)));
                room.setRoomphoto(rs.getString(4));
                room.setIntroduce(rs.getString(5));
                room.setTruename(rs.getString(6));
                room.setType(rs.getInt(7));
                room.setUser1(rs.getInt(8));
                room.setUser2(rs.getInt(9));
                return room;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Room queryRoom(int user1, int user2) {

        String sql = "select * from graduation_room where (user1 = ? and user2 = ?) or (user2 = ? and user1 = ?)";
        ResultSet rs = jdbcUtil.execQuery(sql,user1,user2,user1,user2);
        try {
            RoomTypeService roomTypeService = new RoomTypeServiceImpl();
            if (rs.next()){
                Room room = new Room();
                room.setRoomid(rs.getInt(1));
                room.setRoomname(rs.getString(2));
                room.setRoomtype(roomTypeService.getType(rs.getInt(3)));
                room.setRoomphoto(rs.getString(4));
                room.setIntroduce(rs.getString(5));
                room.setTruename(rs.getString(6));
                room.setType(rs.getInt(7));
                room.setUser1(rs.getInt(8));
                room.setUser2(rs.getInt(9));
                return room;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(Room room) {
        String sql = "insert into graduation_room values(null,?,?,?,?,?,?,?,?)";
        return jdbcUtil.execUpdate(sql,room.getRoomname(),room.getRoomtype().getTypeid(),room.getRoomphoto(),room.getIntroduce(),room.getTruename(),room.getType(),room.getUser1(),room.getUser2());
    }

    @Override
    public List<Room> queryRoomList(int currentPage, int pageSize,int temp) {
        String sql = "select * from graduation_room where type = ? limit ?,?";
        ResultSet rs = jdbcUtil.execQuery(sql,temp,(currentPage-1)*pageSize,pageSize);
        try {
            List<Room> list = new ArrayList<>();
            RoomTypeService roomTypeService = new RoomTypeServiceImpl();
            while (rs.next()){
                Room room = new Room();
                room.setRoomid(rs.getInt(1));
                room.setRoomname(rs.getString(2));
                room.setRoomtype(roomTypeService.getType(rs.getInt(3)));
                room.setRoomphoto(rs.getString(4));
                room.setIntroduce(rs.getString(5));
                room.setTruename(rs.getString(6));
                room.setType(rs.getInt(7));

                room.setUser1(rs.getInt(8));
                room.setUser2(rs.getInt(9));
                list.add(room);
            }
            System.out.println(list);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Room> queryRoomList(RoomType roomType,int temp) {
        String sql = "select * from graduation_room where roomtype = ? and type = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,roomType.getTypeid(),temp);
        try {
            List<Room> list = new ArrayList<>();
            RoomTypeService roomTypeService = new RoomTypeServiceImpl();
            while (rs.next()){
                Room room = new Room();
                room.setRoomid(rs.getInt(1));
                room.setRoomname(rs.getString(2));
                room.setRoomtype(roomTypeService.getType(rs.getInt(3)));
                room.setRoomphoto(rs.getString(4));
                room.setIntroduce(rs.getString(5));
                room.setTruename(rs.getString(6));
                room.setType(rs.getInt(7));
                room.setUser1(rs.getInt(8));
                room.setUser2(rs.getInt(9));
                list.add(room);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Room> queryRoomList(int typeid, int temp) {
        String sql = "select * from graduation_room where roomtype = ? and type = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,typeid,temp);
        try {
            List<Room> list = new ArrayList<>();
            RoomTypeService roomTypeService = new RoomTypeServiceImpl();
            while (rs.next()){
                Room room = new Room();
                room.setRoomid(rs.getInt(1));
                room.setRoomname(rs.getString(2));
                room.setRoomtype(roomTypeService.getType(rs.getInt(3)));
                room.setRoomphoto(rs.getString(4));
                room.setIntroduce(rs.getString(5));
                room.setTruename(rs.getString(6));
                room.setType(rs.getInt(7));
                room.setUser1(rs.getInt(8));
                room.setUser2(rs.getInt(9));
                list.add(room);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Room> queryRoomList(int typeid, int currentPage, int pageSize,int temp) {
        String sql = "select * from graduation_room where roomtype = ? and type = ? limit ?,?";
        ResultSet rs = jdbcUtil.execQuery(sql,typeid,temp,(currentPage-1)*pageSize,pageSize);
        try {
            List<Room> list = new ArrayList<>();
            RoomTypeService roomTypeService = new RoomTypeServiceImpl();
            while (rs.next()){
                Room room = new Room();
                room.setRoomid(rs.getInt(1));
                room.setRoomname(rs.getString(2));
                room.setRoomtype(roomTypeService.getType(rs.getInt(3)));
                room.setRoomphoto(rs.getString(4));
                room.setIntroduce(rs.getString(5));
                room.setTruename(rs.getString(6));
                room.setType(rs.getInt(7));
                room.setUser1(rs.getInt(8));
                room.setUser2(rs.getInt(9));
                list.add(room);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getTotalCount(int temp) {
        String sql = "select count(*) from graduation_room where type = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,temp);
        try{
            if (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int getTotalCount(int typeid, int temp) {
        String sql = "select count(*) from graduation_room where roomtype = ? and type = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,typeid,temp);
        try{
            if (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Room> queryRoomList(String roomname,int currentPage, int pageSize) {
        //模糊查询时使用该方法
        String sql = "select * from graduation_room where roomname like ? limit ?,?";
        ResultSet rs = jdbcUtil.execQuery(sql,"%"+roomname+"%",(currentPage-1)*pageSize,pageSize);
        try {
            List<Room> list = new ArrayList<>();
            RoomTypeService roomTypeService = new RoomTypeServiceImpl();
            while (rs.next()){
                Room room = new Room();
                room.setRoomid(rs.getInt(1));
                room.setRoomname(rs.getString(2));
                room.setRoomtype(roomTypeService.getType(rs.getInt(3)));
                room.setRoomphoto(rs.getString(4));
                room.setIntroduce(rs.getString(5));
                room.setTruename(rs.getString(6));
                room.setType(rs.getInt(7));
                room.setUser1(rs.getInt(8));
                room.setUser2(rs.getInt(9));
                list.add(room);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean HasRoom(int user1, int user2) {
        String sql = "select * from graduation_room where (user1 = ? and user2 = ?) or (user2 = ? and user1 = ?)";
        ResultSet rs = jdbcUtil.execQuery(sql,user1,user2,user1,user2);
        try {
            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
