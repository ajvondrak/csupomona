import java.util.*;

class RunningTime {
   public static boolean search(int needle, int[] haystack) {
      for (int element : haystack)
         if (needle == element)
            return true;
      return false;
   }

   public static void time9(int needle, int[] haystack) {
      for(int i = 1; i <= 9; i++) {
         long now = System.currentTimeMillis();
         search(needle, haystack);
         long end = System.currentTimeMillis();
         System.out.printf("Run %s took %sms.\n", i, (end - now));
      }
   }

   public static void main(String[] args) {
      Random r = new Random();

      int needle = r.nextInt();

      int[] haystack = new int[100000000];
      for(int i = 0; i < haystack.length; i++)
         haystack[i] = r.nextInt();

      System.out.println("--- Haystack = 100,000,000 random ints");
      System.out.println("--- Needle = " + needle);
      
      time9(needle, haystack);
   }
}
