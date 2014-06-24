package ro.inf.p2.project;
import java.util.ArrayList;

/**
*@author Pascal
*/

public class Controller implements IController {

	ISpielfeldAnzeige anzeige;
	//ISpiel spiel;
	
	//Konstruktor
	public  Controller(/*ISpiel neuSpiel, */){
		anzeige = new SpielfeldAnzeige(this);
		//spiel = neuSpiel;
	}
	
	
	public static void main(String[] args) {
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

	public void neustartGedrueckt() {
		System.out.println("Gedrueckt: Neustart");
		/*
		popUpAufrufen(3);
		*/
	}



	public void aufgebenGedrueckt() {
		System.out.println("Gedrueckt: Aufgabe");
		/*
		popUpAufrufen(2);
		*/
	}

	
	public void popUpAufrufen(int code){
		//TODO: Maxis Reich
	}
	
	public void fehlermeldungAusgeben (int code) {
		
		//TODO: Maxis Reich
	}


	public void feldKnopfGedrueckt(int posX, int posY) {
		System.out.println("Spielfeld: " + posX+ "|" + posY);
		
	}
	
	public void neuesSpielStarten(String name1, String name2){
		
		
	}
	
	public void aufgeben(boolean jaodernein){
		/*
		if (jaodernein == true) {
			spiel.aufgeben();
		}
		
		*/
		anzeige.fokusAn();
	}
	
	public void neustarten(boolean jaodernein){
		/*
		if (jaodernein == true) {
			spiel.neustarten();
			anzeige.neuZeichnen(spiel.gibSpielFigurenWeiss(), spiel.gibSpielFigurenSchwarz, spiel.gibIstAmZug(), spiel.gibSelektierteFigur());
		}
		
		*/
		anzeige.fokusAn();
		
	}
	
	public void ende(boolean jaodernein){
		/*
		if (jaodernein == true) {
			spiel.neustarten();
			anzeige.neuZeichnen(spiel.gibSpielFigurenWeiss(), spiel.gibSpielFigurenSchwarz, spiel.gibIstAmZug(), spiel.gibSelektierteFigur());
			anzeige.fokusAn();
		} else {
			System.exit(0);
		}
		
		*/
		
	}
	
	public void nochmalSpringen(boolean jaodernein){
		anzeige.fokusAn();
		/*
		if (jaodernein == true) {
		
		} else {
			spiel.zugBeenden();
			anzeige.neuZeichnen(spiel.gibSpielFigurenWeiss(), spiel.gibSpielFigurenSchwarz, spiel.gibIstAmZug(), spiel.gibSelektierteFigur());
			anzeige.fokusAn();
		}
		*/
	}

}
