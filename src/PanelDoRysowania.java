import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class PanelDoRysowania extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(null);
		
		g.setColor(Color.BLUE);
		g.fillOval(50, 50, 25, 25);
		
		g.setColor(new Color(190,81,215));
		g.fillOval(50, 120, 25, 25);
		
		g.setColor(Color.RED);
		g.drawString("this is some text", 100, 100);
	}
	public static void main(String[] args) 
	{
	
	}
}
