package tracker;
// There can be many clients
// A clinet can subscribe to a list of athletes
// A client list of atheletes status is updated by calling the clients' function

import java.util.ArrayList;

// Observer
public class Client {
    public static ArrayList<Integer> allClients = new ArrayList<Integer>();

    private int portAddress;
    private boolean ACK;

    // Just become a client and you have 0 to many atheletes
    public Client(int portAddress) throws Exception {

        ACK = false; // did it received the first start of race message
        this.portAddress = portAddress;
        allClients.add(portAddress);
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

    // Get list of all the clients instances
    public static ArrayList<Integer> getAllClients() {
        return allClients;
    }

//    // Series of messages that are sent by client to the tracker
//    public void sendSubscribe(int bibNumber) throws Exception {
//        String message = "Subscribe," + String.valueOf(bibNumber);
//        sendMessageToTracker(message);
//    }
//
//    public void sendUnsubscribe(int bibNumber) throws Exception {
//        String message = "Subscribe," + String.valueOf(bibNumber);
//        sendMessageToTracker(message);
//    }
}