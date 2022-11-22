// RMIDemoInterface

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RMIDemoInterface extends Remote{
	public String sayHello() throws RemoteException;
	public int add(int a, int b) throws RemoteException;
	public int subtract(int a, int b) throws RemoteException;
	public int multiply(int a, int b) throws RemoteException;
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;



  // RMIDemoServer

import java.net.MalformedURLException;
import java.rmi.Naming;
//import java.rmi.*;
 import java.rmi.RemoteException;
 import java.rmi.registry.LocateRegistry;
 import java.rmi.server.UnicastRemoteObject;

class RMIDemoImpl extends UnicastRemoteObject implements RMIDemoInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected RMIDemoImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String sayHello() throws RemoteException {
		// TODO Auto-generated method stub
		return "Hello Client! Welcome:";
	}

	@Override
	public int add(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return a+b;
	}

	@Override
	public int subtract(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return a-b;
	}

	@Override
	public int multiply(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return a*b;
	}
	
}
public class RMIDemoServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			RMIDemoInterface rmiDemoObject = new RMIDemoImpl();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmiDemoObject",rmiDemoObject);
			System.out.println("Server Ready");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

  // RMIDemoClient

public class RMIDemoClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url= "rmi://localhost:1099/rmiDemoObject";
		
		Scanner in = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			RMIDemoInterface remoteIntf = (RMIDemoInterface) Naming.lookup(url);
			System.out.println(remoteIntf.sayHello());
			System.out.println("Enter two numbers:");
			System.out.print("a: ");
			//int a = Integer.parseInt(br.readLine());
			int a = in.nextInt();
			System.out.print("b: ");
			int b = Integer.parseInt(br.readLine());
			int sum = remoteIntf.add(a, b);
			int deference = remoteIntf.subtract(a, b); 
			int product = remoteIntf.multiply(a, b);
			
			System.out.println("The sum is : "+sum);
			System.out.println("The deference is : "+deference);
			System.out.println("The product is : "+product);
			
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
