package tracker.Messages;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class AthleteStartedProcessorTest {
    @Test
    public void testValidConstuction() throws Exception {
        String sentMessage = "wasim";
        InetAddress sentAddress = InetAddress.getLocalHost();
        int sentPort = 34;
        AthleteStartedProcessor n1 = new AthleteStartedProcessor(sentMessage, sentAddress, sentPort);
        assertEquals(sentMessage, n1.getMessage());
        assertEquals(sentAddress, n1.getAddress());
        assertEquals(sentPort, n1.getPort());
    }

}