package tracker.Messages;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class NewAtheleteProcessorTest {

    @Test
    public void testValidConstuction() throws Exception{
        String sentMessage = "Registered,1,0,Valentine,Zamora,M,30";
        InetAddress sentAddress = InetAddress.getLocalHost();
        int sentPort = 34;
        NewAtheleteProcessor n1 = new NewAtheleteProcessor(sentMessage, sentAddress, sentPort);
        n1.execute();
        assertEquals(sentMessage, n1.getMessage());
        assertEquals(sentAddress, n1.getAddress());
        assertEquals(sentPort, n1.getPort());
    }

}