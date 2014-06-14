package ro.inf.p2.project;

import java.util.ArrayList;

public interface ISpieler {

	boolean entferneSpielFigur(int posX, int posY);
	boolean entferneSpielFigur(ISpielFigur figur);
	void fuegeDameZurListeHinzu(ISpielFigur figur);
	
	// Setter
	public void setzeName(String name);
	
	// Getter
	int gibAnzahlSteine();
	ArrayList<ISpielFigur> gibFiguren();
	String gibName();
	

	// ISpielFigur gibFigur(int posX, int posY); 
	// -> brauchen wir wahrscheinlich eher nicht
}
