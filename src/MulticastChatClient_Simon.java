import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastChatClient_Simon {
    public static void main(String[] args)
        throws Exception {
        // Default port number
        int portnumber = 8080;
        if(args.length >= 1)
        {
            portnumber = Integer.parseInt(args[0]);
        }

        // Create a MulticastSocket
        MulticastSocket chatMulticastSocket = new MulticastSocket(portnumber);

        // Determine the IP address of a host, given the host name
        // The address range for multicast on a local subnetwork is 244.0.0.0 to 244.0.0.255
        InetAddress group = InetAddress.getByName("224.0.0.1");

        // Joins a multicast group
        chatMulticastSocket.joinGroup(group);

        // Prompt a user to enter a message
        String message = "";
        System.out.println("Type a message for the server");
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));
        message = bufferedReader.readLine();

        // Send the message to Multicast address
        DatagramPacket data =  new DatagramPacket(message.getBytes(), 0,
                message.length(), group, portnumber);
        chatMulticastSocket.send(data);

        // Close the socket
        chatMulticastSocket.close();
    }
}