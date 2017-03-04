package serverANDclientASYNCchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class aSyncChatClientHandler implements ClientHandler {

	private void Chat(BufferedReader inStream, PrintWriter outStream, String exitStr) throws IOException {
		String line;

		while (!((line = inStream.readLine()).equals(exitStr))) {
			outStream.println(line);
		}
		outStream.println("exit");
	}
	
	@Override
	public Thread HandleClient(BufferedReader inStream, PrintWriter outStream, String exitStr) {
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

}
