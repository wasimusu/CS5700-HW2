package tracker.Messages;

import tracker.Communicator;
import tracker.Client;
import tracker.RaceTracker;

import java.net.InetAddress;
import java.util.ArrayList;

public class RaceStartedProcessor extends Message {
    private static String raceStartedMessage;
    private String message;
    private InetAddress address;
    private int port;

    public RaceStartedProcessor(String message, InetAddress address, int port) {
        this.message = message;
        this.address = address;
        this.port = port;
        raceStartedMessage = message;
    }

    public void execute() throws Exception {
        System.out.println("Got hello from a client : " + message);

        ArrayList<Integer> allClients = Client.getAllClients();
        RaceTracker.sendMessage(raceStartedMessage, allClients);
//        Communicator comm = new Communicator();
//        comm.send(raceStartedMessage, address, port);
    }

    public static String getRaceStartedMessage() {
        return raceStartedMessage;
    }
}