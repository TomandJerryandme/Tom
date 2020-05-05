package service.serviceImpl;

import dao.EmojiDAO;
import dao.daoimpl.EmojiDAOImpl;
import entity.Emoji;
import service.EmojiService;

import java.util.List;

public class EmojiServiceImpl implements EmojiService {

    private EmojiDAO emojiDAO = new EmojiDAOImpl();

    @Override
    public Emoji getEmoji(int id) {
        return emojiDAO.getEmoji(id);
    }

    @Override
    public Emoji getEmoji(String picname) {
        return emojiDAO.getEmoji(picname);
    }

    @Override
    public List<Emoji> getEmojiList() {
        return emojiDAO.getEmojiList();
    }

    @Override
    public boolean addEmoji(Emoji emoji) {
        return emojiDAO.insertEmoji(emoji);
    }
}
