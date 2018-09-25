package tracker;
// There can be many clients
// A clinet can subscribe to a list of athletes
// A client list of atheletes status is updated by calling the clients' function

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

// Each client has a port number on which to communicate that
// status changed for one of its atheletes
// Observer
public class Client {
    public static HashMap<Integer, Client> portAddressClientMap = new HashMap<Integer, Client>();

    private int portAddress;
    private ArrayList<Athelete> myAtheletes;
    private Communicator communicator;
    private boolean ACK;

    // Just become a client and you have 0 to many atheletes
    public Client(int portAddress) throws Exception {

        ACK = false; // did it received the first start of race message
        this.portAddress = portAddress;
        portAddressClientMap.put(portAddress, this);
//        communicator = new Communicator(portAddress);

        myAtheletes = new ArrayList<Athelete>();
    }

    @Override
    public String toString() {
        return "Client : \tPort Address : " + portAddress;
    }

    public void setAcknoweledged() {
        this.ACK = true;
    }

    public boolean isAcknoweledged() {
        return this.ACK;
    }

    public int getPortAddress() throws Exception {
        return this.portAddress;
    }

    // Identify the client using port address
    public static Client identifyClient(int portAddress) {
        return portAddressClientMap.get(portAddress);
    }

    // Series of messages that are sent by client to the tracker
    public void sendSubscribe(int bibNumber) throws Exception {
        String message = "Subscribe," + String.valueOf(bibNumber);
        sendMessageToTracker(message);
    }

    public void sendUnsubscribe(int bibNumber) throws Exception {
        String message = "Subscribe," + String.valueOf(bibNumber);
        sendMessageToTracker(message);
    }

    public void sendMessageToTracker(String message) throws Exception {
        communicator.send(message, InetAddress.getLocalHost(), 12000);
    }

}