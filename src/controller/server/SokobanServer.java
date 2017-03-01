package controller.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SokobanServer implements Server {
	
	//Data members
	private volatile boolean stop = false;
	private int port = 0;
	private ClientHandler ch = null;
	private Thread serverThread;
	
	//C'tor
	public SokobanServer(int port, ClientHandler ch) {
		this.port = port;
		this.ch = ch;
	}
	
	@Override
	public void startServer() throws Exception {
		if(ch == null)
			throw new Exception("null client handler");	
		
		
		serverThread = new Thread(new Runnable()  // server and client communication thread
		{	
			@Override
			public void run()
			{
				try 
				{
					runServer();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		serverThread.start();
	}

	private void runServer() throws Exception
	{
		ServerSocket server = new ServerSocket(port); // The server socket
		System.out.println("The server is alive.");
		
		while(stop != true)
		{			
			Socket aClient = null;
			try 
			{
				System.out.println("Waiting for connection...");
				aClient = server.accept(); // blocking call
				System.out.println("A client is connected to server on port :: " + port);
				
				InputStream inFromClient = aClient.getInputStream();
				OutputStream outToClient = aClient.getOutputStream();
				
				ch.HandleClient(inFromClient, outToClient);
				
				inFromClient.close();
				outToClient.close();
				
			} catch (Exception e) 
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
	
	@SuppressWarnings("deprecation")
	@Override
	public void stopServer() {
		stop = true;
		serverThread.stop();
	}

}
