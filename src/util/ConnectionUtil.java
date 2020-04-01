package util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

//获取连接
public class ConnectionUtil {

    private static DruidDataSource ds;

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

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

    public static Connection getConnection(){

      /*  try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        try {

            //从ThreadLocal中取出数据库连接
            Connection conn = threadLocal.get();

            if(conn==null){

                //从连接池中获取数据库连接
                conn = ds.getConnection();

                //将数据库连接保存在ThreadLocal中
                threadLocal.set(conn);
            }

            return conn;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



}
