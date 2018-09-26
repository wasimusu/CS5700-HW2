package tracker.Messages;

import tracker.Athelete;
import tracker.RaceTracker;
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
        Athelete mappedAthelete = Athelete.getAtheleteByBibNumber(Integer.valueOf(bibNumber));
        mappedAthelete.subscribe(port); // Maybe you can just keep the list of port address

        // Inform client that the race started when they first register to the client
        RaceTracker.sendMessage("Race,Bension Loop,16090", port);
    }
}
