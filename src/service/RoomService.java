package service;

import entity.Room;
import entity.RoomType;

import java.util.List;

public interface RoomService {
    Room getRoom(int roomid);
    Room getRoom(int user1, int user2);

    boolean addRoom(Room room);

    //获得room
    int getTotalCount(int temp);
    int getTotalCount(int typeid,int temp);

    List<Room> getRoomList(int temp);
    List<Room> getRoomList(int currentPage,int pageSize,int temp);
    List<Room> getRoomList(RoomType gameType,int temp);
    List<Room> getRoomList(RoomType gameType,int currentPage,int pageSize,int temp);
    List<Room> getRoomList(int typeid,int temp);
    List<Room> getRoomList(int typeid,int currentPage,int pageSize,int temp);
    List<Room> getRoomList(String gamename,int currentPage,int pageSize);

    boolean HasRoom(int user1, int user2);
}
