package ro.inf.p2.project;

/**
 * Interface für die abstract & öffentlichen Methoden
 * @author Robert
 *
 */
public interface ISpielFigur {

	/**
	 * bewegt die SpielFigur von ihrer derzeitigen Position aus nach x und y
	 * und überprüft ob dies überhaupt möglich ist
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean gueltigeBewegungsRichtung( int posX, int posY);
	public void positionAendern(int x, int y);
	
	// Getter:
	public int gibPosX();
	public int gibPosY();
	public int gibFarbe();	 
}
