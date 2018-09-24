package tracker;
// There can be many clients
// A clinet can subscribe to a list of athletes
// A client list of atheletes status is updated by calling the clients' function

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;

// Each client has a port number on which to communicate that
// status changed for one of its atheletes
// Observer
public class Client {
    private static int clientCount = 0;  // no of clients instantiated so far
    public static HashMap<Integer, Client> portAddressClientMap;

    private int portAddress;
    private ArrayList<Athelete> myAtheletes;
    private Communicator communicator;
    private int dependablesCount;

    // Just become a client and you have 0 to many atheletes
    public Client() throws Exception {
        clientCount++;
        portAddressClientMap = new HashMap<Integer, Client>();

        this.dependablesCount = 0;
        communicator = new Communicator();
        portAddressClientMap.put(communicator.getLocalPort(), this);
        this.communicator.send("Hello", InetAddress.getLocalHost(), 12000);

        myAtheletes = new ArrayList<Athelete>();
    }

    @Override
    public String toString() {
        return "Client : "+clientCount+ "\tPort Address : "+portAddress;
    }

    public int getPortAddress() throws Exception {
        return this.portAddress;
    }

    // Identify the client using port address
    public static Client identifyClient(int portAddress) {
        return portAddressClientMap.get(portAddress);
    }

    // get the number of atheletes the client is watching
    public int getDependablesCount() {
        return this.dependablesCount;
    }

    // Series of messages that are sent by client to the tracker
    public void sendSubscribe(int bibNumber) throws Exception {
        String message = "Subscribe," + String.valueOf(bibNumber);
        sendMessageToTracker(message);
        this.dependablesCount++;
    }

    public void sendUnsubscribe(int bibNumber) throws Exception {
        String message = "Subscribe," + String.valueOf(bibNumber);
        sendMessageToTracker(message);
        this.dependablesCount--;
    }

    public void sendHello() throws Exception {
        String message = "Hello";
        sendMessageToTracker(message);
    }

    public void sendMessageToTracker(String message) throws Exception {
        communicator = new Communicator(this.portAddress);
        communicator.send(message, InetAddress.getLocalHost(), 12000);
    }

    public void update() {
        for (Athelete athelete : myAtheletes) {
            // get updated status
            // maybe just print it
            // Display the updated status
        }
    }
}