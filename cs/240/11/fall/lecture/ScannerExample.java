import java.io.*;
import java.util.Scanner;

class Example {
   public static void main(String [] args) throws Exception {
      Scanner kb = new Scanner(System.in);
      report();
      while (true) {
         Student s = Student.read(System.out, kb);
         // we don't say
         // Student s = new Student("", 0);
         // s.read(System.out, kb);
         if (s.getName().equals("quit")) return;
         // Can't say
         // if (s.name.equals("quit")) return;
         report();
      }
   }

   private static void report() {
      System.out.println("\nWe have read " +
                         Student.getCount() +
                         " Students.\n");
   }
}

class Student {
   private String name;
   private int grade;
   private static int count;

   public Student(String name, int grade) {
      this.name = name;
      this.grade = grade;
      count++;
   }

   public static Student read(PrintStream ps, Scanner sc) throws Exception {
      ps.println("Reading a Student record...");
      ps.print("Enter the name:  ");
      String name = sc.nextLine();
      ps.print("Enter the grade: ");
      int grade = sc.nextInt();
      sc.nextLine(); // gobble trailing \n after hitting Enter at the prompt
      return new Student(name, grade);
   }

   public static int getCount() { return count; }

   public String getName() { return name; }
}
