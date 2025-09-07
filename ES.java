
public class ES<T extends Comparable> extends S<T>
{  
   /**
      constructs a new extended stack containing the elements of the specified
      array a, in which the element are in LIFO sequence with the top of the
      extended stack at index length - 1. If the specified array is null, this
      constructor constructs an empty extended stack.
      @param a the specified array containing the elements to be inserted into
             this extended stack

   */
   public ES(T[] a)
   {
      // invoke constructor of superclass
      super();
      
      // preconditions
      if (a == null)
      {
         return;
      }
      
      // insertion of elements into the extended stack 
      for (int i = 0; i < a.length; i++)
      {
         push(a[i]);
      }
   }

   @Override
   /**
      Overrides the superclass method of the same name.
      It behaves in the same way as the method in the superclass, but it is
      implemented through a recursive approach.
   */
   public int search(T x)
   {    
      return search(x, 0);  
   }
   
   private int search(T x, int index)
   {
      // base case - stack is empty
      if (isEmpty())
      {
         return -1;
      }
      
      // base case - element is found on top
      if (top().equals(x))
      {
         return index;
      }
      
      // recursive step
      T top = pop();                    // extract current top
      int ret =  search(x, index + 1);  // search in stack for item
      push(top);                        // re-insert the previously popped item
      
      return ret;  
   }
   /**
      Returns an array containing all the elements in this stack in the LIFO
      sequence, where the topmost item is at index 0
      @return array of stack items in LIFO sequence or an empty array if this
              stack is empty
   */
   public Comparable[] toArray()
   {
      // creation of return array
      Comparable[] r = new Comparable[size()];
      
      // temporary stack
      Stack<T> s = new S();
      
      // inspect and extract items
      int i = 0;
      while (!isEmpty())
      {
         r[i++] = top();
         s.push(pop());
      }
      
      // re-insert items
      while (!s.isEmpty())
      {
         push(s.pop());
      }     
      
      // return sorted array
      return r;
   } 
   
   /**
      Returns an array containing all the elements in this stack sorted in
      ascending order according to their natural ordering
      @return array sorted in ascending order according to their natural
              ordering or an empty array if this stack is empty
   */
   public Comparable[] toSortedArray()
   {
      // get stack items
      Comparable[] w = toArray();
      
      // sort
      sort(w);
      
      return w;
   }  
       
   // sorting
   private static void sort(Comparable[] a)
   {
      // base case - the array is already sorted 
      if (a.length < 2)
      {
         return;
      }
      
      // index of middle element
      int mid = a.length / 2;
      
      // create left and right arrays and copy items
      Comparable[] left = new Comparable[mid];
      Comparable[] right = new Comparable[a.length - mid];
      System.arraycopy(a, 0, left, 0, left.length);
      System.arraycopy(a, mid, right, 0, right.length);
      
      // sort by recursive steps
      sort(left);
      sort(right);
      
      // merge
      merge(a, left, right);
   }
   
   // merge
   private static void merge(Comparable[] a, Comparable[] b, Comparable[] c)
   {
      int ia = 0, ib = 0, ic = 0;
      
      // insert in order 
      while (ib < b.length && ic < c.length)
      {
         if (b[ib].compareTo(c[ic]) < 0)
         {         
            a[ia++] = b[ib++];
         }
         else
         {
            a[ia++] = c[ic++];
         }
      } 
      
      // insert any remaining items
      while (ib < b.length)
      {
         a[ia++] = b[ib++];      
      } 
     
      while (ic < c.length)
      {
         a[ia++] = c[ic++];      
      }           
   }         
}
