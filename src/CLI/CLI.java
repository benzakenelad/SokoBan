package CLI;

import java.util.Scanner;

import controller.Command;
import controller.CreateACommand;
import controller.LoadLevelCommand;
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
		CreateACommand commandCreator = new CreateACommand();
		
		
		while(true) // menu
		{
			choice = s.next();
			choice.toLowerCase();
			Command command = commandCreator.Action(choice);
			if(command != null)
			{
				if(choice.compareTo("load") == 0 || choice.compareTo("save") == 0 || choice.compareTo("move") == 0)
				{					
					note = s.next();
					note.toLowerCase();
					command.setOrder(note);		
				}
				if(choice.compareTo("load") == 0)
				{
					command.execute();
					LoadLevelCommand llc = (LoadLevelCommand)command;
					lvl = llc.getLvl();
				}
				else
				{
					command.setLevel(lvl);
					command.execute();
				}
			}


			
			s.nextLine(); // clear the buffer
			
			if(lvl == null)
				continue;
			
			if(lvl.levelCompletionCheck() == true) // checks if the level completed
			{
				new TextLevelDisplay().Display(lvl);
				System.out.println("Congratulations, Level Completed");
				lvl = null;
			}
		}
		
		
	}

}
