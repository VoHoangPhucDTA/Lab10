package Task1;

import java.util.Arrays;
import java.util.Stack;

public class MyLIFO_App {
	// This method reserves the given array
	public static <E> void reserve(E[] array) {
		Stack<E> st = new Stack<E>();
		int n = array.length;
		int idx = 0;
		for (int i = 0; i < n; i++) {
			st.add(array[i]);
		}
		while (!st.isEmpty()) {
			array[idx++] = st.pop();
		}
	}

	// This method checks the correctness of the
	// given input
	// i.e. ()(())[]{(())} ==> true, ){[]}() ==> false
	public static boolean isCorrect(String input) {
		Stack<Character> st = new Stack<Character>();
		if (input.charAt(0) == ')' || input.charAt(0) == ']' || input.charAt(0) == '}')
			return false;
		for (char c : input.toCharArray()) {
			if (c == '(' || c == '{' || c == '[')
				st.add(c);
			else {
				char top = st.peek();
				if ((c == ')' && top == '(') || (c == ']' && top == '[') || (c == '}' && top == '{')) {
					st.pop();
				}
				// mo ma ko co dong
				else if (st.isEmpty())
					return false;
			}
		}
		return st.isEmpty();
	}

	// This method evaluates the value of an expression
	// i.e. 51 + (54 *(3+2)) = 321
	public static int evaluateExpression(String expression) {
		Stack<Integer> stOperand = new Stack<Integer>();
		Stack<String> stOperator = new Stack<String>();
		expression = insertBlank(expression);
		String[] stringArr = expression.split(" ");
		int n = stringArr.length;
		for (int i = 0; i < n; i++) {
			String ele = stringArr[i];
			if (ele.equals(" ") || ele.isEmpty())
				continue;
			else if (ele.equals(")")) {
				// nếu không rỗng và phần tử trên cùng ko phải là "("
				while (!stOperator.isEmpty() && !stOperator.peek().equals("(")) {
					int operand2 = stOperand.pop();
					int operand1 = stOperand.pop();
					String operator = stOperator.pop();
					int result = tinhToan(operand1, operand2, operator);
					stOperand.push(result);
				}
				// quăng cái mở ngoặc ra
				stOperator.pop();
			} else if (ele.equals("("))
				stOperator.push(ele);
			else if (isOperator(ele)) {
				while (!stOperator.isEmpty() && doUuTien(ele) <= doUuTien(stOperator.peek())) {
					int operand2 = stOperand.pop();
					int operand1 = stOperand.pop();
					String operator = stOperator.pop();
					int result = tinhToan(operand1, operand2, operator);
					stOperand.push(result);
				}
				stOperator.push(ele);
			} else {
				stOperand.push(Integer.parseInt(ele));
			}
		}
		while (!stOperator.isEmpty()) {
			int operand2 = stOperand.pop();
			int operand1 = stOperand.pop();
			String operator = stOperator.pop();
			int result = tinhToan(operand1, operand2, operator);
			stOperand.push(result);
		}
		return stOperand.pop();
	}

	private static int doUuTien(String s) {
		if (s.equals("+") || s.equals("-"))
			return 1;
		else if (s.equals("*") || s.equals("/"))
			return 2;
		return 0;
	}

	private static boolean isOperator(String s) {
		return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
	}

	private static int tinhToan(int operand1, int operand2, String operator) {
		switch (operator) {
		case "+":
			return operand1 + operand2;
		case "-":
			return operand1 - operand2;
		case "*":
			return operand1 * operand2;
		case "/":
			return operand1 / operand2;
		default:
			throw new IllegalArgumentException("Invalid operator: " + operator);
		}
	}

	public static String insertBlank(String s) {
		String res = "";
		for (char c : s.toCharArray()) {
			if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
				res = res + " " + c + " ";
			} else
				res = res + c;
		}
		return res.trim();
	}

	public static void test() {
		Integer[] arr = { 1, 2, 3, 4, 5 };
		System.out.print("Đảo ngược mảng: ");
		reserve(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println();
		String s = "[({})]", s1 = "]{}", s2 = "{(})";

		String s4 = "5+4*(3*5+1)";

		System.out.println("Kiếm tra hợp lệ: " + s + ": " + isCorrect(s));
		System.out.println("Kiếm tra hợp lệ: " + s1 + ": " + isCorrect(s1));
		System.out.println("Kiếm tra hợp lệ: " + s2 + ": " + isCorrect(s2));
		System.out.println();

		System.out.println("Tính toán: " + s4 + "= " + evaluateExpression(s4));
	}

	public static void main(String[] args) {
		test();

	}
}
