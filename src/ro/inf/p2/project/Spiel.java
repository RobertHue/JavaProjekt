package ro.inf.p2.project;

import java.util.ArrayList;
import java.util.Set;

import sun.security.action.GetBooleanAction;

public class Spiel implements ISpiel {

	ISpielFigur SpielStein;
	ISpieler aktiverSpieler;
	ISpielFeld spielFeld;
	ISpieler gegnerSpieler;
	
	
	
	
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

		else if (spielFeld.figurenDieSichBewegenKoennen(aktiverSpieler).isEmpty()
				
				&& spielFeld.figurenDieSpringenKoennen(aktiverSpieler).isEmpty()) { 
			
			return gegnerSpieler;
			
		}

		return null;

	}

	public ISpielFigur gibSelektierteFigur() {

		this.spielFeld.figurSelektieren(aktiverSpieler,
				this.SpielStein.gibPosX(), SpielStein.gibPosY());

		return SpielStein;
	}

	public int figurSelektieren(int posX, int posY) {
		
		 int fehlercode =spielFeld.figurSelektieren(aktiverSpieler, posX, posY);
		
		 
		
		// TODO Auto-generated method stub
		return fehlercode;
	}

	public void zugBeenden() {
		// TODO Auto-generated method stub 		Für den nächsten Spieler gleich Springcheck

	}

	public void neustarten() {
		// TODO Auto-generated method stub

	}

	public void aufgeben() {
		// TODO Auto-generated method stub

	}

}
