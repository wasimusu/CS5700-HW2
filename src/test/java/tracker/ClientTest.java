package tracker;

import java.net.DatagramPacket;

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

        assertEquals("Client : \tPort Address : " + String.valueOf(1200), client1.toString());

        // close the clients otherwise they interfere
    }

    public void testGetPortAddress() throws Exception {
        Client client1 = new Client(1200);
        assertEquals(1200, client1.getPortAddress());

        Client client2 = new Client(2400);
        assertEquals(2400, client2.getPortAddress());
    }

}
