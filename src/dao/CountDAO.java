package dao;

import entity.User;

public interface CountDAO {
    int getCount(User user);

    boolean insertCount(User user);

    boolean updateCount(User user);

    boolean releaseCount(User user);

    boolean updateUser(User user, int count);
}
