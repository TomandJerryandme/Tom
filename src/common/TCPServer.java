package common;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws IOException {

        //创建ServerSocket对象，传入监听的端口号
        ServerSocket serverSocket = new ServerSocket(9000);

        //监听连接请求
        System.out.println("等待客户端的连接。。。");
        Socket socket = serverSocket.accept();
        System.out.println("获取到客户端的连接。。。");

        //获取socket对应的输出流,包装一个缓冲输出流
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        //获取socket对应的输入流,包装一个缓冲输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        while (true) {

            //***********接收数据****************

            //接收数据
            String s = br.readLine();
            //获取到了客户端发送的信息

            //由服务端模仿交谈的用户对用户发出信息
            System.out.println(s);

            //***********发送数据****************

            //发送数据
            bw.write(s.toUpperCase());
            bw.newLine();
            bw.flush();

            if ("886".equals(s)) {
                break;
            }

        }

        //关闭连接
        socket.close();
    }

}
