package tracker.Messages;

import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.*;

public class MessageTest {
    @Test
    public void testValidConstuction() throws Exception {
        String sentMessage = "wasim";
        InetAddress sentAddress = InetAddress.getLocalHost();
    }

    @Test
    public void testCreateMessage() throws Exception {
        InetAddress sentAddress = InetAddress.getLocalHost();
        int sentPort = 34;

        String helloMessage = "Hello";
        String subscribeMessage = "Subscribe,1";
        String unsubscribeMessage = "Unsubscribe,1";

        Message hello = Message.messageObject(helloMessage, sentAddress, sentPort);
        Message subscribe = Message.messageObject(subscribeMessage, sentAddress, sentPort);
        Message unsubscribe = Message.messageObject(unsubscribeMessage, sentAddress, sentPort);

        assert hello instanceof HelloProcessor;
        assert unsubscribe instanceof UnsubscribeProcessor;
        assert subscribe instanceof SubscribeProcessor;

        String raceStarted = "Race,Bension Loop,16090";
        String registeredMessage = "Registered,1,0,Valentine,Zamora,M,30";
        String startedMessage = "Started,1,150";
        String onCourseMessage = "OnCourse,1,180,260.143547994891";
        String finishMessage = "Finished,9,2667";

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