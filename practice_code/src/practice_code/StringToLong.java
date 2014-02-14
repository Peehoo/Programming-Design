package practice_code;

/**
 * Convert String to Long without using any inbuild functions
 */
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
		if(s.charAt(0)=='-'){			//checking if its a negative number
			isNegative = true;
			s = s.substring(1);			//ignoring the first character and later multiplying by -1
		}
		if(s.charAt(0)=='+'){			//ignoring the first character
			s = s.substring(1);
		}
		char[] stringArray = s.toCharArray();
		int j= s.length() - 1;
		int digit;
		long number=0;
		for(char c: stringArray){
			digit = c - '0';			//subtracting ASCII value to get the number
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
