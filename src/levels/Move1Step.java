package levels;

public class Move1Step implements Move{
	
	public void Action(Level lvl, Policy policy, Direction dir) throws Exception // move the character left/up/right/down
	{
		if(lvl == null)
			return;

		switch(dir){
		case left: 
			policy.check(lvl, dir);
			break;
		case up:
			policy.check(lvl, dir);
			break;
		case right:
			policy.check(lvl, dir);
			break;
		case down:
			policy.check(lvl, dir);
			break;
		default: throw new Exception("Illegel Move");
		}
		
	}
}
