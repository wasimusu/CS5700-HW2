package tracker.Messages;

import tracker.Athelete;
import tracker.Client;
import tracker.RaceTracker;

import java.net.InetAddress;

public class SubscribeProcessor extends Message {

    public SubscribeProcessor(String message, InetAddress address, int port) {
        super.Message(message, address, port);
    }

    public void execute() throws Exception {
        System.out.println("Executing in Subscribe " + message + "\t" + address + port);
        String[] messages = message.split(",");
        String bibNumber = messages[1];
        Athelete mappedAthelete = Athelete.getAtheleteByBibNumber(Integer.valueOf(bibNumber));
        if (mappedAthelete != null) {
            mappedAthelete.subscribe(port); // Maybe you can just keep the list of port address
        }
        // Inform client that the race started when they first register to the client
        if (RaceStartedProcessor.getRaceStartedMessage() != null) {
            RaceTracker.sendMessage(RaceStartedProcessor.getRaceStartedMessage(), port);
        }
    }
}
