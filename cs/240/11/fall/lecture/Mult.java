class Mult {
   public static void main(String[] args) {
      System.out.println("tail_recursive_mult(3, 4, 0): " + 
                         tail_recursive_mult(3, 4, 0));
   }

   public static int mult(int m, int n) {
      // Base case
      if(n==0)
         return 0;

      // Recursive case
      return m + mult(m, n - 1);
   }

   public static int tail_recursive_mult(int m, int n, int product) {
      // Base case
      if(n==0)
         return product;

      // Recursive case
      return tail_recursive_mult(m, n - 1, product + m);
   }
}

