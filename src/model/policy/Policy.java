package model.policy;

import model.data.Direction;
import model.data.Level;
import model.data.Position;

public abstract class Policy {
	public abstract void check(Level lvl, Direction dir) throws Exception;
	
	public Position PositionCalculator(Position source, Direction dir) throws Exception // dir = 1 = left, dir = 2 = up, dir = 3 = right, dir = 4 = down
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
