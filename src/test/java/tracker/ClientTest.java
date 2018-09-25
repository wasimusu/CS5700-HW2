package tracker;

import java.net.DatagramPacket;

public class ClientTest {

    public void testConstruction() throws Exception {
        Client client1 = new Client(1200);
        Communicator comm1 = new Communicator(12000);
        DatagramPacket message = comm1.getMessage();
        System.out.print(message);
    }
}
