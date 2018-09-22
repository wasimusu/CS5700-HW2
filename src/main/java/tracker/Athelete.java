package tracker;

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
    private String LastName;
    private String FirstName;
    private SEX sex;

    // You set the bibNumber when you get the simulator data and also set the status
    public Athelete(Status status, int bibNumber, int time, String FirstName, String LastName, SEX sex, int Age) {
        this.bibNumber = bibNumber;
        this.status = status;
        this.time = time;
        this.age = age;
        this.sex = sex;
        this.FirstName = FirstName;
        this.LastName = LastName;
        System.out.println("Player registered : " + this.FirstName + " " + this.LastName);
    }

    // Get time passed for athelete
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
}
