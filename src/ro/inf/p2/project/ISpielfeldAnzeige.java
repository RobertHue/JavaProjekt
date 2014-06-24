package ro.inf.p2.project;
import java.util.ArrayList;

/**
*@author Pascal
*/
public interface ISpielfeldAnzeige {
	
	public abstract void neuZeichnen (ArrayList<ISpielFigur> spieler1,
				ArrayList<ISpielFigur> spieler2, String istAmZug, ISpielFigur selektiert);
	
	public abstract void fokusAn();
	
	public abstract void fokusAus();
}
