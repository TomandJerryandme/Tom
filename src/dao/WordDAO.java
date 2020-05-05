package dao;

import entity.Word;

import java.util.List;

public interface WordDAO {
    //添加屏蔽词
    boolean insertWord(String word);

    //删除屏蔽词
    boolean deleteWord(String word);

    //查询全部屏蔽词
    List<Word> queryWords();

    //检测是否有此关键字
    boolean hasWord(String word);

    Word findWord(String word);
}
