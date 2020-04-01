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
    public boolean addRoom(Room room) {
        return roomDAO.insert(room);
    }

    @Override
    public int getTotalCount() {
        return roomDAO.getTotalCount();
    }

    @Override
    public int getTotalCount(int typeid) {
        return roomDAO.getTotalCount(typeid);
    }

    @Override
    public List<Room> getRoomList() {
        return roomDAO.queryRoom();
    }

    @Override
    public List<Room> getRoomList(int currentPage, int pageSize) {
        return roomDAO.queryRoomList(currentPage, pageSize);
    }

    @Override
    public List<Room> getRoomList(RoomType roomType) {
        return roomDAO.queryRoomList(roomType);
    }

    @Override
    public List<Room> getRoomList(RoomType roomType, int currentPage, int pageSize) {
        return roomDAO.queryRoomList(roomType.getTypeid(),currentPage,pageSize);
    }

    @Override
    public List<Room> getRoomList(int typeid) {
        return roomDAO.queryRoomList(typeid);
    }

    @Override
    public List<Room> getRoomList(int typeid, int currentPage, int pageSize) {
        return roomDAO.queryRoomList(typeid, currentPage, pageSize);
    }

    @Override
    public List<Room> getRoomList(String roomname,int currentPage,int pageSize) {
        return roomDAO.queryRoomList(roomname,1,8);
    }

}
