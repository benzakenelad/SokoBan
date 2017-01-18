package controller;

import java.util.Observer;

import model.Model;
import view.View;

public interface Controller extends Observer{
	public void insertCommand(Command command);
	public void start();
	public void stop();
	public void exit();
	public Model getModel();
	public View getView();
}
