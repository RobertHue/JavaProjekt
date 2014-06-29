package ro.inf.p2.project;

import java.util.logging.Logger;

import junit.framework.TestCase;

import org.junit.Test;

public class TestSpielfigur    extends TestCase implements ISpielFigur {
	
	
	
	private static final int posX = 0;
	private static final int posY = 0;
	
	
	private static Logger logger = Logger.getLogger(SpielStein.class.getName());
	
	
	



	@Test
	public void testGueltigeBewegungs() {
		
		try {
			gueltigeBewegung();
			
		}
		
		
		
		
		
		
		fail("Not yet implemented");
	}

	@Test
	public void testPositionAendern() {
		fail("Not yet implemented");
	}

	@Test
	public void testKannIchSpringen() {
		fail("Not yet implemented");
	}

}
