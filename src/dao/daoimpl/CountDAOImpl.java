package dao.daoimpl;

import dao.CountDAO;
import entity.User;
import service.CountService;
import service.serviceImpl.CountServiceImpl;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountDAOImpl implements CountDAO {

    private JDBCUtil jdbcUtil = JDBCUtil.getInstance();

    @Override
    public int getCount(User user) {
        String sql = "select * from graduation_count where userid = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,user.getUserid());
        try{
            if (rs.next()){
                return rs.getInt(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean insertCount(User user) {
        String sql = "insert into graduation_count values(?,0)";
        return jdbcUtil.execUpdate(sql,user.getUserid());
    }

    @Override
    public boolean updateCount(User user) {
        CountService countService = new CountServiceImpl();
        int count = countService.getCount(user);
        String sql = "update graduation_count set count = ? where userid = ?";
        return jdbcUtil.execUpdate(sql,count+1,user.getUserid());
    }

    @Override
    public boolean releaseCount(User user) {

        String sql = "update graduation_count set count = 0 where userid = ?";
        return jdbcUtil.execUpdate(sql,user.getUserid());
    }

    @Override
    public boolean updateUser(User user, int count) {
        String sql = "update graduation_count set count = ? where userid = ?";
        return jdbcUtil.execUpdate(sql,count,user.getUserid());
    }
}
