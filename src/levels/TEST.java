package levels;

import java.util.Scanner;

public class TEST {

	
	public static void main(String[] args) throws Exception {
		
		
		Level lvl = null;
		lvl = new LoadLevel().Action("level1.txt");
		TextLevelDisplay displayer = new TextLevelDisplay();
		displayer.Display(lvl);
		String s = new String();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		MySokobanPolicy policy =  new MySokobanPolicy();
		while(true)
		{
			s = scan.nextLine();
			switch (s)
			{
			case "4":
				new Move1Step().Action(lvl, policy, Direction.left);
				break;
			case "8":
				new Move1Step().Action(lvl, policy, Direction.up);
				break;
			case "6":
				new Move1Step().Action(lvl, policy, Direction.right);
				break;
			case "5":
				new Move1Step().Action(lvl, policy, Direction.down);
				break;
			default: continue;
			}
			displayer.Display(lvl);
		}		


	}

}


