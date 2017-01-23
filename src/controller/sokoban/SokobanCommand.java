package controller.sokoban;

import controller.general.Command;
import model.Model;
import view.GUI.View;

public abstract class SokobanCommand implements Command{
	protected String order = null;
	protected Model model = null;
	protected View view = null;
	
	
	
	
	public void setModel(Model model) {
		this.model = model;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
	
}
