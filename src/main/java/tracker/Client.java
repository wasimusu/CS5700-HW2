package tracker;
// There can be many clients
// A clinet can subscribe to a list of athletes
// A client list of atheletes status is updated by calling the clients' function

import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Observer;

import static java.nio.charset.StandardCharsets.UTF_16BE;

// Each client has a port number on which to communicate that
// status changed for one of its atheletes
// Observer
public class Client {
    private int portAddress;
    private static int count = 0;

    // Just become a client and you have 0 to many atheletes
    public Client() {
        this.portAddress = count;
        count++;
    }

    // And you get a signal that status of atheletes has changed
    // And call for status updates
    public void statusChange() {
    }

    public void update() {
    }

    // Register with the tracker
    // Unregister with the tracker
}