package view;

import java.util.Observable;

import model.data.Level;

public class SokobanView extends Observable implements View {

	@Override
	public void Display(Level lvl) {
		if(lvl == null)
		{
			System.out.println("There is no level loaded.");
			return;
		}
		new TextLevelDisplay().Display(lvl);
	}
	
}
