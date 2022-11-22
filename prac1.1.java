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
