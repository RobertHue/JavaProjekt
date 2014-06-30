package ro.inf.p2.project;


/**
 * Klasse: DameStein
 * @author Robert
 */
public class DameStein extends SpielStein{


	public DameStein(int posX, int posY, int farbe)
	{
		super(posX, posY, farbe);
	}
	public DameStein(ISpielFigur figur) 
	{
		super(figur.gibPosX(), figur.gibPosX(), figur.gibFarbe() );
	}


	
	
	public boolean gueltigeBewegung( int posX, int posY)
	{
		// falls Zielposition außerhalb des Spielfeldes ist
		if( !validiereZielImSpielFeld(posX, posY) )	return false;
		
		int diffX = Math.abs(posX-this.gibPosX());
		int diffY = Math.abs(posY-this.gibPosY());

		// Bewegung ist länger als ein Sprung
		if(diffX > 2)	return false;
		if(diffY > 2)	return false;
		
		// Dame erhält zusätzliche Bewegungsrichtungen
		// -> unabhängig von der Farbe
		if( !validiereRichtungsVektorSchwarz(diffX, diffY) ||
			!validiereRichtungsVektorWeiss(diffX, diffY))
		{
				return false;		// Bew. nicht möglich
		}
		
		return true;
	}
	
	
	
}
