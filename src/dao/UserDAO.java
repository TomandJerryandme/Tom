package dao;


import entity.User;

import java.util.List;

public interface UserDAO {
    User queryUserByID(int id);

    User queryUserByName(String name);

    User findUser(String username, String password);

    //注册
    boolean findUser(String username);
    //增加
    boolean insertUser(User user);

    //该方法一般是管理员来使用
    List<User> queryUser();

    //删除
    boolean deleteUser(User user);
    boolean deleteUser(int id);
    boolean deleteUser(String name);

    //修改（修改用户名或者密码等用户信息）
    boolean updateUser(User user);

}
