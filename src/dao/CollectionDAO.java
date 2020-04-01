package dao;

import entity.Collection;

import java.util.List;

public interface CollectionDAO {
    //查询所有收藏
    List<Collection> queryCollectionList(int userid);
    List<Collection> queryCollectionList(int userid,int currentPage,int pageSize);

    int getTotalCount(int userid);

    boolean insertCollection(Collection collection);

    Collection getCollection(int userid, int roomid);

    boolean deleteRoom(int userid,int roomid);
    boolean deleteRoom(int userid);

    //该用户已经收藏过该聊天室
    boolean hasRoom(int userid,int roomid);
}
