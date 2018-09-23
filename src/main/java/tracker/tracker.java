package tracker;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Observable;

// Tracker is subject
// tracker has list of clients
// gets the information
public class tracker extends Observable {
    private ArrayList<Client> listOfClients;
    private static int length = 0; // length of listOfClients

    public tracker() {
        listOfClients = new ArrayList<Client>();
    }

    public static void main(String[] args) throws Exception {
        int ServerPort = 12000;
        Communicator communicator = new Communicator(ServerPort);

        MessageProcessor mp = new MessageProcessor("trackerProcessor");
        communicator.setProcessor(mp);
        communicator.start();
        communicator.run();
    }

    //
    public void subscribe(Client client) {
        listOfClients.add(client);
    }

    public void unsubscribe(Client client) {
        int removeIndex = listOfClients.indexOf(client);
        listOfClients.remove(removeIndex);
    }

    public void notifyObserver() {

    }


}
