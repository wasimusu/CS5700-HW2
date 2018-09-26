package tracker;

import tracker.Messages.MessageProcessor;

import java.net.InetAddress;
import java.util.ArrayList;

public class RaceTracker {
    private int port;
    private static Communicator serverComm;
    private MessageProcessor messageProcessor;

    public RaceTracker(int port) throws Exception {
        this.port = port;
        serverComm = new Communicator(port);
        messageProcessor = new MessageProcessor("tracker");
        serverComm.setProcessor(messageProcessor);
        serverComm.start();
        serverComm.run();
        System.out.println("Finished");
    }

    public static void sendMessage(String message, ArrayList<Integer> ports) throws Exception {
        for (Integer port : ports) {
            RaceTracker.serverComm.send(message, InetAddress.getLocalHost(), port);
            System.out.println("Message sent : " + message + "\t" + port);
            System.out.println("........................................");
        }
    }

    public static void sendMessage(String message, int port) throws Exception {
        RaceTracker.serverComm.send(message, InetAddress.getLocalHost(), port);
        System.out.println("Message sent : " + message + "\tPort: " + port);
        System.out.println("........................................");
    }

    public static void main(String[] args) throws Exception {
        RaceTracker r1 = new RaceTracker(12000);
    }

}
