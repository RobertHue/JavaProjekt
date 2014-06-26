package ro.inf.p2.project;

/**
 * Interface für die abstract & öffentlichen Methoden
 * @author Robert
 *
 */
public interface ISpielFigur {

	/**
	 * überprüft lediglich ob die übergebene Zielposition mit der SpielFigur
	 * von der Richtung und der Länge her möglich ist
	 * in anderen Worten: eine Bewegung, ohne Beachtung der Umgebung, gültig ist
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean gueltigeBewegung( int posX, int posY);
	
	// Setter:
	/**
	 * ändere die Position der Spielfigur
	 * @param x
	 * @param y
	 */
	public void positionAendern(int x, int y);
	/**
	 * setzt die Sprungfähgikeit auf status
	 * @param status
	 */
	public void setzteSprungFaehigkeit(boolean status);
	
	// Getter:
	/**
	 * gibt die x position zurück
	 * @return
	 */
	public int gibPosX();
	/**
	 * gibt die y Position zurück
	 * @return
	 */
	public int gibPosY();
	/**
	 * gibt die Farbe der Spielfigur zurück
	 * @return
	 */
	public int gibFarbe();	
	/**
	 * gibt zurück ob die Sprungfähigkeit gesetzt ist oder nicht
	 * @return
	 */
	public boolean kannIchSpringen();
}
