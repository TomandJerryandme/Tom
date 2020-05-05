package dao;

import entity.Emoji;

import java.util.List;

public interface EmojiDAO {
    Emoji getEmoji(int id);
    Emoji getEmoji(String picname);

    List<Emoji> getEmojiList();

    boolean insertEmoji(Emoji emoji);
}
