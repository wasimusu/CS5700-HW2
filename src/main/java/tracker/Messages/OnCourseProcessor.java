package tracker.Messages;

import tracker.Athelete;
import tracker.RaceTracker;

import java.net.InetAddress;
import java.util.ArrayList;

public class OnCourseProcessor extends Message {
    private String message;
    private InetAddress address;
    private int port;

    public OnCourseProcessor(String message, InetAddress address, int port) {
        this.message = message;
        this.address = address;
        this.port = port;
    }

    public void execute() throws Exception {
        System.out.println("Executing in Athelete Status  " + message + "\t" + address + port);
        String[] messages = message.split(",");
        String status = messages[0];
        String bibNumber = messages[1];
        String timeElapsed = messages[2];
        float distanceCovered = Float.valueOf(messages[3]);

        Athelete a = Athelete.bibNumberAthelete.get(Integer.valueOf(bibNumber));
        if (a != null) {
            a.updateStatus(status, Integer.valueOf(timeElapsed), distanceCovered);
            System.out.println("Updated Athelete: " + a);

            // Notify All the existing clients
            String startTime = timeElapsed;
            //Message Format : Status,<bib number>,<status>,<start time>,<distance covered in meters>,<last updated time>, <finished time>
            String broadcastMessage = "Status," + messages[1] + "," + status + "," + String.valueOf(a.getStartTime()) + "," + distanceCovered + "," + messages[2] + "," + messages[2];
            ArrayList<Integer> subscribers = a.getsubscribers();
            RaceTracker.sendMessage(broadcastMessage, subscribers);
            System.out.println("Broadcasting new atheletes to all clients");

        } else {
            System.out.println("Null object encountered. Can't update status");
        }
    }

}
