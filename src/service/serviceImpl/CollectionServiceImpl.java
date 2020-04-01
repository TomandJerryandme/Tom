package service.serviceImpl;

import dao.CollectionDAO;
import dao.daoimpl.CollectionDAOImpl;
import entity.Collection;
import service.CollectionService;

import java.util.List;

public class CollectionServiceImpl implements CollectionService {

    private CollectionDAO collectionDAO = new CollectionDAOImpl();

    @Override
    public boolean addCollection(Collection collection) {
        return collectionDAO.insertCollection(collection);
    }

    @Override
    public boolean removeCollection(int userid, int roomid) {
        return collectionDAO.deleteRoom(userid, roomid);
    }

    @Override
    public boolean removeCollection(int userid) {
        return collectionDAO.deleteRoom(userid);
    }

    @Override
    public List<Collection> getUserCollection(int userid) {
        return collectionDAO.queryCollectionList(userid);
    }

    @Override
    public List<Collection> getUserCollection(int userid, int currentPage, int pageSize) {
        return collectionDAO.queryCollectionList(userid, currentPage, pageSize);
    }

    @Override
    public boolean hasCollection(int userid, int gameid) {
        return collectionDAO.hasRoom(userid, gameid);
    }

    @Override
    public int getTotalCount(int userid) {
        return collectionDAO.getTotalCount(userid);
    }

    @Override
    public Collection findCollection(int userid, int gameid) {
        return collectionDAO.getCollection(userid, gameid);
    }
}
