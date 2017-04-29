package searchLib.mazeExample;

import java.util.HashMap;

import searchLib.Action;
import searchLib.Position;
import searchLib.Searchable;
import searchLib.State;

public class SearchableMaze implements Searchable<Position> {

	MazeLevel level;

	public SearchableMaze(MazeLevel level) {
		this.level = level;
	}

	@Override
	public State<Position> getInitialState() {
		Position pos = level.getPlayer();
		return new State<Position>(pos);
	}

	@Override
	public State<Position> getGoalState() {
		Position pos = level.getWinPosition();
		return new State<Position>(pos);
	}

	@Override
	public HashMap<Action, State<Position>> getAllPossibleStates(State<Position> s) {
		HashMap<Action, State<Position>> hm = new HashMap<Action, State<Position>>();
		Position currentPositon = s.getObj();
		int currentCol = currentPositon.getCol();
		int currentRow = currentPositon.getRow();

		if (level.getLevelData()[currentRow - 1][currentCol] != '#') {
			Position pos = new Position(currentRow - 1, currentCol);
			hm.put(new Action("move up"),new State<Position>(pos,1));
		}
		if (level.getLevelData()[currentRow][currentCol + 1] != '#') {
			Position pos = new Position(currentRow, currentCol + 1);
			hm.put(new Action("move right"),new State<Position>(pos,1));
		}
		if (level.getLevelData()[currentRow + 1][currentCol] != '#') {
			Position pos = new Position(currentRow + 1, currentCol);
			hm.put(new Action("move down"),new State<Position>(pos,1));
		}
		if (level.getLevelData()[currentRow][currentCol - 1] != '#') {
			Position pos = new Position(currentRow, currentCol - 1);
			hm.put(new Action("move left"),new State<Position>(pos,1));
		}
		if (level.getLevelData()[currentRow - 1][currentCol - 1] != '#') {
			Position pos = new Position(currentRow - 1, currentCol - 1);
			hm.put(new Action("move leftUp"),new State<Position>(pos,1.5));
		}
		if (level.getLevelData()[currentRow - 1][currentCol + 1] != '#') {
			Position pos = new Position(currentRow - 1, currentCol + 1);
			hm.put(new Action("move rightUp"),new State<Position>(pos,1.5));
		}
		if (level.getLevelData()[currentRow + 1][currentCol - 1] != '#') {
			Position pos = new Position(currentRow + 1, currentCol - 1);
			hm.put(new Action("move leftDown"),new State<Position>(pos,1.5));
		}
		if (level.getLevelData()[currentRow + 1][currentCol + 1] != '#') {
			Position pos = new Position(currentRow + 1, currentCol + 1);
			hm.put(new Action("move rightDown"),new State<Position>(pos,1.5));
		}

		return hm;
	}

}
