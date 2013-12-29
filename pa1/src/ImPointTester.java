// Name: Peehoo Dewan
// USC loginid: pdewan
// CS 455 PA1
// Fall 2013

public class ImPointTester {

    /**
       Test driver for ImPoint class.
       @param args not used
    */
    public static void main(String[] args) {

    	ImPoint p = null; 
    	 p = new ImPoint(3,4); 
    	 ImPoint q = p; 
    	 System.out.println(p);
    	 
    	 System.out.println(q);
    	 
    	 change(q);
    	 
    	 System.out.println(q);

	//imPointTest(100, 82);

	//imPointTest(0, 0);

	//imPointTest(50, 60);

    }

	private static void change(ImPoint q) {
		ImPoint p;
		q.translate(5,5); 
		System.out.println(q);
    	 q = q.translate(5,5);
    	 System.out.println(q);
	}
		
    /**
       Test all ImPoint methods on (x,y)
     */
    private static void imPointTest(int x, int y) {
	
	ImPoint loc = new ImPoint(x, y);

	System.out.println("Testing with x=" + x + " y=" + y);

	System.out.println("Testing toString...");
	// the following line implicitly calls toString
	System.out.println("loc= " + loc);

	System.out.println("Testing getX, getY...");
	System.out.println("x=" + loc.getX() + " y=" + loc.getY());

	System.out.println("Point2D version: " + loc.getPoint2D());

	testTranslate(loc, 5,10);

	testTranslate(loc, -20, 15);

	System.out.println();

    }

    /**
       Test translate method on loc to be translated by deltaX and deltaY
     */
    private static void testTranslate(ImPoint loc, int deltaX, int deltaY) {

	int oldX = loc.getX();
	int oldY = loc.getY();

	ImPoint p2 = loc.translate(deltaX, deltaY);

	System.out.println("Testing translate by (" + deltaX + "," + deltaY + ") ...");
	System.out.println("Old point [expected:(" + oldX + "," + oldY+")]: "
			   + loc + ".");
	System.out.println("New point [expected:("
			   + (oldX+deltaX) + "," + (oldY+deltaY) + ")]: " 
			   + p2 + ".");
	System.out.println("Point2D version of new point: " + p2.getPoint2D());
    }

}