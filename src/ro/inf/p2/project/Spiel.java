package ro.inf.p2.project;

import java.util.ArrayList;
import java.util.Iterator;

public class Spiel implements ISpiel {

	ISpielFigur selectierterSpielStein = null;
	ISpieler aktiverSpieler= null;
	ISpielFeld spielFeld= null;
	ISpieler gegnerSpieler= null;
	IController controller= null;

	//static final ArrayList<ISpieler> spielerSchwarz = new ArrayList<ISpieler>();
//	static final ArrayList<ISpielFigur> spielerWeiss = new ArrayList<ISpielFigur>();

	//spielerSchwarz = new Spieler(name, 0);
	//spielerWeiss = new Spieler(name,0);
	
	
	
	
//	
//	spielerSchwarz = new Spieler(nameSpieler1, 0);	
//	spielerWeiss   = new Spieler(nameSpieler2, 1);
//	
	
	
	
	// gibKannSpringen () wieder soon schubser nach SpielFeld

	/**
	 * Mittelsstelle für die gibKannSpringen Methode Controller -> SpielFeld
	 */
	public boolean gibKannSpringen(ISpielFigur gibSelektierteFigur) {

		return spielFeld.kannSpielFigurSpringen(selectierterSpielStein); // this.gibKannSpringen(selectierterSpielStein);
	}

	/**
	 * Mittelsstelle für bewegeNach() Controller -> SpielFeld
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
	public Spiel(String nameSpieler1, String nameSpieler2) {

		spielFeld = new SpielFeld(nameSpieler1, nameSpieler2);
//		
//		ISpieler spielerSchwarz = new Spieler(nameSpieler1, 0);	
//		ISpieler spielerWeiss   = new Spieler(nameSpieler2, 1);
//		
		
	}

	/**
	 * Gibt den momentan Aktiven Spieler zurück
	 */
	public ISpieler gibIstAmZug() {

		return aktiverSpieler;
	}

	/**
	 * prüft Obgewonen() bei null wurde noch nicht gewonnen
	 */
	public boolean hatGewonnen() {

		if (pruefeObGewonnen() != null) {
			return true;
		} else
			return false;
	}

	public ArrayList<ISpielFigur> gibSpielFigurenSchwarz() {
		return this.aktiverSpieler.gibFiguren();//   spielerSchwarz;
	}

	public ArrayList<ISpielFigur> gibSpielFigurenWeiss() {
		return this.aktiverSpieler.gibFiguren();  //spielerWeiss;
	}

	/**
	 * Prüft die Anzahl der Spielsteine der Spieler Weiss\Schwarz ,da keine
	 * Steine = Verloren Prüft ob sich überhaupt noch Spielsteine bewegen
	 * können, wenn nicht dann = Verloren
	 */
	public ISpieler pruefeObGewonnen() { // Winning logic

		if (this.gibSpielFigurenSchwarz().isEmpty() == true) {

			return aktiverSpieler;

		} else if (this.gibSpielFigurenWeiss().isEmpty() == true) {
			return aktiverSpieler;
		}

		else if (spielFeld.figurenDieSichBewegenKoennen(aktiverSpieler)
				.isEmpty()

		&& spielFeld.figurenDieSpringenKoennen(aktiverSpieler).isEmpty()) {

			return gegnerSpieler;

		}

		return null;

	}

	/**
	 * Gibt die selectierte Spielfigur zurück mit Posx und PosY
	 */
	public ISpielFigur gibSelektierteFigur() {

		spielFeld.figurSelektieren(aktiverSpieler,
				this.selectierterSpielStein.gibPosX(),
				selectierterSpielStein.gibPosY());

		ArrayList<ISpielFigur> tmp = spielFeld.gibAlleSpielFiguren();

		Iterator<ISpielFigur> i = tmp.iterator();

		while (i.hasNext()) {

			ISpielFigur sucheSelktierteFigur = i.next();

			if (sucheSelktierteFigur.gibPosX() == selectierterSpielStein
					.gibPosX()
					&& sucheSelktierteFigur.gibPosY() == selectierterSpielStein
							.gibPosY()) {

				return this.selectierterSpielStein = sucheSelktierteFigur;

			}
			//
			// this.spielFeld.figurSelektieren(aktiverSpieler,
			// this.selectierterSpielStein.gibPosX(),
			// selectierterSpielStein.gibPosY());

		} // selectierterSpielStein;
		return null;
	}

	/**
	 * Prüft denn fehlercode, bei 0 ist OK. Geht mit dem Iterator durch die
	 * Figuren Liste und sucht nach der selektierten Figur mit der PosXY werten,
	 * wenn gefunden wird die figur der selectiertenFigur zugewiessen. Sonst
	 * return fehlercode
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

	/**
	 * Tauscht den aktieven Spieler mit dem Gegner spieler über ein substitut
	 * und ruft dann die UpdateSprungFaehigkeit auf, dannach die bewegeNech
	 * methode
	 */
	public void zugBeenden() {

		ISpieler tmp = aktiverSpieler;
		aktiverSpieler = gegnerSpieler;
		gegnerSpieler = tmp;

		selectierterSpielStein = null;

		spielFeld.updateSprungFaehigkeiten(aktiverSpieler);

	}

	/**
	 * Setzt für den Controller das neustarten auf true, das der weiss ja jetzt
	 * neustarten
	 */
	public void neustarten() {

		this.controller.neuesSpielStarten(this.aktiverSpieler.gibName(),
				this.gegnerSpieler.gibName());
		;

	}

	/**
	 * Setzt für den Controller das aufgeben auf true , das der weiss ja jetzt
	 * aufgeben
	 */
	public void aufgeben() {

		this.controller.ende(true);

	}

}
