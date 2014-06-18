package ro.inf.p2.project;

import java.util.ArrayList;


//import java.util.ArrayList;


public interface ISpiel {

	



	boolean istAmZug();		
	boolean hatGewonnen();
	

	ArrayList<ISpielFigur> spielerSchwarz = new ArrayList<ISpielFigur>();
	ArrayList<ISpielFigur> spielerWeiss = new ArrayList<ISpielFigur>();
	
	
	public ArrayList<ISpielFigur> gibSpielFigurenSchwartz();
	
	
	
	
	
	/*
	 * istAmZug() ISpieler
	 * hatGewonnen() ISpieler
	 * gibSpielFigurenSchwartz() ArrayList <ISpielFigur>
	 * gibSpielFigurenWeiß() ArrayList <ISpielFigur>
	 * prüfeObGewonnen() ISpieler
	 * gibSelektierteFigur () ISpielFigur
	 * gibIstAmZug () ISpieler
	 * zugBeenden () void
	 * neustarten () void
	 * aufgeben () void
	 * 
	 */
	
}
