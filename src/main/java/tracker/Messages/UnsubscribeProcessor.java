package tracker.Messages;

import java.net.InetAddress;

public class UnsubscribeProcessor extends Message {
    public UnsubscribeProcessor(String message, InetAddress address, int port){
        System.out.println("Executing in Hello");
    }

    public void execute(String message, InetAddress address, int port) throws Exception {
        System.out.println("Executing in Unsubscribe "+message+"\t"+address+port);
    }
}
