//Jose Mora
//CIS 256
//Project 5

import java.util.*;

public class Driver 
{
	public static void main(String [] args)
	{
		Scanner keyboard = new Scanner(System.in);
		int bagChoice; //bag 1 or bag 2 
		int menuChoice = 0; //user choice 
		
		IntTreeBag bag1 = new IntTreeBag(); //first bag 
		IntTreeBag bag2 = new IntTreeBag(); //second bag 
		
		System.out.println("Enter Bag 1 Root Value: "); //enter bag 1 initial root
		int root1 = keyboard.nextInt();
		bag1.add(root1);
				
		System.out.println("Enter Bag 2 Root Value: "); //enter bag 2 initial root
		int root2 = keyboard.nextInt();
		bag2.add(root2);
		
		while(menuChoice != 9) {		//menu options
		System.out.println("1: Add An Integer");
		System.out.println("2: Remove An Integer");
		System.out.println("3: Output Values");
		System.out.println("4: Add Contents of Bags Together");
		System.out.println("5: Combine Bags To Make a Third");
		System.out.println("6: Output Number Of Occurrances of Integer");
		System.out.println("7: Output Bag Contents");
		System.out.println("8: Iterate");
		System.out.println("9: Exit");
			
		menuChoice = keyboard.nextInt();
			
	    if(menuChoice == 1) {	//add integer
				
	    	    int num;
	    	    System.out.println("Add To Bag 1 or Bag 2?"); //user input
			    bagChoice = keyboard.nextInt();
				
				if(bagChoice == 1) {
					System.out.println("Enter Number: ");
					num = keyboard.nextInt();
					bag1.add(num); //add to bag 1
				} else if(bagChoice == 2) {
						System.out.println("Enter Number: ");
						num = keyboard.nextInt();
						bag2.add(num); //add to bag 2
					} else {
						System.out.println("Invalid\n"); //invalid choice 
					}
		}
	    
		if (menuChoice == 2) { //remove integer

				int num;
				System.out.println("Remove From Bag 1 or Bag 2?");
				bagChoice = keyboard.nextInt(); //user input 
				
				if(bagChoice == 1) { //remove from bag 1
						System.out.println("Enter number: ");
						num = keyboard.nextInt();
						if(bag1.remove(num)) {
							System.out.print(num + " has been removed\n");
						} else {
							System.out.println("Invalid\n");
						}
					} else if(bagChoice == 2) { //remove from bag 2 
						System.out.println("Enter number: ");
						num = keyboard.nextInt();
						if(bag2.remove(num)) {
							System.out.println(num + " has been removed\n");
						} 
					} else {
						System.out.println("Invalid\n");
					}
			 }
			 
	      if (menuChoice == 3) { //output values from bag 1 or 2
				
				System.out.println("Output Value From Bag 1 or Bag 2?");
				bagChoice = keyboard.nextInt();
				
				if(bagChoice == 1) {
						System.out.println("Bag 1 size: " + bag1.size());
				} else if(bagChoice == 2) {
						System.out.println("Bag 2 size: " + bag2.size());
				  } else {
						System.out.println("Invalid\n");
					}
		  }
			
		  if (menuChoice == 4) { //add contents of bags together 
					
				System.out.println("Add Contents From Bag 1 Or Bag 2?");
				bagChoice = keyboard.nextInt();
					
				if(bagChoice == 1) {
						bag1.addAll(bag2);
						bag1.print();
				} else if(bagChoice == 2) {
						bag2.addAll(bag1);
						bag2.print();
					} else {
						System.out.println("Invalid\n");
					}
		  }
			
		   if (menuChoice == 5) { //union 
					IntTreeBag.union(bag1, bag2).print();
		   }
			
		   if(menuChoice == 6){ //output times a value occurs 
				
					int num = 0;
					System.out.println("Show Occurrances of Bag 1 or Bag 2? ");
					bagChoice = keyboard.nextInt();
					
					if(bagChoice == 1) {
						System.out.println("Enter number: ");
						num = keyboard.nextInt();
						System.out.println(num + " occurs " + bag1.countOccurrences(num) + " times\n");
					} else if(bagChoice == 2) {
						System.out.println("Enter number: ");
						num = keyboard.nextInt();
						System.out.println(num + " occurs " + bag2.countOccurrences(num) + " times\n");
					} else {
						System.out.println("Invalid\n");
					  }
		    }
			
			if (menuChoice == 7) { //display contents of bag 
				
					System.out.println("Output Contents Of Bag 1 or Bag 2?");
					bagChoice = keyboard.nextInt();
					
					if(bagChoice == 1) {
						bag1.print();	
					} else if(bagChoice == 2) {
						bag2.print();
					} else {
						System.out.println("Invalid\n");
					}
	
			if (menuChoice == 8) { //iterate 
				
					System.out.println("Bag 1: "); //iteration for bag 
					bag1.start();
					while(bag1.isCurrent()) {
						System.out.println(bag1.getCurrent().getData());
						bag1.advance();
					}
					
					System.out.println("Bag 2: "); //iteration for bag 2
					bag2.start();
					while(bag2.isCurrent()) {
						System.out.println(bag2.getCurrent().getData());
						bag2.advance();
					}			
			}
			
			if (menuChoice == 9) { //exit application
					System.exit(0);
			}				
	     } 
       }
	}
}
