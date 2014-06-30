package ro.inf.p2.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spieler implements ISpieler {
	// Attribute

	private String name;
	private ArrayList<ISpielFigur> figuren;

	// List<List<String>> strArray = ArrayList<List<String>> ();

	int XX;

	int posX;
	int posY;
	int farbe;
	int weiss = 1;
	int schwartz = 0;

	ArrayList<ISpielFigur> Positions = new ArrayList<ISpielFigur>();

	/**
	 * Macht Array mit 12 Spielsteinen für X = 0 die Schwartzen X = 1 die Weißen
	 * 
	 * @param x
	 */

	public Spieler(int x) {
		

		if (x == 0) {

			List<SpielStein> SpielerWeiss = new ArrayList<SpielStein>();

			// PosX,PosY,Farbe
			SpielerWeiss.add(new SpielStein(0, 5, 1)); // A6
			SpielerWeiss.add(new SpielStein(0, 7, 1)); // A8
			SpielerWeiss.add(new SpielStein(1, 6, 1)); // B7
			SpielerWeiss.add(new SpielStein(2, 5, 1)); // C6
			SpielerWeiss.add(new SpielStein(2, 7, 1)); // C8
			SpielerWeiss.add(new SpielStein(3, 6, 1)); // D7
			SpielerWeiss.add(new SpielStein(4, 5, 1)); // E6
			SpielerWeiss.add(new SpielStein(4, 7, 1)); // E8
			SpielerWeiss.add(new SpielStein(5, 6, 1)); // F7
			SpielerWeiss.add(new SpielStein(6, 5, 1)); // G6
			SpielerWeiss.add(new SpielStein(6, 7, 1)); // G8
			SpielerWeiss.add(new SpielStein(7, 6, 1)); // H7

		} else if (x == 1) {
			List<SpielStein> SpielerSchwartz = new ArrayList<SpielStein>();

			// PosX,PosY,Farbe
			SpielerSchwartz.add(new SpielStein(0, 1, 0)); // A2
			SpielerSchwartz.add(new SpielStein(1, 0, 0)); // B1
			SpielerSchwartz.add(new SpielStein(1, 2, 0)); // B3
			SpielerSchwartz.add(new SpielStein(2, 1, 0)); // C2
			SpielerSchwartz.add(new SpielStein(3, 0, 0)); // D1
			SpielerSchwartz.add(new SpielStein(3, 2, 0)); // D3
			SpielerSchwartz.add(new SpielStein(4, 1, 0)); // E2
			SpielerSchwartz.add(new SpielStein(5, 0, 0)); // F1
			SpielerSchwartz.add(new SpielStein(5, 2, 0)); // F3
			SpielerSchwartz.add(new SpielStein(6, 1, 0)); // G2
			SpielerSchwartz.add(new SpielStein(7, 0, 0)); // H1
			SpielerSchwartz.add(new SpielStein(7, 2, 0)); // H3

			// Map<Integer,Integer> PosX = new HashMap<Integer,Integer>();
			// PosX.put(1,1);

			// ArrayList<ISpielFigur> SP = new ArrayList<ISpielFigur>();

			// SP.set(posX & posY, null);

			//
			// /**
			// * X-Y Positionen mit 1 kennz.
			// */
			// int[][] Positions = new int[7][7] ;
			//
			// Positions [0][0] =1; // A1
			// Positions [1][1] =1; // B2
			// Positions [0][2] =1; // A3
			// Positions [2][0] =1; // C1
			// Positions [3][1] =1; // D2
			// Positions [2][2] =1; // C3
			// Positions [4][0] =1; // E1
			// Positions [5][1] =1; // F2
			// Positions [4][2] =1; // E3
			// Positions [6][0] =1; // G1
			// Positions [7][1] =1; // H2
			// Positions [6][2] =1; // G3
			//
			//

			// List<int[]> Spieler = Arrays.asList(Positions); // Array in der
			// Liste ......

		}

	}

	/**
	 * Check ob figur existiert, wenn true dann loeschen und return true, wenn
	 * false return false
	 */

	public boolean entferneSpielFigur(ISpielFigur figur) {

		if (figuren.contains(figur)) {
			figuren.remove(figur);
			return true;
		}

		return false;
	}

	/**
	 * Fuege Dame in Array figuren
	 */
	public void fuegeDameZurListeHinzu(ISpielFigur figur) {

		figuren.add(figur);

	}

	public void setzeName(String name) {

		this.name = name;
	}

	/**
	 * Anzahl der figuren im Array figuren
	 */
	public int gibAnzahlSteine() {

		return figuren.size();
	}

	/**
	 * Gibt alle Figuren zurück. Schwartz & Weiss
	 */

	public ArrayList<ISpielFigur> gibFiguren() {
		return this.figuren;
	}

	public String gibName() {

		return this.name;
	}

}