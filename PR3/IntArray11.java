/**
 * Provide a set of Array functions on an Array of ints
 * @author Busheng LOU
 * @version CSE11-Winter2015-PR3
 */
public class IntArray11
{        
        private static int[] array;
	private static int array_len = 0;
	/** 
 	 * 0-argument constructor. Valid instance of an Array of int,
 	 * no ints are stored in the array.
	*/
	public IntArray11()
	{
	  array = new int[0];
	  array_len = 0;
	}

	/** 
 	 * Store an array of size n. Initialize contents of the array to 
 	 * be 1..n 
 	 * @param size the number of elements to store in the array
	*/
	public IntArray11(int size)
	{
	  array = new int[size];
	  int i = 0;
	  while(i != size) {
	    array[i] = i + 1;
	    i++;
	  }
	  array_len = size;
	}

	/** 
 	 * Create an array of size n and store a copy of the contents of the
 	 * input argument
 	 * @param intArray array of elements to copy 
	*/
	public IntArray11(int[] intArray)
	{ 
	  int i = 0, length = intArray.length;
	  array = new int[length];
	  while(i != length) {
	    array[i] = intArray[i];
	    i++;
	  }
	  array_len = intArray.length;
	}

	/* Make a string representation */
	/**
	 * Pretty Print  -- Empty String "[]"
	 *                  else "[e1, e2, ..., en]"
	 */
	@Override
	public String toString()
	{ 
          String rel = new String();  
	  if (array.length == 0) {
            rel = "[]";  
	  } else {
	    int i = 0;
	    rel ="[";
	    rel = rel.concat(Integer.toString(array[i]));
	    while (i++ != (array.length - 1)) {
	      rel = rel.concat(", ");
	      rel = rel.concat(Integer.toString(array[i]));
	    }
	    rel = rel.concat("]");
	  }
	  return rel; 
	}

	/* Getters and Setters */

	/** get the number of elements stored in the array  
	 * @return number of elements in the array
	*/
	public int getNelem()
	{
          return array.length; 
	}
	/** get the Element at index  
	 * @param index of data to retrieve 
	 * @return element if index is valid else  return 
	 * 		Integer.MIN_VALUE
	*/
	public int getElement(int index)
	{ 
	  if (index < array.length) {
	    return array[index];
	  } else { 
	  return Integer.MIN_VALUE;	
	  }
	}
	
	/** retrieve a copy of the stored Array
	 * @return a deep copy of the Array. A new int array should be
	 * 		constructed of the correct size and values should
	 * 		copied into it.  
	*/
	public int[] getArray()
	{  
	  int i = 0;
	  int[] rel_array = new int[array_len];
	  while (i != array_len) {
	    rel_array[i] = array[i];
	    i++;
	  }
          return (int []) rel_array;
 	}

	/** set the value of an element in the stored array
	 * @param index of element to store. Must be a valid index 
	 * @param element the data to insert in the array
	 * @return true if element set was successful
	*/
	public boolean setElement(int index, int element)
	{ 
	  if (index < array.length && index >=0) {
	    array[index] = element;
	    return true;
	  } else {
	    return false;
	  }
	}

	/** Insert an element at index in the array
	 * @param index where to insert. Must be between 0 and number of
	 *              elements in the array
	 * @param element the data to insert in the array
	 * @return true if element insertion was successful
	*/
	public boolean insert(int index, int element)
	{ 
	  if (index < array.length && index >= 0) {
	    int[] temp = this.getArray();	    
	    array_len = array_len + 1;
	    array = new int[array_len];
	    int i = 0;
	    while (i != index) {
	      array[i] = temp[i];
	      i++;
	    }
	    array[i] = element;
	    while (i != (array_len - 1)) {
	      array[i + 1] = temp[i]; 
	      i++;
	    }
	    return true;
	  } else { 
            return false;
	  }
	}

	/** Delete and element at index
	 * @param index of element to delete 
	 * @return true if element deletion was successful, false otherwise
	*/
	public boolean delete(int index)
	{
	  if (index <array.length) {
	    int i = index;
	    while (i != (array.length - 1)) {
	      array[i] = array[i+1];
	      i++;
	    }
	    array_len = array.length - 1;
	    array = this.getArray();
            return true; 
	  } else {
	    return false;
	  }
	}

	/** reverse the order of the elements in the array 
	*/
	public void reverse()
	{
	  this.reverse(0, array.length-1);
	  /*
          int i = 0;
	  for ( ; i < array.length / 2; i++) {
	    int temp = array[i];
	    array[i] = array[array.length - i - 1];
	    array[array.length - 1 - i] = temp;
	  }
	  */
	}

	/** reverse the order of the elements in the array from start to
 	*   end index 
	*   @param start beginning index of to start the reverse
	*   @param end	ending index to end the reverse
	*   @return true if start and end index are valid, false otherwise
	*
	*/
	public boolean reverse(int start, int end)
	{
	  if (end < array.length && start <= end && start >=0) {
	    int len = end - start + 1, i = 0;
	    for ( ; i < len/2; i++) {
	      int temp = array[i + start];
	      array[i + start] = array[start + len - i - 1];
	      array[start + len - i - 1] = temp;
	    }
	    return true;
	  } else {
	    return false;
	  }
	}

	/** swap two elements in the array 
	*   @param index1 index of first element 
	*   @param index2 index of second element
	*   @return true if index1 and index2 are valid, false otherwise
	*
	*/
	public boolean swap(int index1, int index2)
	{
	  if (index1 >=0 && index1 < array.length && index2 >= 0 && index2 <
	    array.length) {
	    int temp= array[index1];
	    array[index1] = array[index2];
	    array[index2] = temp;
	    return true;
	  } else {
            return false;
	  }
	}
}
// vim: ts=4:sw=4:tw=78:
