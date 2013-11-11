class BinarySearch {
   public static boolean search(int needle,
                                int[] haystack,
                                int left,
                                int right)
   {
      int middle = (left + right) / 2;
      System.out.println("left = " + left + " right = " + right);
      System.out.println("middle index = " + middle);
      System.out.println("middle element = " + haystack[middle]);
      System.out.println();
      if (left > right) {
         return false;
      }
      if (haystack[middle] == needle) {
         return true;
      }
      if (haystack[middle] > needle) {
         return search(needle, haystack, left, middle - 1);
      }
      // if (haystack[middle] < needle) {
      return search(needle, haystack, middle + 1, right);
      // }
   }

   public static void main(String[] args) {
      int[] haystack = new int[100];
      for (int i = 0; i < 100; i++) {
         haystack[i] = i + 1;
      }

      int needle = 106;

      if (search(needle, haystack, 0, 99)) {
         System.out.println("Found the needle");
      }
      else {
         System.out.println("Did not find the needle");
      }
   }
}
