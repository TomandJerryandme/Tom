package service;



import entity.User;

import java.util.List;
//import com.neuedu.vo.UserPage;

public interface UserService {

    boolean checkUsername(String username);

    User register(User user);

    User login(String username, String password);

//    List<User> getUserList(String username, String gender, String job, String begintime, String endtime);

    boolean deleteUser(int userid);

    boolean changeUserMessage(User user);

    User getUser(int id);
    User getUser(String name);
    List<User> getUserList();
    List<User> getUserList(int typeid);


}
