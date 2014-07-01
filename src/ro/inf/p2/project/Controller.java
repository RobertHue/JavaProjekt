package ro.inf.p2.project;
import java.util.ArrayList;

/**
*@author Pascal Zimmermann, Maxi Bottin
*@date 31.06.14
*/

public class Controller implements IController {
	
	IPopUp popup;
	ISpielfeldAnzeige anzeige;
	ISpiel spiel;
	IController control;
	
	//Konstruktor
	public  Controller(){
		IPopUp popup = new PopUp(control);
		popUpAufrufen(1, null);
		
	
		//TODO Maxi
	}
	
	
	/*public static void main(String[] args) {
		Controller ctrl =new Controller();
		ctrl.test();
	}

	public void test() {
		ArrayList<ISpielFigur> l1 = new ArrayList<ISpielFigur>();
		ArrayList<ISpielFigur> l2 = new ArrayList<ISpielFigur>();
		l1.add(new SpielStein(2, 5, 0));
		l1.add(new SpielStein(1, 3, 0));
		l1.add(new SpielStein(4, 5, 0));
		l1.add(new SpielStein(3, 7, 0));
		l2.add(new SpielStein(2, 5, 1));
		l2.add(new SpielStein(5, 6, 1));
		l2.add(new SpielStein(3, 5, 1));
		l2.add(new SpielStein(1, 5, 1));
		ISpielFigur sel = l2.get(1);
		String zug = "xyz-Test";
		anzeige.neuZeichnen(l1, l2, zug, sel);
	}
	*/
	public void neustartGedrueckt() {
		//System.out.println("Gedrueckt: Neustart");
		popUpAufrufen(3,null);
		
	}



	public void aufgebenGedrueckt() {
		//System.out.println("Gedrueckt: Aufgabe");
		
		popUpAufrufen(2,null);
		
		
	}

	
	public void popUpAufrufen(int code, String spieler){
		//TODO: Maxis Reich
		if (anzeige != null)
		{
			anzeige.fokusAus();
			popup.popUpAufrufen(code, spieler);
		}


		
	}
	public void fehlermeldungAusgeben (int code) {
		
		//TODO: Maxis Reich
		anzeige.fokusAus();
		popup.fehlermeldungAusgeben(code);
		anzeige.fokusAn();
	}


	public void feldKnopfGedrueckt(int posX, int posY) {
		//System.out.println("Spielfeld: " + posX+ "|" + posY);
		
		
		//Pr?fe, ob schon eine Figur selektiert wurde
		if(spiel.gibSelektierteFigur() == null) {
			
			//Wenn nein, dann soll er versuchen auf den Feldkoordinaten zu selektieren
			int retwert = spiel.figurSelektieren(posX, posY);
			
			//Unterscheidung Erfolg/Fehlschlag
			if (retwert != 0) {
				fehlermeldungAusgeben(retwert);
				return;
			} else {
				anzeige.neuZeichnen(spiel.gibSpielFigurenWeiss(), spiel.gibSpielFigurenSchwarz(),
									spiel.gibIstAmZug().gibName(), spiel.gibSelektierteFigur());
				return;
			}

		}
		
		//Wenn schon eine Figur selektiert wurde,
		//dann soll er zunächst versuchen auf den Koordinaten zu selektieren
		int retwert = spiel.figurSelektieren(posX, posY);
		
		//Pruefe, ob es geklappt hat
		 if (retwert == 0) {
		 	anzeige.neuZeichnen(spiel.gibSpielFigurenWeiss(), spiel.gibSpielFigurenSchwarz(),
		 						spiel.gibIstAmZug().gibName(), spiel.gibSelektierteFigur());
			return;
		} else if (retwert == 4|| retwert == 3) {
			fehlermeldungAusgeben(retwert);
			return;
		}
		//Versuch des Bewegens
		boolean retwertbool = spiel.bewegeNach(spiel.gibSelektierteFigur(), posX, posY);
		if (retwertbool == false) {
		 	fehlermeldungAusgeben(5);
		 	return;
		} else 
		//Pruefe, ob die Figur weiterspringen kann
		if(spiel.gibKannSpringen(spiel.gibSelektierteFigur()) == true) {
			anzeige.neuZeichnen(spiel.gibSpielFigurenWeiss(), spiel.gibSpielFigurenSchwarz(),
								spiel.gibIstAmZug().gibName(), spiel.gibSelektierteFigur());
			popUpAufrufen(5, null);
			return;
		}
		//Pruefe, ob jemand gewonnen hat
		ISpieler gewinner = spiel.pruefeObGewonnen();
		if(gewinner != null){
			anzeige.neuZeichnen(spiel.gibSpielFigurenWeiss(), spiel.gibSpielFigurenSchwarz(),
								spiel.gibIstAmZug().gibName(), spiel.gibSelektierteFigur());	
			popUpAufrufen(4, gewinner.gibName());
		}
		//sonst einfach nur den Zug beenden
		spiel.zugBeenden();
		anzeige.neuZeichnen(spiel.gibSpielFigurenWeiss(), spiel.gibSpielFigurenSchwarz(),
							spiel.gibIstAmZug().gibName(), spiel.gibSelektierteFigur());
		
	}
	
	public void neuesSpielStarten(String name1, String name2){

		ISpiel spiel = new Spiel(name1, name2);
		ISpielfeldAnzeige anzeige = new SpielfeldAnzeige(control);
		anzeige.neuZeichnen(spiel.gibSpielFigurenWeiss(), spiel.gibSpielFigurenSchwarz(),
				spiel.gibIstAmZug().gibName(), spiel.gibSelektierteFigur());
		
		
		
	}
	
	public void aufgeben(boolean jaodernein){
		
		if (jaodernein == true) {
			spiel.aufgeben();
		}
		
		
		anzeige.fokusAn();
	}
	
	public void neustarten(boolean jaodernein){
		
		if (jaodernein == true) {
			spiel.neustarten();
			anzeige.neuZeichnen(spiel.gibSpielFigurenWeiss(), spiel.gibSpielFigurenSchwarz(),
								spiel.gibIstAmZug().gibName(), spiel.gibSelektierteFigur());
		}
		
		
		anzeige.fokusAn();
		
	}
	
	public void ende(boolean jaodernein){
		
		if (jaodernein == true) {
			spiel.neustarten();
			anzeige.neuZeichnen(spiel.gibSpielFigurenWeiss(), spiel.gibSpielFigurenSchwarz(),
								spiel.gibIstAmZug().gibName(), spiel.gibSelektierteFigur());
			anzeige.fokusAn();
		} else {
			System.exit(0);
		}
		
		
		
	}
	
	public void nochmalSpringen(boolean jaodernein){
		anzeige.fokusAn();
		
		if (jaodernein == true) {
		
		} else {
			spiel.zugBeenden();
			anzeige.neuZeichnen(spiel.gibSpielFigurenWeiss(), spiel.gibSpielFigurenSchwarz(),
								spiel.gibIstAmZug().gibName(), spiel.gibSelektierteFigur());
			anzeige.fokusAn();
		}
		
	}
	public static void main(String [] args)
	{
		IController control = new Controller();
	}

}
