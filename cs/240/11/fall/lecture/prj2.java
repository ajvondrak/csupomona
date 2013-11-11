import java.util.Scanner;
import java.util.Stack;

class EvalException extends Exception {
      public EvalException(String msg) { super(msg); }
}

interface Expr {
   public int eval() throws EvalException;
}

class Atom implements Expr {
   String value;

   public Atom(String value) { this.value = value; }

   public String toString() { return value; }

   public int eval() throws EvalException {
      /* IMPLEMENT ME
       * We'll assume Atoms represent integers when we eval them, so try to
       * parse the value as an int.  If this can't be done, we'll want to throw
       * an EvalException with the proper error message.
       */
   }
}

class List<E> implements Expr {
   private Node<E> head;

   public List(E... elements) {
      head = null;

      for(int i = elements.length - 1; i >= 0; i--) {
         head = new Node<E>(elements[i], head);
      }
   }

   public void append(E element) {
      if (head == null) {
         head = new Node<E>(element, null);
      }
      else {
         Node<E> last = head;
         while (last.link != null) last = last.link;
         last.link = new Node<E>(element, null);
      }
   }

   public String toString() {
      String s = "( ";
      Node<E> elements = head;
      while (elements != null) {
         s += elements.data + " ";
         elements = elements.link;
      }
      return s + ")";
   }

   public void evalAndPrintEach() {
      Node<Expr> current = (Node<Expr>) head;
      while (current != null) {
         try {
            System.out.println( ((Expr) current.data).eval() );
         }
         catch (EvalException e) {
            System.out.println(e);
         }
         current = current.link;
      }
   }

   private Node<Integer> evalArgs(Node<Expr> xs) throws EvalException {
      if (xs == null) return null;
      return new Node<Integer>(xs.data.eval(), evalArgs(xs.link));
   }

   public int eval() throws EvalException {
      Node<Expr> elts = (Node<Expr>) head;
      if (elts == null) throw new EvalException("Empty list.");
      String op = ((Atom) elts.data).value;

      /* IMPLEMENT ME
       * To evaluate a (non-empty) List<Expr>, we look at the first element
       * (which we assume is an Atom), evaluate each of the remaining elements
       * (using evalArgs), then apply the appropriate operator to the results.
       * See the examples in the handout.
       *
       * Attempting to evaluate an empty list results in an error.  If op isn't
       * one of "+", "-", "*", "/", then this too is an error.
       */
   }

   private int add(Node<Integer> xs) throws EvalException {
      /* IMPLEMENT ME
       * Find the sum of xs using a right fold.
       */
   }

   private int mul(Node<Integer> xs) throws EvalException {
      /* IMPLEMENT ME
       * Find the product of xs using a left fold.
       */
   }

   private int sub(Node<Integer> xs) throws EvalException {
      /* IMPLEMENT ME
       * Calculate the result by using a tail-recursive helper method.
       */
   }

   private int div(Node<Integer> xs) throws EvalException {
      /* IMPLEMENT ME
       * Calculate the result iteratively.  If xs doesn't have at least 2
       * elements, throw an Exception.
       */
   }

   private class Node<E> {
      E data;
      Node<E> link;

      public Node (E data, Node<E> link) {
         this.data = data;
         this.link = link;
      }
   }
}

class UnclosedListException extends Exception { }

class Lisp {
   public static List<Expr> parse(String[] tokens) throws Exception {
      Stack<List<Expr>> stack = new Stack<List<Expr>>();

      stack.push(new List<Expr>());

      for (String token : tokens) {
         if (token.equals("(")) {
            stack.push(new List<Expr>());
         }

         else if (token.equals(")")) {
            List<Expr> toAppend = stack.pop();
            stack.peek().append(toAppend);
         }

         else {
            stack.peek().append(new Atom(token));
         }
      }

      if (stack.size() > 1)
         throw new UnclosedListException();

      return stack.pop();
   }

   public static void main(String[] args) throws Exception {
      Scanner in = new Scanner(System.in);

      while(true) {
         System.out.print("lisp> ");
         String line = in.nextLine();
         try {
            parse(line.split("\\s+")).evalAndPrintEach();
         }
         catch (Exception e) {
            System.out.println("Parsing error.");
         }
      }
   }
}
