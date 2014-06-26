package ro.inf.p2.project;

import java.util.ArrayList;

public class Spiel implements ISpiel {

	public boolean istAmZug() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hatGewonnen() {
		// TODO Auto-generated method stub
		return false;
	}

	public ArrayList<ISpielFigur> gibSpielFigurenSchwartz() {
		return spielerSchwarz  ;
	}

	public ArrayList<ISpielFigur> gibSpielFigurenWeiss() {
		return spielerWeiss;
	}

	public ISpieler pruefeObGewonnen() { // Winning logic
		// TODO Auto-generated method stub

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
