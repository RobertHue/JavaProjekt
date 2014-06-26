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
		boolean result = true;
		
		int diffX = posX-this.posX;
		int diffY = posY-this.posY;
		
		if(this.gibFarbe()==0)
		{
			// f�r Schwarz sind es die Richtungsvektoren (-1,1) und (1,1)
			if(  !((diffX == -1 && diffY ==1) || (diffX == 1 && diffY == 1) ||
				   (diffX == -2 && diffY ==2) || (diffX == 2 && diffY == 2))  )
				result = false;		// Bew. nicht mlich
		}
		else if(this.gibFarbe()==1)
		{
			// f�r Weiﾟ  sind es die Richtungsvektoren (1,-1) und (-1,-1)
			if(  !((diffX == 1 && diffY == -1) || (diffX == -1 && diffY == -1) ||
				   (diffX == 2 && diffY == -2) || (diffX == -2 && diffY == -2))  )
				result = false;		// Bew. nicht mlich			
		}
		
		return result;
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
