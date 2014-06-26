package ro.inf.p2.project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.junit.Test;

import ro.inf.p2.project.TestSpielfeld;

public class TestSpielfeld<ISpielFigur> extends TestCase {

	private static Logger logger = Logger.getLogger(TestSpielfeld.class
			.getName());

	public ArrayList<SpielStein> figuren;
	ArrayList<SpielStein> spielerSchwarz = new ArrayList<SpielStein>();
	ArrayList<SpielStein> spielerWeiss = new ArrayList<SpielStein>();

	int posX;
	int posY;

	public <spielerSchwarz> int setSpieler(int posX, int posY) {

		spielerSchwarz.set(posX & posY, null);

		return posX & posY;
	}

	@Test
	public void testUpdateStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testFigurSelektieren() {

		SpielFeld r1 = new SpielFeld();

		assertTrue(r1.figurSelektieren(null, posX, posY) == 1);// 1 Fehlercode
																// fuer leeres
																// Feld

		fail("Not yet implemented");
	}

	@Test
	public void testBewegeNach() {
		fail("Not yet implemented");
	}

}
