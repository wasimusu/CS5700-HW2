package tracker;

import org.junit.Test;

import java.net.DatagramPacket;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    @Test
    public void testConstruction() throws Exception {
        Client client1 = new Client(1200);
        Communicator comm1 = new Communicator(12000);
        DatagramPacket message = comm1.getMessage();
        System.out.print(message);

        assert !client1.isAcknoweledged();
        client1.setAcknoweledged();
        assert client1.isAcknoweledged();

        // close the clients otherwise they interfere
    }

    @Test
    public void testToString() throws Exception {
        Client client1 = new Client(1200);
        assertEquals("Client : \tPort Address : " + String.valueOf(1200), client1.toString());
    }

    @Test
    public void testGetPortAddress() throws Exception {
        Client client1 = new Client(1200);
        assertEquals(1200, client1.getPortAddress());

        Client client2 = new Client(2400);
        assertEquals(2400, client2.getPortAddress());
    }

    @Test
    public void testGetAllClients() throws Exception {
        int port1 = 1210;
        int port2 = 1211;
        Client client1 = new Client(port1);
        Client client2 = new Client(port2);
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(port1);
        arrayList.add(port2);
        ArrayList<Integer> expectedArrayList = Client.getAllClients();
        System.out.println(expectedArrayList);
        System.out.println(arrayList);
        assert arrayList.equals(expectedArrayList);
    }

}
