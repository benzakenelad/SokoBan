package model.data;

import model.data.gameObjects.Box;
import model.data.gameObjects.Character;
import model.data.gameObjects.GameObject;
import model.data.gameObjects.Target;
import model.data.gameObjects.Wall;

public class GernerateGameObject {
	
	public GameObject GenerateObject(char c) throws Exception // Generate a new game object from char
	{
		switch(c){
		case '#':   // case its wall
			return new Wall();
		case '@':   // case its box on empty slot
			return new Box();
		case 'A':   // case its player on empty slot
			return new Character();
		case 'o':   // case its empty target
			return new Target();	
		case ' ':
			return null;
		default: return null;
		}	
	}
}
