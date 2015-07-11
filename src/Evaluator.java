import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;


public class Evaluator {
	private String [] tokens;
	private Stack<String> numbers;
	private Stack<String> operators;
	private HashMap<String, Integer> precedence = new HashMap<String, Integer>();
	private static int answer;
	
	public Evaluator(String s) {
		numbers = new Stack<String>();
		operators = new Stack<String>();
		precedence.put("*", 2);
		precedence.put("/", 2);
		precedence.put("+", 1);
		precedence.put("-", 1);
		System.out.println("Evaluating...");
		tokens = s.split("\\s+");
		//System.out.println(Arrays.toString(tokens));
		tokenise();
		evaluate();
	}
	
	private void tokenise() {
		for (int i = 0; i < tokens.length; i++) {
			if (precedence.containsKey(tokens[i]) && precedence.get(tokens[i]) == 1) {
				//System.out.println(tokens[i]);
				numbers.push(tokens[i - 1]);
				operators.push(tokens[i]);
				if (i + 2 < tokens.length - 1 &&
						precedence.containsKey(tokens[i + 2]) && 
						precedence.get(tokens[i]) == 1){
					operators.push(tokens[i + 2]);
					numbers.push(tokens[i + 1]);
					numbers.push(tokens[i + 3]);
					tokens[i + 2] = "0";
					tokens[i + 3] = "0";
					tokens[i + 1] = "0";
				} else {
					numbers.push(tokens[i + 1]);
				}
			}
		}
		
		for (int i = 0; i < tokens.length; i++) {
			if (precedence.containsKey(tokens[i]) && precedence.get(tokens[i]) == 2) {
				numbers.push(tokens[i - 1]);
				operators.push(tokens[i]);
				numbers.push(tokens[i + 1]);
			}
		}
		System.out.println(Arrays.toString(numbers.toArray()));
		System.out.println(Arrays.toString(operators.toArray()));
	}
	
	private void evaluate() {
		while (numbers.size() != 1) {
			String op = operators.pop();
			if (op.equals("*")){
				int i = Integer.parseInt(numbers.pop());
				int j = Integer.parseInt(numbers.pop());
				answer = i * j;
				numbers.push(String.valueOf(answer));
			}
			
			else if (op.equals("+")){
				int i = Integer.parseInt(numbers.pop());
				int j = Integer.parseInt(numbers.pop());
				answer = i + j;
				numbers.push(String.valueOf(answer));
			}
			else if (op.equals("-")){
				int i = Integer.parseInt(numbers.pop());
				int j = Integer.parseInt(numbers.pop());
				answer = j - i;
				numbers.push(String.valueOf(answer));
			}
			else if (op.equals("/")){
				int i = Integer.parseInt(numbers.pop());
				int j = Integer.parseInt(numbers.pop());
				answer = j / i;
				numbers.push(String.valueOf(answer));
			}
		}
	}
	
	public static String answer() {
		return String.valueOf(answer);
	}
}
