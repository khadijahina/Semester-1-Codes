

/*
File: RotatorPanel.java
*/

package doublerotator;

import java.awt.*;
import java.awt.event.*;

public class ArcPanel extends Panel {
	static final long serialVersionUID = 1845867469902235802L;
	private ArcPanel arcPanel;
	private Button run;
	private Button pause;
	
	static final Color RUNCOLOR = Color.GREEN;
	static final Color PAUSECOLOR = Color.RED;
	
	public ArcPanel(String title) {
		this(new BorderLayout(), title);
	}
	
	public ArcPanel(LayoutManager layout, String title) {
		super(layout);
		
		Panel panel = new Panel();
		this.run = new Button("Run");
		this.run.addActionListener(new RunListener(this));
		this.pause = new Button("Pause");
		this.pause.addActionListener(new PauseListener(this));
		panel.add(this.run);
		panel.add(this.pause);
		
		this.arcPanel = new ArcPanel(title);
		this.add(this.arcPanel, BorderLayout.NORTH);
		this.add(panel, BorderLayout.SOUTH);
	}
	
	public void setZero() {
		this.setInActive();
	}
	
	public void setActive() {
		this.run.setEnabled(false);
		this.pause.setEnabled(true);
		this.arcPanel.setBackground(RUNCOLOR);
	}
	
	public void setInActive() {
		this.run.setEnabled(true);
		this.pause.setEnabled(false);
		this.arcPanel.setBackground(PAUSECOLOR);
	}
	
	public void start() {
		this.setZero();
	}
	
	public void stop() {
	}
	
	private class RunListener implements ActionListener {
		private ArcPanel owner;
		
		public RunListener(ArcPanel owner) {
			this.owner = owner;
		}
		
		public void actionPerformed(ActionEvent e) {
			this.owner.setActive();
		}
	}
	
	private class PauseListener implements ActionListener {
		private ArcPanel owner;
		
		public PauseListener(ArcPanel owner) {
			this.owner = owner;
		}
		
		public void actionPerformed(ActionEvent e) {
			this.owner.setInActive();
		}
	}
	
}
