package controller.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class SokobanServer extends Observable implements Server {
	private volatile boolean stop = false;
	
	@Override
	public void startServer(int port) throws Exception {
		ServerSocket server = new ServerSocket(port);
		
		new Thread(new Runnable()  // server and client communication thread
		{	
			@Override
			public void run() {
				while(stop != true)
				{			
					Socket aClient = null;
					try 
					{
						System.out.println("The server is alive and waiting for connection.");
						aClient = server.accept(); // blocking call
						System.out.println("A client is connected to server on port :: " + port);
						
						// Initialize streams
						InputStream inFromClient = aClient.getInputStream();
						OutputStream outToClient = aClient.getOutputStream();	
						
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
							setChanged();
							notifyObservers(inputLine);
						}
								
						fromClient.close();
						toClient.close();
						inFromClient.close();
						outToClient.close();
						aClient.close();
						if(inputLine.compareTo("exit") == 0)
						{
							setChanged();
							notifyObservers(inputLine);
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
					} catch (IOException e) 
					{
						e.printStackTrace();
					} 	
					
				}
				try 
				{
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void stopServer() {
		stop = true;		
	}

}
