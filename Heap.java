/**
 * File:		Heap.java
 * Author:		Annie Wu
 * Class:		CS 241 - Data Structures and Algorithms II
 * 
 * Assignment:		Program 2
 * Date:		3 February 2018
 * 
 * Purpose:		This file contains the methods for the heap that is created with sequential insertion.
 * 			It will reheap the max heap after each node is added.
 * 
 */

import java.util.Arrays;

public class Heap {
	private int[] heap;
	private int size;
	private int swaps;
	private int parent;
	private int array = 100;

	/**
	 * This is the constructor.
	 * The purpose is to create a new heap and initializes the number of swaps
	 * and the size for number of integers currently in the heap to 0.
	 */
	public Heap() {
		heap = new int[array];
		swaps = 0;
		size = 0;
	}
	
	/**
	 * This is the function add.
	 * The purpose is to add a value into the heap with sequential insertion.
	 * Add at leaf level and compare to the parent (up-heap), swap if in wrong order.
	 * @param value
	 */
	public void add(int value) {
		//check if the value exists in the heap
		if (exists(value))
			return;
		heap[size] = value;
		int child = size;
		
		//while the child is greater than the parent, swap them
		while (heap[child] > heap[(child-1)/2]) {
			parent = (child-1)/2;
			swap(child, parent);
			child = parent;
		}
		//increase size count
		size++;
		ensureCapacity();
	}
	
	/**
	 * This is the function removeMax.
	 * the purpose is to remove the max (parent) of the heap and reheap with a new parent.
	 */
	public void removeMax() {		
		parent = 0;
		//the parent will become the last value in the heap
		heap[parent] = heap[size-1];
		//set the last value to -1 so it is out of the heap
		heap[size-1] = -1;
		//decrease size
		size--;

		ensureCapacity();

		//while the parent is smaller than the left child
		//or while the parent is larger than the right child
		while (heap[parent] < heap[2*parent + 1] || heap[parent] < heap[2*parent + 2]) {
			int left = 2*parent + 1;
			int right = 2*parent + 2;
			//if left child > right, swap parent
			//and set the left child to the parent and left child
			if (heap[left] > heap[right]) {
				swap(left, parent);
				parent = left;
			}
		
			//if right child > left, swap parent and right child
			//and set the right child to the parent
			else if (heap[left] < heap[right]) { 
				swap(right, parent);
				parent = right;
			}
			ensureCapacity();
		}
	}


	/**
	 * This is the function swap.
	 * The purpose is to swap two numbers.
	 * It will create a temporary number and set that as the first number in the heap,
	 * swap the first number and second number in the heap,
	 * set the second number in the heap as the temporary number,
	 * and increase the swap count.
	 */
	public void swap(int firstNumber, int secondNumber) {
		int temperary = heap[firstNumber];
		heap[firstNumber] = heap[secondNumber];
		heap[secondNumber] = temperary;
		swaps++;
	}
	
	/**
	 * This is the function numberOfSwaps.
	 * The purpose is to get the number of swaps.
	 */
	public int numberOfSwaps() {
		return swaps;
	}
	
	/**
	 * This is the function exists.
	 * The purpose is to check to see if a value exists in the heap.
	 */
	public boolean exists(int value) {
		for (int i = 0; i < size; i++) {
			if (heap[i] == value)
				return true;
			else if (value > heap[i]) 
				return false;
		}
		return false;
	}

	/**
	 * This is the function display.
	 * The purpose is to print out the first 10 numbers in the heap.
	 */
	public String display() {
		int i=0;
		return heap[i] + "," + heap[i+1] + "," + heap[i+2] + "," + heap[i+3] + "," + heap[i+4] + "," + 
				heap[i+5] + "," + heap[i+6] + "," + heap[i+7] + "," + heap[i+8] + "," + heap[i+9] + ",..." ;
	}
	
	/**
	 * This is the function ensureCapacity.
	 * The purpose to ensure the capacity so there won't be an error for index being out of bounds.
	 * If the last value in the heap greater than or equal to the length of the heap's array
	 * then increase the size of the heap.
	 */
	private void ensureCapacity() {
		int largest = 2*parent + 2;
		if(largest >= heap.length) {
			int []temp = heap;
		    heap = Arrays.copyOf(temp, 2 + heap.length);
		    temp = null;
		}
	}
}
