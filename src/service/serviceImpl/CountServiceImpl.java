package service.serviceImpl;

import dao.CountDAO;
import dao.daoimpl.CountDAOImpl;
import entity.User;
import service.CountService;

public class CountServiceImpl implements CountService {

    private CountDAO countDAO = new CountDAOImpl();

    @Override
    public int getCount(User user) {
        return countDAO.getCount(user);
    }

    @Override
    public boolean addCount(User user) {
        return countDAO.insertCount(user);
    }

    @Override
    public boolean updateCount(User user) {
        return countDAO.updateCount(user);
    }

    @Override
    public boolean releaseCount(User user) {
        return countDAO.releaseCount(user);
    }

    @Override
    public boolean updateUser(User user, int count) {
        return countDAO.updateUser(user,count);
    }
}
