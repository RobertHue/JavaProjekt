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
	public JButton ok, ja_a, nein_a, ja_ns, nein_ns, ja_s, nein_s, ja_w, nein_w;
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
				JOptionPane.showMessageDialog(null,"Das ausgew臧lte Feld ist leer!","Ung�ltige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
			case 2: //Falsche Farbe der Figur
			{
				JOptionPane.showMessageDialog(null,"Gegnerische Figur ausgew臧lt, bitte eigene Figur ausw臧len","Ung�ltige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
			case 3: //Figur kann sich nicht bewegen
			{
				JOptionPane.showMessageDialog(null,"Bewegung dieser Figur ist blockiert!","Ung�ltige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
			case 4: //Figur darf sich nicht bewegen(andere muss schlagen)
			{
				JOptionPane.showMessageDialog(null,"Figur darf sich nicht bewegen, eine andere muss schlagen!","Ung�ltige Aktion", JOptionPane.ERROR_MESSAGE);
				break;
			}
		
			case 5: //Figur kann sich nicht auf das Feld bewegen
			{
				JOptionPane.showMessageDialog(null,"Figur kann nicht auf dieses Feld bewegt werden!","Ung�ltige Aktion", JOptionPane.ERROR_MESSAGE);
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
				

				break;
			}
		
			case 2: //Aufgeben
			{
				JButton ja_a = new JButton("Ja");
				ja_a.addActionListener(this);
				JButton nein_a = new JButton("Nein");
				nein_a.addActionListener(this);
				
				JFrame pop = new JFrame("Aufgeben");
				pop.setPreferredSize(new Dimension(200,200));
				pop.setResizable(false);
				pop.setLayout(new BorderLayout());
				
				pop.add(new JLabel("Wirklich aufgeben?"), BorderLayout.NORTH);
				pop.add(ja_a, BorderLayout.WEST);
				pop.add(nein_a, BorderLayout.EAST);
				pop.setVisible(true);
				
				
				break;
			}
		
			case 3: //Neustarten
			{
				JButton ja_ns = new JButton("Ja");
				ja_ns.addActionListener(this);
				JButton nein_ns = new JButton("Nein");
				nein_ns.addActionListener(this);
				
				JFrame pop = new JFrame("Neustart");
				pop.setPreferredSize(new Dimension(200,200));
				pop.setResizable(false);
				pop.setLayout(new BorderLayout());
				
				pop.add(new JLabel("Spiel wirklich neu starten?"), BorderLayout.NORTH);
				pop.add(ja_ns, BorderLayout.WEST);
				pop.add(nein_ns, BorderLayout.EAST);
				pop.setVisible(true);
				break;
			}
		
			case 4: //Siegesnachricht
			{
				JButton ja_s = new JButton("Rematch?");
				ja_s.addActionListener(this);
				JButton nein_s = new JButton("Beenden");
				nein_s.addActionListener(this);
				
				JFrame pop = new JFrame("Ende");
				pop.setPreferredSize(new Dimension(200,200));
				pop.setResizable(false);
				pop.setLayout(new BorderLayout());
				
				pop.add(new JLabel(spieler1name+"hat gewonnen!"), BorderLayout.NORTH);
				pop.add(ja_s, BorderLayout.WEST);
				pop.add(nein_s, BorderLayout.EAST);
				pop.setVisible(true);
				
				
				break;
			}
		
			case 5: //Weiteren Stein schlagen
			{
				JButton ja_w = new JButton("Ja");
				ja_w.addActionListener(this);
				JButton nein_w = new JButton("Nein");
				nein_w.addActionListener(this);
				
				JFrame pop = new JFrame("Mlichkeit weiteren Stein zu schlagen");
				pop.setPreferredSize(new Dimension(200,200));
				pop.setResizable(false);
				pop.setLayout(new BorderLayout());
				
				pop.add(new JLabel("Weiteren Stein schlagen?"), BorderLayout.NORTH);
				pop.add(ja_w, BorderLayout.WEST);
				pop.add(nein_w, BorderLayout.EAST);
				pop.setVisible(true);
			}
		}
		
	
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.ok)
		{
			if(spieler1 != null && spieler2 != null)
			{
				spieler1name=spieler1.getText();
				spieler2name=spieler2.getText();
				pop.setVisible(false);
				
				controller.neuesSpielStarten(spieler1name,spieler2name);
			
				
				//�ber Funktion neues Spiel im Controller �bergeben
				pop.dispose();
			}

			

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


