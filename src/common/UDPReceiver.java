package common;

import java.io.IOException;

public class UDPReceiver {

    public static void main(String[] args) throws IOException {
        new Thread(new UDPSendRunnable(9004)).start();
        new Thread(new UDPReceiveRunnable(9003)).start();
    }
}
