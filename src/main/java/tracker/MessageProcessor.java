package tracker;

import java.net.InetAddress;
import java.net.SocketException;

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
//            System.out.println(String.format("%s received: %s from %s:%d", name, message, address.toString(), port));
            String[] messages = message.split(",", 0);
            String status = messages[0];
            if (status.equals("Registered")) {
                System.out.println(status);
                // Sends a different message to all clients when the race starts
                tracker t1 = new tracker();
                t1.registerAthelete(messages);
//                t1.updateAthelete(messages);

            } else if (status.equals("OnCourse")) {
                tracker t1 = new tracker();
                t1.updateAthelete(messages);
                //Update the status and inform clients
                System.out.println(status);


            } else if (status.equals("Started") || status.equals("Finished") ||
                    status.equals("DidNotStart") || status.equals("DidNotFinish")) {
                tracker t1 = new tracker();
                t1.generalMessage(messages);
                System.out.println(status);

            } else {
                // Garbage message
//                System.out.println("Garbage Message : " + message);
            }
            receiveCount++;
        }
    }

    public int ReceiveCount() {
        return receiveCount;
    }
}