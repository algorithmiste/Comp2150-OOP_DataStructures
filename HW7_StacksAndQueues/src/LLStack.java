import java.util.Scanner;
@SuppressWarnings("unused")
public class LLStack<E> implements Stack<E>{

	private static class Node<E> {	// "static" means that Node does *not* have access to the instance variables of LinkedList
		private E data;
		private Node<E> next;

		// Constructor (generated automatically through Eclipse)
		public Node(E data, Node<E> next) {
			super();
			this.data = data;
			this.next = next;
		}
	}

	private Node<E> head;
	private int size;
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void push(E newData) {
		head = new Node<>(newData, head);	// Add a new node to the head of the list
		size++;
	}

	@Override
	public E pop() {
		if (!isEmpty()) {
			E temp = head.data;
			head = head.next;	// Remove the head node from the list
			size--;
			return temp;
		} else
			return null;
	}

	@Override
	public E peek() {
		if (!isEmpty())
			return head.data;
		else
			return null;
	}

	public String toString() {
		String r = "LLStack (size = " + size + "), containing (top to bottom):\n";
		for (Node<E> temp = head; temp != null; temp = temp.next)	// Traverse the entire list
			r += temp.data + "\n";
		return r;
	}
	
//	// Used to compare the precedence of the order of operations
//	public static int precedenceHandler(String c) {
//			if (c.equals("+") || c.equals("-"))
//				return 1;
//			else if (c.equals("*") || c.equals("/"))
//				return 2;
//			else if (c.equals("^"))
//				return 3;
//			else
//				return 0;
//		
//	}
//	// Calculates each operation specified by the infix expression
//	public static double operationCalculator(String c, double operand1, double operand2) {
//		switch(c){
//		case "+": 
//			return operand1 + operand2; 
//		case "-":
//			return operand1 - operand2; 
//		case "*":
//			return operand1 * operand2;
//		case "/":
//			return (double) (operand1 / operand2);
//		case "^": 
//			return Math.pow(operand1, operand2);
//		default:
//			System.out.println("Error on Operation");
//			return 0.0;
//		}
//	}
//
//	public static void main(String[] args) {
//		Stack<Double> operandStack = new LLStack<>();
//		Stack<String> operatorStack = new LLStack<>();
//		@SuppressWarnings("resource")
//		Scanner in = new Scanner(System.in);
//		String[] operatorArray = {"-", "+", "*", "/", "^"};
//		boolean answerComplete = true;
//		String infix;
//		
//		do {
//			System.out.println("Enter the infix expression that you would like to evaluate: ");
//			infix = in.nextLine();
//			if (infix.equals("X")) {
//				answerComplete = false;
//				break;
//			}
//			String[] infixExpression = infix.split(" ");
//			// Scan the infix expression one token at a time, from left to right.
//			for (int i = 0; i < infixExpression.length; i++) {
//				// If the token is an operand, push it onto the operand stack.
//				if (Character.isDigit(infixExpression[i].charAt(0)) || infixExpression[i].length() > 1) {
//					double operand = Double.parseDouble(infixExpression[i]);
//					operandStack.push(operand);
//				}
//				else if (infixExpression[i].equals("(")) {
//					operatorStack.push(infixExpression[i]);
//				}
//				else if (infixExpression[i].equals(")")) {
//					if (operatorStack.peek().equals("(")) {
//						System.out.println("Error: Parentheses balancing error has occurred! Fix your infix expression!\n");
//						answerComplete = false;
//						break;
//					}
//					else {
//						/** Every time you pop a non-parentheses operator off the operator stack, also pop the top two elements off the operand 
//						    stack, perform the indicated operation, and push the result back onto the operand stack. */
//						while (!operatorStack.peek().equals("(")) {
//							if (operatorStack.isEmpty()) {
//								System.out.println("Error: Parentheses balancing error has occurred! Fix your infix expression!\n");
//								answerComplete = false;
//								break;
//							}
//							else {
//								String letsOperate = operatorStack.pop();
//								double endOperand = operandStack.pop();
//								double beginOperand = operandStack.pop();
//
//								double operandToPush = operationCalculator(letsOperate, beginOperand, endOperand);
//								operandStack.push(operandToPush);
//							}
//						}
//						operatorStack.pop();
//					}
//				}
//				else {
//					for (int j = 0; j < operatorArray.length; j++) {
//						if (infixExpression[i].equals(operatorArray[j])) {
//							if (operatorStack.isEmpty()) {
//								operatorStack.push(infixExpression[i]);	
//							}
//							
//							else {
//								while (!operatorStack.isEmpty() && (precedenceHandler(infixExpression[i]) <= precedenceHandler(operatorStack.peek()))) {
//									if (!infixExpression[i].equals("(")) {
//										String letsOperate = operatorStack.pop();
//										double endOperand = operandStack.pop();
//										double beginOperand = operandStack.pop();
//										
//										double operandToPush = operationCalculator(letsOperate, beginOperand, endOperand);
//										operandStack.push(operandToPush);
//									}
//									else {
//										operatorStack.pop();
//									}
//									
//								}
//								operatorStack.push(infixExpression[i]);
//							}
//						}
//					}
//				}	
//			}
//			/** Once all tokens in the infix expression have been scanned, pop the remaining operators off the operator stack while also 
//			 * modifying the operand stack */
//			while (!operatorStack.isEmpty()) {
//				if (operatorStack.peek().equals("(")) {
//					System.out.println("Error: Parentheses balancing error has occurred! Fix your infix expression!\n");
//					answerComplete = false;
//					break;
//				}
//				String letsOperate = operatorStack.pop();
//				double endOperand = operandStack.pop();
//				double beginOperand = operandStack.pop();
//				
//				double operandToPush = operationCalculator(letsOperate, beginOperand, endOperand);
//				operandStack.push(operandToPush);
//			}
//			// The final result will be the top (and only) element left on the operand stack at the end.
//			if (answerComplete) {
//				System.out.printf("The result to your infix expression is: %.2f\n\n", operandStack.peek());
//			}
//			else {
//				break;
//			}
//	
//		} while (infix != "X");
//	
//	}

}
