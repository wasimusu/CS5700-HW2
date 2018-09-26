package tracker.Messages;

import tracker.Athelete;
import tracker.RaceTracker;

import java.net.InetAddress;
import java.util.ArrayList;

public class AthleteStartedProcessor extends Message {
    public AthleteStartedProcessor(String message, InetAddress address, int port) {
        super.Message(message, address, port);
    }

    public void execute() throws Exception {
        System.out.println("Executing in Athelete Status  " + message + "\t" + address + port);
        String[] messages = this.message.split(",");
        String status = messages[0];
        String bibNumber = messages[1];
        int timeElapsed = Integer.valueOf(messages[2]);
        float distanceCovered = 0;

        Athelete a = Athelete.bibNumberAthelete.get(Integer.valueOf(bibNumber));
        System.out.println(bibNumber);
        System.out.println("Old " + a);

        if (a != null) {
            a.updateStatus(status, timeElapsed, distanceCovered);
            System.out.println("Updated : " + a);

            // Notify all the clients of status change
            // Notify All the existing clients
            // Format : Status,<bib number>,<status>,<start time>,<distance covered in meters>,<last updated time>, <finished time>
            String broadcastMessage = "Status," + bibNumber + "," + status + "," + a.getStartTime() + "," + distanceCovered + "," + messages[2] + "," + messages[6];
            ArrayList<Integer> subscribers = a.getsubscribers();
            RaceTracker.sendMessage(broadcastMessage, subscribers);
            System.out.println("Broadcasting new atheletes to all clients");

        } else {
            System.out.println("Null object encountered. Can't update status");
        }
    }

}
