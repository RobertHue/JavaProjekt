package ro.inf.p2.project;
import java.util.ArrayList;

/**
*@author Pascal Zimmermann
*@date 30.06.14
*/
public interface ISpielfeldAnzeige {
	
	/**
	 * Zeichnet das Spielfeld neu
	 * 
	 * @param spieler1 Liste der weissen Spielsteine
	 * @param spieler2 Liste der schwarzen Spielsteine
	 * @param istAmZug Name des Spielers, welcher am Zug ist
	 * @param selektiert selektierte Figur
	 */
	public abstract void neuZeichnen (ArrayList<ISpielFigur> spieler1,
				ArrayList<ISpielFigur> spieler2, String istAmZug, ISpielFigur selektiert);
	
	
	/**
	 * schaltet die Fokussierbarkeit des Spielfeldfensters ein
	 */
	public abstract void fokusAn();
	
	/**
	 * schaltet die Fokussierbarkeit des Spielfeldfensters aus
	 */
	public abstract void fokusAus();
}
