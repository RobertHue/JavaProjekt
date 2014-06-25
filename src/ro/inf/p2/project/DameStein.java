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


	
	
	public boolean gueltigeBewegung( int posX, int posY)
	{
		boolean result = true;
		int diffX = Math.abs(posX-this.gibPosX());
		int diffY = Math.abs(posY-this.gibPosY());

		// Bewegung ist l�nger als ein Sprung
		if(diffX > 2)	return false;
		if(diffY > 2)	return false;
		
		// Dame erh�lt zus�tzliche Bewegungsrichtungen
		// -> unabh�ngig von der Farbe werden die Richtungsvektoren (1,1)(1,-1)(-1,1)(-1,-1) �berpr�ft
		if( !((diffX == 1 && diffY ==1) || (diffX == 2 && diffY == 2)) )
		{
				result = false;		// Bew. nicht m�glich
		}
		
		return result;
	}
	
	
	
}
