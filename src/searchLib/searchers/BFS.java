package searchLib.searchers;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import searchLib.Action;
import searchLib.CommonSearcher;
import searchLib.Searchable;
import searchLib.Solution;
import searchLib.State;

public class BFS<T> extends CommonSearcher<T> {

	@Override
	public Solution search(Searchable<T> searchable) {
		HashSet<State<T>> visited = new HashSet<>(); // all the state that we already visit

		PriorityQueue<State<T>> queue = new PriorityQueue<>(new Comparator<State<T>>() { // priority queue that compare by cost

			@Override
			public int compare(State<T> o1, State<T> o2) {
				if (o1.getCost() == o2.getCost())
					return 0;
				if (o1.getCost() - o2.getCost() > 0)
					return 1;
				else
					return -1;

			}
		});

		State<T> state = searchable.getInitialState();
		queue.add(state);
		State<T> goalState = searchable.getGoalState();

		while (!queue.isEmpty()) {
			State<T> currState = queue.poll(); // Current state

			if (currState.equals(goalState))
				return this.backTrace(currState);

			if (!visited.contains(currState)) { // checks if its the goal state
				visited.add(currState);

				HashMap<Action, State<T>> hm = searchable.getAllPossibleStates(currState);

				for (Action a : hm.keySet()) { // for each possible state from current state
					State<T> s = hm.get(a);

					if (!visited.contains(s)) { // if it's on the visited list we can't approve the cost
					if (!queue.contains(s)) { // if the queue contains this state we can't approve the cost
							s.setCost(currState.getCost() + 1);
							s.setAction(a);
							s.setCameFrom(currState);
							queue.add(s);
						}
					}
				}

			}
				

		}

		return null;
	}

}
