package dao.daoimpl;

import dao.WordDAO;
import entity.Word;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAOImpl implements WordDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInstance();
    @Override
    public boolean insertWord(String word) {
        String sql = "insert into graduation_word values(null,?)";
        return jdbcUtil.execUpdate(sql,word);
    }

    @Override
    public boolean deleteWord(String word) {
        String sql = "delete from graduation_word where word = ?";

        return jdbcUtil.execUpdate(sql,word);
    }

    @Override
    public List<Word> queryWords() {
        String sql = "select * from graduation_word";
        ResultSet rs = jdbcUtil.execQuery(sql);
        try{
            List<Word> list = new ArrayList<>();
            while (rs.next()){
                Word word = new Word();
                word.setId(rs.getInt(1));
                word.setWordContent(rs.getString(2));
                list.add(word);
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean hasWord(String word) {
        String sql = "select * from graduation_word where word = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,word);
        try{
            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Word findWord(String word) {
        String sql = "select * from graduation_word where word = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,word);
        try{
            if (rs.next()){
                Word word1 = new Word();
                word1.setId(rs.getInt(1));
                word1.setWordContent(rs.getString(2));
                return word1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
