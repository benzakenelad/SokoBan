package model.policy;

import model.data.Direction;
import model.data.Position;

public class MySokoBanCharPolicy {

	
	boolean moveIsLegal(char[][] levelData, Direction direction) throws Exception
	{
		Position source = new Position();
		Position dest = new Position();
		Position afterDest = new Position();
		
		for(int i = 0; i < levelData.length; i++) // finding the Player
			for(int j = 0; j < levelData[i].length; j++)
				if(levelData[i][j] == 'A' || levelData[i][j] == 'B')
				{
					source.setX(i);
					source.setY(j);
				}
			
		dest = PositionCalculator(source, direction);
		afterDest = PositionCalculator(dest, direction);
		
		if(levelData[dest.getX()][dest.getY()] == '#')
			return false;
		
		if(levelData[dest.getX()][dest.getY()] == '@' || levelData[dest.getX()][dest.getY()] == '$')
			if(levelData[afterDest.getX()][afterDest.getY()] == '@' || levelData[afterDest.getX()][afterDest.getY()] == '$' || levelData[afterDest.getX()][afterDest.getY()] == '#')
				return false;
		
		return true;
	}
	

	public Position PositionCalculator(Position source, Direction dir) throws Exception 
	{
		switch(dir){
		case left:
			return new Position(source.getX(), source.getY() - 1);
		case up:
			return new Position(source.getX() - 1, source.getY());
		case right:
			return new Position(source.getX(), source.getY() + 1);
		case down:
			return new Position(source.getX() + 1, source.getY());
		default: throw new Exception("Invaild Direction");
		
		}
	}
	

}
