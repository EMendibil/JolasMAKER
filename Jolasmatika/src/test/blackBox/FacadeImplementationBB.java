package test.blackBox;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import businesslogic.Facade;
import businesslogic.FacadeImplementation;
import exceptions.WrongLayoutException;

public class FacadeImplementationBB {

	//sut:system under test
		static Facade sut=new FacadeImplementation();

		//labirintoaSortu TESTS

		@Test
		//sut.labirintoaSortu:  The method works properly. 
		public void test1() {
			try {

				//define paramaters
				int [][] layout = {{2,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{3,0,0,0,0,0,0}};

				//invoke System Under Test (sut)  
				sut.labirintoaSortu(layout);

				//if the program continues OK
				assertTrue(true);
			} catch (WrongLayoutException e) {
				// if the program goes to this point fail
				fail();
			} 
		}

		@Test
		//sut.labirintoaSortu:  The layout is null. 
		public void test2() {
			try {

				//define paramaters
				int [][] layout = null;

				//invoke System Under Test (sut) 
				sut.labirintoaSortu(layout);

				//if the program continues fail
				fail();
			} catch (Exception e) {
				// if the program goes to this point OK 
				assertTrue(true);
			} 
		}
		
		@Test
		//sut.labirintoaSortu:  The layout is not properly connected. 
		public void test3() {
			try {

				//define paramaters
				int [][] layout = {{2,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{0,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{3,0,0,0,0,0,0}};

				//invoke System Under Test (sut)  
				sut.labirintoaSortu(layout);

				//if the program continues fail
				fail();
			} catch (Exception e) {
				// if the program goes to this point OK 
				assertTrue(true);
			} 
		}

		@Test
		//sut.labirintoaSortu:  The layout is smaller than a 7*7 matrix. 
		public void test4() {
			try {

				//define paramaters
				int [][] layout = {{1}};

				//invoke System Under Test (sut)  
				sut.labirintoaSortu(layout);

				//if the program continues fail
				fail();
			} catch (Exception e) {
				// if the program goes to this point OK 
				assertTrue(true);
			} 
		}
		
		//blokeakTxertatu TESTS: NONE, because it accepts any variables
		
		//bihurtu TESTS
		
		@Test
		//sut.bihurtu:  The method works properly. 
		public void test5() {
			try {

				//define paramaters
				String jolasIzena = "jolasIzena";
				String jokoMota = "maze";
				String hasierakoPista = "pista";

				//invoke System Under Test (sut)  
				sut.bihurtu(jolasIzena, hasierakoPista, jokoMota);

				//if the program continues fail
				fail();
			} catch (Exception e) {
				// if the program goes to this point OK
				assertTrue(true);
			} 
		}
		
		@Test
		//sut.bihurtu:  The game type is null. 
		public void test6() {
			try {

				//define paramaters
				String jolasIzena = "jolasIzena";
				String jokoMota = null;
				String hasierakoPista = "pista";

				//invoke System Under Test (sut)  
				sut.bihurtu(jolasIzena, hasierakoPista, jokoMota);

				//if the program continues fail
				fail();
			} catch (Exception e) {
				// if the program goes to this point OK 
				assertTrue(true);
			} 
		}
		
		@Test
		//sut.bihurtu:  The game type does not exist. 
		public void test7() {
			try {

				//define paramaters
				String jolasIzena = "jolasIzena";
				String jokoMota = "ezDago";
				String hasierakoPista = "pista";

				//invoke System Under Test (sut)  
				sut.bihurtu(jolasIzena, hasierakoPista, jokoMota);

				//if the program continues fail
				fail();
			} catch (Exception e) {
				// if the program goes to this point OK 
				assertTrue(true);
			} 
		}
}
