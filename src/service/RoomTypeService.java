package service;

import entity.RoomType;

import java.util.List;

public interface RoomTypeService {
    RoomType getType(int typeid);

    List<RoomType> getType();
}
