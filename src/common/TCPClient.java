package common;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {

        //创建Socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9000);

        //获取socket对应的输出流,包装一个缓冲输出流
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        //获取键盘输入的缓冲流
        BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));

        //获取socket对应的输入流,包装一个缓冲输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println("模拟echo服务，输入886结束");

        while(true){

            //***********发送数据****************

            //读取键盘输入，并发送数据
            String s = brInput.readLine();

            bw.write(s);
            bw.newLine();
            bw.flush();

            //***********接收数据****************

            //接收数据
            s = br.readLine();
            System.out.println(s);

            if("886".equals(s)){
                break;
            }
        }

        //关闭连接
        socket.close();
    }
}
