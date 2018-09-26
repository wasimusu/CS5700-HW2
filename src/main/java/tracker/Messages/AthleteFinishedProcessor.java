package tracker.Messages;

import tracker.Athelete;
import tracker.RaceTracker;

import java.net.InetAddress;
import java.util.ArrayList;

public class AthleteFinishedProcessor extends Message {
    public AthleteFinishedProcessor(String message, InetAddress address, int port) {
        super.Message(message, address, port);
    }

    public void execute() throws Exception {
        System.out.println("Athelete Started " + message + "\t" + address + port);
        String[] messages = this.message.split(",");
        String status = messages[0];
        String bibNumber = messages[1];
        String startTime = messages[2];

        Athelete a = Athelete.bibNumberAthelete.get(Integer.valueOf(bibNumber));

        if (a != null) {
            a.updateStatus(status, Integer.valueOf(startTime), a.getDistanceCovered());
            System.out.println("Updated : " + a);

            // Notify all the clients of status change
            // MessageFormat : Status,<bib number>,<status>,<start time>,<distance covered in meters>,<last updated time>, <finished time>
            String broadcastMessage = "Status," + bibNumber + "," + status + "," + a.getStartTime() + "," + a.getDistanceCovered()+ "," + messages[2] + "," + String.valueOf(a.getLastUpdatedTime());
            ArrayList<Integer> subscribers = a.getsubscribers();
            RaceTracker.sendMessage(broadcastMessage, subscribers);
            System.out.println("Broadcasting new atheletes to all clients");

        } else {
            System.out.println("Null object encountered. Can't update status");
        }
    }

}
