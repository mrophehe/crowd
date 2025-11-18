import java.net.*;

class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9876);
        byte[] buf = new byte[1024];

        while (true) {
            DatagramPacket p = new DatagramPacket(buf, buf.length);
            socket.receive(p);

            String msg = new String(p.getData(), 0, p.getLength());
            String reply = msg.toUpperCase();

            DatagramPacket out =
                new DatagramPacket(reply.getBytes(), reply.length(),
                                   p.getAddress(), p.getPort());

            socket.send(out);
        }
    }
}
