//Tcpserver.java:

import java.io.*;
import java.net.*;

public class TcpServer {
	public static void main(String[] args) throws Exception	{
		ServerSocket ss=new ServerSocket(8088);
		System.out.println("server is ready!");
		Socket ls=ss.accept();
		while (true){
			System.out.println("Client Port is "+ls.getPort());
			//READING DATA FROM CLIENT
			InputStream is=ls.getInputStream();
			byte data[]=new byte[50];
			is.read(data);
			String mfc=new String(data);
			//mfc: message from client
			mfc=mfc.trim();
			String mfs="Hello:"+mfc;
			//mfs: message from server
			//SENDING MSG TO CLIENT
			OutputStream os=ls.getOutputStream();
			os.write(mfs.getBytes());
		}
	}
}

//Tcpclient.java:

import java.net.*;
import java.io.*;

class TcpClient {
	public static void main(String[] args) throws Exception	{
		System.out.println("connecting to server");
		Socket cs=new Socket("localhost",8088);
		
		BufferedReader br=new BufferedReader(new InputStreamReader( System.in));

		System.out.println("The Local Port "+cs.getLocalPort()+"\nThe Remote Port"+cs.getPort());
		System.out.println("The Local socket is "+cs);
		System.out.println("Enter your name");
		String str=br.readLine();
		//SENDING DATA TO SERVER
		OutputStream os=cs.getOutputStream();
		os.write(str.getBytes());
		//READING DATA FROM SERVER
		InputStream is=cs.getInputStream();
		byte data[]=new byte[50];
		is.read(data);
		//PRINTING MESSAGE ON CLIENT CONSOLE
		String mfs=new String(data);
		mfs=mfs.trim();
		System.out.println(mfs);
	}
}

//DATE TIME SERVER

//DateClient.java

import java.io.*;
import java.net.*;
class DateClient
{
    public static void main(String args[]) throws Exception
    {
        Socket soc =new Socket(InetAddress.getLocalHost(),5217);        
        BufferedReader in=new BufferedReader(new InputStreamReader(soc.getInputStream()  ));
        System.out.println(in.readLine());
    }    
}

//DateServer.java

import java.net.*;
import java.io.*;
import java.util.*;
class DateServer
{
    public static void main(String args[]) throws Exception
    {
        ServerSocket s=new ServerSocket(5217);
        while(true)
        {
            System.out.println("Waiting For Connection ...");
            Socket soc=s.accept();
            DataOutputStream out=new DataOutputStream(soc.getOutputStream());
            out.writeBytes("Server Date: " + (new Date()).toString() + "\n");
            out.close();
            soc.close();
        }
    }
} 
