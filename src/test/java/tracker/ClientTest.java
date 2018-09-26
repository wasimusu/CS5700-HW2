package tracker;

import java.net.DatagramPacket;

import static java.nio.charset.StandardCharsets.UTF_16BE;
import static org.junit.Assert.assertEquals;

public class ClientTest {

    public void testConstruction() throws Exception {
        Client client1 = new Client(1200);
        Communicator comm1 = new Communicator(12000);
        DatagramPacket message = comm1.getMessage();
        System.out.print(message);

        assert !client1.isAcknoweledged();
        client1.setAcknoweledged();
        assert client1.isAcknoweledged();

        assert client1.equals(Client.identifyClient(1200));
        assertEquals("Client : \tPort Address : " + String.valueOf(1200), client1.toString());

        // close the clients otherwise they interfere
    }

    public void testSendMessageToTracker() throws Exception {
        Client client1 = new Client(1200);
        Communicator tracker = new Communicator(12000);
        String sentMessage = "Hello World!";
        client1.sendMessageToTracker(sentMessage);
        DatagramPacket receivedPacket = tracker.getMessage();
        String receivedMessage = new String(receivedPacket.getData(), 0, receivedPacket.getLength(), UTF_16BE);
        assertEquals(sentMessage, receivedMessage);

        client1.sendSubscribe(2);
        receivedPacket = tracker.getMessage();
        String subscribeMessage = new String(receivedPacket.getData(), 0, receivedPacket.getLength(), UTF_16BE);
        assertEquals(subscribeMessage, "Subscribe,2");

        client1.sendUnsubscribe(2);
        receivedPacket = tracker.getMessage();
        String unsubscribeMessage = new String(receivedPacket.getData(), 0, receivedPacket.getLength(), UTF_16BE);
        assertEquals(unsubscribeMessage, "Unsubscribe,2");
    }

    public void testGetPortAddress() throws Exception {
        Client client1 = new Client(1200);
        assertEquals(1200, client1.getPortAddress());

        Client client2 = new Client(2400);
        assertEquals(2400, client2.getPortAddress());
    }

}
