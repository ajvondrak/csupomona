class QueueUnderflowException extends RuntimeException {
   public QueueUnderflowException() {
      super("Queue underflow.");
   }
}


interface Queue<E> {
   public void enqueue(E item);

   public E dequeue() throws QueueUnderflowException;

   public E peek() throws QueueUnderflowException;
}


class ArrayQueue<E> implements Queue<E> {
   private static final int INITIAL_CAPACITY = 10;
   private int front;
   private int rear;
   private int hugeness;
   private E[] data;

   public ArrayQueue() {
      this.data = (E[]) new Object[this.INITIAL_CAPACITY];
      this.hugeness = 0;
      this.front = this.data.length;
      this.rear = this.data.length;
   }

   public int size() {
      return this.hugeness;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   private int nextIndex(int i) {
      if (i == 0) {
         return this.data.length - 1;
      }
      return i - 1;
   }

   public void enqueue(E item) {
      if (this.size() == this.data.length) {
         this.grow();
      }
      this.rear = this.nextIndex(this.rear);
      if (this.isEmpty()) {
         this.front = this.rear;
      }
      this.data[this.rear] = item;
      this.hugeness++;
   }

   private boolean isWrappedAround() {
      return this.rear > this.front;
   }

   private void grow() {
      E[] biggerArray = (E[]) new Object[2 * this.data.length + 1];
      int biggerArrayIndex = biggerArray.length - 1;
      for (int i = this.front; i != this.rear; i = this.nextIndex(i)) {
         biggerArray[biggerArrayIndex] = this.data[i];
         biggerArrayIndex--;
      }
      biggerArray[biggerArrayIndex] = this.data[this.rear];
      this.data = biggerArray;
      this.front = biggerArray.length - 1;
      this.rear = biggerArrayIndex;
   }

   /*
   private void grow() {
      E[] biggerArray = (E[]) new Object[2 * this.data.length + 1];
      int biggerArrayIndex = biggerArray.length - 1;
      if (this.isWrappedAround()) {
         // Phase 1: copy from this.data[this.front] to this.data[0]
         for (int i = this.front; i >= 0; i--) {
            biggerArray[biggerArrayIndex] = this.data[i];
            biggerArrayIndex--;
         }
         // Phase 2: copy from this.data[this.data.length - 1] to this.rear
         for (int i = this.data.length - 1; i >= this.rear; i--) {
            biggerArray[biggerArrayIndex] = this.data[i];
            biggerArrayIndex--;
         }

      }
      else {
         for (int i = this.front; i >= this.rear; i--) {
            biggerArray[biggerArrayIndex] = this.data[i];
            biggerArrayIndex--;
         }
      }
      this.data = biggerArray;
      this.rear = biggerArrayIndex + 1;
      this.front = biggerArray.length - 1;
   }
   */

   public E dequeue() throws QueueUnderflowException {
      E result = this.peek();
      this.data[this.front] = null;
      this.front = this.nextIndex(this.front);
      this.hugeness--;
      return result;
   }

   public E peek() throws QueueUnderflowException {
      if (this.isEmpty()) {
         throw new QueueUnderflowException();
      }
      return this.data[this.front];
   }

   public String toString() {
      String s = "";
      for (int i = 0; i < this.data.length; i++) {
         s += this.data[i] + " ";
      }
      return s;
   }
}


class Testing {
   public static void main(String[] args) {
      ArrayQueue<Integer> q = new ArrayQueue<Integer>();
      for (int i = 1000; i < 2000; i += 100) {
         q.enqueue(i);
         System.out.println("enqueue " + i + ": " + q);
      }
      q.dequeue();
      q.enqueue(9999);
      q.enqueue(8888);
      System.out.println("EXPERIMENT: " + q);
      while (!q.isEmpty()) {
         q.dequeue();
         System.out.println("dequeue:       " + q);
      }
   }
}
