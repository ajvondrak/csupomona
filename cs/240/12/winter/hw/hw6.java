import java.util.Scanner;

class StackUnderflowException extends RuntimeException {
   public StackUnderflowException() {
      super("Stack underflow.");
   }
}


interface Stack<E> {
   public void push(E item);

   public E pop() throws StackUnderflowException;

   public E peek() throws StackUnderflowException;
}


class Node<E> {
   public E data;
   public Node<E> link;

   public Node(E data, Node<E> link) {
      this.data = data;
      this.link = link;
   }
}


class LinkedStack<E> implements Stack<E> {
   private Node<E> head;
   private int hugeness;

   public LinkedStack() { 
      this.head = null;
      this.hugeness = 0;
   }

   public int size() {
      return this.hugeness;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public E peek() throws StackUnderflowException {
      if (this.isEmpty()) {
         throw new StackUnderflowException();
      }
      return this.head.data;
   }

   public E pop() throws StackUnderflowException {
      E result = this.peek();
      this.head = this.head.link;
      this.hugeness--;
      return result;
   }

   public void push(E item) {
      this.head = new Node<E>(item, this.head);
      this.hugeness++;
   }

   public String toString() {
      String s = "";
      for (Node<E> cursor = this.head; cursor != null; cursor = cursor.link) {
         s += cursor.data + "\n";
      }
      return s;
   }
}


class CallFrame {
   // The following fields keep track of local data in our recursive method(s)
   public int n;
   public int r;

   // This field indicates which statement of the method we're currently
   // executing
   private int stmt;

   public CallFrame(int n) {
      this(n, 0);
   }

   public CallFrame(int n, int r) {
      this.n = n;
      this.r = r;
      this.stmt = 0;
   }

   public int getStmt() {
      return this.stmt;
   }

   public void advance() {
      this.stmt++;
   }

   public String toString() {
      return String.format("CALL FRAME<n = %d, r = %d, stmt = %d>",
                           this.n,
                           this.r,
                           this.stmt);
   }
}


class FactorialSimulation {
   private int iteration;
   private LinkedStack<CallFrame> callstack;
   private int returnValue;

   public FactorialSimulation(int n) {
      this(n, 0);
   }

   public FactorialSimulation(int n, int r) {
      this.callstack = new LinkedStack<CallFrame>();
      this.callstack.push(new CallFrame(n, r));
      this.iteration = 0;
      this.returnValue = 0;
   }

   private void report() {
      this.iteration++;
      System.out.println();
      System.out.println("Iteration " + this.iteration);
      System.out.print(this.callstack);
      System.out.println("returnValue = " + this.returnValue);
      if (!this.callstack.isEmpty()) {
         System.out.println("\nEXECUTING STATEMENT "
                            + this.callstack.peek().getStmt());
      }
   }

   public int factorial(int n) {
      if (n == 1) {                  // }
         return 1;                   // } Statement 0
      }                              // }
      int r =                        //   Statement 2
              this.factorial(n - 1); //   Statement 1
      return n * r;                  //   Statement 3
   }

   public int run() {
      /* IMPLEMENT ME: a manual simulation of the call stack when the
       * factorial(int n) method is invoked.  The simulation should match the
       * structure of the factorial(int n) method above.  See the runTR()
       * implementation for a general idea of the structure you should use
       * here.
       */
   }

   public int factorialTR(int n, int r) {
      /* IMPLEMENT ME: a tail-recursive factorial that takes two parameters and
       * whose form is equivalent to the method simulated by runTR().  Be sure
       * to label the statements!  (See the format of factorial(int n).)
       */
   }

   public int runTR() {
      while (!this.callstack.isEmpty()) {
         this.report();
         CallFrame currentFrame = this.callstack.peek();
         switch (currentFrame.getStmt()) {
            case 0:
               if (currentFrame.n == 1) {
                  this.returnValue = currentFrame.r;
                  this.callstack.pop();
               }
               break;
            case 1:
               CallFrame frame = new CallFrame(currentFrame.n - 1,
                                               currentFrame.n * currentFrame.r);
               this.callstack.push(frame);
               break;
            case 2:
               this.returnValue = this.returnValue;
               this.callstack.pop();
               break;
         }
         currentFrame.advance();
      }
      this.report();
      return this.returnValue;
   }
}


class Homework6 {
   private static void runSim(boolean tailRecursive, int n) {
      int simResult;
      int actualResult;
      if (tailRecursive) {
         System.out.println("--- TAIL-RECURSIVE SIMULATION");
         FactorialSimulation sim = new FactorialSimulation(n, 1);
         simResult = sim.runTR();
         actualResult = sim.factorialTR(n, 1);
      }
      else {
         System.out.println("--- RECURSIVE SIMULATION");
         FactorialSimulation sim = new FactorialSimulation(n);
         simResult = sim.run();
         actualResult = sim.factorial(n);
      }
      if (simResult == actualResult) {
         System.out.println("\nResults matched.");
      }
      else {
         System.out.println("\nResults DID NOT match!  Simulated = "
                            + simResult + ", actual = " + actualResult);
      }
      System.out.println();
   }

   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      while (in.hasNextInt()) {
         int n = in.nextInt();
         runSim(false, n);
         runSim(true, n);
      }
   }
}
