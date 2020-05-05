package dao;

import entity.Room;
import entity.RoomType;

import java.util.List;

public interface RoomDAO {

    List<Room> queryRoom(int temp);

    Room queryRoomByID(int id);
    Room queryRoom(int user1,int user2);

    boolean insert(Room room);

    List<Room> queryRoomList(int pageSize,int currentPage,int temp);

    List<Room> queryRoomList(RoomType roomType, int temp);
    List<Room> queryRoomList(int typeid, int temp);
    List<Room> queryRoomList(int typeid,int currentPage,int pageSize,int temp);
    int getTotalCount(int temp);
    int getTotalCount(int typeid,int temp);

    //获得游戏列表，通过游戏名，主要用于用户查询游戏时使用
    List<Room> queryRoomList(String roomname,int currentPage,int pageSize);


    boolean HasRoom(int user1,int user2);
}
