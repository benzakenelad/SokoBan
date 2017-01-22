package controller.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class SokobanClientHandler extends ClientHandler{
	
	@Override
	public void HandleClient(InputStream inFromClient, OutputStream outToClient) throws Exception // handle a client by a well defined protocal
	{

		// Initialize streams
		InputStreamReader InFromClient = new InputStreamReader(inFromClient);	
		BufferedReader fromClient = new BufferedReader(InFromClient);
		PrintWriter toClient = new PrintWriter(outToClient, true);
		
		// Data Declaration
		String inputLine = null;
		String outputLine = new String("");	
		boolean stopPlay = false;
		
		// Sending terms to the client
		toClient.println("Enter 'menu' for menu display.");
		toClient.println("Enter 'stop' for stop playing.");
		toClient.println("Enter 'exit' to close the server.");
		
		// Protocol
		while(stopPlay != true)
		{
			outputLine = "Enter the next command please.";
			toClient.println(outputLine);
			inputLine = fromClient.readLine();
			if(inputLine.compareTo("stop") == 0 || inputLine.compareTo("exit") == 0)
			{	
				stopPlay = true;
				continue;
			}
			server.notifyObs(inputLine);
		}
		
		InFromClient.close();
		fromClient.close();
		toClient.close();

		if(inputLine.compareTo("exit") == 0)
			server.notifyObs(inputLine);
		
		try {Thread.sleep(10);} catch (InterruptedException e) {}
	}
}
