package common;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPSendRunnable implements Runnable {

    private int port;

    public UDPSendRunnable(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        Scanner sc = new Scanner(System.in);

        DatagramSocket sendSocket = null;

        try {
            //创建发送的Socket
            sendSocket = new DatagramSocket();

            while (true) {

                String s = sc.nextLine();
                //把s送到servlet里，监听线程通过socket将聊天内容返回给客户端
                System.out.println("我说：" + s);

                byte[] bytes = s.getBytes();

                //创建数据包
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), this.port);

                //发送数据
                sendSocket.send(packet);

                if ("886".equals(s)) {
                    break;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭连接
            sendSocket.close();

            sc.close();
        }


    }
}
