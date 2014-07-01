package ro.inf.p2.project;

import java.util.ArrayList;

/**
 * Interface: ISpielFeld
 * @author Robert
 */
public interface ISpielFeld {

	// Konstanten
	public static final int MAX_X = 8;
	public static final int MAX_Y = 8;
	
	// für den Anfang eins SpielZugs
	public void updateSprungFaehigkeiten(ISpieler spieler);
	
	// Getter
	public ISpieler gibSpielerSchwarz();
	public ISpieler gibSpielerWeiss();
	public ArrayList<ISpielFigur> gibAlleSpielFiguren();
	public ArrayList<ISpielFigur> figurenDieSpringenKoennen(ISpieler spieler);
	public ArrayList<ISpielFigur> figurenDieSichBewegenKoennen(ISpieler spieler);
	
	// Setter
	public boolean bewegeNach( ISpielFigur figur, int zielPosX, int zielPosY );
	
	// Selektieren
	public int figurSelektieren(ISpieler spieler, int posX, int posY);
}
