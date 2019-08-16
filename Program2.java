/**
 * File:		Program2.java
 * Author:		Annie Wu
 * Class:		CS 241 - Data Structures and Algorithms II
 * 
 * Assignment:	Program 2
 * Date:		3 February 2018
 * 
 * Purpose:		This file contains the user interactions and the two types of heaps that will be built.
 * 
 */

import java.util.*;

public class Program2 {

	private String lineBreak = "\n\n==========================================================================\n";
	private String prompt = "\nPlease select how to test the program:" +
							"\n(1) 20 sets of 100 randomly generated integers" +
							"\n(2) Fixed integer values 1-100" +
							"\n(3) Exit" +
							"\nEnter choice: ";
	private String avgSequential = "\nAverage number of swaps for series of insertions: ";
	private String avgOptimal = "\nAverage swaps for optimal method: ";
	private String invalidInput = "Invalid Input. Enter 1 or 2.";
	private String insertions = "\nHeap built using a series of insertions: ";
	private String optimalMethod = "\n\nHeap built using optimal method: ";	
	private String swapNumber = "\nNumber of swaps: ";
	private String afterTenRemovals = "\nHeap after 10 removals: ";
	private String endProgram = "\nThanks for using my program!";
	private int hundredRandom = 100;
	
	public static void main(String[] args) {	
		Program2 heap = new Program2();
		heap.choices();
	}
	
	/**
	 * This is the function choices.
	 * The purpose is to prompt the user to input a command.
	 */
	public void choices() {
		String choice;
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		
		while (!exit) {
			System.out.print(lineBreak);
			System.out.print(prompt);
			choice = sc.next();
			switch (choice) {
				case "1": //20 sets of randomly generated integers
					random();
					break;
				case "2": //fixed integers
					fixed();
					break;
				case "3":
					System.out.println(endProgram);
					exit = true;
					break;
				default:
					System.out.println(invalidInput);
					break;
			}
		}
		sc.close();
	}
	
	/**
	 * This is the function random.
	 * The purpose is to generate 20 sets of 100 random values for both heaps and 
	 * calculate the average number of swaps for both of the 20 heaps.
	 */
	public void random() {
		int sequentialAvg = 0;
		int optimalAvg = 0;
		Random random = new Random();
		
		//use for loop for 20 sets
		for(int i = 0; i < 20; i ++)
		{
			Heap sequential = new Heap();
			OptimalHeap optimal = new OptimalHeap();
			//for 100 randomly generated numbers
			for(int j = 0; j < 100; j++)
			{
				//+1 so it eliminates the possibility of getting the number 0
				int number = random.nextInt(hundredRandom) +1;
				
				//while the number exists in the heap, 
				//set the number to a new random number
				while (sequential.exists(number)) {
					number = random.nextInt(hundredRandom) +1;
				}
				//add 100 integers into each heap
				sequential.add(number);
				optimal.add(number);
			}
			//reheap after all the numbers have been added to the heap
			optimal.reheap();
		    sequentialAvg = sequentialAvg + sequential.numberOfSwaps();
		    optimalAvg = optimalAvg + optimal.numberOfSwaps();
		}
		//divide by 20 to get the average out of 20 max-heaps
		System.out.print(avgSequential + (sequentialAvg/20));
		System.out.print(avgOptimal + (optimalAvg/20));
	}
	
	/**
	 * This is the function fixed.
	 * The purpose is to build a max-heap with 100 fixed integers from 1-100.
	 * It will display:
	 *  - the first 10 integers in the array
	 *  - the number of swaps performed for sequential and optimal methods
	 *  - the first 10 integers after 10 removals
	 */
	public void fixed() {
		Heap sequential = new Heap();
		OptimalHeap optimal = new OptimalHeap();

		//add 100 numbers (1-100) to the heaps
		for (int i=1; i<=100; i++) {
			sequential.add(i); 
			optimal.add(i); 
		}

		//using sequential insertion
		System.out.print(insertions + sequential.display());
		System.out.print(swapNumber + sequential.numberOfSwaps());
		
		//remove 10 numbers from the heap
		for (int i=0; i<10; i++) {
			sequential.removeMax();
		}
		System.out.print(afterTenRemovals + sequential.display());
		
		//using optimal method
		//reheap the heap after all values have been added
		optimal.reheap();
		System.out.print(optimalMethod + optimal.display());
		System.out.print(swapNumber + optimal.numberOfSwaps());
		
		//remove 10 numbers from the heap
		for (int i=0; i<10; i++) {
			optimal.removeMax();
		}
		System.out.print(afterTenRemovals + optimal.display());
	}
}
