
/**  
   Implements a stack data structure with Last In First Out data policy. 
   When a stack is first created, it contains no items. 
   
   @typeparam T the parametric type of the stack items
   @see EmptyStackException
*/
public class S<T> implements Stack<T> // Generic stack ADT
{
   // constants
   private static final int INITIAL_CAPACITY = 1;
   
   // instance variables
   private Object[] v;    // note: array of Object needed
   private int vSize;     // counter of stack items
   
   /**
      Construct a new empty stack
   */
   public S()
   {
      makeEmpty();
   }

   /**
      Checks if this stack is empty
      @return true if this stack is empty
   */
   public boolean isEmpty()
   {
      return vSize <= 0;
   }

   /**
     Makes this stack empty
   */
   public void makeEmpty()
   {
      v = new Object[INITIAL_CAPACITY];
      vSize = 0;
   }
  
   /**
      Removes the object at the top of this stack and returns it
      @return the object at the top of this stack
      @throws EmptyStackException if this stack is empty
   */
   public T pop() throws EmptyStackException
   {    
      // store item to remove
      T toRemove = top();     // note: check on the empty stack is demanded to
                              // method top
      
      // remove item
      v[vSize - 1] = null;
      
      // decrement item counter
      vSize--;
      
      // return removed item
      return toRemove;
   }
   
   /**
      Pushes an item onto the top of this stack, if not null
      @param item the item to be pushed onto this stack
      @throws IllegalArgumentException if the item to be pushed is null
   */
   public void push(T item) // O(n) in worst case
   {
      // preconditions
      if (item == null)
      {
         throw new IllegalArgumentException();
      }

      // dynamic resize if needed
      if (vSize >= v.length)
      {
         Object[] newV = new Object[2 * v.length];
         System.arraycopy(v, 0, newV, 0, v.length);
         v = newV;
      }
      
      // push of new item
      v[vSize] = item;
      
      // increment of item counter
      vSize++;
   }
   
   /**
      Returns the position of an object in the stack. If the object x is present
      in the stack, the method returns the distance from the top of the stack to
      the nearest occurrence of x. The topmost item on the stack is considered
      to be at distance 0.
      @param x the desired object
      @return the position from the top of the stack where the object is
              located; -1 indicates that the object is not in this stack.    
   */
   public int search(T x)
   {
      int  topIndex = vSize -1;
      
          // index of topmost element
      for (int i = topIndex; i >= 0; i--)
      {
         if (v[i].equals(x))
         {
            return topIndex - i; // Object is found at index i
         }
      }
      
      return -1;                 // object is not in this stack
   }  
   
   /**
      Returns the size of this container
      @return  the number of items in this container
   */
   public int size()
   {
      return vSize;
   }
  
   /**
      Looks at the object at the top of this stack without removing it
      @return the object at the top of this stack
      @throws EmptyStackException if this stack is empty
   */
   public T top() throws EmptyStackException
   {
      // preconditions
      if (isEmpty())
      {
         throw new EmptyStackException();
      }
      
      return (T)v[vSize - 1];  // note: type cast is necessary
   }
}
