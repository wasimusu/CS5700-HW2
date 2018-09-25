package tracker.Messages;

import java.net.InetAddress;

public class HelloProcessor extends Message{

    public HelloProcessor(String message, InetAddress address, int port){
        System.out.println("Executing in Hello");
    }

    public void execute(String message, InetAddress address, int port) throws Exception {
        System.out.println("Executing in Hello "+message+"\t"+address+port);
    }
}