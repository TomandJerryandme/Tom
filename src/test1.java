import entity.*;
import service.*;
import service.serviceImpl.*;
import util.JDBCUtil;
import util.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class test1 {
    public static void main(String[] args) {
        /*String x[] = {"法轮功","邪教"};
        String content = "法轮功是一种邪教";
        for (String s : x) {
            content = content.replace(s,"***");
        }
        System.out.println(content);

        WordService wordService = new WordServiceImpl();
        List<Word> wordList = wordService.findWords();
        for (Word word : wordList) {
            System.out.println(word.getWordContent());
        }*/


        UserService userService = new UserServiceImpl();
        User user = userService.getUser("user1");
        System.out.println(user);
    }

}
