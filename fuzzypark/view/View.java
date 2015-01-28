package fuzzypark.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JOptionPane;

import fuzzypark.FuzzyParking;
import fuzzypark.events.Event;
import fuzzypark.model.Car;
import fuzzypark.model.Driver;

/**
 * Widok programu
 * 
 * @author J.Komjago
 */
 public class View {
 private SwingFrame frame;
 public SimPanel panel;
 
 LinkedBlockingQueue<Event> eventQ;
 
 	/**
 	 * konstruktor klasy
 	 * 
 	 * @param eventQ
 	 */
 	public View(LinkedBlockingQueue<Event> eventQ){
 		KeyboardControls key = new KeyboardControls();
 		MouseMotionControls mousem = new MouseMotionControls();
 		MouseControls mouse = new MouseControls();
 		this.eventQ = eventQ;
 		frame = new SwingFrame(eventQ);
 		panel = frame.getPanel();
 	 	panel.addKeyListener(key);
 	 	panel.addMouseMotionListener(mousem);
 	 	panel.addMouseListener(mouse);
 	}
	/**
	* rysuje to co trzeba na planszy
 	*/
	public void render() {
		panel.repaint();
	}
	public void sim(){
		double x = panel.carpos.getX()-panel.parkpos.getX();
		double y = panel.carpos.getY()-panel.parkpos.getY();
		double b = panel.getCarAngle();
		Driver kubica = new Driver();
		double a = kubica.drive(b,x);
		Car audi = new Car(a, b, x, y, 20.0);
        audi.doStep();
        x = audi.getX();
        Double temp = x;
        panel.carpos.setX(temp.intValue()+panel.parkpos.getX());
        y = audi.getY();
        temp=y;
        panel.carpos.setY(temp.intValue()+panel.parkpos.getY());
        panel.list.add(panel.carpos);
        b = audi.getB();
        temp=b;
        panel.setCarAngle(temp.intValue());
        panel.repaint();
        a = kubica.drive(b, x);
        audi.setA(a);
        if (x*x + y*y < 4.0 && panel.getCarAngle()==panel.getParkAngle())
        {
            View.infoBox("ZAPARKOWANO POMYSLNIE!", "KONIEC");
            panel.simmode=false;
        }
	}
	/**
	 * tworzy okno informacyjne
	 * @param infoMessage komunikat w oknie
	 * @param name nazwa okna
	 */
	public static void infoBox(String infoMessage, String name)
    {
        JOptionPane.showMessageDialog(null, infoMessage, name, JOptionPane.INFORMATION_MESSAGE);
    }
	/**
	 * Klasa odpowiada za komendy z klawiatury.
	 * 
	 * @author J.Komjago
	 */
	private class KeyboardControls implements KeyListener {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(!simmode()){
					
				if(key == KeyEvent.VK_LEFT){
					if(panel.getCarAngle()>-180)
					panel.setCarAngle(panel.getCarAngle() - 2);
					render();
				}
				else
				if(key == KeyEvent.VK_RIGHT){
					if(panel.getCarAngle()<180)
					panel.setCarAngle(panel.getCarAngle() + 2);
					render();
				}
				}
				if(key == KeyEvent.VK_SPACE){
					/*
					JButton b2 = new JButton("Start Sim");
				    b2.setVerticalTextPosition(AbstractButton.BOTTOM);
				    b2.setHorizontalTextPosition(AbstractButton.CENTER);
				    b2.setMnemonic(KeyEvent.VK_S);
				    b2.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
					*/
					panel.simmode = panel.simmode ? false:true;
					   
			        //	}
				    //});
				    //menuBar.add(b2);
				    //*/
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyTyped(KeyEvent e) {	
			}
	}
	
	private class MouseMotionControls implements MouseMotionListener {
		  //private int mX, mY;

		  public void mouseMoved(MouseEvent me) {
			  //mX = (int) me.getPoint().getX();
			  //mY = (int) me.getPoint().getY();
		  }

		  public void mouseDragged(MouseEvent me) {
			  if(!simmode())
			  {
				  panel.setCarPosition((int) me.getPoint().getX(),(int) me.getPoint().getY());
				  render(); 
			  }
				
		  }

		}
	private class MouseControls implements MouseListener {
		private int mX, mY;
		@Override
		public void mouseClicked(MouseEvent me) {
			
		}

		@Override
		public void mouseEntered(MouseEvent me) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent me) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent me) {
			mX = (int) me.getPoint().getX();
			mY = (int) me.getPoint().getY();
			if(!simmode())
			  {
					panel.setCarPosition(mX,mY);
					render();
			  }	
		}

		@Override
		public void mouseReleased(MouseEvent me) {
			// TODO Auto-generated method stub
			
		}
		
	}

	/**
	 * zwraca true gdy symulacja wlaczona
	 */
	public boolean simmode() {
		return panel.simmode;
	}
}
