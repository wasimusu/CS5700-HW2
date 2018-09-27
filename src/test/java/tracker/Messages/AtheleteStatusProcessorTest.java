
package tracker.Messages;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class AtheleteStatusProcessorTest {

    @Test
    public void testValidConstuction() throws Exception {
        String sentMessage = "OnCourse,7,2340,15841.4693754572";
        InetAddress sentAddress = InetAddress.getLocalHost();
        int sentPort = 34;
        AtheleteStatusProcessor n1 =  new AtheleteStatusProcessor(sentMessage, sentAddress, sentPort);
        n1.execute();
        assertEquals(sentMessage, n1.getMessage());
        assertEquals(sentAddress, n1.getAddress());
        assertEquals(sentPort, n1.getPort());
    }

}