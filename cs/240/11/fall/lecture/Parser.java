import java.util.Scanner;
import java.util.Stack;

interface Expr {}

class Atom implements Expr {
   String value;

   public Atom(String value) { this.value = value; }

   public String toString() { return value; }
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

public class Parser {
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
            System.out.println(parse(line.split("\\s+")));
         }
         catch (UnclosedListException ule) {
            System.out.println("Missing at least one right parenthesis.");
         }
      }
   }
}
