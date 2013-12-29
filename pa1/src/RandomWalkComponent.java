// Name: Peehoo Dewan
// USC loginid: pdewan
// CS 455 PA1
// Fall 2013

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 RandomWalkComponent class
 Represents a class which extends JComponent and draws the trajectory of the path taken 
 by the drunkard 
 */

public class RandomWalkComponent extends JComponent{
	
	ArrayList<ImPoint> trajectory;
	
	/**
    Creates a frame with the trajectory of the drunkard
    @param trajectory trajectory of the drunkard
   */
	public RandomWalkComponent(ArrayList<ImPoint> trajectory){
		this.trajectory = trajectory;
	}

	/* Draws the trajectory in the frame */
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		for(int i=0; i< (trajectory.size() - 1); i++){
			Point2D from = (trajectory.get(i)).getPoint2D(); 
			Point2D to = (trajectory.get(i+1)).getPoint2D();
			Line2D.Double segment = new Line2D.Double(from, to);
			g2.draw(segment);
		}
		
	}

}
