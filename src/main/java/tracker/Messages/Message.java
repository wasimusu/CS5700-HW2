package tracker.Messages;

import java.net.InetAddress;

public class Message {
    protected String message;
    protected int port;
    protected InetAddress address;

    public void Message(String message, InetAddress address, int port) {
        this.address = address;
        this.message = message;
        this.port = port;
    }

    public static Message messageObject(String message, InetAddress address, int port) {
        // We are never going to get a null or empty message here to process
        String[] parts = message.split(",", 0);

        if (parts[0].equals("Race")) {
            return new RaceStartedProcessor(message, address, port);
        } else if (message.equals("Hello")) {
            return new HelloProcessor(message, address, port);
        } else if (parts[0].equals("Subscribe")) {
            return new SubscribeProcessor(message, address, port);
        } else if (parts[0].equals("Unsubscribe")) {
            return new UnsubscribeProcessor(message, address, port);
        } else if (parts[0].equals("OnCourse")) {
            return new OnCourseProcessor(message, address, port);
        } else if (parts[0].equals("DidNotStart")) {
            return new AtheleteStatusProcessor(message, address, port);
        } else if (parts[0].equals("Started")) {
            return new AthleteStartedProcessor(message, address, port);
        } else if (parts[0].equals("Finished")) {
            return new AthleteFinishedProcessor(message, address, port);
        } else if (parts[0].equals("Registered")) {
            return new NewAtheleteProcessor(message, address, port);
        } else {
            System.out.println(message);
            return new Message();
        }
    }

    public void execute() throws Exception {
    }

    public String getMessage() {
        return message;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getAddress() {
        return address;
    }


}
