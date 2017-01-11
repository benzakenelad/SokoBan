package CLI;

import java.util.Scanner;

import commands.DisplayCommand;
import commands.ExitCommand;
import commands.LoadLevelCommand;
import commands.MoveCommand;
import commands.SaveLevelCommand;
import levels.TextLevelDisplay;
import model.data.Level;

public class CLI {


	public void StartSokoban() // Sokoban Game Command Line Interface
	{
		Level lvl = null;
		String choice = new String("");
		String note = new String("");
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		
		System.out.println("Type 'menu' To see the menu");
		
		while(true) // menu
		{
			choice = s.next();
			switch(choice)
			{
			case "load":
				note = s.next();
				LoadLevelCommand LLC = new LoadLevelCommand();
				LLC.execute(lvl, note);
				lvl = LLC.getLvl();
				break;
			case "save":
				if(lvl == null)
				{
					System.out.println("\n" + "Error. There is no loaded level" + "\n");
					break;
				}
				note = s.next();
				SaveLevelCommand SLC = new SaveLevelCommand();
				SLC.execute(lvl, note);
				break;
			case "move":
				if(lvl == null)
				{
					System.out.println("\n" + "Error. There is no loaded level" + "\n");
					break;
				}
				note = s.next();
				MoveCommand MC = new MoveCommand();
				MC.execute(lvl, note);
				break;
			case "display":
				if(lvl == null)
				{
					System.out.println("\n" + "Error. There is no loaded level" + "\n");
					break;
				}
				note = "";
				DisplayCommand DC = new DisplayCommand();
				DC.execute(lvl, note);
				break;
			case "exit":
				note = "";
				ExitCommand EC = new ExitCommand();
				EC.execute(lvl, note);
				break;
			case "menu":
				note = "";
				System.out.println("Type 'menu' To see the menu.");
				System.out.println("Type 'load FileName'(include the suffix) To load a level.");
				System.out.println("Type 'save FileName'(include the suffix) To save a level.");
				System.out.println("Type 'move left/up/right/down' to move your character.");
				System.out.println("Type 'display' To display the level.");
				System.out.println("Type 'exit' To exit the game.");
				break;
			default: break;
			}
			
			s.nextLine();
			if(lvl == null)
				continue;
			if(lvl.levelCompletionCheck() == true)
			{
				new TextLevelDisplay().Display(lvl);
				System.out.println("Congratulations, Level Completed");
				lvl = null;
			}
		}
		
		
	}

}
