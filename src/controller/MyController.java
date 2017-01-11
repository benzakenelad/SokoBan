package controller;

import java.util.Observable;

import javax.swing.text.View;

import model.Model;


public class MyController implements Controller {
	Model model = null;
	View view = null;
	
	public MyController(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
