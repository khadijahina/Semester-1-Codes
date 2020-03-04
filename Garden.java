/* 
 File: Garden.java
 */

package garden;

import java.awt.*;
import java.awt.event.*;

/** the Garden Class
 */

public class Garden extends Frame implements ActionListener {
	static final long serialVersionUID = -7132975727342550366L;
	private Turnstile west;
	private Turnstile east;
	private Counter total;
	private Counter westEntry;
	private Counter eastEntry;
	
	public final static int MAX = 20;
	
	public Garden() {
		setLayout( new BorderLayout() );
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		Panel buttonPanel = new Panel();
		Button goButton = new Button( "Go" );
		
		buttonPanel.add( goButton );
		goButton.addActionListener( this ); 
		goButton.setFont( new Font( "Helvetica",Font.BOLD,24 ) );
		add( buttonPanel, BorderLayout.SOUTH ); 
		
		Panel mainPanel = new Panel( new GridLayout(1,3) ); 
		total = new SafeCounter( "Counter" );
		westEntry = new Counter( "West", Color.GREEN );
		eastEntry = new Counter( "East", Color.GREEN );
		mainPanel.add( westEntry ); 
		mainPanel.add( total ); 
		mainPanel.add( eastEntry ); 
		
		add( mainPanel, BorderLayout.CENTER ); 
		this.setSize(400,200);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private void go() {
		total.setValue( 0 );
		westEntry.setValue( 0 );
		eastEntry.setValue( 0 );
		
		west = new Turnstile( westEntry, total );
		east = new Turnstile( eastEntry, total );
		west.start();
		east.start();
	}
	
	public void actionPerformed( ActionEvent evt ) {
		// start threads only if they are not active
		if ( (west == null && east == null) ||
				(!west.isAlive() && !east.isAlive()) ) {
			go();
		}
	}
	
	public static void main(String[] args) {
		new Garden();
	}
}

