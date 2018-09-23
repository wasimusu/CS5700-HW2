package tracker;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;

// Tracker is subject
// tracker has list of clients
// gets the information
public class tracker extends Observable {
    private static ArrayList<Client> Clients;
    private static ArrayList<Athelete> Atheletes;
    private static Map<String, Athelete> AtheleteIdMap;
    private static int length = 0; // length of Clients

    public tracker() {
        Clients = new ArrayList<Client>();
    }

    // Client becomes a tracker user
    public void subscribe(Client client) {
        Clients.add(client);
    }

    public void unsubscribe(Client client) {
        Clients.remove(client);
    }

    public void registerAthelete(String[] messages) {
        // Making sense of the message received and registering the athelete
        String status = messages[0];
        int bibNumber = Integer.valueOf(messages[1]);
        int timeElapsed = Integer.valueOf(messages[2]);

        // Instanstiate an athelete object
        Athelete a = new Athelete(status, bibNumber, timeElapsed);

        // Map is required to retrieve athelete object based on string id
        AtheleteIdMap.put(messages[1], a);
        Atheletes.add(a);
    }

    public void updateAthelete(String[] messages) {
        String status = messages[0];
        int bibNumber = Integer.valueOf(messages[1]);
        int timeElapsed = Integer.valueOf(messages[2]);
        float distanceCovered = Float.valueOf(messages[3]);

        // Instanstiate an athelete object
        Athelete a = AtheleteIdMap.get(messages[1]);
        a.updateStatus(status, timeElapsed, distanceCovered);
        // Map is required to retrieve athelete object based on string id
        AtheleteIdMap.put(messages[1], a);
        Atheletes.add(a);
    }

    public void notifyObserver() {

    }

    public static void main(String[] args) throws Exception {
        int ServerPort = 12000;
        Communicator communicator = new Communicator(ServerPort);

        // Make some clients and subscribe to some players
        Client c1 = new Client();

        MessageProcessor mp = new MessageProcessor("trackerProcessor");
        communicator.setProcessor(mp);
        communicator.start();
        communicator.run();
    }

}
