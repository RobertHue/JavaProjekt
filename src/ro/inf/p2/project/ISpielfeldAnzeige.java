package ro.inf.p2.project;
import java.util.ArrayList;

/**
*@author Pascal Zimmermann
*@date 26.06.14
*/
public interface ISpielfeldAnzeige {
	
	public abstract void neuZeichnen (ArrayList<ISpielFigur> spieler1,
				ArrayList<ISpielFigur> spieler2, String istAmZug, ISpielFigur selektiert);
	
	public abstract void fokusAn();
	
	public abstract void fokusAus();
}
