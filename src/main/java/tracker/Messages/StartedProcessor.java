package tracker.Messages;

import tracker.Athelete;

import java.net.InetAddress;

public class StartedProcessor extends Message {
    private String message;
    private InetAddress address;
    private int port;

    public StartedProcessor(String message, InetAddress address, int port) {
        this.message = message;
        this.address = address;
        this.port = port;
    }

    public void execute() throws Exception {
        System.out.println("Executing in Athelete Status  " + message + "\t" + address + port);
        String[] messages = message.split(",");
        String status = messages[0];
        String bibNumber = messages[1];
        int timeElapsed = Integer.valueOf(messages[2]);
        float distanceCovered = 0;

        Athelete a = Athelete.bibNumberAthelete.get(Integer.valueOf(bibNumber));
        System.out.println(bibNumber);
        System.out.println("Old " + a);

        if (a != null) {
            a.updateStatus(status, timeElapsed, distanceCovered);
            // Map is required to retrieve athelete object based on string id
            System.out.println("Updated : " + a);

            // Notify all the clients of status change
        } else {
            System.out.println("Null object encountered. Can't update status");
        }
    }
}
