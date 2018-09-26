package tracker.Messages;

import java.net.InetAddress;

public class MessageProcessor implements IMessageProcessor {

    private String name;
    private int receiveCount;

    public MessageProcessor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void process(String message, InetAddress address, int port) throws Exception {
        if (message == null) {
            System.out.println("Null string");
            return;
        }
        if (address == null) {
            System.out.println("Null address");
            return;
        }

        if (message != null) {
            System.out.println(String.format("%s received: %s from %s:%d", name, message, address.toString(), port));
            Message message1 = Message.messageObject(message, address, port);
            message1.execute();
        }
        receiveCount++;
        return;
    }

    public int ReceiveCount() {
        return receiveCount;
    }
}