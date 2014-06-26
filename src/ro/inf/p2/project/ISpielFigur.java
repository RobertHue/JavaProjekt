package ro.inf.p2.project;

/**
 * Interface f�r die abstract & �ffentlichen Methoden
 * @author Robert
 *
 */
public interface ISpielFigur {

	/**
	 * �berpr�ft lediglich ob die �bergebene Zielposition mit der SpielFigur
	 * von der Richtung und der L�nge her m�glich ist
	 * in anderen Worten: eine Bewegung, ohne Beachtung der Umgebung, g�ltig ist
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean gueltigeBewegung( int posX, int posY);
	
	// Setter:
	/**
	 * �ndere die Position der Spielfigur
	 * @param x
	 * @param y
	 */
	public void positionAendern(int x, int y);
	/**
	 * setzt die Sprungf�hgikeit auf status
	 * @param status
	 */
	public void setzteSprungFaehigkeit(boolean status);
	
	// Getter:
	/**
	 * gibt die x position zur�ck
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
	 * gibt zur�ck ob die Sprungf�higkeit gesetzt ist oder nicht
	 * @return
	 */
	public boolean kannIchSpringen();
}
