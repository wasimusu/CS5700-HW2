package tracker;

import java.net.DatagramPacket;

// Tracker is subject
// tracker has list of clients
// gets the information
public class tracker {
    private Client[] listOfClients;

    public static void main(String[] args) throws Exception {
        int ServerPort = 12000;
        Communicator communicator = new Communicator(ServerPort);

        MessageProcessor mp = new MessageProcessor("trackerProcessor");
        communicator.setProcessor(mp);
        communicator.start();
        DatagramPacket packet;
        communicator.run();
    }

    public void register(Client client) {
        listOfClients[0] = client;
    }
}
