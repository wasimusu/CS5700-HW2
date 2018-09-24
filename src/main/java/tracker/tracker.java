package tracker;

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
    private static HashMap<Integer, Client> portAddressClientMap;
    private static String raceStartedMessage;

    public tracker() {
        nameAtheleteMap = new HashMap<String, Athelete>();
        portAddressClientMap = new HashMap<Integer, Client>();
        Clients = new ArrayList<Client>();
        Atheletes = new ArrayList<Athelete>();
    }

    public void registerAthelete(String[] messages) throws Exception {
        // Making sense of the message received and registering the athelete
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

        System.out.println("New client");
        Client c1 = new Client();
        c1.sendSubscribe(1);
        System.out.println("New Client");

//        String message = String.join(" ", messages);
//        notifyObservers(message);
    }

    public void generalMessage(String[] messages) {

        String status = messages[0];
        // Notify all the clients of status change
        String message = String.join(" ", messages);

        // Notify everyone that race stared
        if (status.equals("Started")) {
            raceStartedMessage = String.join(" ", messages);
        }
        notifyObservers(messages);
    }


    public void updateAthelete(String[] messages) throws Exception{
        System.out.println("Updating athelete status");
        String status = messages[0];
        String bibNumber = messages[1];
        int timeElapsed = Integer.valueOf(messages[2]);
        float distanceCovered = Float.valueOf(messages[3]);

//       Instanstiate an athelete object
//        System.out.println(nameAtheleteMap);
//        System.out.print(bibNumber);
        Athelete a = nameAtheleteMap.get(bibNumber);
        if (a != null) {
            a.updateStatus(status, timeElapsed, distanceCovered);
//            // Map is required to retrieve athelete object based on string id
            nameAtheleteMap.put(bibNumber, a);
            System.out.println(a);

            // Notify all the clients of status change
            String message = String.join(" ", messages);
            this.notifyObserverss(message);
        } else {
            System.out.println("Null object encountered. Can't update status");
        }
    }

    public void notifyObserverss(String message) throws Exception {
        System.out.println("Notifying "+message);
        Communicator trackComm = new Communicator(12001);
        for (Client client : Clients) {
            trackComm.send(message, InetAddress.getLocalHost(), client.getPortAddress());
        }
    }

    // Race started -- after client subscribes to an athelete for the first time
    // New Athelete  -- sent to every client after a new athelete registers
    // Athelete Status --sent to the client if they are tracking the athelete when its' status change

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


    // Subscribe a client to an athelete and if its' clients firs, send race started message back
    public void subscribe(String bibNumber, int clientPortAddress) throws Exception {
//        this.nameAtheleteMap.get(bibNumber).subscribe();
    }


    public void unsubscribe(String bibNumber, int clientPortAddress) {
//        athelete.unsubscribe(client);
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
