package calc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

//실제 식을 계산해주는 Arithmetic 객체
public class Arithmetic {
	private String expression;
	private double result;
	
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
	
	//계산을 하고 계산이 가능하면 result 인스턴스 변수에 결과값을 저장한다
	//중위표기식을 후위표기식으로 변환해서 계산한다
	private boolean calculate() {
		//피연산자와 연산자를 담을 스택
		Deque<Double> operand = new ArrayDeque<>();
		Deque<String> operator = new ArrayDeque<>();
		
		//changeExpression 메소드에서 -는 숫자로 변환해 뒀기 때문에 -연산은 없는 취급한다
		//중위표기식을 후위표기식으로 변환하는 과정
		StringTokenizer st = new StringTokenizer(expression, "+*/()", true);
		while(st.hasMoreTokens()) {
			String temp = st.nextToken();
			if(temp.equals("+")) {
				while(!operator.isEmpty() && !operator.peekLast().equals("(")) {
					if(operand.size() < 2) return false;
					String symbol = operator.pollLast();
					if(symbol.equals("*")) {
						double val2 = operand.pollLast();
						double val1 = operand.pollLast();
						operand.addLast(val1*val2);
					} else if(symbol.equals("/")) {
						double val2 = operand.pollLast();
						double val1 = operand.pollLast();
						double val = (double)Math.round((val1/val2)*1000000)/1000000;
						operand.addLast(val);
					} else {
						double val2 = operand.pollLast();
						double val1 = operand.pollLast();
						operand.addLast(val1+val2);
					}
				} 
				operator.addLast(temp);
			} else if(temp.equals("*") || temp.equals("/")) {
				while(!operator.isEmpty() && !operator.peekLast().equals("(") && !operator.peekLast().equals("+")) {
					if(operand.size() < 2) return false;
					String symbol = operator.pollLast();
					if(symbol.equals("*")) {
						double val2 = operand.pollLast();
						double val1 = operand.pollLast();
						operand.addLast(val1*val2);
					} else {
						double val2 = operand.pollLast();
						double val1 = operand.pollLast();
						double val = (double)Math.round((val1/val2)*1000000)/1000000;
						operand.addLast(val);
					}
				} 
				operator.addLast(temp);
			} else if(temp.equals("(")) {
				operator.addLast(temp);
			} else if(temp.equals(")")) {
				while(!operator.isEmpty() && !operator.peekLast().equals("(")) {
					if(operand.size() < 2) return false;
					String symbol = operator.pollLast();
					if(symbol.equals("*")) {
						double val2 = operand.pollLast();
						double val1 = operand.pollLast();
						operand.addLast(val1*val2);
					} else if(symbol.equals("/")) {
						double val2 = operand.pollLast();
						double val1 = operand.pollLast();
						double val = (double)Math.round((val1/val2)*1000000)/1000000;
						operand.addLast(val);
					} else {
						double val2 = operand.pollLast();
						double val1 = operand.pollLast();
						operand.addLast(val1+val2);
					}
				}
				
				if(operator.isEmpty()) return false;
				operator.pollLast();
			} else {
				if(isNumeric(temp)) {
					operand.addLast(Double.parseDouble(temp));
				} else {
					return false;
				}
			}
		}
		//남아있는 연산 수행
		while(!operator.isEmpty()) {
			if(operand.size() < 2) return false;
			String symbol = operator.pollLast();
			if(symbol.equals("*")) {
				double val2 = operand.pollLast();
				double val1 = operand.pollLast();
				operand.addLast(val1*val2);
			} else if(symbol.equals("/")) {
				double val2 = operand.pollLast();
				double val1 = operand.pollLast();
				double val = (double)Math.round((val1/val2)*1000000)/1000000;
				operand.addLast(val);
			} else {
				double val2 = operand.pollLast();
				double val1 = operand.pollLast();
				operand.addLast(val1+val2);
			}
		}
		
		if(operand.size() != 1) {
			return false;
		}
		result = operand.pollLast();
		
		return true;
	}
	//결과를 표시해주는 메소드
	public void printResult() {
		changeExpression();
		if(calculate()) {
			if(result % 1 == 0) {
				System.out.println((int)result);
			} else {
				System.out.println(result);
			}
		} else {
			System.out.println("Error!!");
		}
	}
	
	//연산식을 적당하게 바꿔주는 메소드
	//5-2 같은 경우 5+(-2)로 5(3+2) 같은 경우 5*(3+2)로 바꿔주는 등을 한다
	private void changeExpression() {
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
	}
	//숫자인지 확인하는 메소드
	public boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
}
