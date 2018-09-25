package tracker.Messages;

import java.net.InetAddress;

public class Message {
    static String message;

    public static void Message() {
    }

    public static Message messageObject(String message, InetAddress address, int port) {
        String[] parts = message.split(",", 0);

        if (parts[0].equals("Race")) {
            return new RaceProcessor(message, address, port);
        } else if (message.equals("Hello")) {
            return new HelloProcessor(message, address, port);
        } else if (parts[0].equals("Subscribe")) {
            return new SubscribeProcessor(message, address, port);
        } else if (parts[0].equals("Unsubscribe")) {
            return new UnsubscribeProcessor(message, address, port);
        } else if (parts[0].equals("OnCourse")) {
            return new AtheleteStatusProcessor(message, address, port);
        } else if (parts[0].equals("DidNotStart")) {
            return new AtheleteStatusProcessor(message, address, port);
        } else if (parts[0].equals("Started")) {
            return new StartedProcessor(message, address, port);
        } else if (parts[0].equals("Finished")) {
            return new AtheleteStatusProcessor(message, address, port);
        } else if (parts[0].equals("Registered")) {
            return new NewAtheleteProcessor(message, address, port);
        } else {
            System.out.println(message);
        }
        return new Message();
    }

    public void execute() throws Exception {
    }
}
