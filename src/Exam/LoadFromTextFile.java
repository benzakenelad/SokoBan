package Exam;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoadFromTextFile {

	// load text file to ArrayList of strings
	public ArrayList<String> loadFrom_TextFile_To_ArrayListOfStrings(String fileName) {
		ArrayList<String> data = new ArrayList<String>();
		String string = new String("");

		BufferedReader input = null;

		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

			while (string != null) {
				string = input.readLine();
				if (string != null)
					data.add(string);
			}

			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	// load text file to char[][]
	public char[][] loadFrom_TextFile_To_2DCharArray(String fileName) {
		ArrayList<String> arrayListData = loadFrom_TextFile_To_ArrayListOfStrings(fileName);

		char[][] data = new char[arrayListData.size()][];

		for (int i = 0; i < arrayListData.size(); i++)
			data[i] = new char[arrayListData.get(i).length()];

		for (int i = 0; i < arrayListData.size(); i++)
			for (int j = 0; j < arrayListData.get(i).length(); j++)
				data[i][j] = arrayListData.get(i).charAt(j);

		return data;
	}

	// load test file to String[]
	public String[] loadFrom_TextFile_To_StringsArray(String fileName) {
		ArrayList<String> ALstrings = loadFrom_TextFile_To_ArrayListOfStrings(fileName);
		
		int len = ALstrings.size();
		
		String[] data = new String[len];
		
		for(int i = 0 ; i < len; i++)
			data[i] = ALstrings.get(i);
		
		return data;

	}

}
