package model.data;

import model.data.gameObjects.Box;
import model.data.gameObjects.Character;
import model.data.gameObjects.GameObject;
import model.data.gameObjects.Position;
import model.data.gameObjects.Target;
import model.data.gameObjects.Wall;

public class GernerateGameObject {
	
	public GameObject GenerateObject(char c, Position pos) throws Exception // Generate a new game object from char
	{
		switch(c){
		case '#':   // case its wall
			return new Wall(pos);
		case '@':   // case its box on empty slot
			return new Box(pos);
		case 'A':   // case its player on empty slot
			return new Character(pos);
		case 'o':   // case its empty target
			return new Target(pos);	
		case ' ':
			return null;
		default: return null;
		}	
	}
}
