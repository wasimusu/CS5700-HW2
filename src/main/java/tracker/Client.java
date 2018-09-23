package tracker;
// There can be many clients
// A clinet can subscribe to a list of athletes
// A client list of atheletes status is updated by calling the clients' function

import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Observer;

import static java.nio.charset.StandardCharsets.UTF_16BE;

// Each client has a port number on which to communicate that
// status changed for one of its atheletes
// Observer
public class Client {
    private static int clientCount = 0;  // no of clients instantiated so far
    private static int startPortAddress = 12001;

    private int portAddress;
    private ArrayList<Athelete> myAtheletes;
    private Communicator communicator;
    private int dependablesCount;

    // Just become a client and you have 0 to many atheletes
    public Client() throws Exception {
        this.portAddress = startPortAddress;
        startPortAddress++;
        clientCount++;

        this.dependablesCount = 0;
        communicator = new Communicator(this.portAddress);
        communicator.send("Hello", InetAddress.getLocalHost(), 12000);
    }

    public void sendHello() throws Exception {
    }

    public int getPortAddress() throws Exception {
        return this.portAddress;
    }

    // get the number of atheletes the client is watching
    public int getDependablesCount() {
        return this.dependablesCount;
    }

    public void sendSubscribe(int bibNumber) throws Exception {
        String message = "Unsubscribe," + String.valueOf(bibNumber);
        communicator = new Communicator(this.portAddress);
        communicator.send(message, InetAddress.getLocalHost(), 12000);
        this.dependablesCount++;
    }

    public void sendUnsubscribe(int bibNumber) throws Exception {
        String message = "Subscribe," + String.valueOf(bibNumber);
        communicator = new Communicator(this.portAddress);
        communicator.send(message, InetAddress.getLocalHost(), 12000);
        this.dependablesCount++;
    }


    // And you get a signal that status of atheletes has changed
    // And call for status updates
    public void statusChange() {
        // how do I keep listening to the tracker
    }

    public void update() {
        for (Athelete athelete : myAtheletes) {
            // get updated status
            // maybe just print it
        }
    }

    public void addAthelete(Athelete a) {
        myAtheletes.add(a);
        a.subscribe(this);
    }

    public void removeAthelete(Athelete a) {
        myAtheletes.remove(a);
        a.unsubscribe(this);
    }

    // Register with the tracker
    // Unregister with the tracker
}