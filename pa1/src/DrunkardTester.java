// Name: Peehoo Dewan
// USC loginid: pdewan
// CS 455 PA1
// Fall 2013

/**
       Test driver for Drunkard class.
       @param args not used
 */

public class DrunkardTester {
	public static void main(String[] args) {
		testDrunkard(5, 10);
		testDrunkard(7, 34);
		testDrunkard(100, 3);
		testDrunkard(8, 12);
		testDrunkard(6, 90);
	}

	/**
	Test all Drunkard methods on (stepSize, no. of steps) and checks whether the steps taken by 
	drunkard are legal or illegal
	 */
	private static void testDrunkard(int stepSize, int steps) {
		Drunkard drunk = new Drunkard(new ImPoint(200, 200), stepSize);
		System.out.println(" Number of steps: " + steps + " and step size: " + stepSize);


		for(int i=0;i<steps;i++){
			ImPoint prev = drunk.getCurrentLoc();
			drunk.takeStep();
			ImPoint next = drunk.getCurrentLoc();
			if(next.getX() == prev.getX() && 
					(next.getY() - prev.getY() == stepSize || 
					next.getY() - prev.getY() ==- stepSize))
				System.out.println("Legal Step: " + next);
			else if(next.getY() == prev.getY() && 
					(next.getX() - prev.getX() == stepSize || 
					next.getX() - prev.getX() ==- stepSize))
				System.out.println("Legal Step: " + next);
			else
				System.out.println("Illegal Step: " + next);
		}
	}



}
