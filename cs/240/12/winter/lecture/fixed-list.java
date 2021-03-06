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


class ListIndexOutOfBoundsException extends RuntimeException {
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
      if (index == 0) {
         this.addToFront(item);
      }
      else {
         Node<E> cursor = this.nodeAt(index - 1);
         cursor.link = new Node<E>(item, cursor.link);
         this.hugeness++;
      }
   }

   private Node<E> nodeAt(int index) {
      if (index < 0 || index >= this.size()) {
         throw new ListIndexOutOfBoundsException();
      }
      Node<E> cursor = this.head;
      for (int i = 0; i < index; i++) {
         cursor = cursor.link;
      }
      return cursor;
   }

   public E itemAt(int index) {
      return this.nodeAt(index).data;
   }

   public int count(E item) {
      int howMany = 0;
      Node<E> cursor;
      for (cursor = this.head;
           cursor != null;
           cursor = cursor.link) {
         if (cursor.data.equals(item)) {
            howMany++;
         }
      }
      return howMany;
   }

   public void reverse() {
      Node<E> reversed = null;
      Node<E> cursor;
      for (cursor = this.head;
           cursor != null;
           cursor = cursor.link) {
         reversed = new Node<E>(cursor.data, reversed);
      }
      this.head = reversed;
   }

   // replace front/back/arbitrary
   // removing elements back/arbitrary
   // swap
}


class Test {
   public static void main(String[] args) {
      LinkedList<Integer> xs = new LinkedList<Integer>(8, 6, 7, 5, 3, 0, 9);
      for (int i = 0; i < xs.size(); i++) {
         xs = new LinkedList<Integer>(8, 6, 7, 5, 3, 0, 9);
         xs.addItem(10, i);
         System.out.println("xs.addItem(10, " + i + ") = " + xs);
      }
      xs = new LinkedList<Integer>(8, 6, 7, 5, 3, 0, 9);
      for (int i = 0; i <= xs.size(); i++) {
         System.out.print("xs.itemAt(" + i + ") = ");
         System.out.println(xs.itemAt(i));
      }
   }
}
