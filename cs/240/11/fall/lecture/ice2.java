import java.util.*;

class RunningSum {
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      int sum = 0;
      while (true) {
         System.out.println("Sum = " + sum);
         System.out.print("Enter a number to add to the sum: ");
         sum += in.nextInt();
      }
   }
}
