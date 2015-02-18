import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.Border;


public class Plansza extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
	
	//Warunki zakoñczenia gry.
	boolean wygrywaGracz1 = true;
	boolean wygrywaGracz2 = true;
	
	//Czyj ruch
	boolean ruchGracza1 = false;
	boolean ruchGracza2 = true;
	
	/**
	 * Wspó³rzêdna X wybranego pionka.
	 */
	private static int wXwp = 0;
	
	/**
	 * Wspó³rzêdna Y wybranego pionka.
	 */
	private static int wYwp = 0;
	
	//Stworzenie pionków pierwszego gracza.
	Gracz1 pionekGracza1_1 = new Gracz1(0,5);
	Gracz1 pionekGracza1_2 = new Gracz1(2,5);
	Gracz1 pionekGracza1_3 = new Gracz1(4,5);
	Gracz1 pionekGracza1_4 = new Gracz1(6,5);
	Gracz1 pionekGracza1_5 = new Gracz1(1,6);
	Gracz1 pionekGracza1_6 = new Gracz1(3,6);
	Gracz1 pionekGracza1_7 = new Gracz1(5,6);
	Gracz1 pionekGracza1_8 = new Gracz1(7,6);
	Gracz1 pionekGracza1_9 = new Gracz1(0,7);
	Gracz1 pionekGracza1_10 = new Gracz1(2,7);
	Gracz1 pionekGracza1_11 = new Gracz1(4,7);
	Gracz1 pionekGracza1_12 = new Gracz1(6,7);
	
	Gracz1[] pionkiGracza1 = {pionekGracza1_1, pionekGracza1_2, pionekGracza1_3, pionekGracza1_4, pionekGracza1_5, pionekGracza1_6,
									pionekGracza1_7, pionekGracza1_8, pionekGracza1_9, 
									pionekGracza1_10, pionekGracza1_11, pionekGracza1_12};
	
	//Stworzenie pionków drugiego gracza
	Gracz2 pionekGracza2_1 = new Gracz2(1,0);
	Gracz2 pionekGracza2_2 = new Gracz2(3,0);
	Gracz2 pionekGracza2_3 = new Gracz2(5,0);
	Gracz2 pionekGracza2_4 = new Gracz2(7,0);
	Gracz2 pionekGracza2_5 = new Gracz2(0,1);
	Gracz2 pionekGracza2_6 = new Gracz2(2,1);
	Gracz2 pionekGracza2_7 = new Gracz2(4,1);
	Gracz2 pionekGracza2_8 = new Gracz2(6,1);
	Gracz2 pionekGracza2_9 = new Gracz2(1,2);
	Gracz2 pionekGracza2_10 = new Gracz2(3,2);
	Gracz2 pionekGracza2_11 = new Gracz2(5,2);
	Gracz2 pionekGracza2_12 = new Gracz2(7,2);	
	
	Gracz2[] pionkiGracza2 = {pionekGracza2_1, pionekGracza2_2, pionekGracza2_3, pionekGracza2_4, pionekGracza2_5, pionekGracza2_6, 
			pionekGracza2_7, pionekGracza2_8, pionekGracza2_9, pionekGracza2_10, pionekGracza2_11, pionekGracza2_12};
	
	
	//Dziêki temu wczytywany jest obrazek z folderu Obrazki(nowy folder Ÿród³owy) z katalogu images
	Image iGracza2 = ImageIO.read(getClass().getResource("/images/Pionek1.jpg"));
	Image iGracza1 = ImageIO.read(getClass().getResource("/images/Pionek2.jpg"));
	
	Image iDamka2 = ImageIO.read(getClass().getResource("/images/Pionek1Damka.jpg"));
	Image iDamka1 = ImageIO.read(getClass().getResource("/images/Pionek2Damka.jpg"));
	
	Image pBiale = ImageIO.read(getClass().getResource("/images/PoleBiale.jpg"));
	Image pCzarne = ImageIO.read(getClass().getResource("/images/PoleCzarne.jpg"));
	
	//Zadeklarowanie macierzy z polami planszy
	JButton[][] bPole = new JButton[20][20];

	
	boolean wybranoPionek = false;
	boolean czyMozliweWielokrotneBicie = false;
	boolean wykonanoBicie = false;
	Border ramkaWzniesiona = BorderFactory.createMatteBorder(2,2,2,2, Color.GREEN);

	private JLabel tKoment, tCzyjRuch;
	private JMenuBar menuBar;
	private JMenu menuPlik, menuOpcje, menuPomoc;
	private JMenuItem mNowaGra, mZapisz, mWczytaj, mWyjscie, mOpcja1, mOpcja2, mOProgramie;
	
	
	//KONSTRUKTOR
	public Plansza() throws IOException
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(370,400);
		setLocation(dim.width/2 - 185, dim.height/2 - 200);
		setTitle("Warcaby");
		setLayout(null);
		
		//Stworzenie planszy do gry
		for(int y = 0; y<8; y++)
		{
			for(int x = 0; x<8; x++)
			{
				bPole[x][y] = new JButton("");
				bPole[x][y].setBounds(50 + x*30, 50 + y*30, 30, 30);
				add(bPole[x][y]);
				bPole[x][y].addActionListener(this);
			
				if(		((x==1||x==3||x==5||x==7)&&(y==0||y==2))	||		((x==0||x==2||x==4||x==6)&&(y==1)))
				{
					bPole[x][y].setIcon(new ImageIcon(iGracza1));
				}
				else if(		((x==1||x==3||x==5||x==7)&&(y==6))	||		((x==0||x==2||x==4||x==6)&&(y==5||y==7)))
				{
					//Przypisanie obrazka do pola
					bPole[x][y].setIcon(new ImageIcon(iGracza2));
				}
				else if(((x==1||x==3||x==5||x==7)&&(y==4))	||		((x==0||x==2||x==4||x==6)&&(y==3)))
				{
					bPole[x][y].setIcon(new ImageIcon(pBiale));
				}
				else
				{
					bPole[x][y].setIcon(new ImageIcon(pCzarne));
				}
			}
		}
	
		PanelDoRysowania panel = new PanelDoRysowania();
		panel.paintComponents(null);
		add(panel);
		
		tKoment = new JLabel ("Witajcie! Zaczynaj¹ NIEBIESKIE pionki.");
		tKoment.setBounds(50, 20, 300, 20);
		add(tKoment);
		
		tCzyjRuch = new JLabel ("Ruch: NIEBIESKIE.");
		tCzyjRuch.setBounds(50, 300, 300, 20);
		tCzyjRuch.setForeground(Color.BLUE);
		add(tCzyjRuch);
		
		menuBar = new JMenuBar ();
		
		menuPlik = new JMenu ("Plik");
		
			mNowaGra = new JMenuItem ("Nowa gra");
			mNowaGra.setAccelerator(KeyStroke.getKeyStroke("F5"));
			mNowaGra.addActionListener(this);
			
			mWczytaj = new JMenuItem ("Wczytaj");
			
			mZapisz = new JMenuItem ("Zapisz");
			
			mWyjscie = new JMenuItem ("Wyjœcie");
			mWyjscie.setAccelerator(KeyStroke.getKeyStroke("F4"));
			mWyjscie.addActionListener(this);
			
		menuPlik.add(mNowaGra);
		menuPlik.add(mWczytaj);
		menuPlik.add(mZapisz);
		menuPlik.addSeparator();
		menuPlik.add(mWyjscie);
		
		
		menuOpcje = new JMenu ("Opcje");
		
			mOpcja1 = new JMenuItem ("Opcja1");
			mOpcja2 = new JMenuItem ("Opcja2");
			
		menuOpcje.add(mOpcja1);
		menuOpcje.add(mOpcja2);

		menuPomoc = new JMenu ("Pomoc");
		
			mOProgramie = new JMenuItem ("O programie");
			
		menuPomoc.add(mOProgramie);
		
		setJMenuBar(menuBar);
		menuBar.add(menuPlik);
		menuBar.add(menuOpcje);
		menuBar.add(menuPomoc);
	}
	
	public void restartGry()
	{
		for(int y = 0; y<8; y++)
		{
			for(int x = 0; x<8; x++)
			{
				bPole[x][y].setBorder(UIManager.getBorder("Button.border"));
				
				if(		((x==1||x==3||x==5||x==7)&&(y==0||y==2))	||		((x==0||x==2||x==4||x==6)&&(y==1)))
				{
					bPole[x][y].setIcon(new ImageIcon(iGracza1));
				}
				else if(		((x==1||x==3||x==5||x==7)&&(y==6))	||		((x==0||x==2||x==4||x==6)&&(y==5||y==7)))
				{
					bPole[x][y].setIcon(new ImageIcon(iGracza2));
				}
				else if(((x==1||x==3||x==5||x==7)&&(y==4))	||		((x==0||x==2||x==4||x==6)&&(y==3)))
				{
					bPole[x][y].setIcon(new ImageIcon(pBiale));
				}
				else
				{
					bPole[x][y].setIcon(new ImageIcon(pCzarne));
				}
			}
		}
		
		pionekGracza1_1.setWspolrzednaX(0);
		pionekGracza1_1.setWspolrzednaY(5);
		
		pionekGracza1_2.setWspolrzednaX(2);
		pionekGracza1_2.setWspolrzednaY(5);
		
		pionekGracza1_3.setWspolrzednaX(4);
		pionekGracza1_3.setWspolrzednaY(5);
		
		pionekGracza1_4.setWspolrzednaX(6);
		pionekGracza1_4.setWspolrzednaY(5);
		
		pionekGracza1_5.setWspolrzednaX(1);
		pionekGracza1_5.setWspolrzednaY(6);
		
		pionekGracza1_6.setWspolrzednaX(3);
		pionekGracza1_6.setWspolrzednaY(6);
		
		pionekGracza1_7.setWspolrzednaX(5);
		pionekGracza1_7.setWspolrzednaY(6);
		
		pionekGracza1_8.setWspolrzednaX(7);
		pionekGracza1_8.setWspolrzednaY(6);
		
		pionekGracza1_9.setWspolrzednaX(0);
		pionekGracza1_9.setWspolrzednaY(7);
		
		pionekGracza1_10.setWspolrzednaX(2);
		pionekGracza1_10.setWspolrzednaY(7);
		
		pionekGracza1_11.setWspolrzednaX(4);
		pionekGracza1_11.setWspolrzednaY(7);
		
		pionekGracza1_12.setWspolrzednaX(6);
		pionekGracza1_12.setWspolrzednaY(7);
		
		
		
		pionekGracza2_1.setWspolrzednaX(1);
		pionekGracza2_1.setWspolrzednaY(0);
		
		pionekGracza2_2.setWspolrzednaX(3);
		pionekGracza2_2.setWspolrzednaY(0);
		
		pionekGracza2_3.setWspolrzednaX(5);
		pionekGracza2_3.setWspolrzednaY(0);
		
		pionekGracza2_4.setWspolrzednaX(7);
		pionekGracza2_4.setWspolrzednaY(0);
		
		pionekGracza2_5.setWspolrzednaX(0);
		pionekGracza2_5.setWspolrzednaY(1);
		
		pionekGracza2_6.setWspolrzednaX(2);
		pionekGracza2_6.setWspolrzednaY(1);
		
		pionekGracza2_7.setWspolrzednaX(4);
		pionekGracza2_7.setWspolrzednaY(1);
		
		pionekGracza2_8.setWspolrzednaX(6);
		pionekGracza2_8.setWspolrzednaY(1);
		
		pionekGracza2_9.setWspolrzednaX(1);
		pionekGracza2_9.setWspolrzednaY(2);
		
		pionekGracza2_10.setWspolrzednaX(3);
		pionekGracza2_10.setWspolrzednaY(2);
		
		pionekGracza2_11.setWspolrzednaX(5);
		pionekGracza2_11.setWspolrzednaY(2);
		
		pionekGracza2_12.setWspolrzednaX(7);
		pionekGracza2_12.setWspolrzednaY(2);
		
		for(Gracz2 x : pionkiGracza2)
		{
			x.setDamka(false);
			x.setWGrze(true);
		}
		for(Gracz1 x : pionkiGracza1)
		{
			x.setDamka(false);
			x.setWGrze(true);
		}
		
		tKoment.setText("Witajcie! Zaczynaj¹ NIEBIESKIE pionki.");
		tCzyjRuch.setText("Ruch: NIEBIESKIE.");
		tCzyjRuch.setForeground(Color.BLUE);
		
		ruchGracza1 = false;
		ruchGracza2 = true;
		wybranoPionek = false;
		czyMozliweWielokrotneBicie = false;
		wXwp = 0;
		wYwp = 0;
	}
	
	public static void main(String[] args) throws IOException
	{
		Plansza plansza = new Plansza();
		plansza.setDefaultCloseOperation(EXIT_ON_CLOSE);
		plansza.setVisible(true);
	
		plansza.sprawdzenieStanuGry();
	}
	
	//Wykonywanie dzia³añ po naciœniêciu na pole myszk¹
	public void actionPerformed(ActionEvent e)
	{
		Object zrodlo = e.getSource();
		
		//NOWA GRA
		if(zrodlo == mNowaGra)
		{
			restartGry();
		}
		
		//WYJŒCIE
		if(zrodlo == mWyjscie)
		{
			dispose();
		}
		if(zrodlo instanceof JButton)
		{
			
			for(int y = 0; y<8; y++)
			{
				for(int x = 0; x < 8; x++)
				{
					
					
					
					
					//WYBIERANIE PIONKA
					if(zrodlo == bPole[x][y] && wybranoPionek == false && wXwp == 0 && wYwp == 0)
					{
						for(int i=0; i<12; i++)
						{
							//Sprawdzenie, czy na wybranym polu jest jakiœ pionek do przesuniêcia.
							if(pionkiGracza2[i].getWspolrzednaX() == x && pionkiGracza2[i].getWspolrzednaY() == y && 
									ruchGracza1 == false && ruchGracza2 == true)
							{
								wXwp = x;
								wYwp = y;
								wybranoPionek = true;
								bPole[x][y].setBorder(ramkaWzniesiona);
								tKoment.setText("Wybrano niebieski pionek.");
							}
							if(pionkiGracza1[i].getWspolrzednaX() == x && pionkiGracza1[i].getWspolrzednaY() == y && 
											ruchGracza1 == true && ruchGracza2 == false)
							{
								wXwp = x;
								wYwp = y;
								wybranoPionek = true;
								bPole[x][y].setBorder(ramkaWzniesiona);
								tKoment.setText("Wybrano czerwony pionek.");
							}
						}
					}
					
					
					
					
					
					
					
					
					
					//K£ADZENIE PIONKA
					else if(zrodlo == bPole[x][y] && wybranoPionek == true && (wXwp != x && wYwp != y))
						{	
							//Sprawdzenie, czy na polu stoi jakiœ inny pionek co uniemo¿liwi po³o¿enie na nim wybranego pionka
							boolean czyNaPoluStoiPionek = false;
							for(int i=0; i<12; i++)
							{
								if(pionkiGracza1[i].getWspolrzednaX() == x && pionkiGracza1[i].getWspolrzednaY() == y || 
										pionkiGracza2[i].getWspolrzednaX() == x && pionkiGracza2[i].getWspolrzednaY() == y)
								{
									czyNaPoluStoiPionek = true;
								}
							}
							
							if(czyNaPoluStoiPionek == false)
							{
								for(int i=0; i<12; i++)
								{
									if(pionkiGracza2[i].getWspolrzednaX() == wXwp && pionkiGracza2[i].getWspolrzednaY() == wYwp &&
											ruchGracza1 == false && ruchGracza2 == true)
									{
										boolean niepoprawnyRuch = true;
										
										
										
										
										
										
										//BICIE ZWYKLE PIONKA
										for(int g = 0; g<12; g++)
										{
											if(pionkiGracza2[i].getDamka() == false)
											{
												if(    (x == wXwp + 2 && y == wYwp + 2 && pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1)
													|| (x == wXwp - 2 && y == wYwp + 2 && pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1)
													|| (x == wXwp + 2 && y == wYwp - 2 && pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1)
													|| (x == wXwp - 2 && y == wYwp - 2 && pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1))
												{
													tKoment.setText("Nastêpuje bicie.");
													bPole[pionkiGracza1[g].getWspolrzednaX()][pionkiGracza1[g].getWspolrzednaY()].setIcon(new ImageIcon(pBiale));
													pionkiGracza1[g].setWGrze(false);
													pionkiGracza1[g].setWspolrzednaX(-100);
													pionkiGracza1[g].setWspolrzednaY(-100);
													
													pionkiGracza2[i].setWspolrzednaX(x);
													pionkiGracza2[i].setWspolrzednaY(y);
													bPole[wXwp][wYwp].setIcon(new ImageIcon(pBiale));
													bPole[x][y].setIcon(new ImageIcon(iGracza1));
													bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
													
													
													//BICIE WIELOKROTNE PIONKA
													for(int h = 0; h<12; h++)
													{
														try
														{
														if((pionkiGracza1[h].getWspolrzednaX() == x + 1 && pionkiGracza1[h].getWspolrzednaY() == y + 1 && 
																	bPole[x+2][y+2].getIcon().getIconWidth() == 30) ||
															(pionkiGracza1[h].getWspolrzednaX() == x + 1 && pionkiGracza1[h].getWspolrzednaY() == y - 1 && 
																	bPole[x+2][y-2].getIcon().getIconWidth() == 30)  ||
															(pionkiGracza1[h].getWspolrzednaX() == x - 1 && pionkiGracza1[h].getWspolrzednaY() == y + 1 && 
																	bPole[x-2][y+2].getIcon().getIconWidth() == 30)  ||
															(pionkiGracza1[h].getWspolrzednaX() == x - 1 && pionkiGracza1[h].getWspolrzednaY() == y - 1 && 
																	bPole[x-2][y-2].getIcon().getIconWidth() == 30)) 
														{
															tKoment.setText("Mo¿e zostaæ wykonane bicie wielokrotne.");
															bPole[x][y].setBorder(ramkaWzniesiona);
															wybranoPionek = true;
															czyMozliweWielokrotneBicie = true;
															wykonanoBicie = true;
															
															wXwp = x;
															wYwp = y;
															
															ruchGracza1 = false;
															ruchGracza2 = true;
															
															tCzyjRuch.setText("Ruch: NIEBIESKIE.");
															tCzyjRuch.setForeground(Color.BLUE);
															
															break;
														}
														else 
														{
															bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
															wXwp = 0;
															wYwp = 0;
															wybranoPionek = false;
															ruchGracza1 = true;
															ruchGracza2 = false;
															
															tCzyjRuch.setText("Ruch: CZERWONE.");
															tCzyjRuch.setForeground(Color.RED);
														}
														}
														catch(ArrayIndexOutOfBoundsException AIO)
														{
															System.out.println("B£¥D. TAKIE POLE NIE ISTNIEJE.");
														}
													}
													niepoprawnyRuch = false;
													sprawdzenieStanuGry();
												}
											}
										}
										
										
										
										
										
										//BICIE DAMKI!
										for(int g = 0; g<12; g++)
										{
											if(pionkiGracza2[i].getDamka() == true)
											{			//BICIE 2 POLA
												if(    (x == wXwp + 2 && y == wYwp + 2) && pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1 
													|| (x == wXwp - 2 && y == wYwp + 2) && pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1
													|| (x == wXwp + 2 && y == wYwp - 2) && pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1
													|| (x == wXwp - 2 && y == wYwp - 2) && pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1
													
														//BICIE 3 POLA
													|| (x == wXwp + 3 && y == wYwp + 3) && ((pionkiGracza1[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1))
													|| (x == wXwp - 3 && y == wYwp + 3) && ((pionkiGracza1[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1))
													|| (x == wXwp + 3 && y == wYwp - 3) && ((pionkiGracza1[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1))
													|| (x == wXwp - 3 && y == wYwp - 3) && ((pionkiGracza1[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1))
														
														//BICIE 4 POLA
													|| (x == wXwp + 4 && y == wYwp + 4) && ((pionkiGracza1[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && pionkiGracza1[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1))
													|| (x == wXwp - 4 && y == wYwp + 4) && ((pionkiGracza1[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && pionkiGracza1[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1))
													|| (x == wXwp + 4 && y == wYwp - 4) && ((pionkiGracza1[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && pionkiGracza1[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1))
													|| (x == wXwp - 4 && y == wYwp - 4) && ((pionkiGracza1[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && pionkiGracza1[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1))
													
														//BICIE 5 PÓL
													|| (x == wXwp + 5 && y == wYwp + 5) 
													&&((pionkiGracza1[g].getWspolrzednaX() == wXwp + 4 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 4 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp - 5 && y == wYwp + 5) 
													&&((pionkiGracza1[g].getWspolrzednaX() == wXwp - 4 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 4 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp + 5 && y == wYwp - 5) 
													&&((pionkiGracza1[g].getWspolrzednaX() == wXwp + 4 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 4 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp - 5 && y == wYwp - 5) 
													&&((pionkiGracza1[g].getWspolrzednaX() == wXwp - 4 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 4 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30))
													
														//BICIE 6 PÓL
													|| (x == wXwp + 6 && y == wYwp + 6) 
													&&((pionkiGracza1[g].getWspolrzednaX() == wXwp + 5 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 5 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 4 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 4 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp - 6 && y == wYwp + 6) 
													&&((pionkiGracza1[g].getWspolrzednaX() == wXwp - 5 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 5 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 4 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 4 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp + 6 && y == wYwp - 6) 
													&&((pionkiGracza1[g].getWspolrzednaX() == wXwp + 5 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 5 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 4 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 4 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp - 6 && y == wYwp - 6) 
													&&((pionkiGracza1[g].getWspolrzednaX() == wXwp - 5 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 5 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 4 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 4 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30))
													
														//BICIE 7 POL
													|| (x == wXwp + 7 && y == wYwp + 7) 
													&&((pionkiGracza1[g].getWspolrzednaX() == wXwp + 6 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 6 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 5 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 5 && bPole[wXwp + 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 4 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 4 && bPole[wXwp + 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp + 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp + 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1 && bPole[wXwp + 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp - 7 && y == wYwp + 7) 
													&&((pionkiGracza1[g].getWspolrzednaX() == wXwp - 6 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 6 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 5 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 5 && bPole[wXwp - 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 4 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 4 && bPole[wXwp - 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp - 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp - 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp + 1 && bPole[wXwp - 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp + 7 && y == wYwp - 7) 
													&&((pionkiGracza1[g].getWspolrzednaX() == wXwp + 6 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 6 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 5 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 5 && bPole[wXwp + 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 4 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 4 && bPole[wXwp + 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp + 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp + 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1 && bPole[wXwp + 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp - 7 && y == wYwp - 7) 
													&&((pionkiGracza1[g].getWspolrzednaX() == wXwp - 6 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 6 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 5 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 5 && bPole[wXwp - 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 4 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 4 && bPole[wXwp - 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp - 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp - 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza1[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza1[g].getWspolrzednaY() == wYwp - 1 && bPole[wXwp - 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30))
													)

												{
													
													bPole[pionkiGracza1[g].getWspolrzednaX()][pionkiGracza1[g].getWspolrzednaY()].setIcon(new ImageIcon(pBiale));
													pionkiGracza1[g].setWGrze(false);
													pionkiGracza1[g].setWspolrzednaX(-100);
													pionkiGracza1[g].setWspolrzednaY(-100);
													
													pionkiGracza2[i].setWspolrzednaX(x);
													pionkiGracza2[i].setWspolrzednaY(y);
													bPole[wXwp][wYwp].setIcon(new ImageIcon(pBiale));
													bPole[x][y].setIcon(new ImageIcon(iDamka1));
													bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
													
													wXwp = 0;
													wYwp = 0;
													wybranoPionek = false;
													ruchGracza1 = true;
													ruchGracza2 = false;
													
													tCzyjRuch.setText("Ruch: CZERWONE.");
													tCzyjRuch.setForeground(Color.RED);
													tKoment.setText("Nastêpuje bicie.");
													
													niepoprawnyRuch = false;
													sprawdzenieStanuGry();
												}
											}
										}
										
										
										
										
										
										
										//ZWYK£Y RUCH
										if(pionkiGracza2[i].getDamka() == false && wykonanoBicie == false)
										{
											if((x == wXwp + 1 && y == wYwp + 1) || (x == wXwp - 1 && y == wYwp + 1))
											{
												bPole[wXwp][wYwp].setIcon(new ImageIcon(pBiale));		
												bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
									
												pionkiGracza2[i].setWspolrzednaX(x);
												pionkiGracza2[i].setWspolrzednaY(y);
												bPole[x][y].setIcon(new ImageIcon(iGracza1));
												
												ruchGracza1 = true;
												ruchGracza2 = false;
												niepoprawnyRuch = false;	
												wybranoPionek = false;
												
												wXwp = 0;
												wYwp = 0;
												
												sprawdzenieStanuGry();
												
												tKoment.setText("Wybierz pionek.");
												tCzyjRuch.setText("Ruch: CZERWONE.");
												tCzyjRuch.setForeground(Color.RED);
												
											}
											else if(niepoprawnyRuch == true)
											{
												bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
												tKoment.setText("Niepoprawnie wykonany ruch!");	
												wybranoPionek = false;
												
												wXwp = 0;
												wYwp = 0;
											}
										}
										
										
										
										
										
										
										//RUCH DAMKI
										else if(pionkiGracza2[i].getDamka() == true && wybranoPionek == true)
										{
											if(		   (x == wXwp + 1 && y == wYwp + 1) || (x == wXwp - 1 && y == wYwp + 1) 
													|| (x == wXwp + 1 && y == wYwp - 1) || (x == wXwp - 1 && y == wYwp - 1)
													
													|| (x == wXwp + 2 && y == wYwp + 2) && (bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (x == wXwp - 2 && y == wYwp + 2) && (bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (x == wXwp + 2 && y == wYwp - 2) && (bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 2 && y == wYwp - 2) && (bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													
													|| (x == wXwp + 3 && y == wYwp + 3) && (bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 3 && y == wYwp + 3) && (bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30)
													|| (x == wXwp + 3 && y == wYwp - 3) && (bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 3 && y == wYwp - 3) && (bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30)
													
													|| (x == wXwp + 4 && y == wYwp + 4) && (bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 4 && y == wYwp + 4) && (bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30)
													|| (x == wXwp + 4 && y == wYwp - 4) && (bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 4 && y == wYwp - 4) && (bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30)
													
													|| (x == wXwp + 5 && y == wYwp + 5) && (bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30) && (bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 5 && y == wYwp + 5) && (bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30) && (bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30)
													|| (x == wXwp + 5 && y == wYwp - 5) && (bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30) && (bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 5 && y == wYwp - 5) && (bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30) && (bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30)
													
													|| (x == wXwp + 6 && y == wYwp + 6) && (bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30) && (bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30) && (bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 6 && y == wYwp + 6) && (bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30) && (bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30) && (bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30)
													|| (x == wXwp + 6 && y == wYwp - 6) && (bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30) && (bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30) && (bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 6 && y == wYwp - 6) && (bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30) && (bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30) && (bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30)
													
													|| (x == wXwp + 7 && y == wYwp + 7) && (bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30) && (bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30) && (bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30) && (bPole[wXwp + 6][wYwp + 6].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 7 && y == wYwp + 7) && (bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30) && (bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30) && (bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30) && (bPole[wXwp - 6][wYwp + 6].getIcon().getIconWidth() == 30)
													|| (x == wXwp + 7 && y == wYwp - 7) && (bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30) && (bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30) && (bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30) && (bPole[wXwp + 6][wYwp - 6].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 7 && y == wYwp - 7) && (bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30) && (bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30) && (bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30) && (bPole[wXwp - 6][wYwp - 6].getIcon().getIconWidth() == 30))
											
											{
												bPole[wXwp][wYwp].setIcon(new ImageIcon(pBiale));		
												bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
									
												pionkiGracza2[i].setWspolrzednaX(x);
												pionkiGracza2[i].setWspolrzednaY(y);
												bPole[x][y].setIcon(new ImageIcon(iDamka1));
												
												ruchGracza1 = true;
												ruchGracza2 = false;
												niepoprawnyRuch = false;	
												wybranoPionek = false;
												
												wXwp = 0;
												wYwp = 0;
												
												tCzyjRuch.setText("Ruch: CZERWONE.");
												tCzyjRuch.setForeground(Color.RED);
												
												sprawdzenieStanuGry();
	
												tKoment.setText("Wybierz pionek.");
											}
											
											else if(niepoprawnyRuch == true)
											{
												bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
												tKoment.setText("Niepoprawnie wykonany ruch!");	
												wybranoPionek = false;
												
												wXwp = 0;
												wYwp = 0;
											}
										}
									}
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									
									//GRACZ CZERWONY
									if(pionkiGracza1[i].getWspolrzednaX() == wXwp && pionkiGracza1[i].getWspolrzednaY() == wYwp &&
											ruchGracza1 == true && ruchGracza2 == false)
									{
										boolean niepoprawnyRuch = true;
										
										
										
										
										
										
										//BICIE ZWYKLE PIONKA
										for(int g = 0; g<12; g++)
										{
											if(pionkiGracza1[i].getDamka() == false)
											{
												if(    (x == wXwp + 2 && y == wYwp + 2 && pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1)
													|| (x == wXwp - 2 && y == wYwp + 2 && pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1)
													|| (x == wXwp + 2 && y == wYwp - 2 && pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1)
													|| (x == wXwp - 2 && y == wYwp - 2 && pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1))
												{
													tKoment.setText("Nastêpuje bicie");
													bPole[pionkiGracza2[g].getWspolrzednaX()][pionkiGracza2[g].getWspolrzednaY()].setIcon(new ImageIcon(pBiale));
													pionkiGracza2[g].setWGrze(false);
													pionkiGracza2[g].setWspolrzednaX(-100);
													pionkiGracza2[g].setWspolrzednaY(-100);
													
													pionkiGracza1[i].setWspolrzednaX(x);
													pionkiGracza1[i].setWspolrzednaY(y);
													bPole[wXwp][wYwp].setIcon(new ImageIcon(pBiale));
													bPole[x][y].setIcon(new ImageIcon(iGracza2));
													bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
													
													
													//BICIE WIELOKROTNE PIONKA
													for(int h = 0; h<12; h++)
													{
														try
														{
														if((pionkiGracza2[h].getWspolrzednaX() == x + 1 && pionkiGracza2[h].getWspolrzednaY() == y + 1 && 
																	bPole[x+2][y+2].getIcon().getIconWidth() == 30) ||
															(pionkiGracza2[h].getWspolrzednaX() == x + 1 && pionkiGracza2[h].getWspolrzednaY() == y - 1 && 
																	bPole[x+2][y-2].getIcon().getIconWidth() == 30)  ||
															(pionkiGracza2[h].getWspolrzednaX() == x - 1 && pionkiGracza2[h].getWspolrzednaY() == y + 1 && 
																	bPole[x-2][y+2].getIcon().getIconWidth() == 30)  ||
															(pionkiGracza2[h].getWspolrzednaX() == x - 1 && pionkiGracza2[h].getWspolrzednaY() == y - 1 && 
																	bPole[x-2][y-2].getIcon().getIconWidth() == 30)) 
														{
															tKoment.setText("Mo¿e zostaæ wykonane bicie wielokrotne.");
															bPole[x][y].setBorder(ramkaWzniesiona);
															wybranoPionek = true;
															czyMozliweWielokrotneBicie = true;
															wykonanoBicie = true;
															
															wXwp = x;
															wYwp = y;
															
															ruchGracza1 = true;
															ruchGracza2 = false;
															
															tCzyjRuch.setText("Ruch: CZERWONE");
															tCzyjRuch.setForeground(Color.RED);
															
															break;
														}
														else 
														{
															System.out.println("GÓWNO");
															bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
															wXwp = 0;
															wYwp = 0;
															wybranoPionek = false;
															ruchGracza1 = false;
															ruchGracza2 = true;

															tCzyjRuch.setText("Ruch: NIEBIESKIE.");
															tCzyjRuch.setForeground(Color.BLUE);
														}
														}
														catch(ArrayIndexOutOfBoundsException AIO)
														{
															System.out.println("B£¥D. TAKIE POLE NIE ISTNIEJE.");
														}
													}
													niepoprawnyRuch = false;
													sprawdzenieStanuGry();
												}
											}
										}
										
										
										
										
										
										//BICIE DAMKI!
										for(int g = 0; g<12; g++)
										{
											if(pionkiGracza1[i].getDamka() == true)
											{			//BICIE 2 POLA
												if(    (x == wXwp + 2 && y == wYwp + 2) && pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1 
													|| (x == wXwp - 2 && y == wYwp + 2) && pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1
													|| (x == wXwp + 2 && y == wYwp - 2) && pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1
													|| (x == wXwp - 2 && y == wYwp - 2) && pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1
													
														//BICIE 3 POLA
													|| (x == wXwp + 3 && y == wYwp + 3) && ((pionkiGracza2[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1))
													|| (x == wXwp - 3 && y == wYwp + 3) && ((pionkiGracza2[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1))
													|| (x == wXwp + 3 && y == wYwp - 3) && ((pionkiGracza2[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1))
													|| (x == wXwp - 3 && y == wYwp - 3) && ((pionkiGracza2[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1))
														
														//BICIE 4 POLA
													|| (x == wXwp + 4 && y == wYwp + 4) && ((pionkiGracza2[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && pionkiGracza2[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1))
													|| (x == wXwp - 4 && y == wYwp + 4) && ((pionkiGracza2[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && pionkiGracza2[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1))
													|| (x == wXwp + 4 && y == wYwp - 4) && ((pionkiGracza2[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && pionkiGracza2[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1))
													|| (x == wXwp - 4 && y == wYwp - 4) && ((pionkiGracza2[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && pionkiGracza2[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1))
													
														//BICIE 5 PÓL
													|| (x == wXwp + 5 && y == wYwp + 5) 
													&&((pionkiGracza2[g].getWspolrzednaX() == wXwp + 4 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 4 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp - 5 && y == wYwp + 5) 
													&&((pionkiGracza2[g].getWspolrzednaX() == wXwp - 4 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 4 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp + 5 && y == wYwp - 5) 
													&&((pionkiGracza2[g].getWspolrzednaX() == wXwp + 4 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 4 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp - 5 && y == wYwp - 5) 
													&&((pionkiGracza2[g].getWspolrzednaX() == wXwp - 4 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 4 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30))
													
														//BICIE 6 PÓL
													|| (x == wXwp + 6 && y == wYwp + 6) 
													&&((pionkiGracza2[g].getWspolrzednaX() == wXwp + 5 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 5 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 4 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 4 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp - 6 && y == wYwp + 6) 
													&&((pionkiGracza2[g].getWspolrzednaX() == wXwp - 5 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 5 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 4 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 4 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp + 6 && y == wYwp - 6) 
													&&((pionkiGracza2[g].getWspolrzednaX() == wXwp + 5 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 5 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 4 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 4 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp - 6 && y == wYwp - 6) 
													&&((pionkiGracza2[g].getWspolrzednaX() == wXwp - 5 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 5 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 4 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 4 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30))
													
														//BICIE 7 POL
													|| (x == wXwp + 7 && y == wYwp + 7) 
													&&((pionkiGracza2[g].getWspolrzednaX() == wXwp + 6 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 6 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 5 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 5 && bPole[wXwp + 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 4 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 4 && bPole[wXwp + 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp + 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp + 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1 && bPole[wXwp + 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp - 7 && y == wYwp + 7) 
													&&((pionkiGracza2[g].getWspolrzednaX() == wXwp - 6 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 6 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 5 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 5 && bPole[wXwp - 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 4 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 4 && bPole[wXwp - 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 3 && bPole[wXwp - 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 2 && bPole[wXwp - 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp + 1 && bPole[wXwp - 6][wYwp + 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp + 7 && y == wYwp - 7) 
													&&((pionkiGracza2[g].getWspolrzednaX() == wXwp + 6 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 6 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 5 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 5 && bPole[wXwp + 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 4 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 4 && bPole[wXwp + 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp + 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp + 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp + 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1 && bPole[wXwp + 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30))
													|| (x == wXwp - 7 && y == wYwp - 7) 
													&&((pionkiGracza2[g].getWspolrzednaX() == wXwp - 6 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 6 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 5 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 5 && bPole[wXwp - 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 4 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 4 && bPole[wXwp - 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 3 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 3 && bPole[wXwp - 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) 
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 2 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 2 && bPole[wXwp - 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (pionkiGracza2[g].getWspolrzednaX() == wXwp - 1 && pionkiGracza2[g].getWspolrzednaY() == wYwp - 1 && bPole[wXwp - 6][wYwp - 6].getIcon().getIconWidth() == 30 && bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30 && bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30 && bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30 && bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30))
													)

												{
													tKoment.setText("Nastêpuje bicie");
													bPole[pionkiGracza2[g].getWspolrzednaX()][pionkiGracza2[g].getWspolrzednaY()].setIcon(new ImageIcon(pBiale));
													pionkiGracza2[g].setWGrze(false);
													pionkiGracza2[g].setWspolrzednaX(-100);
													pionkiGracza2[g].setWspolrzednaY(-100);
													
													pionkiGracza1[i].setWspolrzednaX(x);
													pionkiGracza1[i].setWspolrzednaY(y);
													bPole[wXwp][wYwp].setIcon(new ImageIcon(pBiale));
													bPole[x][y].setIcon(new ImageIcon(iDamka2));
													bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
													
													tCzyjRuch.setText("Ruch: NIEBIESKIE.");
													tCzyjRuch.setForeground(Color.BLUE);
													
													niepoprawnyRuch = false;
													sprawdzenieStanuGry();
												}
											}
										}
										
										
										
										
										
										
										//ZWYK£Y RUCH
										if(pionkiGracza1[i].getDamka() == false && wykonanoBicie == false)
										{
											if((x == wXwp + 1 && y == wYwp - 1) || (x == wXwp - 1 && y == wYwp - 1))
											{
												bPole[wXwp][wYwp].setIcon(new ImageIcon(pBiale));		
												bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
									
												pionkiGracza1[i].setWspolrzednaX(x);
												pionkiGracza1[i].setWspolrzednaY(y);
												bPole[x][y].setIcon(new ImageIcon(iGracza2));
												
												ruchGracza1 = false;
												ruchGracza2 = true;
												niepoprawnyRuch = false;	
												wybranoPionek = false;
												
												wXwp = 0;
												wYwp = 0;
												
												sprawdzenieStanuGry();
												
												tKoment.setText("Wybierz pionek.");
												tCzyjRuch.setText("Ruch: NIEBIESKIE.");
												tCzyjRuch.setForeground(Color.BLUE);
											}
											else if(niepoprawnyRuch == true)
											{
												bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
												tKoment.setText("Niepoprawnie wykonany ruch!");	
												wybranoPionek = false;
												
												wXwp = 0;
												wYwp = 0;
											}
										}
										
										
										
										
										
										
										//RUCH DAMKI
										else if(pionkiGracza1[i].getDamka() == true)
										{
											if(		   (x == wXwp + 1 && y == wYwp + 1) || (x == wXwp - 1 && y == wYwp + 1) 
													|| (x == wXwp + 1 && y == wYwp - 1) || (x == wXwp - 1 && y == wYwp - 1)
													
													|| (x == wXwp + 2 && y == wYwp + 2) && (bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) 
													|| (x == wXwp - 2 && y == wYwp + 2) && (bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30)
													|| (x == wXwp + 2 && y == wYwp - 2) && (bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 2 && y == wYwp - 2) && (bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30)
													
													|| (x == wXwp + 3 && y == wYwp + 3) && (bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 3 && y == wYwp + 3) && (bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30)
													|| (x == wXwp + 3 && y == wYwp - 3) && (bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 3 && y == wYwp - 3) && (bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30)
													
													|| (x == wXwp + 4 && y == wYwp + 4) && (bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 4 && y == wYwp + 4) && (bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30)
													|| (x == wXwp + 4 && y == wYwp - 4) && (bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 4 && y == wYwp - 4) && (bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30)
													
													|| (x == wXwp + 5 && y == wYwp + 5) && (bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30) && (bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 5 && y == wYwp + 5) && (bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30) && (bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30)
													|| (x == wXwp + 5 && y == wYwp - 5) && (bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30) && (bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 5 && y == wYwp - 5) && (bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30) && (bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30)
													
													|| (x == wXwp + 6 && y == wYwp + 6) && (bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30) && (bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30) && (bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 6 && y == wYwp + 6) && (bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30) && (bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30) && (bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30)
													|| (x == wXwp + 6 && y == wYwp - 6) && (bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30) && (bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30) && (bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 6 && y == wYwp - 6) && (bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30) && (bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30) && (bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30)
													
													|| (x == wXwp + 7 && y == wYwp + 7) && (bPole[wXwp + 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp + 3].getIcon().getIconWidth() == 30) && (bPole[wXwp + 4][wYwp + 4].getIcon().getIconWidth() == 30) && (bPole[wXwp + 5][wYwp + 5].getIcon().getIconWidth() == 30) && (bPole[wXwp + 6][wYwp + 6].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 7 && y == wYwp + 7) && (bPole[wXwp - 1][wYwp + 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp + 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp + 3].getIcon().getIconWidth() == 30) && (bPole[wXwp - 4][wYwp + 4].getIcon().getIconWidth() == 30) && (bPole[wXwp - 5][wYwp + 5].getIcon().getIconWidth() == 30) && (bPole[wXwp - 6][wYwp + 6].getIcon().getIconWidth() == 30)
													|| (x == wXwp + 7 && y == wYwp - 7) && (bPole[wXwp + 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp + 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp + 3][wYwp - 3].getIcon().getIconWidth() == 30) && (bPole[wXwp + 4][wYwp - 4].getIcon().getIconWidth() == 30) && (bPole[wXwp + 5][wYwp - 5].getIcon().getIconWidth() == 30) && (bPole[wXwp + 6][wYwp - 6].getIcon().getIconWidth() == 30)
													|| (x == wXwp - 7 && y == wYwp - 7) && (bPole[wXwp - 1][wYwp - 1].getIcon().getIconWidth() == 30) && (bPole[wXwp - 2][wYwp - 2].getIcon().getIconWidth() == 30) && (bPole[wXwp - 3][wYwp - 3].getIcon().getIconWidth() == 30) && (bPole[wXwp - 4][wYwp - 4].getIcon().getIconWidth() == 30) && (bPole[wXwp - 5][wYwp - 5].getIcon().getIconWidth() == 30) && (bPole[wXwp - 6][wYwp - 6].getIcon().getIconWidth() == 30))
											
											{
												bPole[wXwp][wYwp].setIcon(new ImageIcon(pBiale));		
												bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
									
												pionkiGracza1[i].setWspolrzednaX(x);
												pionkiGracza1[i].setWspolrzednaY(y);
												bPole[x][y].setIcon(new ImageIcon(iDamka2));
												
												ruchGracza1 = false;
												ruchGracza2 = true;
												niepoprawnyRuch = false;	
												wybranoPionek = false;
												
												wXwp = 0;
												wYwp = 0;
												
												tCzyjRuch.setText("Ruch: NIEBIESKIE.");
												tCzyjRuch.setForeground(Color.BLUE);
												tKoment.setText("Wybierz pionek.");
												
												sprawdzenieStanuGry();
											}
											
											else if(niepoprawnyRuch == true)
											{
												bPole[wXwp][wYwp].setBorder(UIManager.getBorder("Button.border"));
												tKoment.setText("Niepoprawnie wykonany ruch!");	
												wybranoPionek = false;
												
												wXwp = 0;
												wYwp = 0;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	
	//DZIA£A - sprawdzenie, czy którychœ z graczy nie straci³ ju¿ wszystkich pionków.
	public void sprawdzenieStanuGry()
	{
		
		wygrywaGracz1 = true;
		wygrywaGracz2 = true;
		
		for(int g=0; g<12; g++)
		{
			if(pionkiGracza1[g].getWGrze() == true)
			{
				wygrywaGracz2 = false;
			}
			if(pionkiGracza2[g].getWGrze() == true)
			{
				wygrywaGracz1 = false;
			}
			if(pionkiGracza1[g].getWspolrzednaY() == 0)
			{
				pionkiGracza1[g].setDamka(true);
				bPole[pionkiGracza1[g].getWspolrzednaX()][0].setIcon(new ImageIcon(iDamka2));
			}
			if(pionkiGracza2[g].getWspolrzednaY() == 7)
			{
				pionkiGracza2[g].setDamka(true);
				bPole[pionkiGracza2[g].getWspolrzednaX()][7].setIcon(new ImageIcon(iDamka1));
			}
		}
		
		for(Gracz2 x: pionkiGracza2)
		{
			System.out.println("X: " +x.getWspolrzednaX() + ",  Y: " + x.getWspolrzednaY() + ". Id: " + x.getId() + 
					".W grze: " + x.getWGrze() + ". Damka: " + x.getDamka());
		}
		for(Gracz1 x: pionkiGracza1)
		{
			System.out.println("X: " + x.getWspolrzednaX() + ",  Y: " + x.getWspolrzednaY() + ". Id: " + x.getId() + 
					".W grze: " + x.getWGrze() + ". Damka: " + x.getDamka());
		}
		System.out.println("Wybrano pionek: " + wybranoPionek);
		
		if(wygrywaGracz1 == true)
		{
			tKoment.setText("WYGRYWAJ¥ CZERWONE PIONKI!");
			tKoment.setForeground(Color.RED);
			
			tCzyjRuch.setText("");
		}
		if(wygrywaGracz2 == true)
		{
			tKoment.setText("WYGRYWAJ¥ NIEBIESKIE PIONKI!");
			tKoment.setForeground(Color.BLUE);
			
			tCzyjRuch.setText("");
		}
	}
}


