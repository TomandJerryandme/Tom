package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    //根据cookie的名称获取值
    public static String findCookie(String name, HttpServletRequest request){

        Cookie[] cookies = request.getCookies();

        if(cookies!=null){

            for (Cookie cookie : cookies) {

                if(cookie.getName().equals(name)){
                    return cookie.getValue();
                }
            }
        }

        return null;

    }

    //添加一个持久化Cookie
    public static void addCookie(String name, String value, int days, HttpServletResponse response){

        //实例化Cookie对象
        Cookie cookie = new Cookie(name, value);

        //设置最大存活年龄
        cookie.setMaxAge(days*24*60*60);

        //设置路径为项目的根目录
        cookie.setPath("/");

        //发送持久化cookie
        response.addCookie(cookie);
    }

    //删除指定名称的Cookie
    public static void deleteCookie(String name, HttpServletResponse response){

        //实例化Cookie对象
        Cookie cookie = new Cookie(name, "");

        //设置最大存活年龄
        cookie.setMaxAge(0);

        //发送持久化cookie
        response.addCookie(cookie);
    }
}
