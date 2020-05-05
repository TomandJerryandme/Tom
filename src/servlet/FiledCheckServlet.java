package servlet;

import util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/filed/check")
public class FiledCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置输出内容类型
        response.setContentType("text/html;charset=utf-8");
        //获取out输出对象
        PrintWriter out = response.getWriter();
        //获取session对象
        HttpSession session = request.getSession();
        ServletContext application = request.getServletContext();

        String mail = request.getParameter("mail");
        String tel = request.getParameter("telephone");

        System.out.println("CheckServlet======");
        System.out.println("mail======"+mail);
        System.out.println("telephone======="+tel);
        if (mail!=null){
            //进行邮箱的检测
            String reg = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
            if (StringUtil.isOK(reg,mail)){
                //合格
                System.out.println("邮箱格式正确");
                out.print("true");
            }else {
                out.print("false");
                System.out.println("邮箱格式不正确");
            }
            return;
        }
        if (tel!=null){
            //进行电话的检测
            String reg = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
            if (StringUtil.isOK(reg,tel)){
                //合格
                System.out.println("电弧格式正确");
                out.print("true");
            }else {
                System.out.println("电话格式不正确");
                out.print("false");
            }
            return;
        }
    }
}
