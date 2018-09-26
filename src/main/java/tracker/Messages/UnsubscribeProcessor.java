package tracker.Messages;
import tracker.Athelete;
import java.net.InetAddress;

public class UnsubscribeProcessor extends Message {

    public UnsubscribeProcessor(String message, InetAddress address, int port) {
        super.Message(message, address, port);
    }

    public void execute() throws Exception {
        System.out.println("Executing in Unsubscribe " + message + "\t" + address + port);
        String[] messages = message.split(",");
        String bibNumber = messages[1];
        Athelete mappedAthelete = Athelete.getAtheleteByBibNumber(Integer.valueOf(bibNumber));
        mappedAthelete.unsubscribe(port); // Maybe you can just keep the list of port address
    }

}
