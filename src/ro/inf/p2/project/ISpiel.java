package ro.inf.p2.project;

import java.util.ArrayList;

public interface ISpiel {

	boolean hatGewonnen();

//	static final ArrayList<ISpielFigur> spielerSchwarz = new ArrayList<ISpielFigur>();
//	static final ArrayList<ISpielFigur> spielerWeiss = new ArrayList<ISpielFigur>();

	public  ArrayList<ISpielFigur> gibSpielFigurenSchwarz();

	public ArrayList<ISpielFigur> gibSpielFigurenWeiss();

	public  ISpieler pruefeObGewonnen();

	public ISpieler gibIstAmZug();

	public ISpielFigur gibSelektierteFigur();

	public int figurSelektieren(int posX, int posY);

	public void zugBeenden();

	public void neustarten();

	public void aufgeben();

	public boolean bewegeNach(ISpielFigur figur, int zielPosX, int ZielPosY);

	boolean gibKannSpringen(ISpielFigur gibSelektierteFigur);

}
