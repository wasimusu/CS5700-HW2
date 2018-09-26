package tracker;
import java.util.ArrayList;

// Observer
public class Client {
    public static ArrayList<Integer> allClients = new ArrayList<Integer>();

    private int portAddress;
    private boolean ACK;

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
}