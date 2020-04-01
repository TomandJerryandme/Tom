package dao;

import entity.Room;
import entity.RoomType;

import java.util.List;

public interface RoomDAO {

    List<Room> queryRoom();

    Room queryRoomByID(int id);

    boolean insert(Room room);

    List<Room> queryRoomList(int pageSize,int currentPage);

    List<Room> queryRoomList(RoomType roomType);
    List<Room> queryRoomList(int typeid);
    List<Room> queryRoomList(int typeid,int currentPage,int pageSize);
    int getTotalCount();
    int getTotalCount(int typeid);

    //获得游戏列表，通过游戏名，主要用于用户查询游戏时使用
    List<Room> queryRoomList(String roomname,int currentPage,int pageSize);


}
