package tracker.Messages;
import tracker.Athelete;

import java.net.InetAddress;

public class NewAtheleteProcessor extends Message {
    private String message;
    private InetAddress address;
    private int port;

    public NewAtheleteProcessor(String message, InetAddress address, int port) {
        this.message = message;
        this.address = address;
        this.port = port;
    }

    public void execute() throws Exception {
        System.out.println("Executing in New Athelete " + message + "\t" + address + port);
        // Making sense of the message received and registering the athelete
        String[] messages = message.split(",");
        String status = messages[0];
        int bibNumber = Integer.valueOf(messages[1]);
        int time = Integer.valueOf(messages[2]);
        String firstName = messages[3];
        String lastName = messages[4];
        String gender = messages[5];
        int age = Integer.valueOf(messages[6]);

        // Instanstiate an athelete object
        Athelete a = new Athelete(status, bibNumber, time, firstName, lastName, gender, age);

        // Notify All the existing clients
    }
}