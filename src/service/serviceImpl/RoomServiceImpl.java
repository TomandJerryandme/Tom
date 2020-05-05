package service.serviceImpl;

import dao.RoomDAO;
import dao.daoimpl.RoomDAOImpl;
import entity.Room;
import entity.RoomType;
import service.RoomService;
import service.RoomTypeService;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    RoomDAO roomDAO = new RoomDAOImpl();
    RoomTypeService roomTypeService = new RoomTypeServiceImpl();
    @Override
    public Room getRoom(int roomid) {
        return roomDAO.queryRoomByID(roomid);
    }

    @Override
    public Room getRoom(int user1, int user2) {
        return roomDAO.queryRoom(user1,user2);
    }

    @Override
    public boolean addRoom(Room room) {
        return roomDAO.insert(room);
    }

    @Override
    public int getTotalCount(int temp) {
        return roomDAO.getTotalCount(temp);
    }

    @Override
    public int getTotalCount(int typeid,int temp) {
        return roomDAO.getTotalCount(typeid,temp);
    }

    @Override
    public List<Room> getRoomList(int temp) {
        return roomDAO.queryRoom(temp);
    }

    @Override
    public List<Room> getRoomList(int currentPage, int pageSize,int temp) {
        return roomDAO.queryRoomList(currentPage, pageSize,temp);
    }

    @Override
    public List<Room> getRoomList(RoomType roomType,int temp) {
        return roomDAO.queryRoomList(roomType,temp);
    }

    @Override
    public List<Room> getRoomList(RoomType roomType, int currentPage, int pageSize,int temp) {
        return roomDAO.queryRoomList(roomType.getTypeid(),currentPage,pageSize,temp);
    }

    @Override
    public List<Room> getRoomList(int typeid,int temp) {
        return roomDAO.queryRoomList(typeid,temp);
    }

    @Override
    public List<Room> getRoomList(int typeid, int currentPage, int pageSize,int temp) {
        return roomDAO.queryRoomList(typeid, currentPage, pageSize,temp);
    }

    @Override
    public List<Room> getRoomList(String roomname,int currentPage,int pageSize) {
        return roomDAO.queryRoomList(roomname,1,8);
    }

    @Override
    public boolean HasRoom(int user1, int user2) {
        return roomDAO.HasRoom(user1, user2);
    }

}
