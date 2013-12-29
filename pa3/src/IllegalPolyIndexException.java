//
//USC loginid: pdewan@usc.edu
//CSCI 455 PA3
//Fall 2013

/**
 * The exception is thrown when a user provides
 * incorrect index to the Poly Array
 * interface
 * @author Peehoo
 *
 */
public class IllegalPolyIndexException extends Exception {
	String index;
	/**
	 * Constructor
	 * @param message, an error message
	 * @param index, the incorrect index entered by a user
	 */
	public IllegalPolyIndexException(String message, String index) {
		super(message);
		this.index = index;
	}
	public String getIndex(){
		return index;
	}
	
	
}
