package service;

import entity.Room;
import entity.RoomType;

import java.util.List;

public interface RoomService {
    Room getRoom(int roomid);

    boolean addRoom(Room room);

    //获得room
    int getTotalCount();
    int getTotalCount(int typeid);

    List<Room> getRoomList();
    List<Room> getRoomList(int currentPage,int pageSize);
    List<Room> getRoomList(RoomType gameType);
    List<Room> getRoomList(RoomType gameType,int currentPage,int pageSize);
    List<Room> getRoomList(int typeid);
    List<Room> getRoomList(int typeid,int currentPage,int pageSize);
    List<Room> getRoomList(String gamename,int currentPage,int pageSize);
}
