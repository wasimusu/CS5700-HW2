package tracker.Messages;

import tracker.Athelete;
import tracker.Client;

import java.net.InetAddress;

public class SubscribeProcessor extends Message {
    private String message;
    private InetAddress address;
    private int port;

    public SubscribeProcessor(String message, InetAddress address, int port) {
        this.message = message;
        this.address = address;
        this.port = port;
    }

    public void execute() throws Exception {
        System.out.println("Executing in Subscribe " + message + "\t" + address + port);
        String[] messages = message.split(",");
        String bibNumber = messages[1];
        Client mappedClient = Client.identifyClient(port);
        Athelete mappedAthelete = Athelete.getAtheleteByBibNumber(Integer.valueOf(bibNumber));
        mappedAthelete.subscribe(mappedClient); // Maybe you can just keep the list of port address

//        this.nameAtheleteMap.get(bibNumber).subscribe(mappedClient);
//        // Convey to the client that the race started
//        if (!mappedClient.isAcknoweledged()) {
//            this.serverComm.send(raceStartedMessage, InetAddress.getLocalHost(), port);
//        }

    }
}
