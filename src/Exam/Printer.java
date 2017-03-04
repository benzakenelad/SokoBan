package Exam;

import java.util.ArrayList;

public class Printer {
	
	// print Strings[]
	public void PrintStringsArray(String[] data)
	{
		int len = data.length;
		for(int i = 0; i < len; i++)
			System.out.println(data[i]);
	}
	
	// print ArrayList<String>
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
