package tracker;

//import MessageProcessor;

import tracker.Messages.MessageProcessor;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

// Tracker is subject
// tracker has list of clients
// gets the information
public class tracker extends Observable {
    private static ArrayList<Athelete> Atheletes = new ArrayList<Athelete>();
    private static HashMap<String, Athelete> nameAtheleteMap = new HashMap<String, Athelete>();
    private static String raceStartedMessage;
    private int ServerPort = 12000;
    private static Communicator serverComm;
    public static HashMap<Integer, Client> portAddressClientMap = new HashMap<Integer, Client>();
    public static ArrayList<Integer> clientPortAddress = new ArrayList<Integer>();

    public tracker() throws Exception {
//        serverComm = new Communicator(12000);
    }

    public void newAthelete(String message, InetAddress address, int port) throws Exception {
        // Making sense of the message received and registering the athelete
        String[] messages = message.split(",");
        String status = messages[0];
        String bibNumber = messages[1];
        int timeElapsed = Integer.valueOf(messages[2]);

        // Instanstiate an athelete object
        System.out.println("Size of players list : " + nameAtheleteMap.size());
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

    public void helloProcessor(String message, InetAddress address, int portAddress) throws Exception {
        System.out.println("A client started up: " + message);
        clientPortAddress.add(portAddress);
        Client client1 = new Client(portAddress);
        System.out.println("Newbie : " + client1);
        if (!client1.isAcknoweledged()) {
            Communicator trackComm = new Communicator();
            trackComm.send(raceStartedMessage, InetAddress.getLocalHost(), portAddress);
            System.out.println("Sent Race started message back: " + raceStartedMessage);
        }
    }

    public void raceStarted(String message, InetAddress address, int portAddress) throws Exception {
        System.out.println("Got hello from a client : " + message);
        clientPortAddress.add(portAddress);
        this.notifyObserverss(message);
    }


    public void atheleteStatus(String message, InetAddress address, int port) throws Exception {
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
        for (Integer clientPort : clientPortAddress) {
            this.serverComm.send(message, InetAddress.getLocalHost(), clientPort);
            System.out.println("Notifying : " + clientPort + message);
        }
    }

    // Subscribe a client to an athelete
    public void subscribe(String message, InetAddress address, int port) throws Exception {
        String[] messages = message.split(",");
        String bibNumber = messages[1];
        Client mappedClient = Client.identifyClient(port);
        this.nameAtheleteMap.get(bibNumber).subscribe(mappedClient);
        // Convey to the client that the race started
        if (!mappedClient.isAcknoweledged()) {
            this.serverComm.send(raceStartedMessage, InetAddress.getLocalHost(), port);
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
        tracker tracker = new tracker();
        MessageProcessor mp = new MessageProcessor("trackerProcessor");
        serverComm.setProcessor(mp);
        serverComm.start();
        serverComm.run();
    }
}
