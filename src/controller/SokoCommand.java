package controller;


import model.Model;
import view.View;
	
public abstract class SokoCommand implements Command{
	protected String order;
	protected Model model = null;
	protected View view = null;
	
	
    public void setOrder(String order)
    {
    	this.order = new String(order);
    }

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public String getOrder() {
		return order;
	}
}
