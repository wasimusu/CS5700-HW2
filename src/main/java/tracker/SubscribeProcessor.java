package tracker;

import java.net.InetAddress;

public class SubscribeProcessor implements IMessageProcessor{
    public void process(String message, InetAddress address, int port) throws Exception {
        System.out.println("Got a hello world message");
    }
}
