package practice_code;

public class StringToLong {
	public static void main(String args[]){
		System.out.println(stringToLong("123"));
	}
	
	/**
	 * PRE : s cannot be null
	 * @param s
	 * @return
	 */
	public static long stringToLong(String s){
		boolean isNegative = false;
		if(s.charAt(0)=='-'){
			isNegative = true;
			s = s.substring(1);
		}
		if(s.charAt(0)=='+'){
			s = s.substring(1);
		}
		char[] stringArray = s.toCharArray();
		int j= s.length() - 1;
		int digit;
		long number=0;
		for(char c: stringArray){
			digit = c - '0';
			if(digit>=0 && digit<=9){
				number+=digit*Math.pow(10, j--);
			}
			else {
				throw new NumberFormatException("illegal string");
			}
		}
		if(!isNegative){
			return number;
		}
		else return (-1*number);
	}
}
