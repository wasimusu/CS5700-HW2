package tracker;

import java.net.InetAddress;

public class MessageProcessor implements IMessageProcessor {

    private String name;
    private int receiveCount;

    public MessageProcessor(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void process(String message, InetAddress address, int port) {
        if (message == null) {
            System.out.println("Null string");
            return;
        } else if (address == null) {
            System.out.println("Null address");
            return;
        } else {    // If we have a valid message
            System.out.println(String.format("%s received: %s from %s:%d", name, message, address.toString(), port));
            String[] messages = message.split(",", 0);
            if (messages[0] == "Registered") {
                tracker t1 = new tracker();
                t1.registerAthelete(messages);
                // Register a new player
            } else if (messages[0] == "OnCourse") {
                tracker t1 = new tracker();
                t1.updateAthelete(messages);
                //Update the status and inform clients
            } else if (messages[0] == "Started") {
                tracker t1 = new tracker();
                t1.registerAthelete(messages);
                //Update the status and inform clients
            } else if (messages[0] == "Finished") {
                tracker t1 = new tracker();
                t1.registerAthelete(messages);

                //Update the status and inform clients
            } else {
                // Garbage message
            }
            receiveCount++;
        }
    }

    public int ReceiveCount() {
        return receiveCount;
    }
}