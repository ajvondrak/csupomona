class InitializationExamples {
   /* For more info, see
    * download.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
    */
   
   // Primitive Java data types
   static byte    uninitialized_byte;    // default value = 0
   static short   uninitialized_short;   // default value = 0
   static int     uninitialized_int;     // default value = 0
   static long    uninitialized_long;    // default value = 0L
   static float   uninitialized_float;   // default value = 0.0f
   static double  uninitialized_double;  // default value = 0.0d
   static boolean uninitialized_boolean; // default value = false
   static char    uninitialized_char;    // default value = '\u0000'

   // Other data types
   static String uninitialized_String; // default value = null
   static int[]  uninitialized_array;  // default value = null
   /* any Object */                    // default value = null

   public static void main(String[] args) {
      System.out.println("byte " + uninitialized_byte);
      System.out.println("short " + uninitialized_short);
      System.out.println("int " + uninitialized_int);
      System.out.println("long " + uninitialized_long);
      System.out.println("float " + uninitialized_float);
      System.out.println("double " + uninitialized_double);
      System.out.println("boolean " + uninitialized_boolean);
      System.out.println("char " + uninitialized_char);
      System.out.println("String " + uninitialized_String);
      System.out.println("array " + uninitialized_array);

      // Initialized array, uninitialized elements
      int[] initialized_array = new int[3];

      for (int i = 0; i < initialized_array.length; i++)
         System.out.println("initialized_array[" + i + "] = " +
                            initialized_array[i]);
   }
}
