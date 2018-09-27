package tracker;

import java.util.ArrayList;
import java.util.HashMap;

public class Athelete {
    private static HashMap<Integer, Athelete> bibNumberAthelete = new HashMap<Integer, Athelete>();
    private static ArrayList<Integer> bibNumbers = new ArrayList<Integer>(); // list of registered athletes

    private int bibNumber;
    private float distanceCovered;
    private int lastUpdatedTime;
    private String status;
    private int age;
    private int startTime;
    private int finishTime;
    private String lastName;
    private String firstName;
    private String sex;

    private ArrayList<Integer> subscribers;

    public Athelete(String status, int bibNumber, int time, String firstName, String lastName, String sex, int age) {
        this.bibNumber = bibNumber;
        this.status = status;
        this.lastUpdatedTime = time;
        this.age = age;
        this.sex = sex;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startTime = time;
        subscribers = new ArrayList<Integer>(); // list of subscribers port address
        Athelete.bibNumberAthelete.put(bibNumber, this);
        Athelete.bibNumbers.add(bibNumber);
    }

    public String toString() {
        return ("Player : " + this.bibNumber + " " + this.lastUpdatedTime + " : " + status);
    }

    // Update the athelete's information as you get data from race
    public void updateStatus(String status, int time, float distanceCovered) {
        if (status.equals("Started")) {
            this.startTime = time;
        }
        this.distanceCovered = distanceCovered;
        this.status = status;
        this.lastUpdatedTime = time;
        this.finishTime = time;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getSex() {
        return this.sex;
    }
    public int getAge(){return this.age;}

    public String getStatus() {
        return this.status;
    }

    public String getStartTime() {
        return String.valueOf(this.startTime);
    }

    // Get the lis of subscribers of an athelete to post notifications to clients
    public ArrayList<Integer> getsubscribers() {
        return this.subscribers;
    }

    // Subscribe to an athelete's activity
    public void subscribe(int port) throws Exception {
        this.subscribers.add(port);
        System.out.println(bibNumber + " gained a client : " + port);
    }

    // Unsubscribe from an athelete's activity
    public void unsubscribe(int port) throws Exception {
        int portIndex = this.subscribers.indexOf(port);
        this.subscribers.remove(portIndex);
        System.out.println(bibNumber + " lost a client : " + port);
    }

    public int getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    // Get distance convered by Athelete when it is observed/updated
    public float getDistanceCovered() {
        return distanceCovered;
    }

    // Get bib number of the athelete
    public int getBibNumber() {
        return bibNumber;
    }

    public static Athelete getAtheleteByBibNumber(int bibNumber) {
        return Athelete.bibNumberAthelete.get(bibNumber);
    }

    public static ArrayList<Integer> getBibNumbers() {
        return Athelete.bibNumbers;
    }
}
