package ro.inf.p2.project;





public class SpielStein implements ISpielFigur{
	//**********************
	// Attribute
	//**********************
	protected int posX;
	protected int posY;
	private int farbe;	// 0=schwarz, 1=weiss
	private boolean kannSpringen;

	//**********************
	// Konstruktor
	//**********************
	public SpielStein(int posX, int posY, int farbe) {
		this.posX = posX;
		this.posY = posY;
		this.farbe = farbe;
		
		// by default
		this.kannSpringen = false;
	}

	
	public boolean gueltigeBewegung( int posX, int posY)
	{		
		int diffX = posX-this.posX;
		int diffY = posY-this.posY;

		// Bewegung ist l�nger als ein Sprung
		if(diffX > 2)	return false;
		if(diffY > 2)	return false;
		
		if( this.gibFarbe()==0 && !validiereRichtungsVektorSchwarz(diffX, diffY)  )
		{
			return false;		// Bew. nicht m�glich
		}
		if( this.gibFarbe()==1 && !validiereRichtungsVektorWeiss(diffX, diffY)  )
		{
			return false;		// Bew. nicht m�glich			
		}

		return true;
	}


	//*************************
	// Hilfsmethoden
	//*************************
	protected boolean validiereRichtungsVektorSchwarz(int diffX, int diffY) {
		// f�r Schwarz sind es die Richtungsvektoren (-1,1) und (1,1)
		return (diffX == -1 && diffY ==1) || (diffX == 1 && diffY == 1) ||
			   (diffX == -2 && diffY ==2) || (diffX == 2 && diffY == 2);
	}
	protected boolean validiereRichtungsVektorWeiss(int diffX, int diffY) {
		// f�r Wei�  sind es die Richtungsvektoren (1,-1) und (-1,-1)
		return (diffX == 1 && diffY == -1) || (diffX == -1 && diffY == -1) ||
			   (diffX == 2 && diffY == -2) || (diffX == -2 && diffY == -2);
	}


	//*************************
	// Setter
	//*************************
	public void positionAendern(int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	public void setzteSprungFaehigkeit(boolean status)
	{
		this.kannSpringen = status;
	}

	
	
	//*************************
	// Getter
	//*************************
	public int gibPosX() {
		return this.posX;
	}

	public int gibPosY() {
		return this.posY;
	}

	public int gibFarbe() {
		return this.farbe;
	}
	public boolean kannIchSpringen()
	{
		return this.kannSpringen;
	}

}
