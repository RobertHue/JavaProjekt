package ro.inf.p2.project;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.*;
/**
*@author Pascal
*/
public class SpielfeldAnzeige implements ActionListener, ISpielfeldAnzeige {

	private JFrame fenster;
	private JPanel seitenbuttons, feld;
	private JButton[][] spielfeld;
	private JButton aufgabe, neustart;
	private JLabel status;
	IController controller;
	
	//Konstruktor
	public SpielfeldAnzeige( IController ctrl ) {
		controller = ctrl;
		fensterErzeugen();
		}
	
	public void fensterErzeugen() {
		//Erzeugung des Grundsätzlichen Fensters
		fenster = new JFrame("Dame");
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenster.setPreferredSize(new Dimension(600,600));
		fenster.setResizable(false);
		fenster.setLayout(new BorderLayout());
		
		//Erzeugung der Statusleiste
		status = new JLabel("Spieler am Zug:");
		fenster.add(status, BorderLayout.SOUTH);
		
		//Erzeugung der Buttons an der linken Seite
		seitenbuttons = new JPanel();
		seitenbuttons.setLayout(new BorderLayout());
		
		//Aufgeben-Button
		aufgabe = new JButton("Aufgeben");
		aufgabe.setActionCommand("aufgabe");
		aufgabe.addActionListener(this);
		seitenbuttons.add(aufgabe, BorderLayout.NORTH);
		
		//Neustart-Button
		neustart = new JButton("Neustart");
		neustart.setActionCommand("neustart");
		neustart.addActionListener(this);
		seitenbuttons.add(neustart, BorderLayout.SOUTH);
		fenster.add(seitenbuttons, BorderLayout.EAST);
		
		//Hinzufügen des Spielfeldes
		SpielfeldErzeugen();
		//Fertigmachen des Fensters
		fenster.pack();
		fenster.setVisible(true);
		
	}
	
	public void SpielfeldErzeugen(){
		feld =new JPanel();
		
		//Initialisierung der Matrix
		spielfeld = new JButton[8][8];
		feld.setLayout(new GridLayout(9,9));
		
		//Erstes Element leer
		JLabel erst = new JLabel("");
		erst.setBounds(new Rectangle(50, 50));
		feld.add(erst);
		//Erste Reihe an Buchstaben
		char zeichen = 'A';
		for (int x = 0; x < 8; x++){	
		feld.add(new JLabel(""+ ((char)(zeichen +x))));	
		}
		
		//Alle weiteren Reihen
		for (int x = 0; x < 8; x++ ){
			//Zuerst die Zeilennummer
			feld.add(new JLabel((x+1)+""));
			//Dann die Knöpfe
			for (int y = 0; y <8; y++) {
				spielfeld[x][y] = new JButton("leer");
				spielfeld[x][y].setActionCommand(new String(""+(x+1) +(y+1)));
				spielfeld[x][y].addActionListener(this);
				//spielfeld[x][y].setBounds((x + 1)* 50, (y+1)*50, 50, 50);
				//spielfeld[x][y].setMaximumSize(new Dimension(50,50));
				
				feld.add(spielfeld[x][y]);
								
			}
			
		}
		fenster.add(feld, BorderLayout.CENTER);
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		String ausgabe = arg0.getActionCommand();
		if(ausgabe == "neustart"){
			controller.neustartGedrueckt();
			} else if(ausgabe == "aufgabe") {
			controller.aufgebenGedrueckt();
		} else {
			
			int x = Integer.parseInt(ausgabe.substring(0, 1));
			int y = Integer.parseInt(ausgabe.substring(1));
			controller.feldKnopfGedrueckt(x,y);
		}
		

	}
	
	public void fokusAn() {
		fenster.setFocusable(true);
		
		
	}
	
	public void fokusAus() {
		fenster.setFocusable(false);
		
	}
	
	public void neuZeichnen (ArrayList<ISpielFigur> spieler1,
			ArrayList<ISpielFigur> spieler2, String istAmZug, ISpielFigur selektiert){
		
		//Spielfeld aktuellisieren
		
		//Zeile
		for (int x = 0; x < 8; x++ ){
			//Spalte
			for (int y = 0; y <8; y++) {
				boolean gefunden = false;
				//Durchsuchen der ersten Liste
				ListIterator<ISpielFigur> iter = spieler1.listIterator();
				while(iter.hasNext()){
					ISpielFigur temp = iter.next();
					if ((temp.gibPosX() == (x+1))&& (temp.gibPosY() == (y+1))){
						gefunden = true;
						//Prüfe ob Dame
						if(temp.getClass().getName() == "DameStein" ){
							spielfeld[x][y].setText("sp1D");
						} else {
							spielfeld[x][y].setText("sp1");
						}
						
					}
					
				}
				//Zweite Liste
				if(gefunden == false){
					iter = spieler2.listIterator();
					while(iter.hasNext()){
						ISpielFigur temp = iter.next();
						if ((temp.gibPosX() == (x+1))&& (temp.gibPosY() == (y+1))){
							gefunden = true;
							//Prüfe ob Dame
							if(temp.getClass().getName() == "DameStein" ){
								spielfeld[x][y].setText("sp2D");
							} else {
								spielfeld[x][y].setText("sp2");
							}
						
						}
					}
					
				}
				//Wenn nicht gefunden
				if(gefunden == false){
					spielfeld[x][y].setText("leer");
				}
			}
			
		}
		//Selektierte Figur ermitteln
		if(selektiert != null){
			int posX = selektiert.gibPosX();
			int posY = selektiert.gibPosY();
			spielfeld[posX - 1][posY -1].setText(spielfeld[posX - 1][posY - 1].getText() + "sel");
		}
		
		//Statuszeile aktuellisieren
		status.setText("Spieler am Zug: " + istAmZug);
	
	}
	
}
