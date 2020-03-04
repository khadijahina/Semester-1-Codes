package concurrency.carpark;

import java.util.LinkedList;

public class CarPark {
	LinkedList<Integer> carId = new LinkedList<>(); 
    private int spaces;
    private int capacity=10;
    int value=0;
public CarPark(int spaces)
    {
      if (spaces < 0) {
            spaces = 0;
        }
   this.spaces = spaces;//refer to the current class instance variable
    }
public synchronized void entrance() //enter car park
    {
	
	
      while (spaces == 0) { 
            try {
                wait();
            } catch (InterruptedException e)
            {
            	for (int i=0; i<carId.size(); i++) {
            	System.out.println("Car " +  value + " has arrived" );
            }
        }
        spaces --;
        value++;
      }
    }
    public synchronized void exit() // exit car park
    {
    	 while (spaces==capacity)
    		 try {
    		 wait();
    		 } catch(InterruptedException e) {
    			 System.out.println("Car " +  value + " has left" );
    		 }
         spaces++;
        value--;
         notifyAll();
    }
    public synchronized void Parking() throws InterruptedException //  car park
    {
        
    	System.out.println("Car " + value + " has parked" );
    	Thread.sleep(1000);
    }
public static void main(String[] args) throws InterruptedException{     
    CarPark parkingGarage = new CarPark(20); //20 spaces 
    parkingGarage.entrance();
    parkingGarage.exit();
}
}