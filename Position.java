package geom.shape.passthrough;

/**
 * Geometry Simulation Environment (GSE)
 * November 2017
 * 
 * 
 * @author Pascal Gadient (gadient@inf.unibe.ch) 
 * 
 * SCG University of Bern, Concurrency Course
 * 
 */
public class Position {
        private int x, y;
        
		// TODO: Do we need synchronization for this method?
        public synchronized void changeBoth() {
        	// TODO: Apply "Pass Through" concept here
			x += x * 0.1;
			y -= y * 0.2;
        }
        
		// TODO: Do we need synchronization for this method?
        public void changeOne() {
        	// TODO: Apply "Pass Through" concept here
			y += y * 0.4;
        }
        
        public int x() {
        	return this.x;
        }
        
        public int y() {
        	return this.y;
        }
        
        public void x(int newValue) {
        	this.x = newValue;
        }
        
        public void y(int newValue) {
        	this.y = newValue;
        }
}
