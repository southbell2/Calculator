package calc;

public class ArtimeticTest {

	public static void main(String[] args) {
		String test1 = "-3+4*5-7";
		String test2 = "1+(2*3+6)/4";
		String test3 = "-2*-3";
		String test4 = "+5-3*2";
		String test5 = "(5+3)2";
		String test6 = "5(5+3)";
		
		Arithmetic calculator = new Arithmetic();
		calculator.setExpression(test1);
		calculator.changeExpression();
		
		calculator.setExpression(test2);
		calculator.changeExpression();
		
		calculator.setExpression(test3);
		calculator.changeExpression();
		
		calculator.setExpression(test4);
		calculator.changeExpression();
		
		calculator.setExpression(test5);
		calculator.changeExpression();
		
		calculator.setExpression(test6);
		calculator.changeExpression();
	}

}
