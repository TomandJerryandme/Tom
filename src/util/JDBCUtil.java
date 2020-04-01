package util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {

    private static DruidDataSource ds;

    static {

        try {

            //加载属性文件
            Properties prop = new Properties();

            prop.load(JDBCUtil.class.getResourceAsStream("jdbc-config.properties"));

            //通过DruidDataSourceFactory类的工厂方建连接池对象
            ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(prop);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Connection conn = null;
    private PreparedStatement pstmt = null;

    //改造成单例类
    private static JDBCUtil instance = new JDBCUtil();
    public static JDBCUtil getInstance(){
        return instance;
    }

    private JDBCUtil(){
    }

    private void openConnection(){

        //从ThreadLocal中获取连接对象
        conn = ConnectionUtil.getConnection();
    }

    //数据更新
    public boolean execUpdate(String sql, Object... params){

        try {
            openConnection();

            pstmt = conn.prepareStatement(sql);

            //参数赋值
            for(int i=0; i<params.length; i++){
                pstmt.setObject(i+1, params[i]);
            }

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }

    }

    //数据更新
    public boolean execUpdate(Connection conn, String sql, Object... params){

        try {
            //openConnection();

            pstmt = conn.prepareStatement(sql);

            //参数赋值
            for(int i=0; i<params.length; i++){
                pstmt.setObject(i+1, params[i]);
            }

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }

    }

    //数据查询
    public ResultSet execQuery(String sql, Object... params){

        try {
            openConnection();

            pstmt = conn.prepareStatement(sql);

            //参数赋值
            for(int i=0; i<params.length; i++){
                pstmt.setObject(i+1, params[i]);
            }

            return pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ResultSet execQuery(Connection conn, String sql, Object... params) {

        try {
            //openConnection();

            pstmt = conn.prepareStatement(sql);

            //参数赋值
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            return pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //关闭连接
    public void closeConnection() {

//        try {
//            if(pstmt!=null) {
//                pstmt.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            if(conn!=null) {
//                conn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {

        JDBCUtil jdbcUtil = JDBCUtil.getInstance();
        //String sql = "insert into user values(null, ? , ?, ?)";
        //String sql = "update user set score = score + ? where userid = ?";
        //String sql = "delete from user";
        //jdbcUtil.execUpdate(sql);
        String sql = "select * from dept";
        ResultSet rs = jdbcUtil.execQuery(sql);
        try {
            while(rs.next()){
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeConnection();
        }
    }
}
