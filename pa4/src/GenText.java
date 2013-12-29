//Name: Peehoo Dewan
//USC loginid: pdewan@usc.edu
//CSCI 455 PA4
//Fall 2013

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class generates text and writes to a file.
 * 
 * @author Peehoo
 * 
 */
public class GenText {
	private final static int MAX_CHAR_PER_LINE = 80;
	private final static int LENGTH_OF_SPACE = 1;
	private final static int FIXED_SEED = 1;
	private final static int SOURCE_FILE_ARG_OFFSET = 2;
	private final static int OUT_FILE_ARG_OFFSET = 3;
	private final static int NUMBER_OF_WORDS_ARG_OFFSET = 1;
	private final static int MAX_NUMBER_OF_COMMANDLINE_ARGS = 4;
	private final static int MAX_NUMBER_OF_COMMANDLINE_ARGS_DEBUG_MODE = 5;


	/**
	 * main method reads data from sourceFile, generates text and writes to the specified
	 * outFile
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int argsIndex = -1;
		RandomTextGenerator textGenerator;
		if ((args.length < MAX_NUMBER_OF_COMMANDLINE_ARGS) || (args[0].equals("-d") && (args.length < MAX_NUMBER_OF_COMMANDLINE_ARGS_DEBUG_MODE))) {
			handleMissingCommandLineArguments();
		}
		if (args[0].equals("-d")) {
			argsIndex = 1;
			textGenerator = new RandomTextGenerator(FIXED_SEED);
		} else {
			argsIndex = 0;
			textGenerator = new RandomTextGenerator();
		}
		File sourceFile = new File(args[argsIndex + SOURCE_FILE_ARG_OFFSET]);
		File outFile = new File(args[argsIndex + OUT_FILE_ARG_OFFSET]);
		Scanner in = null;
		PrintWriter writer = null;
		try {
			createNewOutFile(outFile);
			int prefixLength = Integer.parseInt(args[argsIndex]); // takes in prefix length from command line
			int numOfWords = Integer.parseInt(args[argsIndex + NUMBER_OF_WORDS_ARG_OFFSET]); // takes in number of words from command line		
			in = new Scanner(sourceFile); // takes in the source file name to be read
			writer = new PrintWriter(outFile);
			handleIllegalCommandLineArguments(prefixLength, numOfWords);
			constructPrefixesFromFile(textGenerator, in, prefixLength);
			generateText(textGenerator, writer, numOfWords);
		} catch (FileNotFoundException e) {
			handleFileNotFoundException(sourceFile);
		} catch (NumberFormatException e) {
			handleNumberFormatException();
		} catch (IOException e) {
			handleIOException(outFile, e);
		} finally {
			closeAllFiles(in, writer);
		}
	}

	/**
	 * This method closes all the files
	 * @param in
	 * @param writer
	 */
	private static void closeAllFiles(Scanner in, PrintWriter writer) {
		in.close();
		writer.close();
	}

	/**
	 * This method creates a new outFile if it doesn't exist already
	 * @param outFile
	 * @throws IOException
	 */
	private static void createNewOutFile(File outFile) throws IOException {
		if (!outFile.exists()) {
			outFile.createNewFile();
		}
	}

	/**
	 * This method handles errors when the user supplies illegal
	 * commandline arguments
	 * @param prefixLength
	 * @param numOfWords
	 */
	private static void handleIllegalCommandLineArguments(int prefixLength,
			int numOfWords) {
		if (prefixLength < 1) {
			handleIllegalPrefixLength(prefixLength);
		}
		if (numOfWords < 0) {
			handleIllegalNumberOfWords(numOfWords);
		}
	}

	/**
	 * This method does error handling when the input source file
	 * does not exist
	 * @param sourceFile
	 */
	private static void handleFileNotFoundException(File sourceFile) {
		System.out.println(" ERROR: File " + sourceFile + " does not exist.");
		System.out.println(" Exiting program");
		System.exit(0);
	}

	/**
	 * This method does error handling when the commandline input for
	 * prefixLength or NumWords is not an integer
	 */
	private static void handleNumberFormatException() {
		System.out
		.println(" ERROR: prefixLength or numWords or both do not have an integer value");
		correctUsage();
		System.out.println(" Exiting program");
		System.exit(0);
	}

	/**
	 * This method does error handling when the program is not able to 
	 * write to outFile
	 * @param outFile
	 * @param e
	 */
	private static void handleIOException(File outFile, IOException e) {
		System.out.println(" ERROR: can't write to " + outFile
				+ ". See exception below");
		e.printStackTrace();
		System.exit(0);
	}

	/**
	 * This method reads data from sourcefile and creates data structures
	 * namely ArrayList and Map containing Prefix data
	 * @param textGenerator
	 * @param in
	 * @param prefixLength
	 */
	private static void constructPrefixesFromFile(
			RandomTextGenerator textGenerator, Scanner in, int prefixLength) {
		Prefix prefix = new Prefix(prefixLength);
		while (in.hasNext()) {
			String word = in.next();
			if (prefix.getPrefix().size() == prefixLength) {
				textGenerator.getPrefixList().add(prefix);
				if (textGenerator.getPrefixMap().containsKey(prefix)) {
					textGenerator.getPrefixMap().get(prefix).add(word);
				} else {
					ArrayList<String> words = new ArrayList<String>();
					words.add(word);
					textGenerator.getPrefixMap().put(prefix, words);
				}
				prefix = prefix.addWord(word);
			} else {
				prefix.addWordIn(word);
			}
		}

		// adding last prefix
		textGenerator.getPrefixList().add(prefix);
		if (!textGenerator.getPrefixMap().containsKey(prefix)) {
			ArrayList<String> words = new ArrayList<String>();
			textGenerator.getPrefixMap().put(prefix, words);
		}

		if ((prefix.getPrefix().size() < prefixLength) && (!in.hasNext())) {
			System.out
			.println(" ERROR : prefixLength exceeds the maximum number of words in the source file. Exiting program");
			System.exit(0);
		}
		if(textGenerator.getNumPrefix() == 1){
			System.out
			.println(" ERROR : prefixLength equals the maximum number of words in the source file. Exiting program");
			System.exit(0);
		}
	}

	/**
	 * This method generates random text 
	 * @param textGenerator
	 * @param writer
	 * @param numOfWords
	 */
	private static void generateText(RandomTextGenerator textGenerator,
			PrintWriter writer, int numOfWords) {
		Prefix currentPrefix = textGenerator.getRandomPrefix(textGenerator.getNumPrefix()-1).copy();// subtracting  1 to make sure we don't get the prefix containing last word of the source file
		int charPerLine =0;
		for (int i = 0; i < numOfWords; i++) {
			String wordAfterPrefix = textGenerator.nextWord(currentPrefix);
			if (wordAfterPrefix != null) {
				charPerLine = writeWordtoFile(writer, wordAfterPrefix, charPerLine);
				currentPrefix.addWordIn(wordAfterPrefix);
			} else {
				currentPrefix = textGenerator.getRandomPrefix(textGenerator.getNumPrefix() - 1).copy(); // subtracting  1 to make sure we don't get the prefix containing
			} // last word of the source file
		}
	}

	/**
	 * This method does error handling when the user enters illegal input for
	 * NumWords
	 * @param numOfWords
	 */
	private static void handleIllegalNumberOfWords(int numOfWords) {
		System.out
		.println(" ERROR : Incorrect number of words = "
				+ numOfWords
				+ " \n Number of words should be greater than or equal to 0 \n");
		correctUsage();
		System.out.println(" Exiting program");
		System.exit(0);
	}

	/**
	 * This method does error handling when the user enters illegal input for
	 * PrefixLength
	 * @param numOfWords
	 */
	private static void handleIllegalPrefixLength(int prefixLength) {
		System.out
		.println(" ERROR : Incorrect prefix length = "
				+ prefixLength
				+ " \n Length of prefix should be greater than or equal to 1 \n");
		correctUsage();
		System.out.println(" Exiting program");
		System.exit(0);
	}

	/**
	 * This method does error handling when the number of commandline arguments
	 * supplied are less than the required number of commandline arguments 
	 */
	private static void handleMissingCommandLineArguments() {
		System.out.println(" ERROR: Missing command-line arguments.");
		correctUsage();
		System.out.println(" Exiting program");
		System.exit(0);
	}

	/**
	 * This method writes words to the outFile as per specification
	 * @param writer
	 * @param wordToBeWritten
	 */
	public static int writeWordtoFile(PrintWriter writer,
			String wordToBeWritten, int charPerLine) {
		if ((charPerLine + wordToBeWritten.length() + LENGTH_OF_SPACE <= MAX_CHAR_PER_LINE)
				&& (charPerLine != 0)) {
			writer.write(" ");
			charPerLine += LENGTH_OF_SPACE;
			writer.write(wordToBeWritten);
			charPerLine += wordToBeWritten.length();
		} else if ((charPerLine + wordToBeWritten.length() <= MAX_CHAR_PER_LINE)
				&& (charPerLine == 0)) {
			writer.write(wordToBeWritten);
			charPerLine += wordToBeWritten.length();
		} else {
			writer.write("\n");
			charPerLine = 0;
			if (wordToBeWritten.length() > MAX_CHAR_PER_LINE) {
				System.out
				.println(" WARNING : The wordlength exceeded 80 characters. Printing it anyway in a single line");
			}
			writer.write(wordToBeWritten);
			charPerLine += wordToBeWritten.length();
		}
		return charPerLine;
	}

	/**
	 * This method gives the correct way to run the program
	 */
	private static void correctUsage() {
		System.out
		.println(" The correct way to run the program is as follows : \n For the command - \n"
				+ "\n java GenText [-d] prefixLength numWords sourceFile outFile \n"
				+ "\n -d should be given when running program in debug mode"
				+ "\n prefixLength is the length of the prefix (must be an integer value greater than or equal to 1)\n"
				+ " numWords is the number of words to be generated (must be an integer value greater than 0)\n"
				+ " sourceFile is the inputfile (sourceFile must be existing)\n"
				+ " outFile is the file to which output is written");
	}
}
