package ro.inf.p2.project;

public class DameStein extends SpielStein{

	/**
	 * Dieser Konstruktor erzeugt einen DameStein der an der Position des �bergebenen SpielSteins sich befindet
	 * -> wird ben�tigt, um einen SpielStein in einen DameStein umzuwandeln
	 * @param posX
	 * @param posY
	 */
	public DameStein(ISpielFigur figur) {
		super(figur.gibPosX(), figur.gibPosX(), figur.gibFarbe() );
	}


	public boolean gueltigeBewegungsRichtung( int posX, int posY)
	{
		boolean result = true;
		int diffX = posX-this.gibPosX();
		int diffY = posY-this.gibPosY();
		
		// Dame erh�lt zus�tzliche Bewegungsrichtungen
		// -> unabh�ngig von der Farbe werden die Richtungsvektoren (1,1)(1,-1)(-1,1)(-1,-1) �berpr�ft
		if(!((diffX == -1 && diffY ==1) || (diffX == 1 && diffY == 1) ||
				(diffX == 1 && diffY == -1) || (diffX == -1 && diffY == -1) ))
		{
				result = false;		// Bew. nicht m�glich
		}
		
		return result;
	}
	
	
	
}
