package tracker.Messages;
import org.junit.Test;
import java.net.InetAddress;
import static org.junit.Assert.*;

public class MessageTest {
    @Test
    public void testValidConstuction() throws Exception {
        String sentMessage = "wasim";
        InetAddress sentAddress = InetAddress.getLocalHost();
        int sentPort = 34;
//        Message n1 =  new Message(sentMessage, sentAddress, sentPort);
//        assertEquals(sentMessage, n1.getMessage());
//        assertEquals(sentAddress, n1.getAddress());
//        assertEquals(sentPort, n1.getPort());
    }

    @Test
    public void testCreateMessage(){

    }

}