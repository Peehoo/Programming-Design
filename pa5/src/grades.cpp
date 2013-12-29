// grades.cpp
// CSCI 455 PA5
// Name: Peehoo Dewan
// Loginid:pdewan@usc.edu
// 
/*
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>
#include <string.h>


/**
 * prints out the command summary
 */
void printCmdSummary() {
	cout << "\n Valid commands are: "
			"\n 1) i name score - Inserts this name and score in the grade table if the name is not already present.";
	cout << "\n 2) c name newscore - Changes the score for name if the name is present.";
	cout << "\n 3) l name - Looksup the name, and prints out his or her score.";
	cout << "\n 4) r name - Removes this student if the student is present.";
	cout << "\n 5) p - Prints out all names and scores in the table.";
	cout << "\n 6) n - Prints out the number of entries in the table.";
	cout << "\n 7) s - Prints out statistics about the hash table at this point.";
	cout << "\n 8) h - Help";
	cout << "\n 9) q - Exits the program.\n";
}

/**
 * this function changes the score if the name is present in the table
 */
void changeScore(const string& name, int score, Table* grades) {
	int* value = grades->lookup(name);
	if (value != NULL) {
		*value = score;
	} else {
		cout << " \n The score cannot be changed as the name is not present in the grade table \n";
	}
}

/**
 * this function returns the score of the looked up name
 */
void lookUpName(const string& name, Table* grades) {
	int* value = grades->lookup(name);
	if (value != NULL) {
		cout << "\n The score for " << name << " is " << *value << endl;
	} else {
		cout << " \n This name is not present in the grades table \n";
	}
}

/**
 * this function removes an entry from the grades table
 */
void removeEntry(const string& name, Table* grades) {
	int* value = grades->lookup(name);
	if (value != NULL) {
		grades->remove(name);
	} else {
		cout << " \n Cannot remove " << name
				<< " as this name is not present in the grades table \n";
	}
}

/**
 * helper function to return the entered name
 */
string getEnteredName(char command) {
	string name= "";
	if (command == 'i' | command == 'c' | command == 'l' | command == 'r') {
		cin >> name;
	}
	return name;
}

/**
 * helper function to return the entered score
 */
int getEnteredScore(char command) {
	int score=0;
	if (command == 'i' | command == 'c') {
		cin >> score;
	}
	return score;
}

/**
 * helper function to get the entered command
 */
char getCommand() {
	cout << "cmd> ";
	char command;
	cin >> command;
	return command;
}

/**
 * this function modifies the grades table depending on the
 * command entered by the user
 */
void modifyGradesTable(Table* grades) {
	bool keepGoing = true;
	do {
		char command = getCommand();
		string name = getEnteredName(command);
		int score = getEnteredScore(command);

		if (command == 'i'){
			grades->insert(name, score);
		}
		else if (command == 'c'){
			changeScore(name, score, grades);
		}
		else if (command == 'l'){
			lookUpName(name, grades);
		}
		else if (command == 'r'){
			removeEntry(name, grades);
		}
		else if (command == 'p'){
			grades->printAll();
		}
		else if (command == 'n'){
			cout << "\n The number of entries in the grades table are : " << grades->numEntries() << endl;
		}
		else if (command == 's'){
			grades->hashStats(cout);
		}
		else if (command == 'h'){
			printCmdSummary();
		}
		else if (command == 'q'){
			keepGoing = false;
		}
		else {
			printCmdSummary();
		}
	} while (keepGoing);
}
/**
 * main method carries out the operations to modify grades table
 */
int main(int argc, char * argv[]) {

	// gets the hash table size from the command line

	int hashSize = Table::HASH_SIZE;

	Table * grades;  // Table is dynamically allocated below, so we can call
	// different constructors depending on input from the user.

	if (argc > 1) {
		hashSize = atoi(argv[1]);  // atoi converts c-string to int
		if (hashSize < 1) {
			cout << "Command line argument (hashSize) must be a positive number"
					<< endl;
			return 1;
		}
		grades = new Table(hashSize);

	} else {   // no command line args given -- use default table size
		grades = new Table();
	}

	grades->hashStats(cout);
	printCmdSummary();
	cout << "\n Please enter a command \n ";
	modifyGradesTable(grades);
	return 0;
}
