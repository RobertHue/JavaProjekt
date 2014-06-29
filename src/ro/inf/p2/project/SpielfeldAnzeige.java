package ro.inf.p2.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.*;
/**
*@author Pascal Zimmermann
*@date 29.06.14
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
		feld.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//Laden der Icons
		ImageIcon iconb = new ImageIcon ("Weisses_Feld.png"); 
		ImageIcon icona = new ImageIcon ("Braunes_Feld.png"); 
		//Erstes Element leer
		JLabel erst = new JLabel("");
		c.gridx = 0;
		c.gridy = 0;
		feld.add(erst, c);
		//Erste Reihe an Buchstaben
		char zeichen = 'A';
		for (int x = 0; x < 8; x++){
		c.gridx = x + 1;
		feld.add(new JLabel(""+ ((char)(zeichen +x))),c);	
		}
		
		//Alle weiteren Reihen
		for (int x = 0; x < 8; x++ ){
			//Zuerst die Zeilennummer
			c.gridx = 0;
			c.gridy = x+1;
			feld.add(new JLabel((x+1)+""), c);
			//In jeder Zeile werden die Feldfarben vertauscht
			ImageIcon temp = icona;
			icona = iconb;
			iconb = temp;
			temp = null;
			int farbenzaehler = 0;
			//Dann die Knöpfe
			for (int y = 0; y <8; y++) {
				
				c.gridx = x +1;
				c.gridy = y + 1;
				spielfeld[x][y] = new JButton("");
				if ((farbenzaehler % 2) == 0){
					spielfeld[x][y].setIcon(icona);
				} else {
					spielfeld[x][y].setIcon(iconb);
				}
				spielfeld[x][y].setActionCommand(new String(""+(x+1) +(y+1)));
				spielfeld[x][y].addActionListener(this);
				spielfeld[x][y].setPreferredSize(new Dimension(60,60));
				feld.add(spielfeld[x][y], c);
				farbenzaehler++;				
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
		//Laden der Icons
		ImageIcon leerweiss = new ImageIcon ("Weisses_Feld.png"); 
		ImageIcon leerbraun = new ImageIcon ("Braunes_Feld.png"); 
		ImageIcon steinschwarz = new ImageIcon ("Schwarzer_Stein.png"); 
		ImageIcon steinschwarzsel = new ImageIcon ("Schwarzer_Stein_ausgewaehlt.png"); 
		ImageIcon dameschwarz = new ImageIcon ("Schwarze_Dame.png"); 
		ImageIcon dameschwarzsel = new ImageIcon ("Schwarze_Dame_ausgewaehlt.png"); 
		ImageIcon steinweiss = new ImageIcon ("Weisser_Stein.png"); 
		ImageIcon steinweisssel = new ImageIcon ("Weisser_Stein_ausgewaehlt.png"); 
		ImageIcon dameweiss = new ImageIcon ("Weisse_Dame.png"); 
		ImageIcon dameweisssel = new ImageIcon ("Weisse_Dame_ausgewaehlt.png");
		//Zaehler fuer Weisse Felder initiallisieren
		int farbenzaehler = 0;
		int erwartetesModuloErgebnis = 1;
		
		//Spielfeld aktuellisieren
		
		//Zeile
		for (int x = 0; x < 8; x++ ){
			
			//Invertieren des erwarteten Moduloergebnisses und Zuruecksetzen des farbenzaehlers
			farbenzaehler = 0;
			if (erwartetesModuloErgebnis == 1) {
				erwartetesModuloErgebnis = 0;
			} else {
				erwartetesModuloErgebnis = 1;
			}
			//Spalte			
			for (int y = 0; y <8; y++) {
				//Rausfiltern der weissen Felder
				if((farbenzaehler % 2) == erwartetesModuloErgebnis){
					spielfeld[x][y].setIcon(leerweiss);
				} else {
					boolean gefunden = false;
					//Durchsuchen der ersten Liste
					ListIterator<ISpielFigur> iter = spieler1.listIterator();
					while(iter.hasNext()){
						ISpielFigur temp = iter.next();
						if ((temp.gibPosX() == (x+1))&& (temp.gibPosY() == (y+1))){
							gefunden = true;
							//Pruefe ob Dame
							if(temp.getClass().getName() == "DameStein" ){
								//Pruefe ob selektiert
								if (pruefeObSelektiert(temp, selektiert) == true) {
									spielfeld[x][y].setIcon(dameweisssel);
								} else {
									spielfeld[x][y].setIcon(dameweiss);
								}
								
							} else {
								//Pruefe ob selektiert
								if (pruefeObSelektiert(temp, selektiert) == true) {
									spielfeld[x][y].setIcon(steinweisssel);
								} else {
									spielfeld[x][y].setIcon(steinweiss);
								}
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
								//Pruefe ob Dame
								if(temp.getClass().getName() == "DameStein" ){
									//Pruefe ob selektiert
									if (pruefeObSelektiert(temp, selektiert) == true) {
										spielfeld[x][y].setIcon(dameschwarzsel);
									} else {
										spielfeld[x][y].setIcon(dameschwarz);
									}
									
								} else {
									//Pruefe ob selektiert
									if (pruefeObSelektiert(temp, selektiert) == true) {
										spielfeld[x][y].setIcon(steinschwarzsel);
									} else {
										spielfeld[x][y].setIcon(steinschwarz);
									}
									
								}
						
							}
						}
					
					}
					//Wenn nicht gefunden
					if(gefunden == false){
						spielfeld[x][y].setIcon(leerbraun);
					}
				}
				farbenzaehler++;
			}
			
		}
		
		//Statuszeile aktuellisieren
		status.setText("Spieler am Zug: " + istAmZug);
	
	}
	
	private boolean pruefeObSelektiert(ISpielFigur figur, ISpielFigur selektiert){
		if (selektiert == null){
			return false;
		}
		if ((figur.gibPosX() == selektiert.gibPosX()) && (figur.gibPosY() == selektiert.gibPosY())) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
