package ro.inf.p2.project;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class TestDame extends TestCase {

	int MaxPosX = 7;
	int MaxPosY = 7;
	int posx; // Neu X
	int posy; // Neu Y
	int altX;
	int altY;

	@SuppressWarnings("rawtypes")
	private List[][] test;

	public void VB() {
		// test = new List[7] [7]; // 8X8 Feld mit null vorbelegt

	}

	/**
	 * Test ob die Richtung der Bewegung Gueltig ist ((nur die Richtung))
	 */
	@Test
	public void testGueltigeBewegungsRichtung() {
		test = new List[2][2];
		List currentpos = test[1][1]; // test Position

		DameStein r1 = new DameStein((ISpielFigur) currentpos);

		assertTrue(r1.gueltigeBewegung(posx, posy));

		// fail("Not yet implemented");
	}

}
