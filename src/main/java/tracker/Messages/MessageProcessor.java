package tracker.Messages;

import sun.net.www.http.ChunkedOutputStream;

import java.net.InetAddress;

public class MessageProcessor implements IMessageProcessor {

    private String name;
    private int receiveCount;
//    private static tracker trackServer;

    public MessageProcessor(String name) throws Exception {
        this.name = name;
//        trackServer = new tracker();
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

//        else {
//
//            System.out.println(String.format("%s received: %s from %s:%d", name, message, address.toString(), port));
//            String[] messages = message.split(",", 0);
//            String status = messages[0];
//            if (status.equals("Registered")) {
//                // Sends a different message to all clients when the race starts
////                trackServer.newAthelete(message, address, port);
//
//            } else if (status.equals("OnCourse")) {
////                trackServer.atheleteStatus(message, address, port);
//                //Update the status and inform clients
//                System.out.println(status);
//
//            } else if (status.equals("Hello")) {
////                trackServer.helloProcessor(message, address, port);
//
//            } else if (status.equals("Started") || status.equals("Finished") ||
//                    status.equals("DidNotStart") || status.equals("DidNotFinish")) {
////                trackServer.atheleteStatus(message, address, port);
//
//            } else if (status.equals("Subscribe")) {
////                trackServer.subscribe(message, address, port);
//
//            } else if (status.equals("Unsubscribe")) {
////                trackServer.unsubscribe(message, address, port);
//            } else {
//                // Garbage message
////                System.out.println("Garbage Message : " + message);
//            }
//        }
//    }

    public int ReceiveCount() {
        return receiveCount;
    }
}