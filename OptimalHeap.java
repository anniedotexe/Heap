/**
 * File:		OptimalHeap.java
 * Author:		Annie Wu
 * Class:		CS 241 - Data Structures and Algorithms II
 * 
 * Assignment:	Program 2
 * Date:		3 February 2018
 * 
 * Purpose:		This file contains the methods for heap that is built with the optimal method,
 * 				which is to reheap after all the nodes have been added to the max heap.
 * 
 */

import java.util.Arrays;

public class OptimalHeap {
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
	public OptimalHeap() {
		heap = new int[array];
		swaps = 0;
		size = 0;
	}
	
	/**
	 * This is the function add.
	 * The purpose is to add a value into the heap with the optimal method.
	 * The values are added with no swaps.
	 */
	public void add(int value) {
		//check if the value exists in the heap
		if (exists(value))
			return;
		heap[size] = value;
		//increase size for every value added
		size++;
	}
	
	/**
	 * This is the function removeMax.
	 * The purpose is to remove the max (root) of the heap and reheap with a new root.
	 */
	public void removeMax() {
		parent = 0;
		//move max to the last place in the heap
		heap[parent] = heap[size-1]; 
		//set the max(parent) to -1 so it is out of the heap
		heap[size-1] = -1; 
		//decrease size
		size--;
		
		ensureCapacity();
		downHeap(0);
	}
	
	/**
	 * This is the function reheap.
	 * The purpose is to reheap the heap by calling upHeap.
	 */
	public void reheap() {
		upHeap(size);
	}
	
	/**
	 * This is the function upheap.
	 * The purpose is to reheap going up from index n/2,
	 * where n is the number of nodes in the heap.
	 */
	public void upHeap(int index) {
		
		//index is the last value in the heap
		index = size-1;
		parent = (index -1)/2;
		
		//while there is a value in the heap	
		while (index > -1) {
			int left = 2*parent + 1;
			int right = 2*parent + 2;
			
			//if the left child is greater than the parent
			//or if the right child is greater than the parent
			if (heap[left] > heap[parent] || heap[right] > heap[parent]) {
				ensureCapacity();
				int current;
				//if left child > parent, swap left child and parent
				if (heap[left] > heap[2*parent + 2]) {
					swap(left, parent);
					current = left;				
				}
				//if right child > parent, swap right child and parent
				else { 
					swap(right, parent);
					current = right;
				}
				downHeap(current);
			}
			//do to next index and set the parent node
			index -= 2;
			parent = (index-1)/2;
		}
	}
	
	
	/**
	 * This is the function downHeap.
	 * The purpose is to reheap down from the current index.
	 * This makes sure the parent stays greater than the child.
	 */
	public void downHeap(int current) {
		//if the right child is greater than the last value in the heap, don't swap
		if (2*current +2 > size-1) {
			return;
		}
		//if the new parent is less than either left or right child
		if (heap[current] < heap[2*current + 1] || heap[current] < heap[2*current + 2]) {
			int left = 2*current + 1;
			int right = 2*current + 2;
			//if the left child is greater than the right child
			//swap left child and parent
			if (heap[left] > heap[right]) {
				swap(left, current);
				current = left;
				upHeap(current);
			}
			//else the right child is greater than the left child
			//swap right child and parent
			else {
				swap(right, current);
				current = right;
				upHeap(current);
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
	 * The purpose is to ensure the capacity so there won't be an error for index being out of bounds.
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