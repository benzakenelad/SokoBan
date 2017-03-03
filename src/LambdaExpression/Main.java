package LambdaExpression;

public class Main {

	public static void main(String[] args) {

		Test t = new Test();
		
		
		t.myFunc0 = (a, b)-> System.out.println(a + b);
		t.myFunc1 = (a, b)-> System.out.println(a - b);
		t.myFunc2 = (a, b)-> System.out.println(a * b);
	
		t.print(5, 3);
		
		t.myFunc2 = (a, b)-> System.out.println(a + b);
		t.print(5, 3);
	}

}
