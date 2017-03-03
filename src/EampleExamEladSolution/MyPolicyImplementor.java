package EampleExamEladSolution;

public class MyPolicyImplementor implements PolicyImplementor {

	@Override
	public boolean isLegal(char[][] level, Direction d) {
		Position source = new Position();
		Position dest = new Position();
		Position afterDest = new Position();
		
		for(int i = 0; i < level.length; i++) // finding the Player
			for(int j = 0; j < level[i].length; j++)
				if(level[i][j] == 'A' || level[i][j] == 'l' || level[i][j] == 'r')
				{
					source.setX(i);
					source.setY(j);
				}
			
		try {
			dest = PositionCalculator(source, d);
			afterDest = PositionCalculator(dest, d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		if(level[source.getX()][source.getY()] == 'r' && d != Direction.right)
			return false;
		if(level[source.getX()][source.getY()] == 'l' && d != Direction.left)
			return false;

		
		
		if((level[dest.getX()][dest.getY()] == '<' || level[dest.getX()][dest.getY()] == 'L') && d == Direction.right)
			return false;
		if((level[dest.getX()][dest.getY()] == '>' || level[dest.getX()][dest.getY()] == 'R') && d == Direction.left)
			return false;
		
		if(level[dest.getX()][dest.getY()] == 'x')
			return false;
		
		if(level[dest.getX()][dest.getY()] == 'B' || level[dest.getX()][dest.getY()] == 'b' || level[dest.getX()][dest.getY()] == 'L' || level[dest.getX()][dest.getY()] == 'R')
			if(level[afterDest.getX()][afterDest.getY()] == 'x' || level[afterDest.getX()][afterDest.getY()] == 'B' || level[afterDest.getX()][afterDest.getY()] == 'b' || level[afterDest.getX()][afterDest.getY()] == 'L' || level[afterDest.getX()][afterDest.getY()] == 'R')     
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
