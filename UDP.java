// UDPClient.java

import java.net.*;
import java.io.*;

class UDPClient{
	public static void main(String[] args) throws Exception	{
		byte[] buff=new byte[1024];
		DatagramSocket ds = new DatagramSocket(8089);
		DatagramPacket p=new DatagramPacket(buff,buff.length);		

		BufferedReader br=new BufferedReader(new InputStreamReader(
			System.in));
		System.out.print("Enter your name:");
		String msg = br.readLine();
		buff = msg.getBytes();
		ds.send(new DatagramPacket(buff,buff.length, InetAddress.getLocalHost(),8088));
		ds.receive(p);
		msg = new String( p.getData(),0,p.getLength()).trim();
		System.out.println("Msg received "+msg);

	}
}

// UDPServer.java

import java.net.*;
class UDPServer{
	public static void 	main(String[] args) throws Exception{
		byte buff[]=new byte[1024];
		DatagramSocket ds =new DatagramSocket(8088);
		DatagramPacket p=new DatagramPacket(buff,buff.length);

		System.out.println("Server ready :");
		
		ds.receive(p);
		//receiving and printing
		//String msg = new String( p.getData(),0,p.getLength()).trim();
		System.out.println("Msg received "+ new String(buff));
		
		// sending to client a new message
		String str = "Hello "+new String(buff);
		buff = str.getBytes();
		ds.send(new DatagramPacket(buff,buff.length,InetAddress.getLocalHost(),8089));
		
	}
}
