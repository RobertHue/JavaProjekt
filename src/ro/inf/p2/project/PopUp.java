package ro.inf.p2.project;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
*@author Maximilian Bottin
*/
public class PopUp implements IPopUp, ActionListener
{
	
	public boolean antwort=false;
	private JFrame pop;
	IController controller;
	private JButton ok, ja_a, nein_a, ja_ns, nein_ns, ja_s, nein_s, ja_w, nein_w;
	public String spieler1name, spieler2name;
	public JTextField spieler1, spieler2;
	

	//Konstruktor
	
	public PopUp(IController ctrl)
	{
		controller = ctrl;
	}
	
	
	
	

	public void fehlermeldungAusgeben(int code) 
	{
		switch(code)
		{
			case 1: //Feld leer
			{
				JOptionPane.showMessageDialog(null,"Das ausgewaehlte Feld ist leer!","Ungueltige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
			case 2: //Falsche Farbe der Figur
			{
				JOptionPane.showMessageDialog(null,"Gegnerische Figur ausgewaehlt, bitte eigene Figur auswaehlen","Ungueltige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
			case 3: //Figur kann sich nicht bewegen
			{
				JOptionPane.showMessageDialog(null,"Bewegung dieser Figur ist blockiert!","Ungueltige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
			case 4: //Figur darf sich nicht bewegen(andere muss schlagen)
			{
				JOptionPane.showMessageDialog(null,"Figur darf sich nicht bewegen, eine andere muss schlagen!","Ungueltige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
			case 5: //Figur kann sich nicht auf das Feld bewegen
			{
				JOptionPane.showMessageDialog(null,"Figur kann nicht auf dieses Feld bewegt werden!","Ungueltige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
		}
		
	}

	public void popUpAufrufen(int code, String spieler) 
	{
	
		switch(code)
		{
			case 1: //Neues Spiel
			{
				/*
				ok = new JButton("Ok");
				spieler1 = new JTextField(10);
				spieler2 = new JTextField(10);
				
				pop = new JFrame("Neues Spiel");
				pop.setPreferredSize(new Dimension(300,200));
				pop.setResizable(false);
				pop.setLayout(new BoxLayout(pop, BoxLayout.Y_AXIS));
				
				pop.add(new JLabel("Spieler 1:"));
				pop.add(spieler1);
				pop.add(new JLabel("Spieler 2:"));
				pop.add(spieler2);
				pop.add(ok);
				ok.addActionListener(this);
				pop.setVisible(true);
				*/
		        pop = new JFrame("Neues Spiel");
		        pop.setPreferredSize(new Dimension(250,150));
				pop.setResizable(false);
				
			    Container pane= pop.getContentPane();
			    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

			    JLabel label1 = new JLabel("Spieler 1:");
			    label1.setAlignmentX(Component.LEFT_ALIGNMENT);
			    pane.add(label1);
			    
			    spieler1 = new JTextField(10);
			    spieler1.setAlignmentX(Component.LEFT_ALIGNMENT);
			    pane.add(spieler1);

			    JLabel label2 = new JLabel("Spieler 2:");
			    label2.setAlignmentX(Component.LEFT_ALIGNMENT);
			    pane.add(label2);

			        
			    spieler2 = new JTextField(10);
			    spieler2.setAlignmentX(Component.LEFT_ALIGNMENT);
			    pane.add(spieler2);

			    ok = new JButton("Ok");
			    ok.addActionListener(this);
			    ok.setAlignmentX(Component.LEFT_ALIGNMENT);
			    pane.add(ok);
			    
			    pop.pack();
			    pop.setVisible(true);
			    
				break;
			}
		
			case 2: //Aufgeben
			{
				ja_a = new JButton("Ja");
				ja_a.addActionListener(this);
				nein_a = new JButton("Nein");
				nein_a.addActionListener(this);
				
				pop = new JFrame("Aufgeben");
				pop.setPreferredSize(new Dimension(140,80));
				pop.setResizable(false);
				pop.setLayout(new BorderLayout());
				
				pop.add(new JLabel("Wirklich aufgeben?"), BorderLayout.NORTH);
				pop.add(ja_a, BorderLayout.WEST);
				pop.add(nein_a, BorderLayout.EAST);
				pop.pack();
				pop.setVisible(true);
				
				
				break;
			}
		
			case 3: //Neustarten
			{
				ja_ns = new JButton("Ja");
				ja_ns.addActionListener(this);
				nein_ns = new JButton("Nein");
				nein_ns.addActionListener(this);
				
				pop = new JFrame("Neustart");
				pop.setPreferredSize(new Dimension(140,80));
				pop.setResizable(false);
				pop.setLayout(new BorderLayout());
				
				pop.add(new JLabel("Spiel wirklich neu starten?"), BorderLayout.NORTH);
				pop.add(ja_ns, BorderLayout.WEST);
				pop.add(nein_ns, BorderLayout.EAST);
				pop.pack();
				pop.setVisible(true);
				break;
			}
		
			case 4: //Siegesnachricht
			{
				ja_s = new JButton("Rematch?");
				ja_s.addActionListener(this);
				nein_s = new JButton("Beenden");
				nein_s.addActionListener(this);
				
				pop = new JFrame("Ende");
				pop.setPreferredSize(new Dimension(140,80));
				pop.setResizable(false);
				pop.setLayout(new BorderLayout());
				
				pop.add(new JLabel(spieler1name+"hat gewonnen!"), BorderLayout.NORTH);
				pop.add(ja_s, BorderLayout.WEST);
				pop.add(nein_s, BorderLayout.EAST);
				pop.pack();
				pop.setVisible(true);
				
				
				break;
			}
		
			case 5: //Weiteren Stein schlagen
			{
				ja_w = new JButton("Ja");
				ja_w.addActionListener(this);
				nein_w = new JButton("Nein");
				nein_w.addActionListener(this);
				
				pop = new JFrame("Moeglichkeit weiteren Stein zu schlagen");
				pop.setPreferredSize(new Dimension(140,80));
				pop.setResizable(false);
				pop.setLayout(new BorderLayout());
				
				pop.add(new JLabel("Weiteren Stein schlagen?"), BorderLayout.NORTH);
				pop.add(ja_w, BorderLayout.WEST);
				pop.add(nein_w, BorderLayout.EAST);
				pop.pack();
				pop.setVisible(true);
			}
		}
		
	
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.ok)
		{
			
			//if(spieler1 != null && spieler2 != null)
				spieler1name=spieler1.getText();
				spieler2name=spieler2.getText();
				if(spieler1name.length()!=0 && spieler2name.length()!= 0)
				{
					
					pop.setVisible(false);
					controller.neuesSpielStarten(spieler1name,spieler2name);
					
					pop.dispose();
				}

			
				
				//ueber Funktion neues Spiel im Controller uebergeben
				

			

		}
		
		if(e.getSource() == this.ja_a)
		{
			pop.setVisible(false);
			controller.aufgeben(true);

			pop.dispose();
			//TODO Verdammt nochmal merk dir das dort die methodenaufrufe sind!!!

		}
		
		if(e.getSource() == this.nein_a)
		{
			pop.setVisible(false);
			controller.aufgeben(false);

			pop.dispose();
			//TODO

		}
		
		if(e.getSource() == this.ja_ns)
		{
			controller.neustarten(true);
			pop.setVisible(false);
			pop.dispose();
			//TODO

		}
		
		if(e.getSource() == this.nein_ns)
		{
			pop.setVisible(false);
			controller.neustarten(false);

			pop.dispose();
			//TODO

		}
		
		if(e.getSource() == this.ja_s)
		{
			pop.setVisible(false);
			controller.ende(true);

			pop.dispose();
			//TODO

		}
		
		if(e.getSource() == this.nein_s)
		{
			pop.setVisible(false);
			controller.ende(false);
			
			pop.dispose();
			//TODO

		}
		
		if(e.getSource() == this.ja_w)
		{
			pop.setVisible(false);
			controller.nochmalSpringen(true);
			
			pop.dispose();
			//TODO

		}
		
		if(e.getSource() == this.nein_w)
		{
			pop.setVisible(false);
			controller.nochmalSpringen(false);
			
			pop.dispose();
			//TODO
		}
		
		
			
		
	}




}


