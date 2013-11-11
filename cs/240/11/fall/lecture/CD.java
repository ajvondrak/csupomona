class C {
   public static void main(String[] args) {
      D d = new D();
      d.hello();
   }
}

class D {
   public void hello() { System.out.println("Hello, world (from D)"); }
}
