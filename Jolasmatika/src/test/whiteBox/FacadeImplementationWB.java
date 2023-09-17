package test.whiteBox;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import businesslogic.Facade;
import businesslogic.FacadeImplementation;
import exceptions.WrongLayoutException;

public class FacadeImplementationWB {

	//sut:system under test
	static Facade sut=new FacadeImplementation();

	//labirintoaSortu TESTS

	@Test
	//sut.labirintoaSortu:  The layout is wrong. 
	public void test1() {
		try {

			//define paramaters
			int [][] layout = {{2,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{0,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{3,0,0,0,0,0,0}};

			//invoke System Under Test (sut)  
			sut.labirintoaSortu(layout);

			//if the program continues fail
			fail();
		} catch (WrongLayoutException e) {
			// if the program goes to this point OK
			assertTrue(true);
		} 
	}

	@Test
	//sut.labirintoaSortu:  The layout is correct. 
	public void test2() {
		try {

			//define paramaters
			int [][] layout = {{2,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{3,0,0,0,0,0,0}};

			//invoke System Under Test (sut)  
			sut.labirintoaSortu(layout);

			//if the program continues OK
			assertTrue(true);
		} catch (Exception e) {
			// if the program goes to this point fail 
			fail();
		} 
	}

	//blokeakTxertatu TESTS
	
	@Test
	//sut.blokeakTxertatu:  The method works properly. 
	public void test3() {
		try {

			//define paramaters
			ArrayList<String> blocks = new ArrayList<>();
			blocks.add("maze_moveForward");

			//invoke System Under Test (sut)  
			sut.blokeakTxertatu(blocks, 1);

			//if the program continues OK
			assertTrue(true);
		} catch (Exception e) {
			// if the program goes to this point fail 
			fail();
		} 
	}
	
	//bihurtu TESTS
	
	@Test
	//sut.bihurtu:  The method works properly. 
	public void test4() {
		try {

			//define paramaters
			String jolasIzena = "jolasIzena";
			String jokoMota = "maze";
			String hasierakoPista = "pista";

			//invoke System Under Test (sut)  
			sut.bihurtu(jolasIzena, hasierakoPista, jokoMota);

			//if the program continues OK
			assertTrue(true);
		} catch (Exception e) {
			// if the program goes to this point fail 
			fail();
		} 
	}
}
