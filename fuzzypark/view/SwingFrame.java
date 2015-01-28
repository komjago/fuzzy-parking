package fuzzypark.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.LayoutStyle;

import fuzzypark.events.Event;
import fuzzypark.model.Car;
import fuzzypark.model.Driver;

public class SwingFrame extends JFrame {

	//version
	private static final long serialVersionUID = 1L;
	private static final String version = "V0.5";
	//Menu
	private JMenuBar menuBar;
	private JMenu menu;
	private SimPanel panel;
	//Menu items
	private JMenuItem exitMenuItem;
	private JRadioButtonMenuItem rbMenuItem;
	private LinkedBlockingQueue<Event> eventQ;
	/**
	 *konstruktor 
	 */
	public SwingFrame(LinkedBlockingQueue<Event> eventQ) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setName("Park");
		setTitle("Fuzzy Parking " + version);
		setResizable(false);
		this.eventQ=eventQ;
		init();
	}
	/**
	 * inicjalizacja, tworzenie menu
	 */
	private void init(){
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_G);
		menuBar.add(menu);   
		panel = new SimPanel();
		setJMenuBar(menuBar);
		
		add(panel,BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setMnemonic(KeyEvent.VK_E);
		exitMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});     
		menu.add(exitMenuItem);
	}
	/**
	 * @return panel z grą
	 */
	public SimPanel getPanel() {
		return panel;
	}

}
