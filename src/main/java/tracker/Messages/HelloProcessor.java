package tracker.Messages;

import tracker.Communicator;
import tracker.Client;

import java.net.InetAddress;

public class HelloProcessor extends Message {
    public HelloProcessor(String message, InetAddress address, int port) {
        super.Message(message, address,port);
    }

    public void execute() throws Exception {
        System.out.println("A client started up :\t" + message + "\t" + address + port);

        // Create a client with the same endpoint
        Client client = new Client(port);
        System.out.println(client);

        // Send out a race stared message to client if you have not already
        if (!client.isAcknoweledged()) {
            Communicator trackComm = new Communicator();
            String raceStartedMessage = RaceStartedProcessor.getRaceStartedMessage();
            if (raceStartedMessage != null) {
                trackComm.send(raceStartedMessage, InetAddress.getLocalHost(), port);
                client.setAcknoweledged();
            }
            trackComm.close();
        }
    }

}