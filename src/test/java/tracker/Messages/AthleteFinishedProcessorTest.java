package tracker.Messages;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class AthleteFinishedProcessorTest {
    @Test
    public void testValidConstuction() throws Exception {
        String sentMessage = "Finished,9,2667";
        InetAddress sentAddress = InetAddress.getLocalHost();
        int sentPort = 34;
        AthleteFinishedProcessor n1 = new AthleteFinishedProcessor(sentMessage, sentAddress, sentPort);
        n1.execute();
        assertEquals(sentMessage, n1.getMessage());
        assertEquals(sentAddress, n1.getAddress());
        assertEquals(sentPort, n1.getPort());
    }
}