package serverANDclientASYNCchat;

import java.io.BufferedReader;
import java.io.PrintWriter;

public interface ClientHandler {
	public Thread HandleClient(BufferedReader inStream, PrintWriter outStream, String exitStr);
}
