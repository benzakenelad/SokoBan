package searchLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import searchLib.mazeExample.MazeLevel;
import searchLib.mazeExample.Position;
import searchLib.mazeExample.SearchableMaze;
import searchLib.mazeExample.levelTXTLoad;
import searchLib.searchers.Dijkstra;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		levelTXTLoad loader = new levelTXTLoad();
		MazeLevel level = loader.load(new FileInputStream("test2.txt"));
	
		level.print();
		
		Searcher<Position> searcher = new Dijkstra<Position>();

		SearchableMaze searchableMaze = new SearchableMaze(level);

		Solution sol = searcher.search(searchableMaze);

		System.out.println(sol);
	}
}
