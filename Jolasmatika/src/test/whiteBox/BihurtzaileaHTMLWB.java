package test.whiteBox;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import converter.BihurtzaileaHTML;
import games.Labirintoa;

public class BihurtzaileaHTMLWB {

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
	//sut.bihurtu:  The application is executed in Windows or Linux. 
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
			e.printStackTrace();
			// if the program goes to this point fail 
			fail();
		} 
	}
}
