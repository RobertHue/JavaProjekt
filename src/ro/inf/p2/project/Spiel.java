package ro.inf.p2.project;

import java.util.ArrayList;
import java.util.Set;

import sun.security.action.GetBooleanAction;

public class Spiel implements ISpiel {

	ISpielFigur SpielStein;
	ISpieler Spieler;
	ISpielFeld spielFeld;

	public boolean istAmZug() {
		// TODO Auto-generated method stub
		return false;
	}

	public ISpieler gibIstAmZug() { // Was was noch kommt...
		// TODO Auto-generated method stub

		this.istAmZug();

		return Spieler;
	}

	public boolean hatGewonnen() {

		 if (pruefeObGewonnen() != null) {
			 return  true;
		 }
		 else
		return false;
	}

	public ArrayList<ISpielFigur> gibSpielFigurenSchwartz() {
		return spielerSchwarz;
	}

	public ArrayList<ISpielFigur> gibSpielFigurenWeiss() {
		return spielerWeiss;
	}

	public ISpieler pruefeObGewonnen() { // Winning logic

		if (this.spielerSchwarz.isEmpty() == true) {

			return Spieler;

		} else if (this.spielerWeiss.isEmpty() == true) {
			return Spieler;
		}

		else if (SpielStein.kannIchSpringen() == false) { // Warscheinlich
															// Falsch... Brauch
															// die methode die
															// die
															// BewegungsFähigket
															// prüft
			return Spieler;
			//
		}

		return null;

	}

	public ISpielFigur gibSelektierteFigur() {

		this.spielFeld.figurSelektieren(Spieler, this.SpielStein.gibPosX(),
				SpielStein.gibPosY());

		return SpielStein;
	}

}
