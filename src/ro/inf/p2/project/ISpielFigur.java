package ro.inf.p2.project;
 
/**
 * Interface: ISpielFigur
 * @author Robert
 */
public interface ISpielFigur {

	/**
	 * Ueberprueft ob die uebergebene Zielposition mit der SpielFigur
	 * von der Richtung und der Laenge her moeglich ist
	 * Zielposition wird dahingehend überprüft ob sie sich im SpielFeld befindet
	 */
	public boolean gueltigeBewegung( int posX, int posY);
	
	// Setter:
	/**
	 * Aendere die Position der Spielfigur
	 * @param x
	 * @param y
	 */
	public void positionAendern(int x, int y);
	/**
	 * setzt die Sprungfaehigkeit auf Status
	 * @param status
	 */
	public void setzteSprungFaehigkeit(boolean status);
	
	// Getter:
	/**
	 * gibt die X position zurueck
	 * @return
	 */
	public int gibPosX();
	/**
	 * Gibt die Y Position zurueck
	 * @return
	 */
	public int gibPosY();
	/**
	 * gibt die Farbe der Spielfigur zurueck
	 * @return
	 */
	public int gibFarbe();	
	/**
	 * gibt zurueck ob die Sprungfaehigkeit gesetzt ist oder nicht
	 * @return
	 */
	public boolean kannIchSpringen();
}
