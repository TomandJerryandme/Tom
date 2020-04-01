package util;

import javax.servlet.ServletContext;
import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/*
-------------------------------
习题1---将给定文件的主文件名改为yyyyMMddHHmmssSSSxxx的形式，其中xxx是100到999之间的随机数，文件的扩展名不变
-------------------------------
String convertFilename(String filename)

-------------------------------
习题2---将数据库格式的日期时间字符串(datetime)转换为指定格式(pattern)的字符串
数据库默认的格式---yyyy-MM-dd HH:mm:ss
-------------------------------
String convertDatetime(String datetime, String pattern)

datetime = "2018-9-12 14:56:20"  2018-09-13 14:56:20
pattern = "yyyy年MM月dd日 HH时mm分ss秒"

返回 "2018年09月12日 14时56分20秒"

-------------------------------
习题3---将日期时间字符串转换为自定义格式的字符串
-------------------------------
String convertDatetime(String datetime)
刚刚发布         //1分钟内
xx分钟前发布  //1小时内
xx小时前发布  //3小时内
今天HH时mm分发布
xxxx年xx月xx日 xx时xx分发布  //超过今天
 */
public class StringUtil {

    public static String convertFilename(String filename){

        //yyyyMMddHHmmssSSSxxx

        //获取文件的扩展名
        String extension = filename.substring(filename.lastIndexOf("."));

        String pattern = "yyyyMMddHHmmssSSS";

        DateFormat df = new SimpleDateFormat(pattern);

        Date now = new Date();

        String currentTime = df.format(now);

        Random random = new Random();
        int number = random.nextInt(900)+100;

        return currentTime + number + extension;
    }

    public static String convertDatetime(String datetime, String pattern) throws ParseException {

        DateFormat source = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = source.parse(datetime);

        DateFormat dest = new SimpleDateFormat(pattern);
        return dest.format(date);

    }

    public static String convertDatetime(Date datetime, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(datetime);
    }

    public static String convertDatetime(String datetime) throws ParseException {

        DateFormat source = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = source.parse(datetime);

        Date now = new Date();

        //计算和当前时间差多少秒
        long diff = (now.getTime() - date.getTime()) / 1000;

        if(diff<60){
            return "刚刚发布";
        }else if(diff < 60* 60){
            return diff / 60 +  "分钟前发布";
        }else if(diff < 60 * 60 * 3){
            return diff / 60 / 60 + "小时前发布";
        }else{  // 今天HH时mm分发布

            //构造一个date，表示今天的凌晨 00:00:00

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            calendar.set(year, month ,day, 0,0,0 );

            Date today = calendar.getTime();

            if(date.after(today)){
                return "今天" + new SimpleDateFormat("HH时mm分").format(date) + "发布";
            }else{
                return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分").format(date) + "发布";
            }
        }
    }

    public static String convertDatetime(Date date) throws ParseException {

        Date now = new Date();

        //计算和当前时间差多少秒
        long diff = (now.getTime() - date.getTime()) / 1000;

        if(diff<60){
            return "刚刚发布";
        }else if(diff < 60* 60){
            return diff / 60 +  "分钟前发布";
        }else if(diff < 60 * 60 * 3){
            return diff / 60 / 60 + "小时前发布";
        }else{  // 今天HH时mm分发布

            //构造一个date，表示今天的凌晨 00:00:00

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            calendar.set(year, month ,day, 0,0,0 );

            Date today = calendar.getTime();

            if(date.after(today)){
                return "今天" + new SimpleDateFormat("HH时mm分").format(date) + "发布";
            }else{
                return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分").format(date) + "发布";
            }
        }
    }

    public static String convertSize(long size) {   //字节为单位

        size = size / 1000;  //转成KB
        /*
        if(size > 1024 * 1024){
            return size / 1000 / 1000 + "," + size / 1000 % 1000 + "," + size % 1000 + "KB";
        }else if(size > 1024 ) {

            return size / 1000 % 1000 + "," + size % 1000 + "KB";
        }else{
            return size + "KB";
        }*/

        NumberFormat nf = NumberFormat.getInstance();
        return nf.format(size);
    }

    //转换关键字，加红处理  将数据库的用户名中出现关键字的地方，转换成加红的关键字
    public static String convertKeyword(String source, String keyword){

        if(!"".equals(keyword)) {
            source = source.replace(keyword, "<font color='red'><b>" + keyword + "</b></font>");
        }

        return source;
    }

    //根据附件的文件名转换成对应的图标文件名  xxx.bmp --> bmp.gif
    public static String getIconFilename(String filename, ServletContext servletContext) {

        String extension = filename.substring(filename.lastIndexOf(".") + 1);

        String iconFilename = extension + ".gif";

        String iconFilePath = servletContext.getRealPath("/image/icon");
        File file = new File(iconFilePath + "/" + iconFilename);

        if(file.exists()){
            return iconFilename;
        }else {
            return "default.gif";
        }
    }

    //根据附件的文件名计算文件的大小
    public static String getFileSize(String filename, ServletContext servletContext) {

        String filePath = servletContext.getRealPath("/attachment");
        File file = new File(filePath + "/" + filename);

        long len = file.length();

        if(len >= 1024*1024){
            if(len % (1024*1024) ==0){
                return len / (1024*1024) + "M";
            }else{
                return String.format("%.1f", 1.0f * len / (1024*1024)) + "M";
            }
        }else if(len >= 1024){
            if(len % 1024 ==0){
                return len / 1024 + "K";
            }else{
                return String.format("%.1f", 1.0f * len / 1024) + "K";
            }
        }else{
            return len + "B";
        }

    }

     public static void main(String[] args) throws ParseException {
        //System.out.println(convertFilename("xxx.jpg"));
        //System.out.println(convertDatetime("2018-9-12 14:56:20", "yyyy年MM月dd日 HH时mm分ss秒"));
        //System.out.println(convertDatetime(new Date()));
        //System.out.println(convertSize(123456789));
        //System.out.println(convertKeyword("user33","3"));
        //System.out.println(getIconFilename("xxx.bmp"));
    }

}
