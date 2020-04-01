package service.serviceImpl;

import dao.RoomTypeDAO;
import dao.daoimpl.RoomTypeDAOImpl;
import entity.RoomType;
import service.RoomTypeService;

import javax.annotation.Resource;
import java.util.List;

public class RoomTypeServiceImpl implements RoomTypeService {

    RoomTypeDAO typeDAO = new RoomTypeDAOImpl();

    @Override
    public RoomType getType(int typeid) {
        return typeDAO.getType(typeid);
    }

    @Override
    public List<RoomType> getType() {
        return typeDAO.getType();
    }
}
