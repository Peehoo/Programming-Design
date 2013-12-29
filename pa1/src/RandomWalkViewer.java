// Name: Peehoo Dewan
// USC loginid: pdewan
// CS 455 PA1
// Fall 2013

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

public class RandomWalkViewer {
	
	/**
    Below are the three named constants which are the step size and the initial location
   */
	    private static int STEP_SIZE = 5;
	    private static int INITIAL_X = 200;
	    private static int INITIAL_Y = 200;
	    
	    /**
	    RandomWalkViewer class
	       Takes in the number of steps to be taken by drunkard and displays the trajectory of the 
	       drunkard in a graphical frame
	       @param args not used
	 */
	public static void main(String[] args) {
		
		
		Drunkard drunk = new Drunkard(new ImPoint(INITIAL_X, INITIAL_Y), STEP_SIZE);
		Scanner noOfSteps = new Scanner(System.in);
		System.out.print(" Enter number of steps : ");
		int steps = noOfSteps.nextInt();
		while(steps<=0){
			System.out.println(" ERROR: Number entered must be greater than 0. ");
			System.out.println(" Enter number of steps : ");
			steps = noOfSteps.nextInt();
		}
		ArrayList<ImPoint> trajectory = new ArrayList<ImPoint>(); 
		for(int i=0;i<steps;i++){
			trajectory.add(drunk.getCurrentLoc());
			drunk.takeStep();
			System.out.println(trajectory.get(i));
		}
		
		JFrame frame = new JFrame();  // Creating a new frame
		frame.setSize(400, 400);   // Setting the size of the frame to 400 x 400 pixels
		frame.setTitle("RandomWalk");  // Giving Title to the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Setting the default close option
		
		RandomWalkComponent component = new RandomWalkComponent(trajectory);
		frame.add(component);
		frame.setVisible(true); //Making the frame visible
		
	}

}
