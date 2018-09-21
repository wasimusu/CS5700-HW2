package tracker;

// A client has
// Status of a athelete : registered, started, on-course, dropped, completed
public class Athelete {
    private int bibNumber;
    private double distanceCovered;
    private double time;

    // You set the bibNumber when you get the simulator data and also set the status
    public Athelete(int bibNumber) {
        this.bibNumber = bibNumber;
//        this.status =
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
