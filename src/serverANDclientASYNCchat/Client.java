package serverANDclientASYNCchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private void Chat(BufferedReader inStream, PrintWriter outStream, String exitStr) throws IOException {
		String line;

		while (!((line = inStream.readLine()).equals(exitStr))) {
			outStream.println(line);
		}
		outStream.println("exit");
	}

	private Thread aSyncChat(BufferedReader inStream, PrintWriter outStream, String exitStr) {
		Thread thread;
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				
				try {
					Chat(inStream, outStream, exitStr);
				} catch (IOException e) {e.printStackTrace();}
				
			}
		});
		thread.start();
		return thread;
	}

	void ConnectServer(String ip, int port, String exitStr) {
		Socket theServer = null;
		try {
			theServer = new Socket(ip, port);
			System.out.println("connection is established");

			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

			PrintWriter outToServer = new PrintWriter(theServer.getOutputStream(), true);
			PrintWriter outToScreen = new PrintWriter(System.out, true);
			
			Thread t2 = aSyncChat(inFromServer, outToScreen, exitStr);
			Thread t1 = aSyncChat(inFromUser, outToServer, exitStr);
			
			t1.join();
			t2.join();
			
			System.out.println("Client Closing");
			
			inFromServer.close();
			outToServer.close();
			inFromUser.close();
			outToScreen.close();
			theServer.close();

		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
