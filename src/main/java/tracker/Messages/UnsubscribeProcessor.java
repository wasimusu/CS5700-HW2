package tracker.Messages;

import java.net.InetAddress;

public class UnsubscribeProcessor extends Message {
    private String message;
    private InetAddress address;
    private int port;

    public UnsubscribeProcessor(String message, InetAddress address, int port) {
        this.message = message;
        this.address = address;
        this.port = port;
    }

    public void execute() throws Exception {
        System.out.println("Executing in Unsubscribe " + message + "\t" + address + port);
    }
}
