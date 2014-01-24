package practice_code;

/*
 * This programs finds all ways to represent n cents given infinite quarters, 
 * infinite nickels, infinite dimes, infinite pennies. This approach uses a global
 * variable which maintains a global count of no. of ways. I have also solved it 
 * without global variable which is under Permutation.java
 */

public class Permutations {

	private int count;
	
	public Permutations(){
		count = 0;
	}
	public static void main(String args[]){
		Permutations p = new Permutations();
		p.permute(10,0);
		System.out.println(p.count);
	}
	
	public void permute(int n, int count){
		if(n>=25){
			permute(n-25, count);
		}
		if(n>=10){
			permute(n-10, count);
		}
		if(n>=5){
			permute(n-5, count);
		}
		if(n>=1){
			permute(n-1, count);
		}
		if(n==0){
			this.count++;
			return;
		}
	}
}
