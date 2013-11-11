import java.util.Scanner;
import java.util.Stack;

class Token {
   private String value;

   public Token(String value) {
      this.value = value;
   }

   public Token(int value) {
      this.value = Integer.toString(value);
   }

   public Integer intValue() throws NumberFormatException {
      return Integer.parseInt(this.value);
   }

   public String stringValue() {
      return this.value;
   }

   public String toString() {
      return this.value;
   }
}


class AssociationList<K, V> {
   /* IMPLEMENT ME: insert field declaration(s) here. */

   public AssociationList() {
      /* IMPLEMENT ME: a constructor. */
   }

   public V get(K key) {
      /* IMPLEMENT ME: if `key` is a key of the association list, return the
       * associated value.  If not, return null.
       */
   }

   public void set(K key, V value) {
      /* IMPLEMENT ME: associate `key` with `value` in the list.  If `key` is
       * already a key of the list, this new association should take precedence
       * (note there are many ways to do this; it depends on how you implement
       * the get method).
       */
   }

   /* IMPLEMENT ME: insert a private class here to use for each node in the
    * linked list.
    */
}


class RPNCalculator {
   private static final String SET = "set";
   private static final String GET = "get";

   private Stack<Token> operands;
   private AssociationList<String, Integer> environment;

   public RPNCalculator() {
      this.operands = new Stack<Token>();
      this.environment = new AssociationList<String, Integer>();
   }

   private void underflow() {
      System.out.print("Not enough operands ");
   }

   private boolean isBinaryOp(String expr) {
      return (expr.length() == 1 && "+-*/".indexOf(expr) != -1)
             || expr.equals(SET);
   }

   private void binaryOp(String expr) {
      /* IMPLEMENT ME: executes the binary operator represented by `expr` (i.e.,
       * a math operator or the "set" command).  If there aren't enough elements
       * on the stack, use the underflow method to print an error message.
       */
   }

   private void mathOp(char op) {
      int y = this.operands.pop().intValue();
      int x = this.operands.pop().intValue();
      switch (op) {
         case '+':
            this.operands.push(new Token(x + y));
            break;
         case '-':
            this.operands.push(new Token(x - y));
            break;
         case '*':
            this.operands.push(new Token(x * y));
            break;
         case '/':
            if (y == 0) {
               System.out.print("Division by zero ");
            }
            else {
               this.operands.push(new Token(x / y));
            }
            break;
         default:
            return;
      }
   }

   private boolean isUnaryOp(String expr) {
      return expr.equals(GET);
   }

   private void unaryOp(String expr) {
      /* IMPLEMENT ME: executes the unary operator represented by `expr` (note
       * that there's only one unary operator!).  If there aren't enough
       * elements on the stack, use the underflow method to print an error.
       */
   }

   private void set() {
      /* IMPLEMENT ME: given a stack like
       *
       * |     |
       * |-----|
       * | var |
       * |-----|
       * | val |
       * |_____|
       *
       * pop the two elements, and associate the String value of the `var` Token
       * with the (presumed) Integer value of the `val` Token in
       * this.environment.  If the second element from the top doesn't have an
       * integer value, print the error message from the project description.
       */
   }

   private void get() {
      /* IMPLEMENT ME: pop the top element of the stack, and push the value
       * associated with its name in this.environment.  If no such association
       * exists, print the error message from the project description.
       */
   }

   public void eval(String expr) {
      if (this.isBinaryOp(expr)) {
         this.binaryOp(expr);
      }
      else if (this.isUnaryOp(expr)) {
         this.unaryOp(expr);
      }
      else {
         this.operands.push(new Token(expr));
      }
      System.out.println(this.operands);
   }
}


class Homework7 {
   public static void main(String[] args) {
      RPNCalculator rpn = new RPNCalculator();
      Scanner in = new Scanner(System.in);
      while (in.hasNext()) {
         rpn.eval(in.next());
      }
   }
}
