package service.serviceImpl;



import dao.UserDAO;
import dao.daoimpl.UserDAOImpl;
import entity.User;
import service.UserService;

import java.util.List;

//import com.neuedu.vo.UserPage;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

/*
    @Override
    public List<User> getUserList(String username, String gender, String job, String begintime, String endtime) {

        List<User> list = userDAO.findUserList(username, gender, job, begintime, endtime);

        for (User user : list) {
            user.setUsername(StringUtil.convertKeyword(user.getUsername(), username));
        }

        return list;
    }
    */

    @Override
    public boolean checkUsername(String username) {
        return !userDAO.findUser(username);
    }

    @Override
    public User register(User user) {

        if(userDAO.insertUser(user)){
            return userDAO.findUser(user.getUsername(), user.getPassword());
        }

        return null;
    }

    @Override
    public User login(String username, String password) {
        return userDAO.findUser(username, password);
    }

    @Override
    public boolean deleteUser(int userid) {
        return userDAO.deleteUser(userid);
    }

    @Override
    public boolean changeUserMessage(User user) {

        return userDAO.updateUser(user);
    }

    @Override
    public User getUser(int id) {
        return userDAO.queryUserByID(id);
    }

    @Override
    public User getUser(String name) {
        return userDAO.queryUserByName(name);
    }

    @Override
    public List<User> getUserList() {
        return userDAO.queryUser();
    }


}
