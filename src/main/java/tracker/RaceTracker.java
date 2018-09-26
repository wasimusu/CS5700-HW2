package tracker;

import org.jetbrains.annotations.NotNull;
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
    }

    public static void sendMessage(String message, @NotNull ArrayList<Integer> ports) throws Exception {
        for (Integer port : ports) {
            sendMessage(message, port);
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
