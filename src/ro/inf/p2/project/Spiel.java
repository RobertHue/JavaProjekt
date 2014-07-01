package ro.inf.p2.project;

import java.util.ArrayList;
import java.util.Iterator;

public class Spiel implements ISpiel {

	ISpielFigur selectierterSpielStein;
	ISpieler aktiverSpieler;
	ISpielFeld spielFeld;
	ISpieler gegnerSpieler;
	IController controller;

	/**
	 * Schiebt die bewegeNach() Methode weiter nach SpielFeld
	 * 
	 * @param figur
	 * @param zielPosX
	 * @param zielPosY
	 * @return
	 */
	public boolean bewegeNach(ISpielFigur figur, int zielPosX, int zielPosY) {

		return this.spielFeld.bewegeNach(figur, zielPosX, zielPosY);

	}

	/**
	 * Konstruktor von Spiel
	 * 
	 * @param nameSP1
	 * @param nameSP2
	 */
	public Spiel(String nameSP1, String nameSP2) {

		SpielFeld player = new SpielFeld(nameSP1, nameSP2);

	}

	public ISpieler gibIstAmZug() {

		return aktiverSpieler;
	}

	public boolean hatGewonnen() {

		if (pruefeObGewonnen() != null) {
			return true;
		} else
			return false;
	}

	public ArrayList<ISpielFigur> gibSpielFigurenSchwarz() {
		return spielerSchwarz;
	}

	public ArrayList<ISpielFigur> gibSpielFigurenWeiss() {
		return spielerWeiss;
	}

	public ISpieler pruefeObGewonnen() { // Winning logic

		if (this.spielerSchwarz.isEmpty() == true) {

			return aktiverSpieler;

		} else if (this.spielerWeiss.isEmpty() == true) {
			return aktiverSpieler;
		}

		else if (spielFeld.figurenDieSichBewegenKoennen(aktiverSpieler)
				.isEmpty()

		&& spielFeld.figurenDieSpringenKoennen(aktiverSpieler).isEmpty()) {

			return gegnerSpieler;

		}

		return null;

	}

	public ISpielFigur gibSelektierteFigur() {

		this.spielFeld.figurSelektieren(aktiverSpieler,
				this.selectierterSpielStein.gibPosX(),
				selectierterSpielStein.gibPosY());

		return selectierterSpielStein;
	}

	
	/**
	 * 
	 */
	public int figurSelektieren(int posX, int posY) {

		int fehlercode = spielFeld.figurSelektieren(aktiverSpieler, posX, posY);

		if (fehlercode == 0) {

			ArrayList<ISpielFigur> tmp = spielFeld.gibAlleSpielFiguren();

			Iterator<ISpielFigur> i = tmp.iterator();

			while (i.hasNext()) {

				ISpielFigur sucheSelktierteFigur = i.next();

				if (sucheSelktierteFigur.gibPosX() == posX
						&& sucheSelktierteFigur.gibPosY() == posY) {

					this.selectierterSpielStein = sucheSelktierteFigur;
				}
			}
		}

		return fehlercode;
	}

	public void zugBeenden() {

		ISpieler substitute = aktiverSpieler;

		gegnerSpieler = aktiverSpieler;
		aktiverSpieler = substitute;

		spielFeld.updateSprungFaehigkeiten(aktiverSpieler);

		this.spielFeld.bewegeNach(selectierterSpielStein,
				this.selectierterSpielStein.gibPosX(),
				this.selectierterSpielStein.gibPosY());

	}

	public void neustarten() {

		this.controller.neustarten(true);

	}

	public void aufgeben() {

		this.controller.aufgeben(true);

	}

	// setzName

	// public String setzeName() {
	//
	//
	//
	// return this.name;
	// }
}
