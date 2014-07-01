package ro.inf.p2.project;

import java.util.ArrayList;
import java.util.List;

public class Spieler implements ISpieler {

	private String name;
	private ArrayList<ISpielFigur> figuren;

	int posX;
	int posY;
	int farbe;
	int weiss = 1;
	int schwarz = 0;

	// ArrayList<ISpielFigur> Positions = new ArrayList<ISpielFigur>();

	/**
	 * Macht Array mit 12 Spielsteinen (PosX,PosY,Farbe) für X = 0 die Schwarzen
	 * X = 1 die Weißen Wird mit einem String für den Namen des Spielers und
	 * einem int für die Farbe des Spielers aufgerufen 1 für Weiss 0 für Schwarz
	 * 
	 * @param nameSpieler2
	 * 
	 * @param x
	 */

	public Spieler(String nameSpieler2, int x) {

		nameSpieler2 = name;

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
			List<SpielStein> SpielerSchwarz = new ArrayList<SpielStein>();

			// PosX,PosY,Farbe
			SpielerSchwarz.add(new SpielStein(0, 1, 0)); // A2
			SpielerSchwarz.add(new SpielStein(1, 0, 0)); // B1
			SpielerSchwarz.add(new SpielStein(1, 2, 0)); // B3
			SpielerSchwarz.add(new SpielStein(2, 1, 0)); // C2
			SpielerSchwarz.add(new SpielStein(3, 0, 0)); // D1
			SpielerSchwarz.add(new SpielStein(3, 2, 0)); // D3
			SpielerSchwarz.add(new SpielStein(4, 1, 0)); // E2
			SpielerSchwarz.add(new SpielStein(5, 0, 0)); // F1
			SpielerSchwarz.add(new SpielStein(5, 2, 0)); // F3
			SpielerSchwarz.add(new SpielStein(6, 1, 0)); // G2
			SpielerSchwarz.add(new SpielStein(7, 0, 0)); // H1
			SpielerSchwarz.add(new SpielStein(7, 2, 0)); // H3

			

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
	 * Gibt alle Figuren zurück. Schwarz & Weiss
	 */

	public ArrayList<ISpielFigur> gibFiguren() {
		return this.figuren;
	}

	public String gibName() {

		return this.name;
	}

}