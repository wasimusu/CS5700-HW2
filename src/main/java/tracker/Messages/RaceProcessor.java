package tracker.Messages;

import tracker.Communicator;
import tracker.Client;

import java.net.InetAddress;

public class RaceProcessor extends Message {
    private static String raceStartedMessage;
    private String message;
    private InetAddress address;
    private int port;

    public RaceProcessor(String message, InetAddress address, int port) {
        this.message = message;
        this.address = address;
        this.port = port;
        raceStartedMessage = message;
    }

    public void execute() throws Exception {
        System.out.println("Got hello from a client : " + message);

        // clientPortAddress.add(port);
        Communicator comm = new Communicator();
        comm.send(raceStartedMessage, address, port);
    }
}