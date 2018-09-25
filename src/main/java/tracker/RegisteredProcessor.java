package tracker;

import java.net.InetAddress;

public class RegisteredProcessor implements IMessageProcessor {

    public void process(String message, InetAddress address, int port) throws Exception {
        // Making sense of the message received and registering the athelete
        String[] messages = message.split(",", 0);
        String status = messages[0];
        String bibNumber = messages[1];
        int timeElapsed = Integer.valueOf(messages[2]);

        // Instanstiate an athelete object
        Athelete a = new Athelete(status, Integer.valueOf(bibNumber), timeElapsed);
        System.out.println(a);

//        // Map is required to retrieve athelete object based on string id
//        nameAtheleteMap.put(bibNumber, a);
//        Atheletes.add(a);

//        Client c1 = new Client();
//        c1.sendSubscribe(1);
//        System.out.println(c1);
//        System.out.println(Client.portAddressClientMap);
    }
}
