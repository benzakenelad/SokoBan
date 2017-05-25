package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import model.data.Level;
import model.data.Move;
import model.levelLoaders.LoadLevel;
import model.levelSavers.SaveLevel;
import model.policy.Policy;
import searchLib.Action;
import solver.solveSokobanLevel;

public class SokobanModel extends Observable implements Model {

	// Data members
	private Level lvl = null;
	private ExecutorService executor = Executors.newCachedThreadPool();

	// Methods implementation
	@Override
	public void move(Move move, Policy policy, String note) {
		try {
			move.Action(lvl, policy, note);
			this.setChanged();
			this.notifyObservers("display");

		} catch (Exception e) {
			/* e.printStackTrace(); */}
	}

	@Override
	public void loadLevel(String note) {
		Level lvl = null;
		try {
			lvl = new LoadLevel().Action(note);
			this.lvl = lvl;
			this.setChanged();
			this.notifyObservers("display");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void saveLevel(String note) {
		try {
			new SaveLevel().Action(lvl, note);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// getters and setters
	public Level getLvl() {
		return lvl;
	}

	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}

	@Override
	public void solve() {
		if(lvl == null)
			return;
		try {
			long begin = System.currentTimeMillis();
			final List<Action> actions = MultiThreadedSolver(10,100);
			long end = System.currentTimeMillis();
			
			double time = (end - begin) / 1000;
			
			System.out.println("Solving time : " + time + " seconds");
			
			if (actions == null){
				System.out.println("Didnt solve the level.");
				return;
			}
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (Action action : actions) {
						setChanged();
						notifyObservers(action.getName());
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}).start();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		;

	}

	private List<Action> MultiThreadedSolver(int threadsNum, int testSize) throws InterruptedException, ExecutionException, TimeoutException {
		
		ArrayList<Future<List<Action>>> futures = new ArrayList<Future<List<Action>>>();

		for (int i = 0; i < threadsNum; i++) {
			futures.add(executor.submit(new Callable<List<Action>>() {
				@Override
				public List<Action> call() throws Exception {
					solveSokobanLevel solver = new solveSokobanLevel();
					List<Action> actions = solver.optimalSolve(lvl, testSize);
					return actions;
				}
			}));
		}
		ArrayList<List<Action>> lists = new ArrayList<List<Action>>();

		for (int i = 0; i < threadsNum; i++)
			lists.add(futures.get(i).get());

		List<Action> returnedList = null;

		int largest = 99999999;
		for (int i = 0; i < threadsNum; i++) {
			if (lists.get(i) != null)
				if (lists.get(i).size() < largest) {
					largest = lists.get(i).size();
					returnedList = lists.get(i);
				}
		}
		
		return returnedList;
	}
}
