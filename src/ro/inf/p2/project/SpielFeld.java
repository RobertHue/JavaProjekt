package ro.inf.p2.project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * speichert alle Spielsteine indirekt dadurch dass es die Spieler speichert
 * fungiert als Hilfsklasse, um den ausgew臧lten Spielstein seine Umgebung(das Spielfeld) zu �bergeben
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
 		spielerWeiss   = new Spieler(1);	// Spieler mit weiﾟen SpielFiguren
	}
	
	/**
	 * muss vom Spiel in jeder Standby-Phase aufgerufen werden um 
	 * die F臧igkeiten der Steine des Spielers zu setzten der am Zug ist
	 * (wegen Laufzeit-Performance)
	 */
	public void updateStatus(ISpieler spieler)
	{
		Iterator<ISpielFigur> it;
		// setze Status von vorherigen Zug zur�ck
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
		
		// falls ausgew臧lte Position unbesetzt ist
		if( ausgewaehlteFigur.equals(null) )
		{
			return 1;	// Feld der Auswahl ist leer
		}
		
		// falls ausgew臧lte Figur nicht von spieler ist
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
			return 4;	// andere Spielfiguren haben Priorit舩
		}
		
		
		return 0;	// Auswahl Correct!
	}
	
	


	/**
	 * Diese Methode bewegt eine Spielfigur von ihrer aktuellen Position 
	 * zu einer neuen Position und �berpr�ft ob Zielposition unbesetzt und 
	 * die Bewegungsrichtung der Spielfigur in Ordnung war.
	 * 
	 * Falls Bewegung ein Sprung war, wird dieser ebenfalls auf G�ltigkeit �berpr�ft 
	 * und der gegnerische �bersprungene Spielstein wird entfernt.
	 * 
	 * Falls Spielfigur das Ende des Spielfeldes erreicht hat, mache sie zur Dame
	 * 
	 * @param figur	die Figur die bewegt werden soll
	 * @param zielPosX die neue Position X
	 * @param zielPosY die neue Position Y
	 * @return	gibt die G�ltigkeit der Bewegung an den Aufrufer zur�ck
	 * 			false = nicht g�ltige Bewegung; true = g�ltige Bewegung
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
			

		// 3.) Bewegung ein Sprung? falls Ja ist der Sprung mlich?
		if(istBewegungEinSprung(figur, zielPosX, zielPosY))
		{
			ISpielFigur ueberSprungener = gueltigerSprung(figur, zielPosX, zielPosY);
			if( ueberSprungener.equals(null) )
			{
				return false;
			}
			else
			{
				// Sprung war korrekt & lche den �bersprungenen SpielStein aus der FigurenListe des jeweiligen Spielers
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
	
		// ﾄndere die Position
		figur.positionAendern(zielPosX, zielPosY);
		
		// macheSpielFigurZuDame falls gegen�berliegendes Ende erreicht
		if( ( figur.gibFarbe()==0 && figur.gibPosY()==7 ) ||
			( figur.gibFarbe()==1 && figur.gibPosY()==0 ))
		{
			macheSpielFigurZuDame(figur);
		}
		
		// gib G�ltigkeit der Bewegung an den Aufrufer zur�ck
		return true;
	}
	

	
	
	
	//******************************
	// Hilfsmethoden f�r SpielFeld
	//******************************
	private boolean freundlicheFiguren(ISpielFigur figur1, ISpielFigur figur2)
	{
		return figur1.gibFarbe() == figur2.gibFarbe();
	}
	
	/**
	 * gibt zur�ck ob Bewegung ein Sprung ist
	 * @param figur
	 * @param zielPosX
	 * @param zielPosY
	 * @return
	 */
	private boolean istBewegungEinSprung(ISpielFigur figur, int zielPosX, int zielPosY)
	{
		// mlicher Sprung ist alles was einen Richtungsvektor von (2,2) hat
		int diffX = zielPosX-figur.gibPosX();
		int diffY = zielPosY-figur.gibPosY();
		return (Math.abs(diffX)==2 && Math.abs(diffY)==2);
	}
	/**
	 * ﾜberpr�ft ob die Bewegung ein Sprung war und ob der Sprung erlaubt ist
	 * Falls g�ltiger Sprung liefere die �bersprungene Spielfigur
	 * @param figur
	 * @param zielPosX
	 * @param zielPosY
	 * @return	Sprung erlaubt?
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
			return null;	// Springen nur auf leere Felder mlich
		}
		
		if( ueberSprungener.equals(null) )
		{
			return null;	// Springen �ber leere Felder nicht mlich
		}

		if( freundlicheFiguren(figur, besetztDurch) )
		{
			return null; 	// Springen �ber eigene (freundliche) Spielfiguren nicht mlich
		}
		
		return ueberSprungener;
	}
	
	

	
	
	/**
	 * Gibt zur�ck welche Spielfigur die �bergebene Position besetzt
	 * Falls keine diese Position besetzt gibt sie null zur�ck
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
		
		// Entferne die alte figur aus der Liste des jeweiligen Spielers und f�ge Dame in Liste
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
	// Hilfsfunktionen f�r Figur selektieren
	//****************
	/**
	 * Gibt alle Figuren des Spielers zur�ck die springen knen und damit auch m�ssen
	 * @return ArrayList<ISpielFigur>
	 */
	private ArrayList<ISpielFigur> figurenDieSpringenMuessen(ISpieler spieler)
	{
		ArrayList<ISpielFigur> figuren = spieler.gibFiguren();
		
		// streiche Figuren heraus die nicht springen m�ssen (12 Tests, dh. max.: 5*12+12=72 if's)
		Iterator<ISpielFigur> it = figuren.iterator();
		while(it.hasNext())
		{
			ISpielFigur figur = it.next();			
			
			// falls Spielfigur nicht springen kann
			if(!kannSpielFigurSpringen(figur))
			{
				figuren.remove(figur);	// lche die Spielfigur aus der Liste
			}
		}
		
		return figuren;
	}
	
	
	/**
	 * Pr�ft ob �bergebene SpielFigur springen kann
	 * @param figur
	 * @return
	 */
	private boolean kannSpielFigurSpringen(ISpielFigur figur) 
	{

		int merkeX = figur.gibPosX();
		int merkeY = figur.gibPosY();
		
		// Pr�fe ob Figur schon  die Umgebung jeder Figur die der Spieler besitzt
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
	 * Pr�ft ob �bergebene SpielFigur sich um einen Schritt bewegen kann
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
	 * Getter f�r den Spieler mit den Schwarzen Spielsteinen
	 * @return	ISpieler
	 */
	public ISpieler gibSpielerSchwarz()
	{
		return this.spielerSchwarz;
	}
	/**
	 * Getter f�r den Spieler mit den Weiﾟen Spielsteinen
	 * @return	ISpieler
	 */
	public ISpieler gibSpielerWeiss() {
		return this.spielerWeiss;
	}
	/**
	 * gibt alle Spielfiguren in einer ArrayListe zur�ck
	 * @return ArrayListe<ISpielFigur>
	 */
	public ArrayList<ISpielFigur> gibAlleSpielFiguren() {
		ArrayList<ISpielFigur> figurListe = new ArrayList<ISpielFigur>();
		figurListe.addAll(this.gibSpielerSchwarz().gibFiguren());
		figurListe.addAll(this.gibSpielerWeiss().gibFiguren());
		return figurListe;
	}



	
}
