package tracker.Messages;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class UnsubscribeProcessorTest {
    @Test
    public void testValidConstuction() throws Exception {
        String sentMessage = "Unsubscribe,1";
        InetAddress sentAddress = InetAddress.getLocalHost();
        int sentPort = 34;
        UnsubscribeProcessor n1 = new UnsubscribeProcessor(sentMessage, sentAddress, sentPort);
        n1.execute();
        assertEquals(sentMessage, n1.getMessage());
        assertEquals(sentAddress, n1.getAddress());
        assertEquals(sentPort, n1.getPort());
    }


}