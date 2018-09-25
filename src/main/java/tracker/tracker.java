package tracker;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

// Tracker is subject
// tracker has list of clients
// gets the information
public class tracker extends Observable {
    private static ArrayList<Client> Clients = new ArrayList<Client>();
    private static ArrayList<Athelete> Atheletes = new ArrayList<Athelete>();
    private static HashMap<String, Athelete> nameAtheleteMap = new HashMap<String, Athelete>();
    private static String raceStartedMessage;
    int ServerPort = 12000;
    private static Communicator serverComm;
    public static HashMap<Integer, Client> portAddressClientMap = new HashMap<Integer, Client>();
    public static ArrayList<Integer> clientPortAddress = new ArrayList<Integer>();

    public tracker() throws Exception {
        serverComm = new Communicator(ServerPort);
    }

    public void registerAthelete(String message, InetAddress address, int port) throws Exception {
        // Making sense of the message received and registering the athelete
        String[] messages = message.split(",");
        String status = messages[0];
        String bibNumber = messages[1];
        int timeElapsed = Integer.valueOf(messages[2]);

        // Instanstiate an athelete object
        System.out.println("Size of players list : " + nameAtheleteMap.size());
        System.out.println(nameAtheleteMap);
        Athelete a = new Athelete(status, Integer.valueOf(bibNumber), timeElapsed);
        System.out.println(a);

        // Map is required to retrieve athelete object based on string id
        nameAtheleteMap.put(bibNumber, a);
        Atheletes.add(a);

        this.notifyObserverss(message);
    }

    public void generalMessage(String message, InetAddress address, int port) throws Exception {
        String[] messages = message.split(",");
        String status = messages[0];

        // Notify everyone that race stared
        if (status.equals("Race")) {
            raceStartedMessage = String.join(" ", messages);
            this.notifyObserverss(raceStartedMessage);
        } else {
            this.notifyObserverss(message);
        }
    }

    public void helloProcessor(String message, InetAddress address, int portAddress) {
        System.out.println(message);
        clientPortAddress.add(portAddress);
    }

    public void updateAthelete(String message, InetAddress address, int port) throws Exception {
        String[] messages = message.split(",");
        String status = messages[0];
        String bibNumber = messages[1];
        int timeElapsed = Integer.valueOf(messages[2]);
        float distanceCovered = Float.valueOf(messages[3]);

        Athelete a = nameAtheleteMap.get(bibNumber);
        if (a != null) {
            a.updateStatus(status, timeElapsed, distanceCovered);
            // Map is required to retrieve athelete object based on string id
            nameAtheleteMap.put(bibNumber, a);
            System.out.println("Updated : " + a);

            // Notify all the clients of status change
            this.notifyObserverss(message);
        } else {
            System.out.println("Null object encountered. Can't update status");
        }
    }

    public void notifyObserverss(String message) throws Exception {
        System.out.println("Notifying : ----------------------------");
        for (Client client : Clients) {
            serverComm.send(message, InetAddress.getLocalHost(), client.getPortAddress());
            System.out.println("Notifying : " + client);
        }
    }

    // Race started -- after client subscribes to an athelete for the first time
    // New Athelete  -- sent to every client after a new athelete registers
    // Athelete Status --sent to the client if they are tracking the athelete when its' status change

    // Subscribe a client to an athelete
    public void subscribe(String message, InetAddress address, int port) throws Exception {
        String[] messages = message.split(",");
        String bibNumber = messages[1];
        Client mappedClient = Client.identifyClient(port);
        this.nameAtheleteMap.get(bibNumber).subscribe(mappedClient);
        // Convey to the client that the race started
        if (!mappedClient.isAcknoweledged()) {
            Communicator trackComm = new Communicator();
            trackComm.send(raceStartedMessage, InetAddress.getLocalHost(), mappedClient.getPortAddress());
        }
    }

    // Unsubscribe a client from an athelete
    public void unsubscribe(String message, InetAddress address, int port) throws Exception {
        String[] messages = message.split(",");
        String bibNumber = messages[1];
        Client mappedClient = Client.identifyClient(port);
        this.nameAtheleteMap.get(bibNumber).unsubscribe(mappedClient);
    }

    public static void main(String[] args) throws Exception {
        MessageProcessor mp = new MessageProcessor("trackerProcessor");
        serverComm.setProcessor(mp);
        serverComm.start();
        serverComm.run();
    }
}
