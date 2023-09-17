package test.blackBox;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import converter.BihurtzaileaHTML;
import games.Labirintoa;

public class BihurtzaileaHTMLBB {

	//sut:system under test
	static BihurtzaileaHTML sut=new BihurtzaileaHTML();

	private Labirintoa createMaze(int[][] layout, String hasierakoPista, int blokeKopurua, List<String> onartutakoBlokeak) {
		Labirintoa lab = new Labirintoa(layout);
		lab.setHasierakoPista(hasierakoPista);
		lab.setBlokeKopurua(blokeKopurua);
		lab.setOnartutakoBlokeak(onartutakoBlokeak);
		return lab;	
	}

	@Test
	//sut.bihurtu:  The application works properly. 
	public void test1() {
		try {

			//define paramaters
			int [][] layout = {{2,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{3,0,0,0,0,0,0}};
			ArrayList<String> blocks = new ArrayList<>();
			blocks.add("maze_moveForward");
			Labirintoa lab = createMaze(layout, "pista", 1, blocks);

			String jolasIzena = "jolasIzena";
			String jokoMota = "maze";

			//invoke System Under Test (sut)  
			sut.bihurtu(lab, jolasIzena, jokoMota);


			//if the program continues OK
			assertTrue(true);
		} catch (Exception e) {
			// if the program goes to this point fail 
			fail();
		} 
	}

	@Test
	//sut.bihurtu:  jolasa is null. 
	public void test2() {
		try {

			//define paramaters
			Labirintoa lab = null;

			String jolasIzena = "jolasIzena";
			String jokoMota = "maze";

			//invoke System Under Test (sut)  
			sut.bihurtu(lab, jolasIzena, jokoMota);


			//if the program continues fail
			fail();
		} catch (Exception e) {
			// if the program goes to this point OK
			assertTrue(true);
		} 
	}

	@Test
	//sut.bihurtu:  The game type is null. 
	public void test3() {
		try {

			//define paramaters
			int [][] layout = {{2,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{3,0,0,0,0,0,0}};
			ArrayList<String> blocks = new ArrayList<>();
			blocks.add("maze_moveForward");
			Labirintoa lab = createMaze(layout, "pista", 1, blocks);

			String jolasIzena = "jolasIzena";
			String jokoMota = null;

			//invoke System Under Test (sut)  
			sut.bihurtu(lab, jolasIzena, jokoMota);


			//if the program continues fail
			fail();
		} catch (Exception e) {
			// if the program goes to this point OK
			assertTrue(true);
		} 
	}

	@Test
	//sut.bihurtu:  The game type does not exist. 
	public void test4() {
		try {

			//define paramaters
			int [][] layout = {{2,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{3,0,0,0,0,0,0}};
			ArrayList<String> blocks = new ArrayList<>();
			blocks.add("maze_moveForward");
			Labirintoa lab = createMaze(layout, "pista", 1, blocks);

			String jolasIzena = "jolasIzena";
			String jokoMota = "ezDago";

			//invoke System Under Test (sut)  
			sut.bihurtu(lab, jolasIzena, jokoMota);


			//if the program continues fail
			fail();
		} catch (Exception e) {
			// if the program goes to this point OK
			assertTrue(true);
		} 
	}
}
