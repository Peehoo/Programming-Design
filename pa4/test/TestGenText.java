import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Test;


public class TestGenText {

	@Test
	public void test1() throws FileNotFoundException{
		String s = "blue hat on his tusk. blue dog better than the big elephant with the big "
				+ "with the big blue hat on his tusk. hat on his tusk. the big blue hat on his";
		String[] arr = s.split(" ");
		PrintWriter writer = new PrintWriter(new File("test.txt"));
		for (String string : arr) {
			//GenText.writeWordtoFile(writer, string, charPerLine);
		}
		writer.close();
	}
}
