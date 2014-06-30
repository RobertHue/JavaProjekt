package ro.inf.p2.project;
/**
*@author Pascal Zimmermann
*@date 30.06.14
*/
public interface IController {

	/**
	 * Wird von der SpielfeldAnzeige aufgerufen, wenn auf den "Neustarten"-Knopf gedrueckt wurde.
	 * Laesst ein PopUp oeffnen, bei dem gefragt wird ob man wirklich neustarten will
	 */
	public abstract void neustartGedrueckt();
	
	/**
	 * Wird von der SpielfeldAnzeige aufgerufen, wenn auf den "Aufgeben"-Knopf gedrueckt wurde.
	 * Laesst ein PopUp oeffnen, bei dem gefragt wird ob man wirklich aufgeben will
	 */
	public abstract void aufgebenGedrueckt();
	
	/**
	 * Reagiert, wenn ein Button des Spielfelds gedrueckt wurde.
	 * Zunaechst wird versucht die Spielfigur auf den Koordinaten zu selektieren.
	 * Falls bereits eine Figur selektiert ist, dann wird versucht diese auf die Koordinaten des Feldes zu bewegen.
	 * @param posX X-Position des gedrueckten Feldknopfes
	 * @param posY Y-Position des gedrueckten Feldknopfes
	 */
	public abstract void feldKnopfGedrueckt(int posX, int posY);
	
	public abstract void popUpAufrufen(int code);
	public abstract void popUpAufrufen(int code, ISpieler gewinner);
	
	
	public abstract void fehlermeldungAusgeben(int code);
	
	
	public abstract void neuesSpielStarten(String name1, String name2);
	
	/**
	 * Je nach Ergebnis des PopUp-Fensters gibt der sich am Zug befindende Spieler auf oder es passiert nichts.
	 * @param jaodernein Die Antwort des PopUps
	 */
	public abstract void aufgeben(boolean jaodernein);
	
	/**
	 * Je nach Ergebnis des PopUp-Fensters startet das Spiel neu oder nicht.
	 * @param jaodernein Die Antwort des PopUps
	 */
	public abstract void neustarten(boolean jaodernein);
	
	/**
	 * Je nach Ergebnis des PopUp-Fensters wird ein neues Spiel gestartet oder das Programm beendet.
	 * @param jaodernein Die Antwort des PopUps
	 */
	public abstract void ende(boolean jaodernein);
	
	/**
	 * Je nach Ergebnis des PopUp-Fensters darf die selektierte Figur noch mal Springen oder der Zug wird beendet.
	 * @param jaodernein Die Antwort des PopUps
	 */
	public abstract void nochmalSpringen(boolean jaodernein);

}
