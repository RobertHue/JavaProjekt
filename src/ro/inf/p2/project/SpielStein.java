package ro.inf.p2.project;



public class SpielStein implements ISpielFigur{
	//**********************
	// Attribute
	//**********************
	protected int posX;
	protected int posY;
	private int farbe;	// 0=schwarz, 1=weiss
	

	//**********************
	// Konstruktor
	//**********************
	public SpielStein(int posX, int posY, int farbe) {
		this.posX = posX;
		this.posY = posY;
		this.farbe = farbe;
	}

	
	public boolean gueltigeBewegungsRichtung( int posX, int posY)
	{
		boolean result = true;
		
		int diffX = posX-this.posX;
		int diffY = posY-this.posY;
		
		if(this.gibFarbe()==0)
		{
			// f�r Schwarz sind es die Richtungsvektoren (-1,1) und (1,1)
			if(  !((diffX == -1 && diffY ==1) || (diffX == 1 && diffY == 1))  )
				result = false;		// Bew. nicht m�glich
		}
		else if(this.gibFarbe()==1)
		{
			// f�r Wei�  sind es die Richtungsvektoren (1,-1) und (-1,-1)
			if(  !((diffX == 1 && diffY == -1) || (diffX == -1 && diffY == -1))  )
				result = false;		// Bew. nicht m�glich			
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

}
