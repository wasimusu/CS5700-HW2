package tracker.Messages;

import org.junit.Test;
import tracker.Communicator;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class MessageProcessorTest {

    @Test
    public void testConstruction() throws Exception {
        Communicator comm1 = new Communicator();
        MessageProcessor processor1 = new MessageProcessor("A");
        assert processor1.getName().equals("A");
        comm1.setProcessor(processor1);
        comm1.start();

        Communicator comm2 = new Communicator();
        MessageProcessor processor2 = new MessageProcessor("B");
        assert processor2.getName().equals("B");
        comm2.setProcessor(processor2);
        comm2.start();

        comm1.send("Hello", InetAddress.getLocalHost(), comm2.getLocalPort());
        comm2.send("Hello there!", InetAddress.getLocalHost(), comm1.getLocalPort());
        comm2.send("What's up", InetAddress.getLocalHost(), comm1.getLocalPort());
        comm1.send("Bye", InetAddress.getLocalHost(), comm2.getLocalPort());
        comm2.send("Bye Bye", InetAddress.getLocalHost(), comm1.getLocalPort());

        Thread.sleep(100);
        assertEquals(3, processor1.ReceiveCount());
        assertEquals(2, processor2.ReceiveCount());

        // We are not going to ever be able to send a null message so can't test that
        // We can not also send message to/from a null address so can't that also
    }


}