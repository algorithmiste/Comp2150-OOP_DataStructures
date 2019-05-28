import java.util.Scanner;

public class Question2_InfixToPostfix {

	// Used to compare the precedence of the order of operations
	public static int precedenceHandler(String c) {
		if (c.equals("+") || c.equals("-"))
			return 1;
		else if (c.equals("*") || c.equals("/"))
			return 2;
		else if (c.equals("^"))
			return 3;
		else
			return 0;	
	}
	// Calculates each operation specified by the infix expression
	public static double operationCalculator(String c, double operand1, double operand2) {
		switch(c){
		case "+": 
			return operand1 + operand2; 
		case "-":
			return operand1 - operand2; 
		case "*":
			return operand1 * operand2;
		case "/":
			return (double) (operand1 / operand2);
		case "^": 
			return Math.pow(operand1, operand2);
		default:
			System.out.println("Error on Operation");
			return 0.0;
		}
	}

	public static void main(String[] args) {
		Stack<Double> operandStack = new LLStack<>();
		Stack<String> operatorStack = new LLStack<>();
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String[] operatorArray = {"-", "+", "*", "/", "^"};
		boolean answerComplete = true;
		String infix; boolean go = false;

		do {
			System.out.println("Enter the infix expression that you would like to evaluate or enter \"X\" to exit: ");
			infix = in.nextLine();
			if (infix.equals("X")) {
				answerComplete = false;
				break;
			}
			String[] infixExpression = infix.split(" ");
			// Scan the infix expression one token at a time, from left to right.
			for (int i = 0; i < infixExpression.length; i++) {
				// If the token is an operand, push it onto the operand stack.

				if (Character.isDigit(infixExpression[i].charAt(0)) || infixExpression[i].length() > 1) {
					double operand = Double.parseDouble(infixExpression[i]);
					operandStack.push(operand);
				}
				else if (infixExpression[i].equals("(")) {
					operatorStack.push(infixExpression[i]);
				}
				else if (infixExpression[i].equals(")")) {
					if (operatorStack.peek().equals("(")) {
						System.out.println("Error1: Parentheses balancing error has occurred! Fix your infix expression!\n");
						answerComplete = false;
						break;
					}
					else {
						/** Every time you pop a non-parentheses operator off the operator stack, also pop the top two elements off the operand 
						    stack, perform the indicated operation, and push the result back onto the operand stack. */
						while (!operatorStack.peek().equals("(")) {
							if (operatorStack.isEmpty()) {
								System.out.println("Error2: Parentheses balancing error has occurred! Fix your infix expression!\n");
								answerComplete = false;
								break;
							}
							else {
								String letsOperate = operatorStack.pop();
								double endOperand = operandStack.pop();
								double beginOperand = operandStack.pop();

								double operandToPush = operationCalculator(letsOperate, beginOperand, endOperand);
								operandStack.push(operandToPush);
							}
						}
						operatorStack.pop();
					}
				}
				else {
					// "go" is a flow-control variable
					go = false;
					for (int j = 0; j < operatorArray.length; j++) {
						if (infixExpression[i].equals(operatorArray[j])) {
							go = true;
							if (operatorStack.isEmpty()) {
								operatorStack.push(infixExpression[i]);	
							}
							else {
								while (!operatorStack.isEmpty() && (precedenceHandler(infixExpression[i]) <= precedenceHandler(operatorStack.peek()))) {
									if (!infixExpression[i].equals("(")) {
										String letsOperate = operatorStack.pop();
										double endOperand = operandStack.pop();
										double beginOperand = operandStack.pop();

										double operandToPush = operationCalculator(letsOperate, beginOperand, endOperand);
										operandStack.push(operandToPush);
									}
									else {
										operatorStack.pop();
									}
								}
								operatorStack.push(infixExpression[i]);
							}
						}
					}
					if (!go) {
						System.out.println("Error: Invalid Token! Start Over.\n");
						answerComplete = false;
						break;
					}
				}	
			}
			/** Once all tokens in the infix expression have been scanned, pop the remaining operators off the operator stack while also 
			 * modifying the operand stack */
			while (!operatorStack.isEmpty() && go) {
				if (operatorStack.peek().equals("(")) {
					System.out.println("Error3: Parentheses balancing error has occurred! Fix your infix expression!\n");
					answerComplete = false;
					break;
				}
				String letsOperate = operatorStack.pop();
				double endOperand = operandStack.pop();
				double beginOperand = operandStack.pop();

				double operandToPush = operationCalculator(letsOperate, beginOperand, endOperand);
				operandStack.push(operandToPush);
			}
			// The final result will be the top (and only) element left on the operand stack at the end.
			if (answerComplete && go) {
				System.out.printf("The result to your infix expression is: %.2f\n\n", operandStack.peek());
			}
			else {
				break;
			}

		} while (go && infix != "X");

	}


}
