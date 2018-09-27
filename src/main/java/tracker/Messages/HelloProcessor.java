package tracker.Messages;

import tracker.Athelete;
import tracker.Communicator;
import tracker.Client;
import tracker.RaceTracker;

import java.net.InetAddress;

public class HelloProcessor extends Message {
    public HelloProcessor(String message, InetAddress address, int port) {
        super.Message(message, address, port);
    }

    public void execute() throws Exception {
        System.out.println("A client started up :\t" + message + "\t" + address + port);

        // Create a client with the same endpoint
        Client client = new Client(port);
        System.out.println(client);

        // Send out a race stared message to client if you have not already
        if (!client.isAcknoweledged() || Athelete.getBibNumbers() != null || RaceStartedProcessor.getRaceStartedMessage() != null) {
            String raceStartedMessage = RaceStartedProcessor.getRaceStartedMessage();
            if (raceStartedMessage != null) {
                RaceTracker.sendMessage(raceStartedMessage, port);
                client.setAcknoweledged();
            }
            for (Integer bibNumber : Athelete.getBibNumbers()) {
                Athelete a = Athelete.getAtheleteByBibNumber(bibNumber);
                String boardcastMessage = "Athlete," + bibNumber + "," + a.getFirstName() + "," + a.getLastName() + "," + a.getSex() + "," + a.getAge();
                RaceTracker.sendMessage(boardcastMessage, port);
            }
        }
    }

}