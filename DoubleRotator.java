/*
 File: DoubleRotator.java
 */

package doublerotator;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DoubleRotator extends Frame implements Runnable {
	static final long serialVersionUID = -4145005270654024017L;
	private RotatorPanel rotatorA;
	private RotatorPanel rotatorB;
	
	public DoubleRotator() {
		super("DoubleRotator");
		this.setLayout(new GridLayout(1,2));
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				stop();
			}
		});
		this.setFont(new Font("Helvetica",Font.BOLD,14));
		this.rotatorA = new RotatorPanel("Thread A");
		this.add(this.rotatorA);
		this.rotatorB = new RotatorPanel("Thread B");
		this.add(this.rotatorB);
		
		this.start();
		this.setSize(350, 260);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void start() {
		this.rotatorA.start();
		this.rotatorB.start();
	}
	
	public void stop() {
		this.rotatorA.stop();
		this.rotatorB.stop();
	}
	
	public static void main(String[] args) {
		new DoubleRotator();
	}

	@Override
	public void run() {
		try {
		      while(true) rotatorA.rotate();
		    } catch(InterruptedException e) {}
		  }
		}
		
	}


		
	}
}
