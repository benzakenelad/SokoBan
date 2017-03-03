package EampleExamEladSolution;

public class Level {
	
	char[][] data;
    MyInterface onChange = null;
	
	
	public Level(char[][] data) {
		this.data=data;
	}

	
	public void changeData(int row,int col,char c) {
		
	   if(onChange == null){
		   data[row][col]=c; 
		   System.out.println("changeData Worked");
	   }
	   else
		   onChange.exe();
	}

	
	
	public interface MyInterface {
	      public void exe();
	   }
	
}
