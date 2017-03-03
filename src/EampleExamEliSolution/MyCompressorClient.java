package EampleExamEliSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyCompressorClient implements CompressorClient{

	@Override
	public String compress(String uncompressed) {
		StringBuilder s=new StringBuilder();
		
		char[] chars=uncompressed.toCharArray();
		int i=0;
		while(i<chars.length){
			s.append(chars[i]);
			int count=1;
			while(i<chars.length-1 && chars[i+1]==chars[i]){
				count++;
				i++;
			}
			s.append(count);
			i++;
		}
		return s.toString();
	}

	@Override
	public void send(String fileName, String ip, int port) {
		try{
			Socket theServer=new Socket(ip, port);
			PrintWriter out=new PrintWriter(theServer.getOutputStream());
			BufferedReader infromServer=new BufferedReader(new InputStreamReader(theServer.getInputStream()));
			BufferedReader in=new BufferedReader(new FileReader(fileName));
			String line;
			while((line=in.readLine())!=null){
				out.println(compress(line));
				out.flush();
			}
			out.println("end");
			out.flush();
			infromServer.readLine(); // ok;
			
			out.close();
			infromServer.close();
			in.close();
			theServer.close();			
		}catch (IOException e){
			e.printStackTrace();
		}		
	}

}
