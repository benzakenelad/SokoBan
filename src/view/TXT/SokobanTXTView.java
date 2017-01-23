package view.TXT;

import java.util.Observable;

import model.data.Level;
import view.GUI.View;

public class SokobanTXTView extends Observable implements View {

	@Override
	public void Display(Level lvl) {
		if(lvl == null)
		{
			System.out.println("There is no level loaded.");
			return;
		}
		new TXTLevelDisplay().Display(lvl);
	}
	
}
