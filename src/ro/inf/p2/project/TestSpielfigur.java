package ro.inf.p2.project;

import java.util.logging.Logger;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

public abstract class TestSpielfigur    extends TestCase implements ISpielFigur {
	
	
	
	private static final int posX = 0;
	private static final int posY = 0;
	
	
	private static Logger logger = Logger.getLogger(SpielStein.class.getName());
	
	ISpielFigur SpielStein;
	
	
	int farbe;
	int fweiss =1;
	int fschwartz= 0; 


	@Test
	public void testGueltigeBewegungs() {				// farblich ...	// Muss nachsehen ob die trues stimmen...
		
		
		
		
		SpielStein weiss = new SpielStein(posX, posY, fweiss);
		SpielStein schwartz = new SpielStein(posX, posY, fschwartz);
		
		try {
			
			
			
			assertTrue(weiss.gueltigeBewegung(0 , 0) == true );
			assertTrue(weiss.gueltigeBewegung(1 , 1) == true );
			assertTrue(weiss.gueltigeBewegung(-1, 1) == true );
			
			assertTrue(weiss.gueltigeBewegung(2, 1) == false );
			assertTrue(weiss.gueltigeBewegung(2, 2) == false );
			assertTrue(weiss.gueltigeBewegung(1, 2) == false );
			
			
			assertTrue(schwartz.gueltigeBewegung( 0, 0) == true );
			assertTrue(schwartz.gueltigeBewegung(-1,-1) == true );
			assertTrue(schwartz.gueltigeBewegung( 1,-1) == true );
			
			assertTrue(schwartz.gueltigeBewegung(-2, 1) == false );
			assertTrue(schwartz.gueltigeBewegung(0, 0) == false );
			assertTrue(schwartz.gueltigeBewegung(0, 0) == false );
		
			
		//fail("Not yet implemented");		Auf keinen Fall ereichbarer Code Hard Stop !!!
		
		
		}
		
		catch (Exception e) {
			logger.info("Exception bei testGueltigeBewegungs Erkannt");
			
			
		}
		
		
		
		
		
		
		
	}

	@Test
	public void testPositionAendern() {
	//	fail("Not yet implemented");
	}

	@Test
	public void testKannIchSpringen() {
		//fail("Not yet implemented");
	}

}
