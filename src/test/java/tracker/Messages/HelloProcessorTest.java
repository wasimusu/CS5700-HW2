package tracker.Messages;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class HelloProcessorTest {

    @Test
    public void testValidConstuction() throws Exception {
        String sentMessage = "Hello";
        InetAddress sentAddress = InetAddress.getLocalHost();
        int sentPort = 34;
        HelloProcessor n1 = new HelloProcessor(sentMessage, sentAddress, sentPort);
        n1.execute();
        assertEquals(sentMessage, n1.getMessage());
        assertEquals(sentAddress, n1.getAddress());
        assertEquals(sentPort, n1.getPort());
    }
}