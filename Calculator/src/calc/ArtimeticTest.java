package calc;
import java.util.ArrayList;

public class ArtimeticTest {

	public static void main(String[] args) {
		ArrayList<String> tests = new ArrayList<>();
		tests.add("-3+4*5-7");
		tests.add("1+(2*3+6)/4");
		tests.add("-2*-3");
		tests.add("+5-3*2");
		tests.add("(5+3)2");
		tests.add("5(5+3)");
		tests.add("13-28/9+2(132*24+13-12)");
		tests.add("-*23");
		tests.add("+123+45");
		tests.add(",123+45");
		tests.add("++123+45");
		tests.add("+123+*45");
		tests.add("123+45*");
		tests.add("/123*45");
		
		Arithmetic calculator = new Arithmetic();
		for(String test : tests) {
			calculator.setExpression(test);
			calculator.printResult();
		}
	}

}
