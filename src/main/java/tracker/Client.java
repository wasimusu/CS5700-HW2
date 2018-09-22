package tracker;
// There can be many clients
// A clinet can subscribe to a list of athletes
// A client list of atheletes status is updated by calling the clients' function

import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.InetAddress;

import static java.nio.charset.StandardCharsets.UTF_16BE;

public class Client {
    private Atheletes atheletes;

    // Just become a client and you have 0 to many atheletes
    public Client() {
    }

    // And you get a signal that status of atheletes has changed
    // And call for status updates
    public void statusChange() {
    }

    public void updateStatus() {
        for (int i = 0; i < Array.getLength(atheletes); i++) {
            System.out.println(atheletes);
        }
    }
}