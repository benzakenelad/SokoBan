package EampleExamEladSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyCompressorClient implements CompressorClient {

	@Override
	public String compress(String uncompressed) {
		String[] uncomptemp = uncompressed.split("\n");
		String compressed = new String("");
		
		for(int i = 0; i < uncomptemp.length; i++)
		{
			compressed += compress1line(uncomptemp[i]);
			compressed += "\n";
		}
		return compressed;
	}

	@Override
	public void send(String fileName, String ip, int port) {
			try {
			Socket theServer = new Socket(ip, port); // connection to server
			System.out.println("connected to server");
			@SuppressWarnings({ "resource", "unused" })
			Scanner s = new Scanner(System.in); // s can read from command line
			
			InputStream serverInputStream = theServer.getInputStream();
			OutputStream serverOutputStream = theServer.getOutputStream();
			InputStreamReader serverInputStreamReader = new InputStreamReader(serverInputStream);
			
			BufferedReader inFromServer = new BufferedReader(serverInputStreamReader);  // in from the server 
			PrintWriter outToServer = new PrintWriter(serverOutputStream, true); // out to the server

			String uncompressed = loadFromFile(fileName);
			String compressed = compress(uncompressed);
			String msg = null;
			
			// PROTOCOL
			while(true)
			{
				outToServer.println(compressed);
				outToServer.println("end\n");
			
				msg = inFromServer.readLine();
				if(msg.compareTo("ok") == 0)
					break;
			}
			
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
	
	public ArrayList<String> loadFromTextFileToArrayListOfStrings(String fileName) throws Exception
	{
		ArrayList<String> data = new ArrayList<String>();
		String string = new String("");
	
		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	    
		while(string != null)
		{
			try {  string = input.readLine();         } catch (IOException e) {}
			if(string != null)
				data.add(string);
		}
		
		input.close();
	
		return data;
	}
	
	public String loadFromFile(String fileName)
	{
		ArrayList<String> data = null;
		try {
			data = loadFromTextFileToArrayListOfStrings(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		int len = data.size();
		String str = new String("");
		
		for(int i = 0; i < len; i++)
		{
			str += data.get(i);
			str += "\n";
		}
		return str;
	}

	public String compress1line(String uncompressed)
	{
		if(uncompressed == null)
			return null;
		
		String compressed = new String("");
		int counter = 1;
		int uncompleng = uncompressed.length();
		
		if(uncompleng == 0)
			return compressed;
		
		if(uncompleng == 1)
		{
			compressed += uncompressed.charAt(0);
			compressed += "1";
			return compressed;
		}
		
		
		for(int i = 0; i < uncompleng; i++)
		{
			if(i == uncompleng - 1)
			{
				if(uncompressed.charAt(i-1) == uncompressed.charAt(i))
				{
					compressed += uncompressed.charAt(i);
					compressed += Integer.toString(counter);
					break;
				}
				else
				{
					compressed += uncompressed.charAt(i);
					compressed += "1";
					break;
				}
			}
			
			if(uncompressed.charAt(i) == uncompressed.charAt(i + 1))
				counter++;
			else
			{
				compressed += uncompressed.charAt(i);
				compressed += Integer.toString(counter);
				counter = 1;
			}
		}
		
		return compressed;
	}
	
}
