package com.avantrip.capacitaciones.ejercicio_ajedrez.mliwski;

import static org.junit.Assert.*;

import org.junit.Test;

public class EscaqueTest {

	@Test
	public void test() {
		Character A = 'a';
		Character B = 'b';
		Character C = 'c';
		
		System.out.println(A.compareTo(B));
		System.out.println(A.compareTo(C));
		System.out.println(C.compareTo(B));
		System.out.println(B.compareTo(B));
		fail("Not yet implemented");
	}
	@Test
	public void test2() {
		Integer A = 1;
		Integer B = 2;
		Integer C = 3;
		
		System.out.println(A - B);
		System.out.println(A - C);
		System.out.println(C - B);
		System.out.println(B - B);
		fail("Not yet implemented");
	}

}
