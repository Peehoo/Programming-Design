import java.util.Random;

// Name: Peehoo Dewan
// USC loginid: pdewan
// CS 455 PA1
// Fall 2013

/**
   Drunkard class
       Represents a "drunkard" doing a random walk on a grid.
*/
public class Drunkard {

    
	private ImPoint currentLoc;
	private int theStepSize;
	
   /**
    Creates drunkard with given starting location and distance
    moved in a single step.
    @param startLoc starting location of drunkard
    @param theStepSize size of one step in the random walk
   */
    public Drunkard(ImPoint startLoc, int theStepSize) {
    	this.currentLoc = startLoc;
    	this.theStepSize = theStepSize;
    }


    /**
       Takes a step of length step-size (see constructor) in one of
       the four compass directions.  Changes the current location of the
       drunkard.
       @param d represents the direction to be taken where d can have values
       d=0 means north , d=1 means east, d=2 means south and d=3 means west
    */
    public void takeStep() {
    	Random direction = new Random();
    	int d = direction.nextInt(4);  
    	if(d==0){
    		currentLoc = currentLoc.translate(0, theStepSize);
    	}
    	else if(d==1){
    		currentLoc = currentLoc.translate(theStepSize, 0);
    	}
    	else if(d==2){
    		currentLoc = currentLoc.translate(0, -theStepSize);
    	}
    	else if(d==3){
    		currentLoc = currentLoc.translate(-theStepSize, 0);
    	}

    }

    /**
       gets the current location of the drunkard.
       @return an ImPoint object representing drunkard's current location
    */
    public ImPoint getCurrentLoc() {
	return currentLoc;  
    }
    

}