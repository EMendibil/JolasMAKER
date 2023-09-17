package test.whiteBox;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import converter.Idazlea;
import converter.IdazleaMaze;
import games.Labirintoa;

public class IdazleaMazeWB {

	//sut:system under test
		static Idazlea sut=new IdazleaMaze(System.getProperty("user.home") + "/Documents/blockly-games-iruzkinak");

		private Labirintoa createMaze(int[][] layout, String hasierakoPista, int blokeKopurua, List<String> onartutakoBlokeak) {
			Labirintoa lab = new Labirintoa(layout);
			lab.setHasierakoPista(hasierakoPista);
			lab.setBlokeKopurua(blokeKopurua);
			lab.setOnartutakoBlokeak(onartutakoBlokeak);
			return lab;	
		}

		@Test
		//sut.berridatzi:  The maximum amount of blocks is bigger than -1. 
		public void test1() {
			try {

				//define paramaters
				int [][] layout = {{2,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{3,0,0,0,0,0,0}};
				ArrayList<String> blocks = new ArrayList<>();
				blocks.add("maze_moveForward");
				Labirintoa lab = createMaze(layout, "pista", 5, blocks);

				String jokoMota = "maze";

				//invoke System Under Test (sut)  
				sut.berridatzi(lab, jokoMota);

				//if the program continues OK
				assertTrue(true);
			} catch (Exception e) {
				// if the program goes to this point fail 
				fail();
			} 
		}
		
		@Test
		//sut.berridatzi:  The amount of blocks is -1. 
		public void test2() {
			try {

				//define paramaters
				int [][] layout = {{2,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{1,0,0,0,0,0,0},{3,0,0,0,0,0,0}};
				ArrayList<String> blocks = new ArrayList<>();
				blocks.add("maze_moveForward");
				blocks.add("maze_turn");
				Labirintoa lab = createMaze(layout, "pista", -1, blocks);

				String jokoMota = "maze";

				//invoke System Under Test (sut)  
				sut.berridatzi(lab, jokoMota);

				//if the program continues OK
				assertTrue(true);
			} catch (Exception e) {
				// if the program goes to this point fail 
				fail();
			} 
		}
}
