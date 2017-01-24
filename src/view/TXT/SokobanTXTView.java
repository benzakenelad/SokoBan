package view.TXT;

import java.util.Observable;

import model.data.Level;
import view.GUI.View;

public class SokobanTXTView extends Observable implements View {

	@Override
	public void Display(Level lvl) // display level by TXT
	{
		if(lvl == null)
		{
			System.out.println("There is no level loaded.");
			return;
		}
		new TXTLevelDisplay().Display(lvl);
	}

	@Override
	public void safeExit(int value) // the view will close all his responsibilities
	{

	}
	
	
}
