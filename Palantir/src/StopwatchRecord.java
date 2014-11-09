import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Stack;

/**
 * I tested for the input given in the booklet
[
StopwatchRecord(key='render_module',start_time=101.0,end_time=105.0),
StopwatchRecord(key='get_data',start_time=101.1,end_time=101.2),
StopwatchRecord(key='get_data',start_time=101.5,end_time=103.0),
StopwatchRecord(key='cache_get_data',start_time=101.6,end_time=101.7),
StopwatchRecord(key='db_get_data',start_time=101.8,end_time=102.9),
StopwatchRecord(key='render_module',start_time=103.1,end_time=104.9),
StopwatchRecord(key='render_module',start_time=105.1,end_time=106.0),
]
 * @author peehoo
 *
 */


public class StopwatchRecord {

	private String key;
	private double startTime;
	private double endTime;

	public StopwatchRecord(String key, double startTime, double endTime){
		this.endTime = endTime;
		this.startTime = startTime;
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public double getStartTime() {
		return startTime;
	}

	public double getEndTime() {
		return endTime;
	}

	private static StopwatchRecord getStopwatchRecord(String line) {
		if(!line.contains("[") && !line.contains("]")){
			line = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
			String[] recordArray = line.split(",");
			String key = null;
			double start_time = 0, end_time = 0;
			for (String string : recordArray) {
				String[] parts = string.split("=");
				if(parts[0].equals("key")){
					key = parts[1];
				}
				if(parts[0].equals("start_time")){
					start_time = Double.parseDouble(parts[1]);
				}
				if(parts[0].equals("end_time")){
					end_time = Double.parseDouble(parts[1]);
				}
			}
			StopwatchRecord record = new StopwatchRecord(key, start_time, end_time);
			return record;
		}
		return null;
	}

	public static void main(String args[]){
		System.out.println("Enter the records : \n");

		try{
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(in);
			String line;
			Stack<StopwatchRecord> records = new Stack<StopwatchRecord>();
			int indentationLevel=0;
			while((line = br.readLine())!=null){
				StopwatchRecord record = getStopwatchRecord(line);
				if(record!=null){
					if(records.isEmpty()){
						System.out.println("\n");
						prettyPrintRecord(record, indentationLevel);
						records.push(record);
					}
					else{
						StopwatchRecord temp = records.peek();
						if((temp.getStartTime() < record.getStartTime()) && 
								(temp.getEndTime() > record.getEndTime())){
							indentationLevel++;
							prettyPrintRecord(record, indentationLevel);
							records.push(record);
						}
						else{
							while(!records.isEmpty() && temp.getEndTime() < record.getStartTime()){
								records.pop();
								indentationLevel--;
								if(!records.isEmpty()){
									temp = records.peek();
								}
							}
							indentationLevel++;
							prettyPrintRecord(record, indentationLevel);
							records.push(record);
						}	
					}
				}
			}
			br.close();	
		}
		catch(FileNotFoundException e){
			System.out.println("\n No input file found");
		} catch (IOException e) {
		System.out.println("\n Unexpected IO Exception");
		}
	}

	private static void prettyPrintRecord(StopwatchRecord record,
			int indentationLevel) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i< indentationLevel; i++){
			sb.append("    ");
		}
		System.out.println(record.toString(sb));		
	}
	
	private String toString(StringBuilder sb){
		String str = this.getKey().replaceAll("'", "");
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		return this.startTime + " " + sb.toString() + "[" + df.format(this.endTime - this.startTime) + "]" + " "
				+ str;
	}
}
