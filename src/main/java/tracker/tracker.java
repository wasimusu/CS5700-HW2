//package tracker;
//
//import tracker.Messages.MessageProcessor;
//
//import java.net.InetAddress;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Observable;
//
//public class tracker extends Observable {
//    private static ArrayList<Athelete> Atheletes = new ArrayList<Athelete>();
//    private static HashMap<String, Athelete> nameAtheleteMap = new HashMap<String, Athelete>();
//    private int ServerPort = 12000;
//    private static Communicator serverComm;
//    public static HashMap<Integer, Client> portAddressClientMap = new HashMap<Integer, Client>();
//    public static ArrayList<Integer> clientPortAddress = new ArrayList<Integer>();
//
//    public tracker() throws Exception {
//    }
//
//    public void helloProcessor(String message, InetAddress address, int portAddress) throws Exception {
//        System.out.println("A client started up: " + message);
//        clientPortAddress.add(portAddress);
//        Client client1 = new Client(portAddress);
//        System.out.println("Newbie : " + client1);
//        if (!client1.isAcknoweledged()) {
//            Communicator trackComm = new Communicator();
////            trackComm.send(raceStartedMessage, InetAddress.getLocalHost(), portAddress);
////            System.out.println("Sent Race started message back: " + raceStartedMessage);
//        }
//    }
//
//    public void raceStarted(String message, InetAddress address, int portAddress) throws Exception {
//        System.out.println("Got hello from a client : " + message);
//        clientPortAddress.add(portAddress);
//        this.notifyObserverss(message);
//    }
//
//    public void notifyObserverss(String message) throws Exception {
//        System.out.println("Notifying : ----------------------------");
//        for (Integer clientPort : clientPortAddress) {
//            this.serverComm.send(message, InetAddress.getLocalHost(), clientPort);
//            System.out.println("Notifying : " + clientPort + message);
//        }
//    }
//
//}
