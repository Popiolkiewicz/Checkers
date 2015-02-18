
public class Gracz1
{
	private int wspolrzednaX;
	private int wspolrzednaY;
	private int id;
	static int nextId = 1;
	private boolean wGrze;
	private boolean damka;

	public Gracz1()
	{
		this(1,1);
	}
	public Gracz1(int vI, int vJ)
	{
		id = nextId;
		nextId++;
		wspolrzednaX = vI;
		wspolrzednaY = vJ;
		wGrze = true;
		damka = false;
	}
	public int getWspolrzednaX() {
		return wspolrzednaX;
	}
	public int getWspolrzednaY() {
		return wspolrzednaY;
	}
	public int getId()
	{
		return id;
	}
	public boolean getWGrze()
	{
		return wGrze;
	}

	public void setWspolrzednaX(int wspolrzednaX) {
		this.wspolrzednaX = wspolrzednaX;
	}
	public void setWspolrzednaY(int wspolrzednaY) {
		this.wspolrzednaY = wspolrzednaY;
	}
	public void setId(int vGracz1)
	{
		id = vGracz1;
	}
	public void setWGrze(boolean vWGrze)
	{
		wGrze = vWGrze;
	}
	
	public boolean getDamka() {
		return damka;
	}
	public void setDamka(boolean damka) {
		this.damka = damka;
	}
}