package tracker;
// There can be many clients
// A clinet can subscribe to a list of athletes
// A client list of atheletes status is updated by calling the clients' function

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
    }

    public static void main(String[] args) throws Exception {
        int ServerPort = 12000;
        Communicator communicator = new Communicator(ServerPort);

        MessageProcessor mp = new MessageProcessor("trackerProcessor");
        communicator.setProcessor(mp);
        communicator.start();
        DatagramPacket packet;
        communicator.run();
//        for (int i = 0; i < 100; i++) {
//            packet = communicator.getMessage();
//
//            if (packet != null) {
//                String message = new String(packet.getData(), 0, packet.getLength(), UTF_16BE);
//                mp.process(message, InetAddress.getLocalHost(), ServerPort);
//            } else {
//                System.out.print(".");
//            }
//        }
    }
}