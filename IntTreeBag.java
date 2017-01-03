//Jose Mora
//CIS 256
//Project 5

import java.util.*;

public class IntTreeBag implements Cloneable
{
   // Invariant of the IntTreeBag class:
   //   1. The elements in the bag are stored in a binary search tree.
   //   2. The instance variable root is a reference to the root of the
   //      binary search tree (or null for an empty tree).
   private IntBTNode root;   
   /**
   * Insert a new element into this bag.
   * @param <CODE>element</CODE>
   *   the new element that is being inserted
   * <dt><b>Postcondition:</b><dd>
   *   A new copy of the element has been added to this bag.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory a new IntBTNode.
   **/
   public void add(int element)
   {  
      IntBTNode cursor = root; //cursor is root initially
      boolean done = false;
    
      try {
      if (root == null) { //if root is null
         root = new IntBTNode(element, null, null);
      } else {       
         while (!done){
            if (cursor.getData() >= element) {  //tests value to data of cursor
               if (cursor.getLeft() == null) { // if empty
                  cursor.setLeft( new IntBTNode(element, null, null) ); //set value to left side of subtree
                  done = true;
               } else {
                  cursor = cursor.getLeft(); //keep going left
                 } 
           } else { 
               if (cursor.getRight() == null) { //if empty
                  cursor.setRight( new IntBTNode(element, null, null)); // set value to right subtree
                  done = true; //stop going
               } else {
                  cursor = cursor.getRight();//keep going 
                }
             } 
          }      
        }
      } catch(OutOfMemoryError e ) { //java out of memory
    	  e.printStackTrace();
        }
   }
     

   /**
   * Add the contents of another bag to this bag.
   * @param <CODE>addend</CODE>
   *   a bag whose contents will be added to this bag
   * <dt><b>Precondition:</b><dd>
   *   The parameter, <CODE>addend</CODE>, is not null.
   * <dt><b>Postcondition:</b><dd>
   *   The elements from <CODE>addend</CODE> have been added to this bag.
   * @exception IllegalArgumentException
   *   Indicates that <CODE>addend</CODE> is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of the bag.
   **/
   public void addAll(IntTreeBag addend)
   {
      IntBTNode addroot;

      try {
    	  addTree(addend.root); 
      } catch (OutOfMemoryError e) { //if java runs out of memory
    	  e.printStackTrace();
      } catch (IllegalArgumentException e) { //illegal input
    	  e.printStackTrace();
      } if (root == addend.root) {  
         addroot = IntBTNode.treeCopy(addend.root);
         addTree(addroot);
      } 
   }

   private void addTree(IntBTNode addroot) //add new tree
   {
      if(addroot != null) {
    	  add(addroot.getData()); //get root data
    	  addTree(addroot.getLeft()); //traverse left subtree
    	  addTree(addroot.getRight()); //traverse right subtree
      }     
   }
   
   /**
   * Generate a copy of this bag.
   * @param - none
   * @return
   *   The return value is a copy of this bag. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an <CODE>IntTreeBag</CODE> before it can be used.
   * @throw OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public Object clone() //clone of bag
   {  
      IntTreeBag answer = null;    
         try {
			answer = (IntTreeBag) super.clone();
		} catch (CloneNotSupportedException e) { //if cannot clone
			e.printStackTrace();
		} catch(OutOfMemoryError e) {//if java out of memory
			e.printStackTrace();
		}
      answer.root = IntBTNode.treeCopy(root);   //makes copy of tree  
      return answer;
   }
   
   /**
   * Accessor method to count the number of occurrences of a particular element
   * in this bag.
   * @param <CODE>target</CODE>
   *   the element that needs to be counted
   * @return
   *   the number of times that <CODE>target</CODE> occurs in this bag
   **/
   public int countOccurrences(int target) //count occurrences of int value
   {
	   IntBTNode cursor = root; //cursor initially at root
	   int answer = 0;
	 	   
      while (cursor != null) { //while cursor does not have a null value
         if (cursor.getData() < target) {
            cursor = cursor.getRight();
         } else {
            if (cursor.getData() == target) { //if data same as the target
               answer++; //add number of occurrences 
            cursor = cursor.getLeft();
            }
         }
      }
      return answer; //return occurrence number 
   }
   
             
   /**
   * Remove one copy of a specified element from this bag.
   * @param <CODE>target</CODE>
   *   the element to remove from the bag
   * <dt><b>Postcondition:</b><dd>
   *   If <CODE>target</CODE> was found in the bag, then one copy of
   *   <CODE>target</CODE> has been removed and the method returns true. 
   *   Otherwise the bag remains unchanged and the method returns false. 
   **/

   public boolean remove(int target)
   {
      IntBTNode parent = null; //parent initially null
      IntBTNode cursor = root; //cursor starts at root
      
      while (cursor != null && target != cursor.getData()) { //while data isn't null and cursor is not the same value as cursor
         parent = cursor; //cursor is put into parent
         if (target < cursor.getData()) {
            cursor = cursor.getLeft(); //if target is less than data, go left
         } else {
            cursor = cursor.getRight();//else go right 
         }
      }
      
      if (cursor ==  null) {//if cursor is null
         return false;
      } else if (cursor.getLeft() == null) { //if left data is null
         if (parent == null) {//and the parent is null
            root = cursor.getRight(); //go right
         } else if (cursor == parent.getLeft()) {
            parent.setLeft(cursor.getRight()); //set left
         } else {
            parent.setRight(cursor.getRight());//set right
         } return true;
      } else {
         cursor.setData(cursor.getLeft().getRightmostData());  //set data
         cursor.setLeft(cursor.getLeft().removeRightmost());//set left
         return true;
      }
   }
   
      
   /**
   * Determine the number of elements in this bag.
   * @param - none
   * @return
   *   the number of elements in this bag
   **/                           
   public int size() //gets size 
   {
      return IntBTNode.treeSize(root); 
   }
 
  
   public void print() { //prints elements 
      root.print(0);
   }
   

   /**
   * Create a new bag that contains all the elements from two other bags.
   * @param <CODE>b1</CODE>
   *   the first of two bags
   * @param <CODE>b2</CODE>
   *   the second of two bags
   * <dt><b>Precondition:</b><dd>
   *   Neither b1 nor b2 is null.
   * @return
   *   the union of b1 and b2
   * @exception IllegalArgumentException
   *   Indicates that one of the arguments is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new bag.
   **/   
   public static IntTreeBag union(IntTreeBag b1, IntTreeBag b2) { //bag union 
      IntTreeBag tree = new IntTreeBag();
      try {
      tree.addAll(b1); //add all elements to bag 1
	  tree.addAll(b2);//add all elements to bag 2
      } catch (IllegalArgumentException e) { //illegal input 
    	  e.printStackTrace();
      } catch (OutOfMemoryError e) { //out of memory exception 
    	  e.printStackTrace();
      } return tree;
   }
   
   private Stack<IntBTNode> s = new Stack<IntBTNode>();
   
   public void start() {//start traversing from root
	  traversal(root);
      }
   
   public void advance() { //pop 
		  s.pop();
	   }
    
   public boolean isCurrent() { //if current stack is empty 
	  if(s.isEmpty()) {
		return false;
	  } else {
		return true;
	    }
   }
     
   public IntBTNode getCurrent() { //get current node
	return s.peek();
   }
   
   private void traversal(IntBTNode root) {
	if(root.getLeft() != null) {
		traversal(root.getLeft());
	}
	s.push(root); //push
	if(root.getRight() != null) {
		traversal(root.getRight());
    }
  }
}
