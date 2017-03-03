package LambdaExpression;

public class Test {
	FuncT myFunc0 = null;
	FuncT myFunc1 = null;
	FuncT myFunc2 = null;
	
	public void print(int a, int b)
	{
		if(myFunc0 == null)
			System.out.println("Didn't change yet");
		else
		{
			myFunc0.execute(a, b);
			myFunc1.execute(a, b);
			myFunc2.execute(a, b);
		}
	}
	
	
	public interface FuncT{
		public void execute(int a, int b);
	}
}
