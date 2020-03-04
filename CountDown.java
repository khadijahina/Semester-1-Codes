/*
 File: CountDown.java
 */

package counter;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import support.NumberPanel;
import support.SoundClips;

/**
 * CountDown
 *
 **/

public class CountDown extends Frame implements Runnable {
	static final long serialVersionUID = -445629717879839816L;
	private boolean isRunning = false;
	private int current;
	Thread counter;
	private final static int START = 10;
	private static final String N = null;
	private SoundClips clips;
	private NumberPanel display;
	 
	  
	
	public CountDown() {
		super();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		 display = new NumberPanel("Counter"); 
		add(display);
		setResizable(false);
		setSize(100 + 20, 150);
		current = START;
		display.setValue(current);
		this.setVisible(true);
//		clips = new SoundClips();
		tick();
		counter = new Thread(this);
		counter.start();
	}
	
	private boolean getCurrentState() {
		return this.isRunning; 
	}


public void run () {
	this.isRunning = true;
	while(getCurrentState()) {
	      if (current>=0) 
	      {
	    			display.setValue(current); 
//	    			clips.tick();
	    			try { 
	    				Thread.sleep( 1000 );
	    			} catch (InterruptedException e) {
	    				return; 
	    			}
	      current--; }
//	      if (current==0) 
//	      {
////	    	  beep(); 
//	    	  return;
//	      }
	    }
	  
}
	private  void tick() {
		synchronized(this) {
		display.setValue(current); 
//		clips.tick();
		try { 
			Thread.sleep( 1000 );
		} catch (InterruptedException e) {
			return; 
		}
		}
	}
	
	
	@SuppressWarnings("unused")
	private void beep() {
		display.setValue(current);
//		clips.beep();
	}
	
	public static void main(String[] args) {
		new CountDown();
	}
}
