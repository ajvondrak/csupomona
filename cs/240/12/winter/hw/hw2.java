class Function implements Comparable {
   public static final int MIN_ID = 0;
   public static final int MAX_ID = 8;

   private int id;
   private double n;

   public Function(int id) {
      if (this.MIN_ID > id || id > this.MAX_ID) {
         throw new IllegalArgumentException("Function ID out of range: " + id);
      }
      this.id = id;
   }

   public double value() {
      switch(this.id) {
         case 0: return this.n;
         case 1: return Math.sqrt(this.n);
         case 2: return Math.log(this.n);
         case 3: return Math.log(Math.log(this.n));
         case 4: return Math.pow(Math.log(this.n), 2);
         case 5: return this.n / Math.log(this.n);
         case 6: return Math.pow(1.0 / 3.0, this.n);
         case 7: return Math.pow(3.0 / 2.0, this.n);
         case 8: return 17;
         default: throw new RuntimeException("Bad Function ID: " + this.id);
      }
   }

   public String toString() {
      return "f" + this.id;
   }

   public void setN(double n) {
      this.n = n;
   }

   public int compareTo(Object that) {
      /* IMPLEMENT ME */
   }
}
