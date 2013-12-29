//Name: Peehoo Dewan
//USC loginid: pdewan@usc.edu
//CSCI 455 PA4
//Fall 2013

import java.util.LinkedList;

/**
 * This class implements the functionality of prefix required for
 * random text generation.
 * @author Peehoo
 *
 */
public class Prefix {
	private LinkedList<String> prefix;
	private int maxPrefixSize;
	private int hashCode;
	private boolean isHashCodeCached;

	/**
	 * Default constructor
	 */
	public Prefix() {
		this.prefix = new LinkedList<String>();
		this.maxPrefixSize = 0;
		isHashCodeCached = false;
	}

	/**
	 * This method constructs the object with the specified prefix length
	 * 
	 * @param prefixLength
	 */
	public Prefix(int prefixLength) {
		this.prefix = new LinkedList<String>();
		this.maxPrefixSize = prefixLength;
	}

	/**
	 * returns the Linkedlist
	 * @return
	 */
	public LinkedList<String> getPrefix() {
		return prefix;
	}

	/**
	 * Mutates this object by adding a word to the Prefix.
	 * If the prefix has size maxPrefixSize, the first word
	 * in the prefix is removed and the word passed in the
	 * parameter is added.
	 * @param word
	 */
	public void addWordIn(String word) {		
		if (prefix.size() < this.maxPrefixSize) {
			prefix.add(word);
		} else {
			prefix.removeFirst();
			prefix.addLast(word);
		}
		assert isValidPrefix();
		isHashCodeCached = false;		
	}

	/**
	 * Makes a copy of this Prefix and adds the word to
	 * the copy and return it so that this object is not mutated.
	 * If the copy has size maxPrefixSize, the first word
	 * in the copy is removed and the word passed in the
	 * parameter is added.
	 * @param word
	 * @return
	 */
	public Prefix addWord(String word) {
		Prefix newPrefix = this.copy();
		if (newPrefix.prefix.size() < newPrefix.maxPrefixSize) {
			newPrefix.prefix.add(word);
		} else {
			newPrefix.prefix.removeFirst();
			newPrefix.prefix.addLast(word);
		}
		assert isValidPrefix();
		return newPrefix;
	}

	/**
	 * Makes a copy of this Prefix
	 */
	public Prefix copy(){
		Prefix copy = new Prefix(this.maxPrefixSize);
		for (String s : this.prefix) {
			copy.prefix.add(s);
		}
		assert isValidPrefix();
		return copy;
	}

	/**
	 * This method returns true iff the prefix data is in a valid state.
	 * Length of prefix should always be less than the max prefixLength supplied
	 * prefix should not be null
	 * @return
	 */
	private boolean isValidPrefix(){
		// Length of prefix should always be less than the
		// max prefixLength supplied
		if(this.prefix.size() > this.maxPrefixSize){
			return false;
		}
		// prefix should not be null
		if(this.prefix==null){
			return false;
		}
		return true;

	}

	/**
	 * This method overrides the default equals method and checks whether
	 * the linkedlists have the same set of words or not. 
	 */
	@Override
	public boolean equals(Object other){
		if(!(other instanceof Prefix)) { // return false if the passed object is not an instance of prefix
			return false;	
		}
		Prefix otherObject = (Prefix) other;
		boolean equals = this.prefix.equals(otherObject.prefix);
		return equals;
	}

	/**
	 * This method returns the hashcode for the linkedlist. 
	 * It also caches the hashcode for faster retrieval.
	 * Since the object of prefix are never modified after they are added
	 * to hashmap, the hashcode will not change after the initial call to hashcode.
	 */
	@Override
	public int hashCode(){
		if(isHashCodeCached)
			return this.hashCode;
		else{
			hashCode = this.prefix.hashCode();
			isHashCodeCached = true;
		}
		return hashCode;
	}

	/**
	 * This method returns the prefix as a string
	 */
	@Override
	public String toString(){
		String string = "";
		for (String word : prefix) {
			string = string + " " + word;
		}
		return string;
	}
}
