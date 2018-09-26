package tracker.Messages;

import tracker.Communicator;
import tracker.Client;
import tracker.RaceTracker;

import java.net.InetAddress;

public class HelloProcessor extends Message {
    private String message;
    private InetAddress address;
    private int port;

    public HelloProcessor(String message, InetAddress address, int port) {
        this.message = message;
        this.address = address;
        this.port = port;
    }

    public void execute() throws Exception {
        System.out.println("A client started up" + message + "\t" + address + port);

        // Create a client with the same endpoint
        Client client = new Client(port);
        System.out.println(client);

        // Send out a race stared message to client if you have not already
        if (!client.isAcknoweledged()) {
            Communicator trackComm = new Communicator();
            System.out.println(trackComm.getLocalPort());
            String raceStartedMessage = "Race,Bension Loop,16090";
            raceStartedMessage = RaceStartedProcessor.getRaceStartedMessage();
            if (raceStartedMessage != null) {
                trackComm.send(raceStartedMessage, InetAddress.getLocalHost(), port);
                client.setAcknoweledged();
            }
            trackComm.close();
        }
    }
}