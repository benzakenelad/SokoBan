package serverANDclientASYNCchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ClientHandler CH;
	
	public Server(ClientHandler CH) {
		this.CH = CH;
	}

	void Start(int port, String exitStr) {
		try {
			ServerSocket serversock = new ServerSocket(port);
			Socket aClient = serversock.accept();
			System.out.println("Client is connected to server");

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(aClient.getInputStream()));
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			
			PrintWriter outToClient = new PrintWriter(aClient.getOutputStream(), true);
			PrintWriter outToScreen = new PrintWriter(System.out, true);
			
			Thread t1 = CH.HandleClient(inFromClient, outToScreen, exitStr);
			Thread t2 = CH.HandleClient(inFromUser, outToClient, exitStr);

			t1.join();
			t2.join();
			
			System.out.println("Closing Server");

			inFromClient.close();
			outToClient.close();
			aClient.close();
			serversock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
