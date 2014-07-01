package ro.inf.p2.project;



public interface IPopUp
{
	
	public abstract void fehlermeldungAusgeben(int code);
	public abstract boolean popUpAufrufen(int code, ISpieler spieler_1, ISpieler spieler_2);


	
}
