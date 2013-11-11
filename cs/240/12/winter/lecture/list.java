class Node<E> {
   public E data;
   public Node<E> link;

   public Node(E data, Node<E> link) {
      this.data = data;
      this.link = link;
   }
}


class EmptyListException extends RuntimeException {
}


class LinkedList<E> {
   private Node<E> head;
   private int hugeness;

   // This constructor uses varargs
   public LinkedList(E... data) {
      // data has the type E[]
      this.head = null;
      this.hugeness = 0;
      for (int i = data.length - 1; i >= 0 ; i--) {
         this.addToFront(data[i]);
      }
   }

   public void addToFront(E item) {
      this.head = new Node<E>(item, this.head);
      this.hugeness++;
   }

   public E removeFromFront() {
      if (this.isEmpty()) {
         throw new EmptyListException();
      }
      E result = this.head.data;
      this.head = this.head.link;
      this.hugeness--;
      return result;
   }

   public String toString() {
      String s = "";
      Node<E> cursor;
      for (cursor = this.head;
           cursor != null;
           cursor = cursor.link) {
         s += cursor.data + " ";
      }
      return s;
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   // O(1) size()
   public int size() {
      return this.hugeness;
   }

   // O(n) size()
   /*
   public int size() {
      int hugeness = 0;
      Node<E> cursor = this.head;
      for (; cursor != null; cursor = cursor.link) {
         hugeness++;
      }
      return hugeness;
   }
   */

   public void addItem(E item, int index) {
      // todo: check the bounds of index
      Node<E> cursor = this.head;
      int position = 0;
      while ((cursor != null) && (position < index - 1)) {
         position++;
         cursor = cursor.link;
      }
      // Add AFTER cursor
      cursor.link = new Node<E>(item, cursor.link);
      this.hugeness++;
   }

   // addToBack
   // replace front/back/arbitrary
   // removing elements back/arbitrary
   // itemAt
   // swap
   // reverse
   // count occurrences of item
}


class Test {
   public static void main(String[] args) {
      LinkedList<Integer> xs;
      xs = new LinkedList<Integer>(3, 1, 4, 1, 5);
      System.out.println(xs + " has size " + xs.size());
      xs.addItem(9 /* item */, 2 /* index */);
      System.out.println("afterwards: " + xs);
   }
}
