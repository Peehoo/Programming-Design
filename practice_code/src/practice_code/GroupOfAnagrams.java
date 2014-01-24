package practice_code;

/* Write a method to sort an array of strings so that all 
*  the anagrams are next to each other
*/
import java.util.Arrays;
import java.util.Comparator;

public class GroupOfAnagrams {

	public static void main(String[] args){
		String[] arr = { "Mother in law","Bad credit", "acrd", "Debit card", "card","Woman Hitler"};
		arr = sort(arr);
		for(String s : arr){
			System.out.println(s);
		}
	}
	
	public static String[] sort(String[] arr){
		if(arr.length == 0 || arr.length == 1){ //if array is empty or has only
			return arr;							// only one element then do nothing
		}
		Arrays.sort(arr, new AnagramComparator()); // sort the array
		return arr;
	}	
}

/**
 * This class sorts the given array or strings. The idea used is that if 2 strings 
 * are anagrams then they have the same character set. Once we sort the character 
 * set in each string, we return a new string with the sorted character set. This 
 * array of strings when sorted using Anagram comparator land up together.
 */
class AnagramComparator implements Comparator<String>{
	public String sortedCharArray(String S){
		S = S.replaceAll("\\s","");
		S = S.toLowerCase();
		char[] strArray = S.toCharArray();
		Arrays.sort(strArray);
		return new String(strArray);
	}
	
	public int compare(String S1, String S2){
		return sortedCharArray(S1).compareTo(sortedCharArray(S2));	
	}
}
