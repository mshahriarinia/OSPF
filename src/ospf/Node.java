package ospf;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * starts a UDP connection OSPF. <br>
 * node strats working once it receive an OK from its neighbors. Once a node is
 * all set (receives -1 -1) it broadcasts its existence and its link state to
 * all neighbors (if they are on already) . once a node hears availability of a
 * new node it sends its info to that node. <br>
 * <br>
 * need two programs, udp sender, udp receiver. What if while it is in the
 * process of sending something it receives something and there is noone to
 * receive it?
 * 
 * @author Morteza
 * 
 */
public class Node {

	int port;

	LinkState[] linkStateArr;

	public Node(int port) {

		this.port = port;
	}

	public void run() {

		try {
			DatagramSocket socket = new DatagramSocket();

			byte[] buf = new byte[256];
			InetAddress address = InetAddress.getByName("localhost");

			// send request
			int destPort = 0;
			DatagramPacket packet = new DatagramPacket(buf, buf.length,
					address, destPort);

			socket.send(packet);

			//
			//
			// get response
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);

			// display response
			String received = new String(packet.getData(), 0,
					packet.getLength());
			System.out.println("Quote of the Moment: " + received);

			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
