import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;


public class Evaluator {
	private Stack<String> valueStack;
	private Stack<String> operatorStack;
	private static double answer;
	
	public Evaluator(String s) {
		//the two stacks that will store the values and operations
		valueStack = new Stack<String>();
		operatorStack = new Stack<String>();
		
		//debugging print statement
		System.out.println("Evaluating...");
		answer = infix(s);
	}
	
	public double infix(String expression)
    {
        //remove white space and add evaluation operator
        expression=expression.replaceAll("[\t\n ]", "")+"=";
        String operator="*/+-=";
        
        //split up the operators from the values
        StringTokenizer tokenizer=new StringTokenizer(expression, operator, true);
        while(tokenizer.hasMoreTokens())
        {
            //add the next token to the proper stack
            String token=tokenizer.nextToken();
            if(operator.indexOf(token)<0)
                valueStack.push(token);
            else
                operatorStack.push(token);
			System.out.println(Arrays.toString(valueStack.toArray()));
			System.out.println(Arrays.toString(operatorStack.toArray()));
            //perform any pending operations
            resolve();
        }
        //return the top of the value stack
        String lastOne=(String)valueStack.pop();
        return Double.parseDouble(lastOne);   
    }
        
    public int getPriority(String op)
    {
        if(op.equals("*") || op.equals("/"))
            return 1;
        else if(op.equals("+") || op.equals("-"))
            return 2;
        else if(op.equals("="))
            return 3;
        else
            return Integer.MIN_VALUE;
    }
    
    public void resolve()
    {
		System.out.println("Resolving...");
		//resolve checks to see if there are more than two operators in the stack
        while(operatorStack.size()>=2)
        {
            String first=(String)operatorStack.pop();
            String second=(String)operatorStack.pop();
			System.out.println("Working on " + first + " and " +
			second);
            if(getPriority(first)<getPriority(second))
            {
            	//if they are in the right priority order it puts the popped value back into the
            	//stack and continues
            	//e.g if you have a - b * c, the multiplication is in the right order in the stack
				System.out.println("Second has greater priority. Putting them back.");
                operatorStack.push(second);
                operatorStack.push(first);
                return;
            }
            else
            {
            	//if they are not in the right priority order it evaluates the higher op and stores result
            	//stack and continues
            	//e.g if you have a * b - , then the result of a * b get to the stack and the minus is pushed into the stack
				System.out.println("First has greater priority. Resolving.");
                String firstValue=(String)valueStack.pop();
                String secondValue=(String)valueStack.pop();
                valueStack.push(getResults(secondValue, second, firstValue));
                operatorStack.push(first);
            }
        }
    }
    
    public String getResults(String operand1, String operator, String operand2)
    {
        System.out.println("Performing "+
                operand1+operator+operand2);
        double op1=Double.parseDouble(operand1);
        double op2=Double.parseDouble(operand2);
        if(operator.equals("*"))
            return ""+(op1*op2);
        else if(operator.equals("/"))
            return ""+(op1/op2);
        else if(operator.equals("+"))
            return ""+(op1+op2);
        else if(operator.equals("-"))
            return ""+(op1-op2);
        else
            return null;
    }
	
	
	public static String answer() {
		String s = String.valueOf(answer);
		if (s.endsWith(".0")){
			return s.substring(0, s.indexOf('.'));
		}
		return String.valueOf(answer);
	}
}
