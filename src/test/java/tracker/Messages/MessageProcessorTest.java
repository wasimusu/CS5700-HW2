package tracker.Messages;

import org.junit.Test;
import tracker.Communicator;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class MessageProcessorTest {

    @Test
    public void testConstruction() throws Exception{
        int serverPort = 1000;
        String serverName = "tracker";
        Communicator serverComm = new Communicator(serverPort);
        MessageProcessor messageProcessor = new MessageProcessor(serverName);
        serverComm.setProcessor(messageProcessor);

        Communicator communicator = new Communicator(1200);
        communicator.send("Hey how are you", InetAddress.getLocalHost(), serverPort);
        communicator.send("Another message", InetAddress.getLocalHost(), serverPort);
        communicator.send("Count this one too", InetAddress.getLocalHost(), serverPort);

        assertEquals(messageProcessor.ReceiveCount(), 3);
        assert serverName.equals(messageProcessor.getName());
    }


}