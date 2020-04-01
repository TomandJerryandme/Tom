package common;

import java.io.IOException;

public class UDPSender {

    public static void main(String[] args) throws IOException {

       new Thread(new UDPSendRunnable(9003)).start();
       new Thread(new UDPReceiveRunnable(9004)).start();    //9004可以作为一个服务器端的接口
    }
}
