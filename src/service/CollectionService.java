package service;

import entity.Collection;

import java.util.List;

public interface CollectionService {

    boolean addCollection(Collection collection);

    boolean removeCollection(int userid,int roomid);
    boolean removeCollection(int userid);

    List<Collection> getUserCollection(int userid);
    List<Collection> getUserCollection(int userid,int currentPage,int pageSize);


    boolean hasCollection(int userid, int gameid);

    int getTotalCount(int userid);

    Collection findCollection(int userid, int gameid);
    
    
}
