import java.util.Scanner;

class StackUnderflowException extends Exception {
   public StackUnderflowException() { super("Stack underflow."); }
}

interface Stack<E> {
   public void push(E value);
   public E pop() throws StackUnderflowException;
   public E top() throws StackUnderflowException;
   public int size();
   public boolean isEmpty();
}

class ArrayStack<E> implements Stack<E> {
   private E[] data;
   private int top;

   public ArrayStack() {
      final int CAPACITY = 10;
      data = (E[]) new Object[CAPACITY];
      top = -1;
   }

   public void push(E value) {
      if (size() == data.length)
         grow();
      data[++top] = value;
   }

   private void grow() {
      final int CAPACITY = 2 * data.length + 1;
      E[] biggerArray = (E[]) new Object[CAPACITY];

      for(int i = 0; i < data.length; i++)
         biggerArray[i] = data[i];

      data = biggerArray;
   }

   public E pop() throws StackUnderflowException {
      E result = top();
      data[top] = null;
      top--;
      return result;
   }

   public E top() throws StackUnderflowException {
      if (isEmpty())
         throw new StackUnderflowException();
      return data[top];
   }

   public int size() { return top + 1; }

   public boolean isEmpty() { return size() == 0; }
}

class Balancing {
   public static void main(String[] args) throws Exception {
      Scanner kb = new Scanner(System.in);
      while(true) {
         System.out.print("Enter some parens: ");
         String parens = kb.nextLine();
         balanced(parens);
      }
   }

   public static void balanced(String parens) throws Exception {
      ArrayStack<Character> stk = new ArrayStack<Character>();

      for(int i = 0; i < parens.length(); i++) {
         char paren = parens.charAt(i);

         if      (paren == '(') { stk.push(paren); }
         else if (paren == ')') {
            try {
               stk.pop();
            }
            catch (StackUnderflowException sue) {
               // handle the error here
               System.out.println("Unbalanced right parenthesis at location " + i);
               return;
            }
         }
      }

      if (stk.isEmpty()) System.out.println("Balanced");
      else  System.out.println("Unbalanced left parenthesis.");
   }
}
