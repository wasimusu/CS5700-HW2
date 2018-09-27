package tracker.Messages;

import tracker.Client;
import tracker.RaceTracker;

import java.net.InetAddress;
import java.util.ArrayList;

public class RaceStartedProcessor extends Message {
    private static String raceStartedMessage;

    public RaceStartedProcessor(String message, InetAddress address, int port) {
        super.Message(message, address, port);
        raceStartedMessage = message;
    }

    public void execute() throws Exception {
        System.out.println("Got hello from a client : " + message);

        ArrayList<Integer> allClients = Client.getAllClients();
        if (allClients != null) {
            RaceTracker.sendMessage(raceStartedMessage, allClients);
        }
    }

    public static String getRaceStartedMessage() {
        return raceStartedMessage;
    }

}