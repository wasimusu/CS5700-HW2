package tracker;

import org.junit.Test;

public class AtheleteTest {

    @Test
    public void testConstruction() {
        Athelete a1 = new Athelete("Registered", 4, 40, "Wasim", "Khan", "M", 30);
        Athelete a2 = new Athelete("OnCourse", 10, 100, "Shelly", "Rice", "F", 30);
        assert a1.getStatus().equals("Registered");
        assert a1.getFirstName().equals("Wasim");
        assert a1.getLastName().equals("Khan");
        assert a1.getStartTime().equals("40");
        assert a1.getBibNumber() == 4;
        assert a1.getStatus().equals("Registered");
    }

    @Test
    public void testSubscribtion() throws Exception {
        // did the number of subscribers change
        int c1Port = 800;
        Client c1 = new Client(c1Port);
        Athelete athelete1 = new Athelete("Registered", 4, 40, "Wasim", "Khan", "M", 30);
        athelete1.subscribe(c1Port);
        int c2Port = 8000;
        Client c2 = new Client(c2Port);
        athelete1.subscribe(c2Port);

        Athelete athelete2 = new Athelete("Registered", 10, 100, "Shelly", "Rice", "F", 30);
        athelete2.subscribe(c2Port);
    }

    @Test
    public void testUnscription() throws Exception {
        Athelete athelete1 = new Athelete("Registered", 4, 40, "Wasim", "Khan", "M", 30);

        // did the number of subscribers change
        int c1Port = 800;
        Client c1 = new Client(c1Port);

        int c2Port = 8000;
        Client c2 = new Client(c2Port);

        athelete1.subscribe(c1Port);
        athelete1.subscribe(c2Port);
        athelete1.unsubscribe(c1Port);
        athelete1.unsubscribe(c2Port);

        Athelete athelete2 = new Athelete("Registered", 10, 100, "Shelly", "Rice", "F", 30);
        athelete2.subscribe(c2Port);
        athelete2.subscribe(c1Port);
    }


}
