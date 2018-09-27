package tracker.Messages;

import tracker.Athelete;
import tracker.Client;
import tracker.RaceTracker;

import java.net.InetAddress;
import java.util.ArrayList;

public class NewAtheleteProcessor extends Message {
//    private String message;
//    private InetAddress address;
//    private int port;

    public NewAtheleteProcessor(String message, InetAddress address, int port) {
        super.Message(message, address, port);
//        this.message = message;
//        this.address = address;
//        this.port = port;
    }

    public void execute() throws Exception {
        System.out.println("Executing in New Athelete " + message + "\t" + address + port);
        // Making sense of the message received and registering the athelete
        String[] messages = this.message.split(",");
        String status = messages[0];
        String bibNumber = messages[1];
        int time = Integer.valueOf(messages[2]);
        String firstName = messages[3];
        String lastName = messages[4];
        String gender = messages[5];
        String age = messages[6];

        // Instanstiate an athelete object
        Athelete a = new Athelete(status, Integer.valueOf(bibNumber), time, firstName, lastName, gender, Integer.valueOf(age));

        // Notify All the existing clients
        ArrayList<Integer> allClients = Client.getAllClients();
        // Format : Athlete,<bib number>,<first name>,<last name>,<gender>,<age>
        String boardcastMessage = "Athlete," + bibNumber + "," + firstName + "," + lastName + "," + gender + "," + age;
        if (allClients != null) {
            RaceTracker.sendMessage(boardcastMessage, allClients);
            System.out.println("Broadcasting new atheletes to all clients");
        }
    }
}