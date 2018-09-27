package tracker;

import org.junit.Test;

import java.util.ArrayList;

public class AtheleteTest {

    @Test
    public void testConstruction() {
        Athelete a1 = new Athelete("Registered", 4, 40, "Wasim", "Khan", "M", 30);
        assert a1.getStatus().equals("Registered");
        assert a1.getFirstName().equals("Wasim");
        assert a1.getLastName().equals("Khan");
        assert a1.getStartTime().equals("40");
        assert a1.getBibNumber() == 4;
        assert a1.getAge() == 30;
        assert a1.getSex().equals("M");
        assert Athelete.getAtheleteByBibNumber(4).equals(a1);
    }

    public void testGetBibNumbers(){
        Athelete a1 = new Athelete("Registered", 24, 30, "Wasim", "Khan", "M", 30);
        Athelete a2 = new Athelete("Registered", 43, 47, "Mary", "Kom", "F", 19);
        ArrayList<Integer> bibNumbers = Athelete.getBibNumbers();
        ArrayList<Integer> actualBibNumbers = new ArrayList<Integer>();
        actualBibNumbers.add(24);
        actualBibNumbers.add(43);
        assert actualBibNumbers.equals(bibNumbers);
    }

    public void testUpdateStatus() {
        Athelete a1 = new Athelete("Registered", 4, 40, "Wasim", "Khan", "M", 30);
        a1.updateStatus("Finish", 4000, 1233);
        assert a1.getDistanceCovered() == 1233;
        assert a1.getLastUpdatedTime() == 4000;
        assert a1.getStartTime().equals("40");
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

        ArrayList<Integer> subscribers = athelete1.getsubscribers();
        ArrayList<Integer> actualSubscribers = new ArrayList<Integer>();
        actualSubscribers.add(c1Port);
        actualSubscribers.add(c2Port);
        assert subscribers.equals(actualSubscribers);
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

    public void testGetAtheleteByBibNumber() {
        Athelete a1 = new Athelete("Registered", 4, 40, "Wasim", "Khan", "M", 30);
        assert Athelete.getAtheleteByBibNumber(4).equals(a1);
    }

}
