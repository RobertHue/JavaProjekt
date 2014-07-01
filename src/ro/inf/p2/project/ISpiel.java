package ro.inf.p2.project;

import java.util.ArrayList;


//import java.util.ArrayList;


public interface ISpiel {

	



	boolean  istAmZug();		
	boolean hatGewonnen();
	

	ArrayList<ISpielFigur> spielerSchwarz = new ArrayList<ISpielFigur>();
	ArrayList<ISpielFigur> spielerWeiss = new ArrayList<ISpielFigur>();
	
	
	public ArrayList<ISpielFigur> gibSpielFigurenSchwarz();
	public ArrayList<ISpielFigur> gibSpielFigurenWeiss();

	
	public ISpieler pruefeObGewonnen();
	public ISpieler gibIstAmZug();
	public ISpielFigur gibSelektierteFigur();
	
	/*
	 * istAmZug() ISpieler
	 * hatGewonnen() ISpieler
	 * gibSpielFigurenSchwarz() ArrayList <ISpielFigur>
	 * gibSpielFigurenWeiß() ArrayList <ISpielFigur>
	 * 
	 * prüfeObGewonnen() ISpieler
	 * gibSelektierteFigur () ISpielFigur
	 * gibIstAmZug () ISpieler			
	 * 
	 * zugBeenden () void				Contr
	 * neustarten () void				Contr
	 * aufgeben () void					Contr
	 * 
	 */
	
}
