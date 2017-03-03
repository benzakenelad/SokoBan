package EampleExamEliSolution;

public interface PolicyImplementor {
	public enum Direction{up,down,left,right}
	boolean isLegal(char[][] level,Direction d);
}
