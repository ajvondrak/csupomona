class StackUnderflowException extends RuntimeException {
   public StackUnderflowException() {
      super("Stack underflow.");
   }
}


interface Stack {
   public void push(int item);

   public int pop() throws StackUnderflowException;

   public int peek() throws StackUnderflowException;
}


class ArrayStack implements Stack {
   private static final int INITIAL_CAPACITY = 10;
   private int[] data;
   private int top;

   public ArrayStack() { 
      this.data = new int[this.INITIAL_CAPACITY];
      this.top = -1;
   }

   public int size() {
      return this.top + 1;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public int peek() throws StackUnderflowException {
      if (this.isEmpty()) {
         throw new StackUnderflowException();
      }
      return this.data[this.top];
   }

   public int pop() throws StackUnderflowException {
      int result = this.peek();
      this.top--;
      return result;
   }

   private void grow() {
      int[] biggerArray = new int[2 * this.data.length + 1];
      for (int i = 0; i < this.data.length; i++) {
         biggerArray[i] = this.data[i];
      }
      this.data = biggerArray;
   }

   public void push(int item) {
      if (this.size() == this.data.length) {
         this.grow();
      }
      this.top++;
      this.data[this.top] = item;
   }
}
