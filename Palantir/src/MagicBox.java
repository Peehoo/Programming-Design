import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;


public class MagicBox {

	public static void main(String args[]){
		System.out.println("Enter the dimensions of the matrix along with the matrix : \n");
		
		try{
			InputStreamReader in = new InputStreamReader(new FileInputStream(new File("input")));
			BufferedReader br = new BufferedReader(in);
			String dimensions, line;
			String[] dimensionsArray;
			char[][] matrix = null;
			int i=0, m = 0, n = 0;
			while((line = br.readLine())!=null){
				if(i==0){
					dimensions = line;
					dimensionsArray = dimensions.split(" ");
					m = Integer.parseInt(dimensionsArray[0]);
					n = Integer.parseInt(dimensionsArray[1]);
					matrix = new char[m][n];
					i++;
				}
				else{
					matrix[i-1]=line.toCharArray();
					i++;
				}
			}
			int maxWishes = makeMap(matrix);
			System.out.print("Maximum wishes : " + maxWishes);
			br.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private static int makeMap(char[][] matrix) {
		int max=-1;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int r=0; r<matrix.length; r++){
			String key = Arrays.toString(matrix[r]);
			String flippedKey = Arrays.toString(flipKey(matrix[r]).toCharArray());
			if(!map.containsKey(key) && !map.containsKey(flippedKey)){
				map.put(key, 1);
				max = Math.max(1, max);
			}
			else{
				if(map.containsKey(key)){
					map.put(key, map.get(key) + 1);
					max = Math.max(map.get(key), max);
				}
				else{
					map.put(flippedKey, map.get(flippedKey) + 1);
					max = Math.max(map.get(flippedKey), max);
				}
			}	
		}
		return max;
	}

	private static String flipKey(char[] key) {
		String flipped = "";
		for (char c : key) {
			if(c == 'P'){
				flipped+= 'T';
			}
			else{
				flipped+= 'P';
			}
		}
		return flipped;
	}
}
