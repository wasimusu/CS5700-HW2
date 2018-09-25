package tracker.Messages;

import java.net.InetAddress;

public class Message {
    static String message;

    public static void Message() {
        message = "hi";
    }

    public static Message messageObject(String message, InetAddress address, int port) {
        System.out.println("Got a message : " + message);

        String[] parts = message.split(",");
        System.out.println(parts[0] + "\t" + parts[1] + "\t" + parts[2]);
        if (parts[0].equals("Race")) {
            return new RaceProcessor(message, address, port);
        }
        if (parts[0].equals("Hello")) {
            return new HelloProcessor(message, address, port);
        }
        if (parts[0].equals("Subscribe")) {
            return new HelloProcessor(message, address, port);
        }
        if (parts[0].equals("Unsubscribe")) {
            return new UnsubscribeProcessor(message, address, port);
        }
        if (parts[0].equals("OnCourse")) {
            return new AtheleteStatusProcessor(message, address, port);
        }
        if (parts[0].equals("DidNotStart")) {
            return new Message();
        }
        if (parts[0].equals("Started")) {
            return new Message();
        }
        if (parts[0].equals("Finished")) {
            return new Message();
        }
        if (parts[0].equals("Registered")) {
            return new HelloProcessor(message, address, port);
        }
        return new Message();
    }

    public void execute() {
    }
}
