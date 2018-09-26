package tracker;

public class AtheleteTest {

    public void testConstruction() {
        Athelete athelete1 = new Athelete("Registered", 4, 40, "Wasim", "Khan", "M", 30);
        Athelete athelete2 = new Athelete("Registered", 10, 100, "Shelly", "Rice", "F", 30);
    }

    public void testSubscribtion() throws Exception {
        // did the number of subscribers change
        int c1Port = 800;
        Client c1 = new Client(c1Port);
        Athelete athelete1 = new Athelete("Registered", 4, 40, "Wasim", "Khan", "M", 30);
        athelete1.subscribe(c1);
        int c2Port = 8000;
        Client c2 = new Client(c2Port);
        athelete1.subscribe(c2);

        Athelete athelete2 = new Athelete("Registered", 10, 100, "Shelly", "Rice", "F", 30);
        athelete2.subscribe(c2);
    }

    public void testUnscription() throws Exception {
        // did the number of subscribers change
        int c1Port = 800;
        Client c1 = new Client(c1Port);
        Athelete athelete1 = new Athelete("Registered", 4, 40, "Wasim", "Khan", "M", 30);
        athelete1.subscribe(c1);
        int c2Port = 8000;
        Client c2 = new Client(c2Port);
        athelete1.subscribe(c2);
        athelete1.unsubscribe(c2);
        athelete1.unsubscribe(c1);

        Athelete athelete2 = new Athelete("Registered", 10, 100, "Shelly", "Rice", "F", 30);
        athelete2.subscribe(c2);
        athelete2.subscribe(c1);

    }
}
