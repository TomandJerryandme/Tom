package dao.daoimpl;


import dao.UserDAO;
import entity.User;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private JDBCUtil jdbcUtil = JDBCUtil.getInstance();

    @Override
    public User queryUserByID(int id) {
        String sql = "select * from graduation_user where id = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,id);
        try {
            if(rs.next()){
                //创建并填充实体对象
                User user = new User();
                user.setUserid(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setUserpic(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPhone(rs.getString(6));
                user.setQuestion(rs.getString(7));
                user.setAnswer(rs.getString(8));
                user.setUsertype(rs.getBoolean(9));
                user.setRegtime(rs.getDate(10));
                user.setChatime(rs.getDate(11));
                user.setGender(rs.getString(12));
                user.setTruename(rs.getString(13));
                user.setCount(rs.getInt(14));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            jdbcUtil.closeConnection();
        }
        return null;
    }

    @Override
    public User queryUserByName(String name) {
        String sql = "select * from graduation_user where username = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,name);
        try {
            if(rs.next()){
                //创建并填充实体对象
                User user = new User();
                user.setUserid(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setUserpic(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPhone(rs.getString(6));
                user.setQuestion(rs.getString(7));
                user.setAnswer(rs.getString(8));
                user.setUsertype(rs.getBoolean(9));
                user.setRegtime(rs.getDate(10));
                user.setChatime(rs.getDate(11));
                user.setGender(rs.getString(12));
                user.setTruename(rs.getString(13));
                user.setCount(rs.getInt(14));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            jdbcUtil.closeConnection();
        }
        return null;
    }

    @Override
    public User findUser(String username, String password) {

        String sql = "select * from graduation_user where username = ? and password = ?";
        ResultSet rs = jdbcUtil.execQuery(sql, username, password);

        try {
            if(rs.next()){
                //userid,username,password,score,photo,gender,job,interest,mail,telephone,regtime,用户类型
                //创建并填充实体对象
                User user = new User();
                user.setUserid(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setUserpic(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPhone(rs.getString(6));
                user.setQuestion(rs.getString(7));
                user.setAnswer(rs.getString(8));
                user.setUsertype(rs.getBoolean(9));
                user.setRegtime(rs.getDate(10));
                user.setChatime(rs.getDate(11));
                user.setGender(rs.getString(12));
                user.setTruename(rs.getString(13));
                user.setCount(rs.getInt(14));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            jdbcUtil.closeConnection();
        }

        return null;
    }

    @Override
    public boolean findUser(String username) {
        String sql = "select * from graduation_user where username = ?";
        ResultSet rs = jdbcUtil.execQuery(sql, username);

        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeConnection();
        }
        return false;
    }

    @Override
    public List<User> queryUser() {
        String sql = "select * from graduation_user";
        ResultSet rs = jdbcUtil.execQuery(sql);

        try {
            List<User> list = new ArrayList<>();
            while (rs.next()){
                //userid,username,password,score,photo,gender,job,interest,mail,telephone,regtime,用户类型
                //创建并填充实体对象
                User user = new User();
                user.setUserid(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setUserpic(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPhone(rs.getString(6));
                user.setQuestion(rs.getString(7));
                user.setAnswer(rs.getString(8));
                user.setUsertype(rs.getBoolean(9));
                user.setRegtime(rs.getDate(10));
                user.setChatime(rs.getDate(11));
                user.setGender(rs.getString(12));
                user.setTruename(rs.getString(13));
                user.setCount(rs.getInt(14));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            jdbcUtil.closeConnection();
        }
        return null;
    }

    @Override
    public boolean insertUser(User user) {
        //userid,username,password,userpic,email,phone,question,answer,用户类型，createtime,updatetime,truename,count
        String sql = "insert into graduation_user values(null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcUtil.execUpdate(sql,user.getUsername(),user.getPassword(),
                user.getUserpic(),user.getEmail(),user.getPhone(),user.getQuestion(),user.getAnswer(),user.isUsertype(),user.getRegtime(),user.getChatime(),user.getGender(),user.getTruename(),user.getCount());
    }

    @Override
    public boolean deleteUser(User user) {
        String sql = "delete from graduation_user where id = ?";
        return jdbcUtil.execUpdate(sql,user.getUserid());
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "delete from graduation_user where id = ?";
        return jdbcUtil.execUpdate(sql,id);
    }

    @Override
    public boolean deleteUser(String name) {
        String sql = "delete from graduation_user where username = ?";
        return jdbcUtil.execUpdate(sql,name);
    }

    @Override
    public boolean updateUser(User user) {
        //userid,username,password,userpic,email,phone,question,answer,用户类型，createtime,updatetime
        Date now = new Date();
        String sql = "update graduation_user set username = ?,password = ?,userpic = ?,email = ?,phone = ?,question = ?,answer = ?,update_time = ? ,gender = ? , count = ? where id = ?";
        return jdbcUtil.execUpdate(sql,user.getUsername(),user.getPassword(),user.getUserpic(),user.getEmail(),user.getPhone(),user.getQuestion(),user.getAnswer(),now
                ,user.getGender(),user.getCount(),user.getUserid());
    }


}
