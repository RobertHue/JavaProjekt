package ro.inf.p2.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PopUp implements IPopUp, ActionListener
{
	
	public boolean antwort=false;
	private JFrame pop;
	IController controller;
	public JButton ok, ja, nein;
	//Konstruktor
	
	public PopUp(IController ctrl)
	{
		controller = ctrl;
	}
	
	
	
	

	@Override
	public void fehlermeldungAusgeben(int code) 
	{
		switch(code)
		{
			case 1: //Feld leer
			{
				JOptionPane.showMessageDialog(null,"Das ausgewählte Feld ist leer!","Ungültige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
			case 2: //Falsche Farbe der Figur
			{
				JOptionPane.showMessageDialog(null,"Gegnerische Figur ausgewählt, bitte eigene Figur auswählen","Ungültige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
			case 3: //Figur kann sich nicht bewegen
			{
				JOptionPane.showMessageDialog(null,"Bewegung dieser Figur ist blockiert!","Ungültige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
			case 4: //Figur darf sich nicht bewegen(andere muss schlagen)
			{
				JOptionPane.showMessageDialog(null,"Figur darf sich nicht bewegen, eine andere muss schlagen!","Ungültige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
			case 5: //Figur kann sich nicht auf das Feld bewegen
			{
				JOptionPane.showMessageDialog(null,"Figur kann nicht auf dieses Feld bewegt werden!","Ungültige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
		}
		
	}

	@Override
	public boolean popUpAufrufen(int code, ISpieler spieler_1, ISpieler spieler_2) 
	{
	
		switch(code)
		{
			case 1: //Neues Spiel
			{
				antwort=false;
				JButton ok = new JButton("Ok");
				JTextField spieler1 = new JTextField(10);
				JTextField spieler2 = new JTextField(10);
				
				JFrame pop = new JFrame("Neues Spiel");
				pop.setPreferredSize(new Dimension(300,200));
				pop.setResizable(false);
				pop.setLayout(new BoxLayout(null, BoxLayout.Y_AXIS));
				
				pop.add(new JLabel("Spieler 1:"));
				pop.add(spieler1);
				pop.add(new JLabel("Spieler 2:"));
				pop.add(spieler2);
				pop.add(ok);
				ok.addActionListener(this);
				pop.setVisible(true);
				
				if(antwort && spieler1 != null || spieler2 != null)
				{
					spieler_1.setzeName(spieler1.getText());
					spieler_2.setzeName(spieler2.getText());
					pop.dispose();
				}
				break;
			}
		
			case 2: //Aufgeben
			{
				antwort=false;
				JButton ja = new JButton("Ja");
				ja.addActionListener(this);
				JButton nein = new JButton("Nein");
				nein.addActionListener(this);
				
				JFrame pop = new JFrame("Aufgeben");
				pop.setPreferredSize(new Dimension(200,200));
				pop.setResizable(false);
				pop.setLayout(new BorderLayout());
				
				pop.add(new JLabel("Wirklich aufgeben?"), BorderLayout.NORTH);
				pop.add(ja, BorderLayout.WEST);
				pop.add(nein, BorderLayout.EAST);
				pop.setVisible(true);
				
				break;
			}
		
			case 3: //Neustarten
			{
				antwort=false;
				JButton ja = new JButton("Ja");
				ja.addActionListener(this);
				JButton nein = new JButton("Nein");
				nein.addActionListener(this);
				
				JFrame pop = new JFrame("Neustart");
				pop.setPreferredSize(new Dimension(200,200));
				pop.setResizable(false);
				pop.setLayout(new BorderLayout());
				
				pop.add(new JLabel("Spiel wirklich neu starten?"), BorderLayout.NORTH);
				pop.add(ja, BorderLayout.WEST);
				pop.add(nein, BorderLayout.EAST);
				pop.setVisible(true);
				break;
			}
		
			case 4: //Siegesnachricht
			{
				antwort=false;
				JButton ja = new JButton("Rematch?");
				ja.addActionListener(this);
				JButton nein = new JButton("Beenden");
				nein.addActionListener(this);
				
				JFrame pop = new JFrame("Ende");
				pop.setPreferredSize(new Dimension(200,200));
				pop.setResizable(false);
				pop.setLayout(new BorderLayout());
				
				pop.add(new JLabel(spieler_1+"hat gewonnen!"), BorderLayout.NORTH);
				pop.add(ja, BorderLayout.WEST);
				pop.add(nein, BorderLayout.EAST);
				pop.setVisible(true);
				
				
				break;
			}
		
			case 5: //Weiteren Stein schlagen
			{
				antwort=false;
				JButton ja = new JButton("Ja");
				ja.addActionListener(this);
				JButton nein = new JButton("Nein");
				nein.addActionListener(this);
				
				JFrame pop = new JFrame("Möglichkeit weiteren Stein zu schlagen");
				pop.setPreferredSize(new Dimension(200,200));
				pop.setResizable(false);
				pop.setLayout(new BorderLayout());
				
				pop.add(new JLabel("Weiteren Stein schlagen?"), BorderLayout.NORTH);
				pop.add(ja, BorderLayout.WEST);
				pop.add(nein, BorderLayout.EAST);
				pop.setVisible(true);
			}
		}
		return antwort;
	
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.ok)
		{
			antwort=true;
		}
		
		if(e.getSource() == this.ja)
		{
			//TODO
			antwort=true;
			pop.dispose();
		}
		
		if(e.getSource() == this.nein)
		{
			//TODO
			antwort=false;
			pop.dispose();
		}
			
		
	}


}


