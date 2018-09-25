package tracker.Messages;

import tracker.Communicator;
import tracker.tracker;

import java.net.InetAddress;

public class HelloProcessor extends Message{
    private String message;
    private InetAddress address;
    private int port;

    public HelloProcessor(String message, InetAddress address, int port){
        this.message = message;
        this.address = address;
        this.port = port;
    }

    public void execute(String message, InetAddress address, int port) throws Exception {
        System.out.println("Got a message : " + message);
        System.out.println("Executing in Hello "+message+"\t"+address+port);
        Communicator comm = tracker.getTrackerCommunicator();
        comm.send("Yeyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy", InetAddress.getLocalHost(), port);
    }
    public void execute() throws Exception {
        System.out.println("Got a message : " + message);
        System.out.println("Executing in Hello "+message+"\t"+address+port);
        Communicator comm = tracker.getTrackerCommunicator();
        comm.send("Yeyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy", InetAddress.getLocalHost(), port);
    }
}