package calc;

import java.util.ArrayDeque;
import java.util.Deque;

public class Arithmetic {
	private String expression;
	private int result;
	
	//생성자 
	public Arithmetic() {
		this.expression = "0";
	}
	public Arithmetic(String string) {
		this.expression = string;
	}
	
	public void setExpression(String string) {
		this.expression = string;
	}
	
	//사칙연산의 결과값을 리턴하는 메소드
	public int calculate() {
		return 0;
	}
	
	//연산식을 적당하게 바꿔주는 메소드
	//5-2 같은 경우 5+(-2)로 5(3+2) 같은 경우 5*(3+2)로 바꿔주는 등 계산을 쉽게 만들어준다
	public void changeExpression() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < expression.length(); i++) {
			// 5-3 같은 경우 5+(-3)으로 변경
			if(expression.charAt(i) == '-') {
				if(i == 0) {
					sb.append("-");
					continue;
				}
				if(sb.charAt(sb.length()-1) == '*' || sb.charAt(sb.length()-1) == '/' || sb.charAt(sb.length()-1) == '+') {
					sb.append("-");
					continue;
				} 
				else if(sb.charAt(sb.length()-1) == '-') {
					sb.deleteCharAt(sb.length()-1);	
					sb.append("-");
					continue;
				} 
				// 마이너스 전에 +,-,*,/ 를 제외한게 나왔을 시 더하기를 먼저 더해준다
				else {
					sb.append("+");
					sb.append("-");
					continue;
				} 
			}
			else if(expression.charAt(i) == '(') {
				if(i == 0) {
					sb.append("(");
					continue;
				}
				// ( 앞에 숫자가 나오면 *를 추가해준다
				if(!(sb.charAt(sb.length()-1) == '+' || sb.charAt(sb.length()-1) == '-'
						|| sb.charAt(sb.length()-1) == '*' || sb.charAt(sb.length()-1) == '/')) {
					sb.append("*");
					sb.append("(");
					continue;
				} else {
					sb.append("(");
					continue;
				}
			}
			//+ 가 맨 처음 나왔을 시 생략한다
			else if(expression.charAt(i) == '+'){
				if(i == 0) continue;
				sb.append("+");
			}
			else {
				//숫자가 나왔을때, 그 전에 )가 있다면 곱하기 연산자를 삽입한다
				if(!(expression.charAt(i) == '*' || expression.charAt(i) == '/' || expression.charAt(i) == ')')) {
					if(sb.length() == 0) {
						sb.append(expression.charAt(i));
						continue;
					}
					
					if(sb.charAt(sb.length()-1) == ')') {
						sb.append("*");
						sb.append(expression.charAt(i));
						continue;
					} else {
						sb.append(expression.charAt(i));
						continue;
					}
				} else {
					sb.append(expression.charAt(i));
					continue;
				}
			}
		}
		
		expression = sb.toString();
		System.out.println(expression);
	}
	
}
