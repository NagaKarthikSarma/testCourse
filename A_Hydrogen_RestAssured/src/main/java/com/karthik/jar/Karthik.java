package com.karthik.jar;

import java.util.Scanner;

public class Karthik {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("the value of a: ");
		int a = sc.nextInt();
		System.out.print("the value of b: ");
		int b = sc.nextInt();
		
		
		System.out.println(a+b);
		System.out.println(a*b);
		System.out.println(a-b);
		
		sc.close();
		
		
	}

}
