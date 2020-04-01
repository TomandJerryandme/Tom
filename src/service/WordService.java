package service;

import entity.Word;

import java.util.List;

public interface WordService {
    boolean addWord(String word);

    boolean removeWord(String word);

    boolean hasWord(String word);

    List<Word> findWords();

}
