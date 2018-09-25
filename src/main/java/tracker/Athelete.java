package tracker;

import java.util.ArrayList;
import java.util.HashMap;

public class Athelete {
    public static HashMap<Integer, Athelete> bibNumberAthelete = new HashMap<Integer, Athelete>();

    private int bibNumber;
    private double distanceCovered;
    private double time;
    private String status;
    private int age;
    private String lastName;
    private String firstName;
    private String sex;

    private ArrayList<Integer> subscribersPortAddress;
    private int totalSubscriber;
    private static HashMap<String, Athelete> mapa;

    public Athelete(String status, int bibNumber, int time, String firstName, String lastName, String sex, int age) {
        this.bibNumber = bibNumber;
        this.status = status;
        this.time = time;
        this.age = age;
        this.sex = sex;
        this.firstName = firstName;
        this.lastName = lastName;
        System.out.println(this);
        subscribersPortAddress = new ArrayList<Integer>();
        bibNumberAthelete.put(bibNumber, this);
    }

    public String toString() {
        return ("Player : " + this.bibNumber + " " + this.time + " : " + status);
    }

    // Update the athelete's information as you get data from race
    public void updateStatus(String status, int time, float distanceCovered) {
        this.distanceCovered = distanceCovered;
        this.status = status;
        this.time = time;
    }

    // Subscribe to an athelete's activity
    public void subscribe(Client client) throws Exception {
        this.subscribersPortAddress.add(client.getPortAddress());
        totalSubscriber++;
        System.out.println(bibNumber + " gained a client : " + totalSubscriber);
    }

    // Unsubscribe from an athelete's activity
    public void unsubscribe(Client client) throws Exception {
        this.subscribersPortAddress.remove(client.getPortAddress());
        totalSubscriber--;
        System.out.println(bibNumber + " lost a client: " + totalSubscriber);
    }

//    public void notifyChange() {
//        // Notify the tracker maybe
//        for (Client subscriber : subscribers) {
//            // Somehow inform them that a change occured
//        }
//    }

    public double getTime() {
        return time;
    }

    // Get distance convered by Athelete when it is observed/updated
    public double getDistanceCovered() {
        return distanceCovered;
    }

    // Get bib number of the athelete
    public double getBibNumber() {
        return bibNumber;
    }

    // Get the total number of subscriber of an athelete
    public int getTotalSubscriber() {
        return totalSubscriber;
    }

    public static Athelete getAtheleteByBibNumber(int bibNumber) {
        return bibNumberAthelete.get(bibNumber);
    }
}
