package ro.inf.p2.project;

import java.util.*;

/**
 * Klasse: SpielFeld
 * ist verantwortlich für die Spiellogik
 * @author Robert
 */
public class SpielFeld implements ISpielFeld {
	
	private ISpieler spielerSchwarz;
	private ISpieler spielerWeiss;

	
	/**
	 * erzeugt ein SpielFeld mit 2 Spielern
	 */
	public SpielFeld(String nameSpieler1, String nameSpieler2) {
		// ruft den Konstruktor von Spieler auf
		spielerSchwarz = new Spieler(nameSpieler1, 0);	// Spieler mit schwarzen SpielFiguren
 		spielerWeiss   = new Spieler(nameSpieler2, 1);	// Spieler mit weissen SpielFiguren
	}
	
	/**
	 * Diese Methode wird vom Spiel in jeder Standby-Phase aufgerufen, um die 
	 * Sprung-Faehigkeiten der Steine des Spielers - welcher am Zug ist - zu setzten.
	 * @param spieler = Turn-Player
	 */
	public void updateSprungFaehigkeiten(ISpieler spieler)
	{
		Iterator<ISpielFigur> it;
		// setze Status von vorherigen Zug zurueck
		ArrayList<ISpielFigur> alleSpielFiguren = this.gibAlleSpielFiguren();
		it = alleSpielFiguren.iterator();
		while(it.hasNext())
		{
			it.next().setzteSprungFaehigkeit(false);
		}
		
		// setze alle 
		ArrayList<ISpielFigur> koennenSpringen = figurenDieSpringenKoennen(spieler);
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
	 * @param spieler - Spieler der am Zug ist
	 * @param posX - selektierte Zielposition X
	 * @param posY - selektierte Zielposition Y
	 * @return das oben erwähnte i
	 */
	public int figurSelektieren(ISpieler spieler, int posX, int posY) {

		ISpielFigur ausgewaehlteFigur = positionIstBesetztDurch( posX, posY );
		
		// falls ausgewaehlte Position unbesetzt ist
		if( ausgewaehlteFigur.equals(null) )
		{
			return 1;	// Feld der Auswahl ist leer
		}
		
		// falls ausgewaehlte Figur nicht von Spieler ist
		if( !spieler.gibFiguren().contains(ausgewaehlteFigur) )
		{
			return 2;	// Figur ist nicht von dir
		}
		
		if( !istSpielFigurBewegbar(ausgewaehlteFigur) && !ausgewaehlteFigur.kannIchSpringen() )
		{
			return 3;	// SpielFigur kann sich nicht bewegen und auch nicht springen
		}	
		
		if(!ausgewaehlteFigur.kannIchSpringen())
		{
			return 4;	// andere Spielfiguren haben Prioritaet
		}
		
		
		return 0;	// Auswahl Korrekt!
	}
	
	


	/**
	 * Diese Methode versucht - nach den Dameregeln - eine Spielfigur 
	 * von ihrer aktuellen Position zu einer neuen Position zu bewegen.
	 * 
	 * @param figur - die Figur die bewegt werden soll
	 * @param zielPosX - die neue Position X
	 * @param zielPosY - die neue Position Y
	 * @return gibt die Gueltigkeit der Bewegung an den Aufrufer zurueck
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
			

		// 3.) Bewegung ein Sprung? falls Ja ist der Sprung moeglich?
		if(istBewegungEinSprung(figur, zielPosX, zielPosY))
		{
			ISpielFigur ueberSprungener = gueltigerSprung(figur, zielPosX, zielPosY);
			if( ueberSprungener.equals(null) )
			{
				return false;
			}
			else
			{
				// Sprung war korrekt & loesche den uebersprungenen SpielStein aus der FigurenListe des jeweiligen Spielers
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
	
		// Aendere die Position
		figur.positionAendern(zielPosX, zielPosY);
		
		// macheSpielFigurZuDame falls gegenueberliegendes Ende erreicht
		if( ( figur.gibFarbe()==0 && figur.gibPosY()==7 ) ||
			( figur.gibFarbe()==1 && figur.gibPosY()==0 ))
		{
			macheSpielFigurZuDame(figur);
		}
		
		// gib Gueltigkeit der Bewegung an den Aufrufer zurueck
		return true;
	}
	

	
	
	
	//******************************
	// Hilfsmethoden fuer SpielFeld
	//******************************
	private boolean freundlicheFiguren(ISpielFigur figur1, ISpielFigur figur2)
	{
		return figur1.gibFarbe() == figur2.gibFarbe();
	}
	
	/**
	 * Prüft ob Bewegung ein Sprung ist
	 * 
	 * @param figur - die Figur deren Bewegung getestet werden soll
	 * @param zielPosX - die neue Position X
	 * @param zielPosY - die neue Position Y
	 * @return gibt zurueck ob Bewegung ein Sprung ist
	 */
	private boolean istBewegungEinSprung(ISpielFigur figur, int zielPosX, int zielPosY)
	{
		// moeglicher Sprung ist alles was einen Richtungsvektor von (2,2) hat
		int diffX = zielPosX-figur.gibPosX();
		int diffY = zielPosY-figur.gibPosY();
		return (Math.abs(diffX)==2 && Math.abs(diffY)==2);
	}
	/**
	 * Ueberprueft ob der Sprung erlaubt ist
	 * 
	 * @param figur - die Figur deren Sprung getestet werden soll
	 * @param zielPosX - die neue Position X
	 * @param zielPosY - die neue Position Y
	 * @return	falls gueltiger Sprung liefert diese Methode die uebersprungene Spielfigur
	 */
	private ISpielFigur gueltigerSprung(ISpielFigur figur, int zielPosX, int zielPosY) 
	{
		int diffX = zielPosX-figur.gibPosX();
		int diffY = zielPosY-figur.gibPosY();

		// pr�fe Figur auf halber Strecke = 1/2* (A+B) = Mittelpunkt auf Vektor
		ISpielFigur besetztDurch = positionIstBesetztDurch( zielPosX, zielPosY);
		ISpielFigur ueberSprungener = positionIstBesetztDurch( 
				(int)0.5*(figur.gibPosX()+diffX), (int)0.5*(figur.gibPosY()+diffY));
		
		
		if( !besetztDurch.equals(null) )
		{  
			return null;	// Springen nur auf leere Felder moeglich
		}
		
		if( ueberSprungener.equals(null) )
		{
			return null;	// Springen ueber leere Felder nicht moeglich
		}

		if( freundlicheFiguren(figur, besetztDurch) )
		{
			return null; 	// Springen ueber eigene (freundliche) Spielfiguren nicht mlich
		}
		
		return ueberSprungener;
	}
	
	

	
	
	/**
	 * Gibt zurueck welche Spielfigur die uebergebene Position besetzt
	 * Falls keine diese Position besetzt gibt sie null zurueck
	 * @param posX
	 * @param posY
	 * @return gibt null oder SpielFigur zurueck
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
	 * Wandelt einen Spielfigur in einen DameStein
	 * Falls Spielfigur schon Dame ist tue nichts
	 * @param figur - den zu transformierenden SpielStein
	 */
	private void macheSpielFigurZuDame(ISpielFigur figur)
	{
		// Falls Figur schon Dame ist
		if( figur.getClass().equals(DameStein.class))
		{
			return; 
		}
		
		ISpielFigur neueDame = new DameStein(figur);	// erzeuge neue Figur als Dame
		
		// Entferne die alte figur aus der Liste des jeweiligen Spielers und fuege Dame in Liste
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
	
	
	//*******************************************
	// Hilfsfunktionen fuer Figur selektieren
	//*******************************************
	/**
	 * Prueft ob uebergebene SpielFigur springen kann
	 * @param figur
	 * @return
	 */
	public boolean kannSpielFigurSpringen(ISpielFigur figur) 
	{

		int merkeX = figur.gibPosX();
		int merkeY = figur.gibPosY();
		
		// Pruefe ob Figur schon  die Umgebung jeder Figur die der Spieler besitzt
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
	 * Prueft ob uebergebene SpielFigur sich um einen Schritt bewegen kann
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
	 * Getter fuer den Spieler mit den Schwarzen Spielsteinen
	 * @return	ISpieler
	 */
	public ISpieler gibSpielerSchwarz()
	{
		return this.spielerSchwarz;
	}
	
	/**
	 * Getter fuer den Spieler mit den Weissen Spielsteinen
	 * @return	ISpieler
	 */
	public ISpieler gibSpielerWeiss() {
		return this.spielerWeiss;
	}
	
	/**
	 * gibt alle Spielfiguren in einer ArrayListe zurueck
	 * @return ArrayListe<ISpielFigur>
	 */
	public ArrayList<ISpielFigur> gibAlleSpielFiguren() {
		ArrayList<ISpielFigur> figurListe = new ArrayList<ISpielFigur>();
		figurListe.addAll(this.gibSpielerSchwarz().gibFiguren());
		figurListe.addAll(this.gibSpielerWeiss().gibFiguren());
		return figurListe;
	}

	/**
	 * Gibt alle Figuren des Spielers zurueck die springen koennen und damit auch muessen
	 * @param spieler - Spieler der am Zug ist
	 * @return ArrayList<ISpielFigur>
	 */
	public ArrayList<ISpielFigur> figurenDieSpringenKoennen(ISpieler spieler)
	{
		ArrayList<ISpielFigur> figuren = spieler.gibFiguren();
		
		// streiche Figuren heraus die nicht springen muessen (12 Tests, dh. max.: 5*12+12=72 if's)
		Iterator<ISpielFigur> it = figuren.iterator();
		while(it.hasNext())
		{
			ISpielFigur figur = it.next();			
			
			// falls Spielfigur nicht springen kann
			if(!kannSpielFigurSpringen(figur)) 
			{ 
				figuren.remove(figur);	// loesche die Spielfigur aus der Liste
			}
		}
		
		return figuren;
	}
	
	/**
	 * Gibt alle Figuren des Spielers zurueck die sich diagonal bewegen können
	 * @param spieler - Spieler der am Zug ist
	 * @return ArrayList<ISpielFigur>
	 */
	public ArrayList<ISpielFigur> figurenDieSichBewegenKoennen(ISpieler spieler)
	{
		ArrayList<ISpielFigur> figuren = spieler.gibFiguren();
		
		// streiche Figuren heraus die nicht sich bewegen koennen (12 Tests, dh. max.: 5*12+12=72 if's)
		Iterator<ISpielFigur> it = figuren.iterator();
		while(it.hasNext())
		{
			ISpielFigur figur = it.next();			
			
			// falls Spielfigur nicht bewegen kann
			if(!istSpielFigurBewegbar(figur)) 
			{ 
				figuren.remove(figur);	// loesche die Spielfigur aus der Liste
			}
		}
		
		return figuren;
	}
}
