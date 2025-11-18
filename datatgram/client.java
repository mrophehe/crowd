import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");

        System.out.print("Enter text: ");
        String msg = new BufferedReader(
                new InputStreamReader(System.in)).readLine();

        DatagramPacket p =
            new DatagramPacket(msg.getBytes(), msg.length(), ip, 9876);

        socket.send(p);

        byte[] buf = new byte[1024];
        DatagramPacket reply = new DatagramPacket(buf, buf.length);
        socket.receive(reply);

        System.out.println("FROM SERVER: " +
                new String(reply.getData(), 0, reply.getLength()));

        socket.close();
    }
}
