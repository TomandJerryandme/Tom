package dao;

import entity.RoomType;

import java.util.List;

public interface RoomTypeDAO {

    RoomType getType(int typeid);

    List<RoomType> getType();
}
