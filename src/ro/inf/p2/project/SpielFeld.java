package ro.inf.p2.project;

import java.util.ArrayList;
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
		// TODO benötigt noch die Spieler-Klasse
//		spielerSchwarz = new Spieler(0);	// Spieler mit schwarzen SpielFiguren
// 		spielerWeiss   = new Spieler(1);	// Spieler mit weißen SpielFiguren
	}
	
	
	

	private ISpielFigur positionIstBesetztDurch( int posX, int posY )
	{
		
		// Erzeuge eine Liste aus allen SpielFiguren auf dem SpielFeld
		ArrayList<ISpielFigur> figurListe = this.gibAlleSpielFiguren();
		
		// Gehe durch diese Liste bis ein SpielStein gefunden wurde mit den gegebenen Koordinaten
		ListIterator<ISpielFigur> it = figurListe.listIterator();
		while(it.hasNext())
		{
			ISpielFigur tmp = it.next();
			if(tmp.gibPosX()==posX && tmp.gibPosY()==posY)
			{
				return tmp;
			}
		}
		
		// wenn nichts gefunden wurde
		return null;
	}
	
	
	// das Schwierigste überhaupt ist die bewegeNach-Methode
	// Trick: übergebe das gesamt spiel an die Methode der SpielFigur
	/**
	 * Jeder SpielStein besitzt Regeln die durch bewegeNach abgeprüft werden ob Zug überhaupt möglich
	 */
	public boolean bewegeNach( ISpielFigur figur, int posX, int posY)
	{
		// 1.) Sprungziel schon besetzt/belegt?   [null bedeutet unbesetzt]
		if( positionIstBesetztDurch( posX, posY) != null ) 		return false;
		
		
		// 2.) ist die Bewegung von der Richtung her
		if( this.getClass().equals(SpielStein.class) )
		{
			if( !figur.gueltigeBewegungsRichtung( posX, posY) )		
				return false;	
		}
		
		// 3.) ist Bewegung ein Sprung?
		// möglicher Sprung ist alles was einen Richtungsvektor von der Länge 2 hat 
		int diffX = posX-figur.gibPosX();
		int diffY = posY-figur.gibPosY();
		if( Math.abs(diffX)==2 && Math.abs(diffY)==2 )
		{
			// ist Sprung überhaupt möglich? gehe die Hälfte der Koordinaten
			// vom Startpunkt aus nach links und rechts		
			ISpielFigur tmp = positionIstBesetztDurch( figur.gibPosX()+diffX, figur.gibPosY()+diffY); 
			if( tmp == null )
			{  
				return false;
			}
			// falls ein freundlicher Spielstein
			else if( figur.gibFarbe() == tmp.gibFarbe() )
			{
				return false; // Springen über eigene SpielSteine nicht möglich
			}
			else
			{
				// falls Sprung korrekt ausgeführt -> lösche Spielstein auf SpielFeld
				//spiel.g
			}
		}	
		
		// Ändere die Position und gib true an den Aufrufer zurück
		figur.positionAendern(posX, posY);
		return true;
	}
	
	//****************
	// Getter
	//****************
	public ISpieler gibSpielerSchwarz()
	{
		return this.spielerSchwarz;
	}
	public ISpieler gibSpielerWeiss() {
		return this.spielerWeiss;
	}




	public ArrayList<ISpielFigur> gibAlleSpielFiguren() {
		ArrayList<ISpielFigur> figurListe = new ArrayList<ISpielFigur>();
		figurListe.addAll(this.gibSpielerSchwarz().gibFiguren());
		figurListe.addAll(this.gibSpielerWeiss().gibFiguren());
		return figurListe;
	}

	//****************
	// Setter
	//****************
	
	
}
