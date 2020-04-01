package dao.daoimpl;

import dao.RoomDAO;
import entity.Room;
import entity.RoomType;
import service.RoomTypeService;
import service.serviceImpl.RoomTypeServiceImpl;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInstance();
    @Override
    public List<Room> queryRoom() {
        String sql = "select * from graduation_room";
        ResultSet rs = jdbcUtil.execQuery(sql);
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
                return room;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(Room room) {
        String sql = "insert into graduation_room values(null,?,?,?,?,?)";
        return jdbcUtil.execUpdate(sql,room.getRoomname(),room.getRoomtype().getTypeid(),room.getRoomphoto(),room.getIntroduce(),room.getTruename());
    }

    @Override
    public List<Room> queryRoomList(int currentPage, int pageSize) {
        String sql = "select * from graduation_room limit ?,?";
        ResultSet rs = jdbcUtil.execQuery(sql,(currentPage-1)*pageSize,pageSize);
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
    public List<Room> queryRoomList(RoomType roomType) {
        String sql = "select * from graduation_room where roomtype = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,roomType.getTypeid());
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
                list.add(room);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Room> queryRoomList(int typeid) {
        String sql = "select * from graduation_room where roomtype = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,typeid);
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
                list.add(room);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Room> queryRoomList(int typeid, int currentPage, int pageSize) {
        String sql = "select * from graduation_room where roomtype = ? limit ?,?";
        ResultSet rs = jdbcUtil.execQuery(sql,typeid,(currentPage-1)*pageSize,pageSize);
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
                list.add(room);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getTotalCount() {
        String sql = "select count(*) from graduation_room";
        ResultSet rs = jdbcUtil.execQuery(sql);
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
    public int getTotalCount(int typeid) {
        String sql = "select count(*) from graduation_room where roomtype = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,typeid);
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
                list.add(room);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
