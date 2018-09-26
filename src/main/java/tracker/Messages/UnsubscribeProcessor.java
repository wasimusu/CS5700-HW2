package tracker.Messages;

import tracker.Athelete;

import java.net.InetAddress;

public class UnsubscribeProcessor extends Message {
    private String message;
    private InetAddress address;
    private int port;

    public UnsubscribeProcessor(String message, InetAddress address, int port) {
        this.message = message;
        this.address = address;
        this.port = port;
    }

    public void execute() throws Exception {
        System.out.println("Executing in Unsubscribe " + message + "\t" + address + port);
        String[] messages = message.split(",");
        String bibNumber = messages[1];
        Athelete mappedAthelete = Athelete.getAtheleteByBibNumber(Integer.valueOf(bibNumber));
        mappedAthelete.unsubscribe(port); // Maybe you can just keep the list of port address
    }
}
