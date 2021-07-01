package calc;

import java.util.ArrayDeque;
import java.util.Deque;

public class Arithmetic {
	private String string;
	
	//생성자 
	public Arithmetic() {
		this.string = "0";
	}
	public Arithmetic(String string) {
		this.string = string;
	}
	
	public void setString(String string) {
		this.string = string;
	}
	
	//사칙연산의 결과값을 리턴하는 메소드
	public int calculate() {
		//만약 마지막에 연산기호가 나오고 숫자가 나오지 않으면 에러메시지를 출력하고 리턴한다
		if(string.charAt(string.length()-1) == '+' || string.charAt(string.length()-1) == '-'
				|| string.charAt(string.length()-1) == '*' || string.charAt(string.length()-1) == '/') {
			System.out.println("Error!");
			return 0;
		}
		//deque에는 숫자와 연산기호가 들어간다
		Deque<String> dq = new ArrayDeque<>();
		//deque에 숫자와 연산기호를 구분지어 push 해준다
		int standard = 0;
		for(int i = 0; i < string.length(); i++) {
			if(string.charAt(i) == '+' || string.charAt(i) == '-' || string.charAt(i) == '*' || string.charAt(i) == '/') {
				//numberBeforeSymbom은 연산기호 바로 전에 있던 숫자
				String numberBeforeSymbol = string.substring(standard, i);
				String symbol = Character.toString(string.charAt(i));
				
				if(dq.isEmpty()) {
					dq.addLast(numberBeforeSymbol);
					dq.addLast(symbol);
					standard = i + 1;
					continue;
				}
				// 곱하기와 나누기 연산자는 먼저 계산해준다
				if(dq.peekLast().equals("*")){
					dq.pollLast();
					int x = Integer.parseInt(dq.pollLast());
					int y = Integer.parseInt(numberBeforeSymbol);
					dq.addLast(Integer.toString(x*y));
					dq.addLast(symbol);
					standard = i + 1;
				} else if(dq.peekLast().equals("/")) {
					dq.pollLast();
					int x = Integer.parseInt(dq.pollLast());
					int y = Integer.parseInt(numberBeforeSymbol);
					dq.addLast(Integer.toString(x/y));
					dq.addLast(symbol);
					standard = i + 1;
				} else {
					dq.addLast(numberBeforeSymbol);
					dq.addLast(symbol);
					standard = i + 1;
				}
			}
		}
		dq.addLast(string.substring(standard));
		//나머지 숫자들의 더하기와 빼기 계산
		int resultNumber = 0;
		while(!dq.isEmpty()) {
			if(dq.peekFirst().equals("+")) {
				dq.pollFirst();
				resultNumber += Integer.parseInt(dq.pollFirst());
			} else if(dq.peekFirst().equals("-")) {
				dq.pollFirst();
				resultNumber -= Integer.parseInt(dq.pollFirst());
			} else {
				resultNumber += Integer.parseInt(dq.pollFirst());
			}
		}
		
		return resultNumber;
	}
}
