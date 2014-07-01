package ro.inf.p2.project;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.junit.Test;

public abstract class TestSpielfigur extends TestCase implements ISpielFigur {


	private static Logger logger = Logger.getLogger(SpielStein.class.getName());

	ISpielFigur SpielStein;

	int farbe;
	int fweiss = 1;
	int fschwarz = 0;

	@Test
	public void testGueltigeBewegungs() {

		List<SpielStein> SpielerWeiss = new ArrayList<SpielStein>();

		List<SpielStein> SpielerSchwarz = new ArrayList<SpielStein>();

		
		// PosX,PosY,Farbe
		SpielerWeiss.add(new SpielStein(3, 6, 1)); // D7
	//	SpielerWeiss.add(new SpielStein(0, 7, 1)); // A8
		
		

		try {

			assertTrue(((SpielStein) SpielerWeiss).gueltigeBewegung(2, 5) == true); //C6
			assertTrue(((SpielStein) SpielerWeiss).gueltigeBewegung(4, 5) == true); //E6
			assertTrue(((SpielStein) SpielerWeiss).gueltigeBewegung(0, 0) == true); //D7

			assertTrue(((SpielStein) SpielerWeiss).gueltigeBewegung(2, 7) == false); //C8
			assertTrue(((SpielStein) SpielerWeiss).gueltigeBewegung(4, 7) == false); //E8
			assertTrue(((SpielStein) SpielerWeiss).gueltigeBewegung(0, 0) == false);

			
			assertTrue(((SpielStein) SpielerSchwarz).gueltigeBewegung(2, 7) == true); //C8
			assertTrue(((SpielStein) SpielerSchwarz).gueltigeBewegung(4, 7) == true); //E8
			assertTrue(((SpielStein) SpielerSchwarz).gueltigeBewegung(0, 0) == true); //D7
			
			assertTrue(((SpielStein) SpielerSchwarz).gueltigeBewegung(2, 5) == false); //C6
			assertTrue(((SpielStein) SpielerSchwarz).gueltigeBewegung(4, 5) == false); //C6
			assertTrue(((SpielStein) SpielerSchwarz).gueltigeBewegung(3, 7) == false); //D6
			

			// fail("Not yet implemented"); Auf keinen Fall ereichbarer Code
			// Hard Stop !!!

		}

		catch (Exception e) {
			logger.info("Exception bei testGueltigeBewegungs Erkannt");

		}

	}

}
