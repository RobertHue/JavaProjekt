package ro.inf.p2.project;
/**
*@author Pascal Zimmermann
*@date 26.06.14
*/
public interface IController {

	public abstract void neustartGedrueckt();
	
	public abstract void aufgebenGedrueckt();
	
	public abstract void feldKnopfGedrueckt(int posX, int posY);
	
	public abstract void popUpAufrufen(int code, ISpieler spieler1, ISpieler spieler2);
	
	public abstract void fehlermeldungAusgeben(int code);
	
	public abstract void neuesSpielStarten(String name1, String name2);
	
	public abstract void aufgeben(boolean jaodernein);
	
	public abstract void neustarten(boolean jaodernein);
	
	public abstract void ende(boolean jaodernein);
	
	public abstract void nochmalSpringen(boolean jaodernein);

}
