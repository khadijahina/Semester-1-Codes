package geom.shape.locksplitting;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import geom.shape.Shape;

/**
 * Geometry Simulation Environment (GSE) November 2017
 * 
 * 
 * @author Pascal Gadient (gadient@inf.unibe.ch)
 * 
 *         SCG University of Bern, Concurrency Course
 * 
 */
public class LockSplittingShape implements Shape {
	private int x, y, width, height;

	// TODO: Apply "Lock Splitting" concept here (you may need some final variables
	// here? :)
	private final Object dimensionLock = new Object();
	private final Object positionLock = new Object();

	public LockSplittingShape() {
		// TODO: Apply "Lock Splitting" concept here
	}

	@Override
	public void changePosition() {
		// TODO: Apply "Lock Splitting" concept here
		synchronized (positionLock) {
			x += x * 0.1;
			y -= y * 0.2;
		}
	}

	@Override
	public void changeDimension() {
		// TODO: Apply "Lock Splitting" concept here
		synchronized (dimensionLock) {
			width += width * 0.5;
			height -= height * 0.7;
		}
	}

	@Override
	public void changePositionAndDimension() {
		// TODO: Apply "Lock Splitting" concept here
		synchronized (positionLock) {
			y += y * 0.4;
		}
		synchronized (dimensionLock) {
			height -= height * 0.6;
		}
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public void setRectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}
