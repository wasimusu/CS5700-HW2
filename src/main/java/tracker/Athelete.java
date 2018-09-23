package tracker;

import javafx.beans.Observable;

import java.util.ArrayList;

// A client has pool of players to observe
enum Status {
    REGISTERED, STARTED, ONCOURSE, FINISHED
}

enum SEX {
    M, F
}

public class Athelete {
    private int bibNumber;
    private double distanceCovered;
    private double time;
    private Status status;
    private int age;
    private String lastName;
    private String firstName;
    private SEX sex;

    private ArrayList<Client> subscribers;
    private int totalSubscriber;

    // You set the bibNumber when you get the simulator data and also set the status
    public Athelete(Status status, int bibNumber, int time, String firstName, String lastName, SEX sex, int age) {
        this.bibNumber = bibNumber;
        this.status = status;
        this.time = time;
        this.age = age;
        this.sex = sex;
        this.firstName = firstName;
        this.lastName = lastName;
        System.out.println("Player registered : " + this.firstName + " " + this.lastName);
    }

    // You set the bibNumber when you get the simulator data and also set the status
    public Athelete(String status, int bibNumber, int time) {
        this.bibNumber = bibNumber;
        this.status = Status.valueOf(status);
        this.time = time;
        System.out.println("Player registered : " + this.bibNumber + " " + this.status + "\t" + this.time);
    }

    // Update the athelete's information as you get data from race
    public void updateStatus(String status, int time, float distanceCovered) {
        this.distanceCovered = distanceCovered;
        this.status = Status.valueOf(status);
        this.time = time;
    }

    // Subscribe to an athelete's activity
    public void subscribe(Client client) {
        this.subscribers.add(client);
        totalSubscriber++;
    }

    // Unsubscribe from an athelete's activity
    public void unsubscribe(Client client) {
        this.subscribers.remove(client);
        totalSubscriber--;
    }

    public void notifyChange() {
        // Notify the tracker maybe
        for (Client subscriber : subscribers) {
            // Somehow inform them that a change occured
        }
    }

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
}
