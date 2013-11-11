class Nullary {
   public static Integer maybeNull(boolean returnNull) {
      if (returnNull) {
         return null;
      }
      return new Integer(5);
   }

   public static void main(String[] args) {
      int i = maybeNull(true);
   }
}
