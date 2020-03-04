
package bridge;

/** This bridge should be safe and fair 
 */
public class FairBridge extends DefaultBridge {
	 private int nred  = 0;
	    private int nblue = 0;
	    private int waitblue = 0;
	    private int waitred = 0;
	    private boolean blueturn = true;

	    public synchronized void redEnter() throws InterruptedException {
	        ++waitred;
	        while (nblue>0 || (waitblue>0 && blueturn)) wait();
	        --waitred;
	        ++nred;
	    }

	    public synchronized void redExit(){
	        --nred;
	        blueturn = true;
	        if (nred==0)
	            notifyAll();
	    }

	    public synchronized void blueEnter()  throws InterruptedException {
	        ++waitblue;
	        while (nred>0 || (waitred>0 && !blueturn)) wait();
	        --waitblue;
	        ++nblue;
	    }

	    public synchronized void blueExit(){
	        --nblue;
	        blueturn = false;
	        if (nblue==0)
	            notifyAll();
	    }
}
