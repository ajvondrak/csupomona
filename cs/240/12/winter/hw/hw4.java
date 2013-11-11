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


class ArrayStack<E> implements Stack<E> {
   private static final int INITIAL_CAPACITY = 10;
   private E[] data;
   private int top;

   public ArrayStack() { 
      this.data = (E[]) new Object[this.INITIAL_CAPACITY];
      this.top = -1;
   }

   public int size() {
      return this.top + 1;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public E peek() throws StackUnderflowException {
      if (this.isEmpty()) {
         throw new StackUnderflowException();
      }
      return this.data[this.top];
   }

   public E pop() throws StackUnderflowException {
      E result = this.peek();
      this.data[this.top] = null;
      this.top--;
      return result;
   }

   private void grow() {
      E[] biggerArray = (E[]) new Object[2 * this.data.length + 1];
      for (int i = 0; i < this.data.length; i++) {
         biggerArray[i] = this.data[i];
      }
      this.data = biggerArray;
   }

   public void push(E item) {
      if (this.size() == this.data.length) {
         this.grow();
      }
      this.top++;
      this.data[this.top] = item;
   }

   public String toString() {
      String s = "";
      for (int i = 0; i < this.size(); i++) {
         s += this.data[i] + " ";
      }
      return s;
   }
}
