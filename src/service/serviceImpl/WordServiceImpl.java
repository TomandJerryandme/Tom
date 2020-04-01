package service.serviceImpl;

import dao.WordDAO;
import dao.daoimpl.WordDAOImpl;
import entity.Word;
import service.WordService;

import java.util.List;

public class WordServiceImpl implements WordService {
    private WordDAO wordDAO = new WordDAOImpl();
    @Override
    public boolean addWord(String word) {
        return wordDAO.insertWord(word);
    }

    @Override
    public boolean removeWord(String word) {
        return wordDAO.deleteWord(word);
    }

    @Override
    public boolean hasWord(String word) {
        return wordDAO.hasWord(word);
    }

    @Override
    public List<Word> findWords() {
        return wordDAO.queryWords();
    }
}
