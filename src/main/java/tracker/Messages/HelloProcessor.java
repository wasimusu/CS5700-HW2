package tracker.Messages;

import tracker.Communicator;
import tracker.Client;

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
            trackComm.send("Sent Race started message back", InetAddress.getLocalHost(), port);
            System.out.println("Sent Race started message back: ");
            trackComm.close();
        }
    }
}