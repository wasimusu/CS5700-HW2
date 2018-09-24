package tracker;

import java.net.InetAddress;
import java.net.SocketException;

public class MessageProcessor implements IMessageProcessor {

    private String name;
    private int receiveCount;
    private static tracker t1;

    public MessageProcessor(String name) {
        this.name = name;
        t1 = new tracker();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void process(String message, InetAddress address, int port) throws Exception {
        if (message == null) {
            System.out.println("Null string");
            return;
        } else if (address == null) {
            System.out.println("Null address");
            return;
        } else {

            System.out.println(String.format("%s received: %s from %s:%d", name, message, address.toString(), port));
            String[] messages = message.split(",", 0);
            String status = messages[0];

            if (status.equals("Registered")) {
                // Sends a different message to all clients when the race starts
                t1.registerAthelete(messages);

            } else if (status.equals("OnCourse")) {
                t1.updateAthelete(messages);
                //Update the status and inform clients
                System.out.println(status);


            } else if (status.equals("Started") || status.equals("Finished") ||
                    status.equals("DidNotStart") || status.equals("DidNotFinish")) {
                t1.generalMessage(messages);
                System.out.println(status);

            } else if (status.equals("Subscribe")) {
                t1.subscribe();
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