package tracker;

import java.net.InetAddress;

public class UnsubscribeProcessor implements IMessageProcessor {
    public UnsubscribeProcessor(String message, InetAddress address, int port) throws Exception {
        System.out.println("Got a hello world message");
    }

    public void process(String message, InetAddress address, int port) throws Exception {
        System.out.println("Got a hello world message");
    }
}
