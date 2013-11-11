public class Throttle
{
   private int top;      // The topmost position of the throttle
   private int position; // The current position of the throttle

   public Throttle(int size)
   {
      if (size <= 0)
         throw new IllegalArgumentException("Size <= 0: " + size);
      top = size;
      position = 0;
   }

   public double getFlow()
   {
      return (double) position / (double) top;
   }

   public boolean isOn()
   {
      return (position > 0);
   }

   public void shift(int amount)
   {
      if (position + amount > top) position = top;
      else if (position + amount < 0) position = 0;
      else position += amount;
   }

   public void shutoff ()
   {
      position = 0;
   }
}
