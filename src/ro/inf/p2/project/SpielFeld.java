package ro.inf.p2.project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * speichert alle Spielsteine indirekt dadurch dass es die Spieler speichert
 * fungiert als Hilfsklasse, um den ausgewählten Spielstein seine Umgebung(das Spielfeld) zu übergeben
 * @author Robert
 *
 */
public class SpielFeld implements ISpielFeld {
	
	private ISpieler spielerSchwarz;
	private ISpieler spielerWeiss;

	
	public SpielFeld() {
		// ruft den Konstruktor von Spieler auf
		// jetzt pushe dich halt!!!!
		spielerSchwarz = new Spieler(0);	// Spieler mit schwarzen SpielFiguren
 		spielerWeiss   = new Spieler(1);	// Spieler mit weißen SpielFiguren
	}
	
	/**
	 * muss vom Spiel in jeder Standby-Phase aufgerufen werden um 
	 * die Fähigkeiten der Steine des Spielers zu setzten der am Zug ist
	 * (wegen Laufzeit-Performance)
	 */
	public void updateStatus(ISpieler spieler)
	{
		Iterator<ISpielFigur> it;
		// setze Status von vorherigen Zug zurück
		ArrayList<ISpielFigur> alleSpielFiguren = this.gibAlleSpielFiguren();
		it = alleSpielFiguren.iterator();
		while(it.hasNext())
		{
			it.next().setzteSprungFaehigkeit(false);
		}
		
		// setze alle 
		ArrayList<ISpielFigur> koennenSpringen = figurenDieSpringenMuessen(spieler);
		it = koennenSpringen.iterator();
		while(it.hasNext())
		{
			it.next().setzteSprungFaehigkeit(true);
		}
	}

	/**
	 * Versucht die Figur auf dem Feld der Koordinaten posX und PosY zu selektieren. 
	 * Falls erfolgreich selektierbar (i=0)
	 * 
	 * Fehler-Codes:
	 * Wenn das Feld der Auswahl leer(i=1), 
	 * Wenn Farbe der selektierten Figur falsch ist(i=2)
	 * Wenn sich die selektierte Figur nicht bewegen/springen kann (i=3)
	 * Wenn sich die selektierte Figur nicht bewegen darf (i=4).
	 * 
	 * @param spieler
	 * @param posX
	 * @param posY
	 * @return
	 */
	public int figurSelektieren(ISpieler spieler, int posX, int posY) {

		ISpielFigur ausgewaehlteFigur = positionIstBesetztDurch( posX, posY );
		
		// falls ausgewählte Position unbesetzt ist
		if( ausgewaehlteFigur.equals(null) )
		{
			return 1;	// Feld der Auswahl ist leer
		}
		
		// falls ausgewählte Figur nicht von spieler ist
		if( !spieler.gibFiguren().contains(ausgewaehlteFigur) )
		{
			return 2;	// Figur ist nicht von dir
		}
		
		if( !istSpielFigurBewegbar(ausgewaehlteFigur) && !ausgewaehlteFigur.kannIchSpringen() )
		{
			return 3;	// Spielfigur kann sich nicht bewegen und auch nicht springen
		}	
		
		if(!ausgewaehlteFigur.kannIchSpringen())
		{
			return 4;	// andere Spielfiguren haben Priorität
		}
		
		
		return 0;	// Auswahl Correct!
	}
	
	


	/**
	 * Diese Methode bewegt eine Spielfigur von ihrer aktuellen Position 
	 * zu einer neuen Position und überprüft ob Zielposition unbesetzt und 
	 * die Bewegungsrichtung der Spielfigur in Ordnung war.
	 * 
	 * Falls Bewegung ein Sprung war, wird dieser ebenfalls auf Gültigkeit überprüft 
	 * und der gegnerische übersprungene Spielstein wird entfernt.
	 * 
	 * Falls Spielfigur das Ende des Spielfeldes erreicht hat, mache sie zur Dame
	 * 
	 * @param figur	die Figur die bewegt werden soll
	 * @param zielPosX die neue Position X
	 * @param zielPosY die neue Position Y
	 * @return	gibt die Gültigkeit der Bewegung an den Aufrufer zurück
	 * 			false = nicht gültige Bewegung; true = gültige Bewegung
	 */
	public boolean bewegeNach( ISpielFigur figur, int zielPosX, int zielPosY)
	{
		// 1.) Sprungziel schon besetzt?
		if( !positionIstBesetztDurch( zielPosX, zielPosY).equals(null) ) 
		{
			return false;
		}
		
		// 2.) Falsche Bewegungsrichtung?
		if( !figur.gueltigeBewegung( zielPosX, zielPosY) )	
		{
			return false;
		}	
			

		// 3.) Bewegung ein Sprung? falls Ja ist der Sprung möglich?
		if(istBewegungEinSprung(figur, zielPosX, zielPosY))
		{
			ISpielFigur ueberSprungener = gueltigerSprung(figur, zielPosX, zielPosY);
			if( ueberSprungener.equals(null) )
			{
				return false;
			}
			else
			{
				// Sprung war korrekt & lösche den übersprungenen SpielStein aus der FigurenListe des jeweiligen Spielers
				if( this.gibSpielerSchwarz().gibFiguren().contains(ueberSprungener))
				{
					this.gibSpielerSchwarz().entferneSpielFigur(ueberSprungener);
				}
				else 
				{
					this.gibSpielerWeiss().entferneSpielFigur(ueberSprungener);
				}
			}		
		}
	
		// Ändere die Position
		figur.positionAendern(zielPosX, zielPosY);
		
		// macheSpielFigurZuDame falls gegenüberliegendes Ende erreicht
		if( ( figur.gibFarbe()==0 && figur.gibPosY()==7 ) ||
			( figur.gibFarbe()==1 && figur.gibPosY()==0 ))
		{
			macheSpielFigurZuDame(figur);
		}
		
		// gib Gültigkeit der Bewegung an den Aufrufer zurück
		return true;
	}
	

	
	
	
	//******************************
	// Hilfsmethoden für SpielFeld
	//******************************
	private boolean freundlicheFiguren(ISpielFigur figur1, ISpielFigur figur2)
	{
		return figur1.gibFarbe() == figur2.gibFarbe();
	}
	
	/**
	 * gibt zurück ob Bewegung ein Sprung ist
	 * @param figur
	 * @param zielPosX
	 * @param zielPosY
	 * @return
	 */
	private boolean istBewegungEinSprung(ISpielFigur figur, int zielPosX, int zielPosY)
	{
		// möglicher Sprung ist alles was einen Richtungsvektor von (2,2) hat
		int diffX = zielPosX-figur.gibPosX();
		int diffY = zielPosY-figur.gibPosY();
		return (Math.abs(diffX)==2 && Math.abs(diffY)==2);
	}
	/**
	 * Überprüft ob die Bewegung ein Sprung war und ob der Sprung erlaubt ist
	 * Falls gültiger Sprung liefere die übersprungene Spielfigur
	 * @param figur
	 * @param zielPosX
	 * @param zielPosY
	 * @return	Sprung erlaubt?
	 */
	private ISpielFigur gueltigerSprung(ISpielFigur figur, int zielPosX, int zielPosY) 
	{
		int diffX = zielPosX-figur.gibPosX();
		int diffY = zielPosY-figur.gibPosY();

		// prüfe Figur auf halber Strecke = 1/2* (A+B) = Mittelpunkt auf Vektor
		ISpielFigur besetztDurch = positionIstBesetztDurch( zielPosX, zielPosY);
		ISpielFigur ueberSprungener = positionIstBesetztDurch( 
				(int)0.5*(figur.gibPosX()+diffX), (int)0.5*(figur.gibPosY()+diffY));
		
		
		if( !besetztDurch.equals(null) )
		{  
			return null;	// Springen nur auf leere Felder möglich
		}
		
		if( ueberSprungener.equals(null) )
		{
			return null;	// Springen über leere Felder nicht möglich
		}

		if( freundlicheFiguren(figur, besetztDurch) )
		{
			return null; 	// Springen über eigene (freundliche) Spielfiguren nicht möglich
		}
		
		return ueberSprungener;
	}
	
	

	
	
	/**
	 * Gibt zurück welche Spielfigur die übergebene Position besetzt
	 * Falls keine diese Position besetzt gibt sie null zurück
	 * @param posX
	 * @param posY
	 * @return
	 */
	private ISpielFigur positionIstBesetztDurch( int posX, int posY )
	{
		
		// Erzeuge eine Liste aus allen SpielFiguren auf dem SpielFeld
		ArrayList<ISpielFigur> figurListe = this.gibAlleSpielFiguren();
		
		// Gehe durch diese Liste bis ein SpielStein gefunden wurde mit den gegebenen Koordinaten
		ListIterator<ISpielFigur> it = figurListe.listIterator();
		while(it.hasNext())
		{
			ISpielFigur tmp = it.next();
			if( (tmp.gibPosX() == posX) && (tmp.gibPosY() == posY) )
			{
				return tmp;
			}
		}
		
		// wenn nichts gefunden wurde
		return null;
	}
	
	
	/**
	 * wandelt einen Spielfigur in einen DameStein
	 * Falls Spielfigur schon Dame ist tue nichts
	 * @param figur
	 * @return
	 */
	private void macheSpielFigurZuDame(ISpielFigur figur)
	{
		// Falls Figur schon Dame ist
		if( figur.getClass().equals(DameStein.class))
		{
			return;
		}
		
		ISpielFigur neueDame = new DameStein(figur);	// erzeuge neue Figur als Dame
		
		// Entferne die alte figur aus der Liste des jeweiligen Spielers und füge Dame in Liste
		if( this.gibSpielerSchwarz().gibFiguren().contains(figur))
		{
			this.gibSpielerSchwarz().entferneSpielFigur(figur);
			this.gibSpielerSchwarz().fuegeDameZurListeHinzu(neueDame);
		}
		else 
		{
			this.gibSpielerWeiss().entferneSpielFigur(figur);
			this.gibSpielerSchwarz().fuegeDameZurListeHinzu(neueDame);
		}
	}
	
	//****************
	// Hilfsfunktionen für Figur selektieren
	//****************
	/**
	 * Gibt alle Figuren des Spielers zurück die springen können und damit auch müssen
	 * @return ArrayList<ISpielFigur>
	 */
	private ArrayList<ISpielFigur> figurenDieSpringenMuessen(ISpieler spieler)
	{
		ArrayList<ISpielFigur> figuren = spieler.gibFiguren();
		
		// streiche Figuren heraus die nicht springen müssen (12 Tests, dh. max.: 5*12+12=72 if's)
		Iterator<ISpielFigur> it = figuren.iterator();
		while(it.hasNext())
		{
			ISpielFigur figur = it.next();			
			
			// falls Spielfigur nicht springen kann
			if(!kannSpielFigurSpringen(figur))
			{
				figuren.remove(figur);	// lösche die Spielfigur aus der Liste
			}
		}
		
		return figuren;
	}
	
	
	/**
	 * Prüft ob übergebene SpielFigur springen kann
	 * @param figur
	 * @return
	 */
	private boolean kannSpielFigurSpringen(ISpielFigur figur) 
	{

		int merkeX = figur.gibPosX();
		int merkeY = figur.gibPosY();
		
		// Prüfe ob Figur schon  die Umgebung jeder Figur die der Spieler besitzt
		if(figur.gueltigeBewegung(merkeX+2, merkeY+2) &&
				!gueltigerSprung(figur, merkeX+2, merkeY+2).equals(null) )
		{
			return true;
		}
		if(figur.gueltigeBewegung(merkeX-2, merkeY+2) &&
				!gueltigerSprung(figur, merkeX-2, merkeY+2).equals(null) )
		{
			return true;
		}
		if(figur.gueltigeBewegung(merkeX-2, merkeY-2) &&
				!gueltigerSprung(figur, merkeX-2, merkeY-2).equals(null) )
		{
			return true;
		}
		if(figur.gueltigeBewegung(merkeX+2, merkeY-2) &&
				!gueltigerSprung(figur, merkeX+2, merkeY-2).equals(null) )
		{
			return true;
		}
		
		return false;
	}

	
	
	/**
	 * Prüft ob übergebene SpielFigur sich um einen Schritt bewegen kann
	 * @param figur
	 * @return
	 */
	private boolean istSpielFigurBewegbar(ISpielFigur figur) 
	{
		int merkeX = figur.gibPosX();
		int merkeY = figur.gibPosY();
		
		
		if( figur.gueltigeBewegung(merkeX+1, merkeY+1) &&
				positionIstBesetztDurch(merkeX+1, merkeY+1).equals(null))
		{
			return true;
		}
		if( figur.gueltigeBewegung(merkeX-1, merkeY+1) &&
				positionIstBesetztDurch(merkeX-1, merkeY+1).equals(null))
		{
			return true;
		}
		if( figur.gueltigeBewegung(merkeX-1, merkeY-1) &&
				positionIstBesetztDurch(merkeX-1, merkeY-1).equals(null))
		{	
			return true;
		}
		if( figur.gueltigeBewegung(merkeX+1, merkeY-1) &&
				positionIstBesetztDurch(merkeX+1, merkeY-1).equals(null))
		{	
			return true;
		}
		
		return false;
	}

	
	
	//****************
	// Getter
	//****************
	/**
	 * Getter für den Spieler mit den Schwarzen Spielsteinen
	 * @return	ISpieler
	 */
	public ISpieler gibSpielerSchwarz()
	{
		return this.spielerSchwarz;
	}
	/**
	 * Getter für den Spieler mit den Weißen Spielsteinen
	 * @return	ISpieler
	 */
	public ISpieler gibSpielerWeiss() {
		return this.spielerWeiss;
	}
	/**
	 * gibt alle Spielfiguren in einer ArrayListe zurück
	 * @return ArrayListe<ISpielFigur>
	 */
	public ArrayList<ISpielFigur> gibAlleSpielFiguren() {
		ArrayList<ISpielFigur> figurListe = new ArrayList<ISpielFigur>();
		figurListe.addAll(this.gibSpielerSchwarz().gibFiguren());
		figurListe.addAll(this.gibSpielerWeiss().gibFiguren());
		return figurListe;
	}



	
}
