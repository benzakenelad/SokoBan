package Exam;

import java.util.ArrayList;

public class Convertor {
	
	
	// convert char[][] to ArrayList<String>
	public ArrayList<String> convert_Char2DArray_To_ArrayListOfStrings(char[][] level) {
		ArrayList<String> ALlevel = new ArrayList<String>();
		int len = level.length;

		for (int i = 0; i < len; i++)
			ALlevel.add(new String(level[i]));

		return ALlevel;
	}

	// convert ArrayList<String> to Char[][]
	public char[][] convert_ArrayListOfStrings_To_2DCharArray(ArrayList<String> ALlevel) {
		char[][] level = new char[ALlevel.size()][];

		int len = ALlevel.size();

		for (int i = 0; i < len; i++)
			level[i] = ALlevel.get(i).toCharArray();

		return level;
	}

	// convert from ArrayList<String> to String[]
	public String[] convert_ArrayListOfStrings_To_StringsArray(ArrayList<String> ALlevel)
	{
		String[] level = new String[ALlevel.size()];
		int len = ALlevel.size();
		for(int i = 0 ; i < len ; i++)
			level[i] = new String(ALlevel.get(i));
				
		return level;
	}
	
	// convert from String[] to char[][]
	public char[][] convert_StringsArray_To_2DCharArray(String[] data)
	{
		ArrayList<String> ALdata = new ArrayList<String>();
		int len = data.length;
		for(int i = 0; i < len ; i++)
			ALdata.add(data[i]);
		
		char[][] newData = this.convert_ArrayListOfStrings_To_2DCharArray(ALdata);
		
		return newData;
	}
	
}
