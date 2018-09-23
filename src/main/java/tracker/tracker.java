package tracker;

import java.net.DatagramPacket;
import java.net.InetAddress;
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

    // Race started -- after client subscribes to an athelete for the first time
    // New Athelete  -- sent to every client after a new athelete registers
    // Athelete Status --sent to the client if they are tracking the athelete when its' status change
    public void sendRaceStarted() {

    }

    public void newAtheleteRegistered() {

    }

    public void atheleteStatusChange() {
    }

    public void notifyObserver() {

    }

    public void listenToClients() {
        // Messages the client sends to the server
        // Hello
        // Subscribe to an athelete
        // UnSubscribe from an athelete
    }

    public void sendMessageToClients(String message) throws Exception {
        Communicator trackComm = new Communicator(12001);
        for (Client client : Clients) {
            trackComm.send(message, InetAddress.getLocalHost(), client.getPortAddress());
        }
    }

    public void subscribe(Athelete athelete, Client client) {
        athelete.subscribe(client);
    }

    public void unsubscribe(Athelete athelete, Client client) {
        athelete.unsubscribe(client);
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
