package practice_code;


/*
 * This programs finds all ways to represent n cents given infinite quarters, 
 * infinite nickels, infinite dimes, infinite pennies. This approach uses the tree
 * approach to find the count of no. of ways. 
 */

public class Permutation {
	public static void main(String args[]){
		System.out.println(permute(10));
	}
	
	public static int permute(int n){
		if(n==0){
			return 1;
		}
		else if (n <0){
			return 0;
		}
		return permute(n-25) + permute(n-10) + permute(n-5) + permute(n-1);
	}
}
