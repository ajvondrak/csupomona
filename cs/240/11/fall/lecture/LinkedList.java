class EmptyListException extends Exception {
}

class IndexOutOfBoundsException extends Exception {
}

class List<E> {
   private Node<E> head;

   // O(n)
   public List(E... elements) {
      head = null;

      for(int i = elements.length - 1; i >= 0; i--) {
         head = new Node<E>(elements[i], head);
      }
   }

   // O(n) -- assuming string concatenation is O(1)
   public String toString() {
      String result = "( ";
      Node<E> current = head;

      while(current != null) {
         result += current.data + " ";
         current = current.link;
      }

      return result + ")";
   }

   // O(1)
   public void addToFront(E something) {
      head = new Node<E>(something, head);
   }

   // O(1)
   public E removeFromFront() throws EmptyListException {
      if (head == null)
         throw new EmptyListException();
      E tmp = head.data;
      head = head.link;
      return tmp;
   }

   // O(n)
   public int length() {
      return length(head, 0);
   }

   private int length(Node<E> xs, int total) {
      if (xs == null)
         return total;
      return length(xs.link, total+1);
   }

   // O(n)
   public E atIndex(int index) throws IndexOutOfBoundsException {
      if (index < 0 || index >= length())
         throw new IndexOutOfBoundsException();

      Node<E> current = head;

      for(int i = 0; i < index; i++) {
         current = current.link;
      }

      return current.data;
   }

   private class Node<E> {
      E       data;
      Node<E> link;

      public Node(E data, Node<E> link) {
         this.data = data;
         this.link = link;
      }
   }
}

class Main {
   public static void main(String[] args) throws Exception {
      List<Integer> xs = new List<Integer>(8, 6, 7, 5, 3, 0, 9);
      List<String> ys = new List<String>("a", "b", "c");

      System.out.println("xs = " + xs);
      xs.addToFront(11);
      System.out.println("xs.addToFront(11) = " + xs);
      int x = xs.removeFromFront();
      System.out.println("xs now = " + xs);
      System.out.println("x = " + x);

      System.out.println("xs[2] = " + xs.atIndex(2));

      System.out.println("ys = " + ys);
      ys.addToFront("dog");
      ys.addToFront("doggies");
      ys.addToFront("e");
      // ys.addToFront(52); // can't do that
      System.out.println("now ys = " + ys);
   }
}
