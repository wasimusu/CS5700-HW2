package tracker;

import tracker.Messages.MessageProcessor;

public class RaceTracker {

    public void RaceTracker() throws Exception{
    }

    public static void main(String[] args) throws Exception {
        Communicator serverComm = new Communicator(12000);
        MessageProcessor mp = new MessageProcessor("trackerProcessor");
        serverComm.setProcessor(mp);
        serverComm.start();
        serverComm.run();
        System.out.println("RaceTracker finished");
    }
}
