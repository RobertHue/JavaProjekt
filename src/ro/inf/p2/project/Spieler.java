package ro.inf.p2.project;

import java.util.ArrayList;

public class Spieler implements ISpieler  {
	//Attribute 
	
	private String name;
	private ArrayList<ISpielFigur> figuren;
	
	
	
	
	public Spieler(int i) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Check ob figur existiert, 
	 * wenn true dann loeschen und return true,
	 * wenn false return false
	 */
	
	public boolean entferneSpielFigur(ISpielFigur figur) {
	
		 if (figuren.contains(figur)){
			figuren.remove(figur);
			return true;
		 }
							
		return false;
	}

	/**
	 * Fuege Dame in Array figuren
	 */
	public void fuegeDameZurListeHinzu(ISpielFigur figur) {
		
		figuren.add(figur);
				
	}

	public void setzeName(String name) {
		
		this.name = name;
	}

	/**
	 * Anzahl der figuren im Array figuren
	 */
	public int gibAnzahlSteine() {
		
		return figuren.size();		
	}

	/**
	 * Gibt alle Figuren zurück. Schwartz & Weiss
	 */
	
	public ArrayList<ISpielFigur> gibFiguren() {
		return this.figuren;				
	}

	
	public String gibName() {
				
		return this.name;
	}
	 
	
	
	
	

}
