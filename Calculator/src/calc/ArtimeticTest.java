package calc;

public class ArtimeticTest {

	public static void main(String[] args) {
		String test1 = "3+4*5-7";
		String test2 = "1+2+3";
		
		Arithmetic testCalc = new Arithmetic();
		testCalc.setString(test1);
		System.out.println(testCalc.calculate());
		
		testCalc.setString(test2); 
		System.out.println(testCalc.calculate());
		 
	}

}
