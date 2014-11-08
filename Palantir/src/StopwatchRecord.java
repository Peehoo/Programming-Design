import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Stack;


public class StopwatchRecord {

	private String key;
	private double start_time;
	private double end_time;

	public StopwatchRecord(String key, double start_time, double end_time){
		this.end_time = end_time;
		this.start_time = start_time;
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public double getStart_time() {
		return start_time;
	}

	public double getEnd_time() {
		return end_time;
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
			InputStreamReader in = new InputStreamReader(new FileInputStream(new File("inputRecords")));
			BufferedReader br = new BufferedReader(in);
			String line;
			Stack<StopwatchRecord> records = new Stack<StopwatchRecord>();
			int indentationLevel=0;
			while((line = br.readLine())!=null){
				StopwatchRecord record = getStopwatchRecord(line);
				if(record!=null){
					if(records.isEmpty()){
						prettyPrintRecord(record, indentationLevel);
						records.push(record);
					}
					else{
						StopwatchRecord temp = records.peek();
						if((temp.getStart_time() < record.getStart_time()) && 
								(temp.getEnd_time() > record.getEnd_time())){
							indentationLevel++;
							prettyPrintRecord(record, indentationLevel);
							records.push(record);
						}
						else{
							while(!records.isEmpty() && temp.getEnd_time() < record.getStart_time()){
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
		catch(Exception e){
			e.printStackTrace();
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
		return this.start_time + " " + sb.toString() + "[" + df.format(this.end_time - this.start_time) + "]" + " "
				+ str;
	}
}
