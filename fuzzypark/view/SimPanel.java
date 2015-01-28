package fuzzypark.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fuzzypark.common.Position;
import fuzzypark.events.Event;



/**
 * Klasa tworzy zawartość okna i wątki oraz imi zarządza
 * 
 * @author J.Komjago
 */
public class SimPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	//screen variables
	public static final int WIDTH = 600, HEIGHT= 600;
	//event queue
	LinkedBlockingQueue<Event> eventQ;
	
	public Position carpos = new Position(0,0);
	public Position parkpos = new Position(300,20);
	public LinkedList<Position> list = new LinkedList<Position>();
	private int carAngle = 90;
	private int parkAngle = 0;
	int mode = 0;
	public boolean simmode = false;

	//image handling
	ImageIcon car = (new ImageIcon(getClass().getResource("/fuzzypark/textures/car.png")));
	Image carImg = car.getImage();
	
 	public SimPanel(){
 		setFocusable(true);
 		setPreferredSize(new Dimension(WIDTH,HEIGHT));
	}
	//methods
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(final Graphics g){
		//draw map
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		for(Integer i=50;i<600;i+=50)
		{
			g.drawLine(1, i, 10, i);
			g.drawLine(i, 1, i, 10);
			g.drawString(i.toString(), 11, i+6);
		}
		
			g.setColor(Color.RED);
			if(list.size()>1)
			for(int i=1;i<list.size();i++)
			  g.drawLine(list.get(i-1).getX(), list.get(i-1).getY(), list.get(i).getX(),list.get(i).getY());
		if (parkpos != null)
			drawpark(g,parkpos,getParkAngle());
		if (carpos != null)
			drawcar(g,carpos,getCarAngle());
		String s = simmode ? "simmode on":"simmode off";
		g.drawString(s,500,10);
		
	}
	private void drawpark(Graphics g, Position pos2, Integer angle) {
		int w = car.getIconWidth(), h = car.getIconHeight();
		Graphics2D g2d = (Graphics2D) g.create();
		angle = angle-90;
		AffineTransform tx = new AffineTransform();
	    g2d.setTransform(tx);
	    g2d.translate(pos2.getX()-w/2, pos2.getY()-h/2);
	    g2d.rotate(Math.toRadians(angle), w/2, h/2);
	    g2d.setColor(Color.WHITE);
	    g2d.drawRect(0, 0, 42, 24);
	    g2d.setColor(Color.RED);
		g2d.drawLine(0, h/2, -100, h/2);
		
	}
	
	private void drawcar(Graphics g ,Position p , Integer angle) {
	    int w = car.getIconWidth(), h = car.getIconHeight();
		Graphics2D g2d = (Graphics2D) g.create();
		angle = angle-90;
		AffineTransform tx = new AffineTransform();
	    g2d.setTransform(tx);
	    g2d.translate(p.getX()-w/2, p.getY()-h/2);
	    g2d.rotate(Math.toRadians(angle), w/2, h/2);
	    g2d.setColor(Color.YELLOW);
		g2d.drawLine(0, h/2, 100, h/2);
		angle = angle+90;
		g2d.drawString(angle.toString(), 0, 0);
		g2d.drawImage(carImg,tx, null);
	}
	
	public void setCarPosition(int x,int y){
		carpos.setX(x);
		carpos.setY(y);
	}
	public void mode(int i) {
		mode=i;
	}
	public void setParkPosition(int x, int y) {
		parkpos.setX(x);
		parkpos.setY(y);		
	}
	public int getCarAngle() {
		return carAngle;
	}
	public void setCarAngle(int carAngle) {
		this.carAngle = carAngle;
	}
	public int getParkAngle() {
		return parkAngle;
	}
	void setParkAngle(int parkAngle) {
		this.parkAngle = parkAngle;
	}
	
}
