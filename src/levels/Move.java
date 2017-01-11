package levels;

public interface Move {
	public void Action(Level lvl, Policy policy, Direction dir) throws Exception;
}
