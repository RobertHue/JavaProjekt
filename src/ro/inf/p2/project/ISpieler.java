package ro.inf.p2.project;

import java.util.ArrayList;

public interface ISpieler {

	boolean entferneSpielFigur(int posX, int posY);
	
	// Getter
	int gibAnzahlSteine();
	ISpielFigur gibFigur(int posX, int posY);
	ArrayList<ISpielFigur> gibFiguren();
	String gibName();
}
