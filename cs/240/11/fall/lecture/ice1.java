import java.io.*;
import java.util.*;

class wc {
   public static void main(String[] args) throws Exception {
      if (args.length == 2 && args[0].equals("-l"))
         System.out.println(countLines(args[1]) + "");
      else if (args.length == 2 && args[0].equals("-w"))
         System.out.println(countWords(args[1]) + "");
      else {
         System.err.println("Usage: java wc [-l|-w] filename");
         System.exit(1);
      }
   }

   public static int countWords(String filename) throws Exception {
      Scanner input = new Scanner(new File(filename));
      int count = 0;
      while (input.hasNext()) {
         input.next();
         count++;
      }
      return count;
   }

   public static int countLines(String filename) throws Exception {
      Scanner input = new Scanner(new File(filename));
      int count = 0;
      while (input.hasNextLine()) {
         input.nextLine();
         count++;
      }
      return count;
   }
}
