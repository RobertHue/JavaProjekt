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
		// TODO Auto-generated constructor stub
		// x = Anzahl der steine

		if (x == 0) {

			List<SpielStein> SpielerWeiss = new ArrayList<SpielStein>();

			// PosX,PosY,Farbe
			SpielerWeiss.add(new SpielStein(0, 0, 1)); // A1
			SpielerWeiss.add(new SpielStein(1, 1, 1)); // B2
			SpielerWeiss.add(new SpielStein(0, 2, 1)); // A3
			SpielerWeiss.add(new SpielStein(2, 0, 1)); // C1
			SpielerWeiss.add(new SpielStein(3, 1, 1)); // D2
			SpielerWeiss.add(new SpielStein(2, 2, 1)); // C3
			SpielerWeiss.add(new SpielStein(4, 0, 1)); // E1
			SpielerWeiss.add(new SpielStein(5, 1, 1)); // F2
			SpielerWeiss.add(new SpielStein(4, 2, 1)); // E3
			SpielerWeiss.add(new SpielStein(6, 0, 1)); // G1
			SpielerWeiss.add(new SpielStein(7, 2, 1)); // H2
			SpielerWeiss.add(new SpielStein(6, 2, 1)); // G3

		} else if (x == 1) {
			List<SpielStein> SpielerSchwartz = new ArrayList<SpielStein>();

			// PosX,PosY,Farbe
			SpielerSchwartz.add(new SpielStein(0, 7, 0)); // A7
			SpielerSchwartz.add(new SpielStein(1, 6, 0)); // B6
			SpielerSchwartz.add(new SpielStein(1, 8, 0)); // B8
			SpielerSchwartz.add(new SpielStein(2, 7, 0)); // C7
			SpielerSchwartz.add(new SpielStein(3, 6, 0)); // D6
			SpielerSchwartz.add(new SpielStein(3, 8, 0)); // D8
			SpielerSchwartz.add(new SpielStein(4, 7, 0)); // E7
			SpielerSchwartz.add(new SpielStein(5, 6, 0)); // F6
			SpielerSchwartz.add(new SpielStein(5, 8, 0)); // F8
			SpielerSchwartz.add(new SpielStein(6, 7, 0)); // G7
			SpielerSchwartz.add(new SpielStein(7, 6, 0)); // H6
			SpielerSchwartz.add(new SpielStein(7, 8, 0)); // H8

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