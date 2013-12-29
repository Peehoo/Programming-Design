//Name: Peehoo Dewan
//USC loginid: pdewan@usc.edu
//CSCI 455 PA4
//Fall 2013

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class implements the functionality for random text
 * generation
 * @author Peehoo
 *
 */
public class RandomTextGenerator {
	private Random random;
	private Map<Prefix, ArrayList<String>> prefixMap;
	private ArrayList<Prefix> prefixList;
	private boolean isDebugMode;

	/**
	 * Default constructor
	 */
	public RandomTextGenerator(){
		this.random = new Random();
		this.prefixMap = new HashMap<Prefix, ArrayList<String>>();
		this.prefixList = new ArrayList<Prefix>();
		this.isDebugMode = false;
	}

	/**
	 * This method constructs the object with the given seed
	 * @param seed
	 */
	public RandomTextGenerator(long seed){
		this.random = new Random(seed);
		this.prefixMap = new HashMap<Prefix, ArrayList<String>>();
		this.prefixList = new ArrayList<Prefix>();
		this.isDebugMode = true;
	}

	/**
	 * This method returns a random Prefix between indexes 0 and max-1 (inclusive)
	 * @param max
	 * @return
	 */
	public Prefix getRandomPrefix(int max){
		int index = random.nextInt(max);
		if(isDebugMode){
			System.out.println("DEBUG: chose a new initial prefix: " + prefixList.get(index));
		}
		//assert isValidRandomTextGenerator();
		return prefixList.get(index);
	}

	/**
	 * This method returns the next word after the supplied Prefix p
	 * @param p
	 * @return
	 */
	public String nextWord(Prefix p){
		ArrayList<String> listOfWords = prefixMap.get(p);
		if(listOfWords == null || listOfWords.size()==0){
			if(isDebugMode){
				System.out.println("DEBUG: prefix: " + p.toString());
				System.out.println("DEBUG: successors: <END OF FILE>");
			}
			return null;
		}
		int nextWordIndex = random.nextInt(listOfWords.size());
		if(isDebugMode){
			System.out.println("DEBUG: prefix: " + p.toString());
			System.out.println("DEBUG: successors: " + listOfWords.toString());
			System.out.println("DEBUG: word generated: " + listOfWords.get(nextWordIndex));
		}
		//assert isValidRandomTextGenerator();
		return listOfWords.get(nextWordIndex);	
	}

	/**
	 * returns the Maps of prefixes
	 * @return
	 */
	public Map<Prefix, ArrayList<String>> getPrefixMap() {
		return prefixMap;
	}

	/**
	 * returns the ArrayList of prefixes
	 * @return
	 */
	public ArrayList<Prefix> getPrefixList() {
		return prefixList;
	}

	/**
	 * returns the length of the ArrayList of prefixes
	 * @return
	 */
	public int getNumPrefix(){
		return prefixList.size();
	}

	private boolean isValidRandomTextGenerator(){
		for (Map.Entry<Prefix, ArrayList<String>> entry : prefixMap.entrySet()) {
			if(entry.getValue()==null){
				return false;
			}
		}
		for (Prefix prefix : prefixList) {
			if(!prefixMap.containsKey(prefix)){
				return false;	
			}
		}
		return true;
	}
}
