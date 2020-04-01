package common;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiveRunnable implements Runnable {

    private int port;

    public UDPReceiveRunnable(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        DatagramSocket receiveSocket = null;

        try {
            //创建接收的Socket
            receiveSocket = new DatagramSocket(this.port);

            while (true) {

                byte[] bytes = new byte[1024];

                //创建数据包
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);

                //接收数据
                receiveSocket.receive(packet);  //阻塞

                //获取数据
                String data = new String(packet.getData(),0, packet.getLength());

                //获取发送方IP地址
                String ip = packet.getAddress().getHostAddress();

                System.out.println(ip + "说:" + data);

                if ("886".equals(data)) {
                    break;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭连接
            receiveSocket.close();
        }

    }
}
