package ro.inf.p2.project;
 
/**
 * Interface fuer die abstract & fentlichen Methoden
 * @author Robert
 *
 */
public interface ISpielFigur {

	/**
	 * ueberprueft lediglich ob die uebergebene Zielposition mit der SpielFigur
	 * von der Richtung und der L舅ge her mlich ist
	 * in anderen Worten: eine Bewegung, ohne Beachtung der Umgebung, gueltig ist
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean gueltigeBewegung( int posX, int posY);
	
	// Setter:
	/**
	 * 舅dere die Position der Spielfigur
	 * @param x
	 * @param y
	 */
	public void positionAendern(int x, int y);
	/**
	 * setzt die Sprungf臧gikeit auf status
	 * @param status
	 */
	public void setzteSprungFaehigkeit(boolean status);
	
	// Getter:
	/**
	 * gibt die x position zurueck
	 * @return
	 */
	public int gibPosX();
	/**
	 * gibt die y Position zur�ck
	 * @return
	 */
	public int gibPosY();
	/**
	 * gibt die Farbe der Spielfigur zur�ck
	 * @return
	 */
	public int gibFarbe();	
	/**
	 * gibt zur�ck ob die Sprungf臧igkeit gesetzt ist oder nicht
	 * @return
	 */
	public boolean kannIchSpringen();
}
