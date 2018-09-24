package tracker;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

// Tracker is subject
// tracker has list of clients
// gets the information
public class tracker extends Observable {
    private static ArrayList<Client> Clients;
    private static ArrayList<Athelete> Atheletes;
    private static HashMap<String, Athelete> nameAtheleteMap;
    private static int length = 0; // length of Clients
    private static String raceStartedMessage;

    public tracker() {
        nameAtheleteMap = new HashMap<String, Athelete>();
        Clients = new ArrayList<Client>();
        Atheletes = new ArrayList<Athelete>();
    }

    public void registerAthelete(String[] messages) {
        // Making sense of the message received and registering the athelete
        String status = messages[0];
        int bibNumber = Integer.valueOf(messages[1]);
        int timeElapsed = Integer.valueOf(messages[2]);

        // Instanstiate an athelete object
        System.out.println(status + " : " + bibNumber + " " + timeElapsed);
        Athelete a = new Athelete(status, bibNumber, timeElapsed);

        // Map is required to retrieve athelete object based on string id
        nameAtheleteMap.put(messages[1], a);
        Atheletes.add(a);

//        String message = String.join(" ", messages);
//        notifyObservers(message);
    }


    public void generalMessage(String[] messages) {

        String status = messages[0];
        if (status.equals("Started")) {
            raceStartedMessage = String.join(" ", messages);
        }
        // Notify all the clients of status change
        String message = String.join(" ", messages);
        notifyObservers(messages);
    }


    public void updateAthelete(String[] messages) {
        System.out.println("Updating athelete status");
        String status = messages[0];
        String bibNumber = messages[1];
        int timeElapsed = Integer.valueOf(messages[2]);
        float distanceCovered = Float.valueOf(messages[3]);

        // Instanstiate an athelete object
        System.out.print(bibNumber);
        Athelete a = nameAtheleteMap.get(bibNumber);
        if (a != null) {
            a.updateStatus(status, timeElapsed, distanceCovered);

            // Map is required to retrieve athelete object based on string id
            nameAtheleteMap.put(messages[1], a);
            Atheletes.add(a);

            // Notify all the clients of status change
            String message = String.join(" ", messages);
            notifyObservers(messages);
        } else {
            System.out.println("Null object encountered. Can't update status");
        }
    }

    public void notifyObservers(String message) throws Exception {
        System.out.println("notifying people");
        Communicator trackComm = new Communicator(12001);
        for (Client client : Clients) {
            trackComm.send(message, InetAddress.getLocalHost(), client.getPortAddress());
        }
    }

    // Race started -- after client subscribes to an athelete for the first time
    // New Athelete  -- sent to every client after a new athelete registers
    // Athelete Status --sent to the client if they are tracking the athelete when its' status change

    public void listenToClients() {
        // Messages the client sends to the server
        // Hello
        // Subscribe to an athelete
        // UnSubscribe from an athelete
        // Increase the number of observes
    }

    // Subscribe a client to an athelete and if its' clients firs, send race started message back
    public void subscribe(Athelete athelete, Client client) throws Exception {
        athelete.subscribe(client);

        // Convey to the client that the race started
        if (client.getDependablesCount() == 1) {
            Communicator trackComm = new Communicator();
            trackComm.send(raceStartedMessage, InetAddress.getLocalHost(), client.getPortAddress());
        }
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
