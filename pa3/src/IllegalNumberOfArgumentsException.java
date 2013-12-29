//
//USC loginid: pdewan@usc.edu
//CSCI 455 PA3
//Fall 2013

/**
 * The exception is thrown when a user provides
 * incorrect number of commands to PolyProg commandline 
 * interface
 * @author Peehoo
 *
 */
public class IllegalNumberOfArgumentsException extends Exception {
	/**
	 * constructor
	 * @param message
	 */
	public IllegalNumberOfArgumentsException(String message) {
		super(message);
	}
	
	

}
