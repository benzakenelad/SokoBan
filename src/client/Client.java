package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public void start(String ip, int port)
	{
		try {
		Socket theServer=new Socket(ip, port); // connection to server
		System.out.println("connected to server");
		@SuppressWarnings({ "resource", "unused" })
		Scanner s = new Scanner(System.in); // s can read from command line
		
		InputStream serverInputStream = theServer.getInputStream();
		OutputStream serverOutputStream = theServer.getOutputStream();
		InputStreamReader serverInputStreamReader = new InputStreamReader(serverInputStream);
		
		BufferedReader inFromServer = new BufferedReader(serverInputStreamReader);  // in from the server 
		PrintWriter outToServer = new PrintWriter(serverOutputStream, true); // out to the server

		
		/*      WELL DEFINED PROTOCOL
		String inputS = null;
		String outputS = null;
		boolean stop = false;
		
		System.out.println(inFromServer.readLine());
		System.out.println(inFromServer.readLine());
		System.out.println(inFromServer.readLine());
		while(stop != true)
		{
			inputS = inFromServer.readLine();
			if(inputS != null)
				System.out.println(inputS);
			
			outputS = s.nextLine();
			if(outputS.compareTo("stop") == 0 || outputS.compareTo("exit") == 0)
			{
				outToServer.println(outputS);
				stop = true;
				continue;
			}
			if(outputS != null)
				outToServer.println(outputS);
		}
		*/
		
		
		// Streams close
		serverInputStream.close();
		serverOutputStream.close();
		serverInputStreamReader.close();
		inFromServer.close();
		outToServer.close();
		theServer.close();
		} catch (UnknownHostException e) {}
		catch (IOException e) {}
	}
}
