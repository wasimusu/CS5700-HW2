package tracker.Messages;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class SubscribeProcessorTest {
    @Test
    public void testValidConstuction() throws Exception {
        String sentMessage = "Subscribe,1";
        InetAddress sentAddress = InetAddress.getLocalHost();
        int sentPort = 34;
        SubscribeProcessor s1 =  new SubscribeProcessor(sentMessage, sentAddress, sentPort);
        s1.execute();
        assertEquals(sentMessage, s1.getMessage());
        assertEquals(sentAddress, s1.getAddress());
        assertEquals(sentPort, s1.getPort());
    }


}