package service;

import entity.Emoji;

import java.util.List;

public interface EmojiService {
    Emoji getEmoji(int id);
    Emoji getEmoji(String picname);
    List<Emoji> getEmojiList();

    boolean addEmoji(Emoji emoji);
}
