package com.karthik.table;

import java.util.HashSet;
import java.util.Set;


public class App{
	public static void main(String[] args) {
	Set<String> set = new HashSet<String>();
	set.add("PL0060622083");
	set.add("karthik");
	set.add("PL0060622083");
	
	for(String word: set) {
		System.out.println(word);
	}
	
	}
}
