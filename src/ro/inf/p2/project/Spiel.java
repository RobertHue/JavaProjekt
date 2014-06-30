package ro.inf.p2.project;

import java.util.ArrayList;
import java.util.Set;

import sun.security.action.GetBooleanAction;

public class Spiel implements ISpiel {

	public boolean istAmZug() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hatGewonnen() {

		//if (pruefeObGewonnen(Spieler) != null)

			// TODO Auto-generated method stub
			return false;
	}

	public ArrayList<ISpielFigur> gibSpielFigurenSchwartz() {
		return spielerSchwarz;
	}

	public ArrayList<ISpielFigur> gibSpielFigurenWeiss() {
		return spielerWeiss;
	}

	public ISpieler pruefeObGewonnen() { // Winning logic
		// TODO Auto-generated method stub

		if (this.spielerSchwarz.isEmpty() == true) {

			return (ISpieler) this.spielerSchwarz;

		} else if (this.spielerWeiss.isEmpty() == true) {
			return (ISpieler) this.spielerWeiss;
		}

		else if ( SpielStein.kannIchSpringen( ISpielFigur figur) == true)

			return null;
	}

	public ISpieler gibIstAmZug() {
		// TODO Auto-generated method stub
		return null;
	}

	public ISpielFigur gibSelektierteFigur() {
		// TODO Auto-generated method stub
		return null;
	}

}
