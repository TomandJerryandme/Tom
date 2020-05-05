package dao.daoimpl;

import dao.EmojiDAO;
import entity.Emoji;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmojiDAOImpl implements EmojiDAO {

    private JDBCUtil jdbcUtil = JDBCUtil.getInstance();
    @Override
    public Emoji getEmoji(int id) {
        String sql = "select * from graduation_emoji where id = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,id);
        try{
            if (rs.next()){
                Emoji emoji = new Emoji();
                emoji.setId(rs.getInt(1));
                emoji.setEmojiname(rs.getString(2));
                return emoji;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Emoji getEmoji(String picname) {
        String sql = "select * from graduation_emoji where emojiname = ?";
        ResultSet rs = jdbcUtil.execQuery(sql,picname);
        try{
            if (rs.next()){
                Emoji emoji = new Emoji();
                emoji.setId(rs.getInt(1));
                emoji.setEmojiname(rs.getString(2));
                return emoji;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Emoji> getEmojiList() {
        String sql = "select * from graduation_emoji";
        ResultSet rs = jdbcUtil.execQuery(sql);
        try{
            List<Emoji> list = new ArrayList<>();
            while (rs.next()){
                Emoji emoji = new Emoji();
                emoji.setId(rs.getInt(1));
                emoji.setEmojiname(rs.getString(2));
                list.add(emoji);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertEmoji(Emoji emoji) {
        String sql = "insert into graduation_emoji values(null,?)";

        return jdbcUtil.execUpdate(sql,emoji.getEmojiname());
    }
}
