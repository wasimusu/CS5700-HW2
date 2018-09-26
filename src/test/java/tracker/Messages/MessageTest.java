package tracker.Messages;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class MessageTest {
    @Test
    public void testValidConstuction() throws Exception {
        String sentMessage = "wasim";
        InetAddress sentAddress = InetAddress.getLocalHost();
        int sentPort = 34;
    }

    @Test
    public void testCreateMessage() throws Exception {
        String raceStarted = "Race,Bension Loop,16090";
        String registeredMessage = "Registered,1,0,Valentine,Zamora,M,30";
        String startedMessage = "Started,1,150";
        String onCourseMessage = "OnCourse,1,180,260.143547994891";
        String finishMessage = "Finished,9,2667";
        InetAddress sentAddress = InetAddress.getLocalHost();
        int sentPort = 34;
        Message raceS = Message.messageObject(raceStarted, sentAddress, sentPort);
        Message registered = Message.messageObject(registeredMessage, sentAddress, sentPort);
        Message started = Message.messageObject(startedMessage, sentAddress, sentPort);
        Message oncourse = Message.messageObject(onCourseMessage, sentAddress, sentPort);
        Message finish = Message.messageObject(finishMessage, sentAddress, sentPort);
        assert raceS instanceof RaceStartedProcessor;
        assert registered instanceof NewAtheleteProcessor;
        assert started instanceof AthleteStartedProcessor;
        assert oncourse instanceof OnCourseProcessor;
        assert finish instanceof AthleteFinishedProcessor;
    }
}