package tracker;

import java.net.InetAddress;

public class MessageProcessor implements IMessageProcessor {

    private String name;
    private int receiveCount;

    public MessageProcessor(String name) {
        this.name = name;
    }
    public void setName(String name){this.name= name;}

    @Override
    public void process(String message, InetAddress address, int port) {
        if (message == null) {
            System.out.println("Null string");
            return;
        }

        if (address == null) {
            System.out.println("Null address");
            return;
        }

        System.out.println(String.format("%s received: %s from %s:%d", name, message, address.toString(), port));
        receiveCount++;
    }

    public int ReceiveCount() {
        return receiveCount;
    }
}