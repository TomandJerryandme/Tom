package service;

import entity.User;

public interface CountService {
    int getCount(User user);

    boolean addCount(User user);

    boolean updateCount(User user);

    boolean releaseCount(User user);

    boolean updateUser(User user, int count);
}
