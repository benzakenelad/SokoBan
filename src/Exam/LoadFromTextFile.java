package Exam;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoadFromTextFile {

	
	
	
	// load text file to ArrayList of strings
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
	
	
	
	
	// load text file to char[][]
	public char[][] loadFromTextFileTo2DCharArray(String fileName) throws Exception
	{
		ArrayList<String> arrayListData = loadFromTextFileToArrayListOfStrings(fileName);
			
		char[][] data = new char[arrayListData.size()][];	
		
		for(int i = 0; i < arrayListData.size(); i++)
			data[i] = new char[arrayListData.get(i).length()];
		
		for(int i = 0; i < arrayListData.size(); i++)
			for(int j = 0; j < arrayListData.get(i).length(); j++)
				data[i][j] = arrayListData.get(i).charAt(j);
			
		return data;
	}
	
	
	
	// print ArrayList of strings
	public void PrintArrayListOfString(ArrayList<String> data)
	{
		if(data != null)
			for(int i = 0; i < data.size(); i++)
				System.out.println(data.get(i));
	}
	
	
	
	
	// print char[][]
	public void Print2DCharArray(char[][] data)
	{
		String str = new String();
		
		if(data != null)
			for(int i = 0; i < data.length; i++)
			{
				str = "";
				for(int j = 0; j < data[i].length; j++)
					str += data[i][j];
				System.out.println(str);
			}
	}
	
}

