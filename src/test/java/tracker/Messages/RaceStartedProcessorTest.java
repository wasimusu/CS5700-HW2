package tracker.Messages;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class RaceStartedProcessorTest {
    @Test
    public void testValidConstuction() throws Exception {
        String sentMessage = "Race,Bension Loop,16090";
        InetAddress sentAddress = InetAddress.getLocalHost();
        int sentPort = 34;
        RaceStartedProcessor n1 =  new RaceStartedProcessor(sentMessage, sentAddress, sentPort);
        n1.execute();
        assertEquals(sentMessage, n1.getMessage());
        assertEquals(sentAddress, n1.getAddress());
        assertEquals(sentPort, n1.getPort());
    }


}