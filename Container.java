
public interface Container
{
   /**
      Checks if this container is empty
      @return true if this container is empty
   */
   boolean isEmpty();

   /**
     Makes this container empty
   */
   void makeEmpty();
   
   /**
      Returns the size of this container
      @return  the number of items in this container
   */
   int size();
}
