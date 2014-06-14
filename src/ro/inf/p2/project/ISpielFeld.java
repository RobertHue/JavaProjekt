package ro.inf.p2.project;

import java.util.ArrayList;

public interface ISpielFeld {

	// Konstanten
	public static final int MAX_X = 8;
	public static final int MAX_Y = 8;
	
	// für den Anfang eins SpielZugs
	public void updateStatus(ISpieler spieler);
	
	// Getter
	public ISpieler gibSpielerSchwarz();
	public ISpieler gibSpielerWeiss();
	public ArrayList<ISpielFigur> gibAlleSpielFiguren();
	
	// Setter
	public boolean bewegeNach( ISpielFigur figur, int zielPosX, int zielPosY );
	
	// selektieren
	public int figurSelektieren(ISpieler spieler, int posX, int posY);
}
