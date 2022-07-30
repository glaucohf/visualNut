package com.visualnuts.exercices;

import java.io.IOException;



public class Exercise1 {
	
	public static void printNumber(int n) {
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0) {
				if (i % 5 == 0) {
					System.out.println("Visual Nuts");
				} 
				else {
					System.out.println("Visual");
				}
			}
			else if (i % 5 == 0) {
				System.out.println("Nuts");
			} 
			else {
				System.out.println(i);
			}			
		}
	}
	
	public static void main(String[] args) throws IOException {
		int limit = 100;
		printNumber(limit);

	}
}
