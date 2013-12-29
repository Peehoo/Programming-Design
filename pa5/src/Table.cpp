// Table.cpp  Table class implementation
// CSCI 455 PA5
// Name: Peehoo Dewan
// Loginid: pdewan@usc.edu

/*
 * Modified 11/22/11 by CMB
 *   changed name of constructor formal parameter to match .h file
 */

#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

//*************************************************************************
// Node class definition and member functions
//     You don't need to add or change anything in this section

// Note: we define the Node in the implementation file, because it's only
// used by the Table class; not by any Table client code.

struct Node {
	string key;
	int value;

	Node *next;

	Node(const string &theKey, int theValue);

	Node(const string &theKey, int theValue, Node *n);
};

Node::Node(const string &theKey, int theValue) {
	key = theKey;
	value = theValue;
	next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
	key = theKey;
	value = theValue;
	next = n;
}

typedef Node * ListType;

//*************************************************************************

Table::Table() {
	hashSize = HASH_SIZE;
	data = new ListType[hashSize];
	numEntries();
}

Table::Table(unsigned int hSize) {
	hashSize = hSize;
	data = new ListType[hashSize];
	numEntries();
}

/**
 * helper function to lookup a key in the list
 */
int * listLookUp(ListType & list, string key) {
	if (list != NULL) {
		ListType p = list;
		while (p != NULL) {
			if (p->key == key) {
				return &p->value;
			} else {
				p = p->next;
			}
		}
	}
	return NULL;
}

/**
 * returns the address of the value or null if key is not present
 */
int * Table::lookup(const string &key) {
	if (data[hashCode(key)] != NULL) {
		return listLookUp(data[hashCode(key)], key);
	}
	return NULL;
}

/**
 * helper function to remove an entry from the list
 */
bool listRemove(ListType & list, string key) {
	while (list != NULL) {
		ListType p = list;
		if (p->key == key) {
			list = list->next;
			delete p;
			return true;
		} else if ((p->next != NULL) && (p->next->key == key)) {
			ListType q = p->next;
			p->next = q->next;
			delete q;
			return true;
		}
		p = p->next;
	}
	cout << "Element not present in the table";
	return false;
}

/**
 * removes an entry from the table
 */
bool Table::remove(const string &key) {
	if (data[hashCode(key)] == NULL) {
		cout << "List is empty";
		return false;
	} else {
		listRemove(data[hashCode(key)], key);
	}
	return false;
}

/**
 * this helper function helps insert a new pair in the
 * list if it was not already present in the list
 */
bool insertInList(ListType & list, string key, int value) {
	ListType p = list;
	if (p->key == key) {
		return false;
	}
	while (p->next != NULL) {
		p = p->next;
		if (p->key == key) {
			return false;
		}
	}
	p->next = new Node(key, value);
	return true;
}

/**
 * This function inserts a new pair into the table
 * returns false iff it was already there in the table
 */
bool Table::insert(const string &key, int value) {
	if (data[hashCode(key)] == NULL) {
		data[hashCode(key)] = new Node(key, value);
	} else {
		insertInList(data[hashCode(key)], key, value);
	}
	return true;
}

/**
 * this helper function returns the number of entries
 * in the list
 */
int numListEntries(ListType & list) {
	int numListEntries = 0;
	ListType p = list;
	while (p != NULL) {
		numListEntries += 1;
		p = p->next;
	}
	return numListEntries;
}

/**
 * returns the number of entries in the table
 */
int Table::numEntries() const {
	int numOfEntries = 0;
	if (sizeof(data) == 0) {
		cout << "\n There are no elements in the table. ";
	} else {
		for (int i = 0; i < hashSize; i++) {
			numOfEntries = numOfEntries + numListEntries(data[i]);
		}
	}
	return numOfEntries;
}

/**
 * This helper method prints all the key-value pairs in
 * the list
 */
void printList(ListType & list) {
	ListType p = list;
	while (p != NULL) {
		cout << p->key << "   " << p->value << endl;
		p = p->next;
	}
}

/**
 * This function prints all the key-value pairs in the table
 */
void Table::printAll() const {
	if (numEntries() == 0) {
		cout << "\n There are no elements to print. \n\n";
		return;
	} else {
		cout << endl << "key-value pair \n\n";
		for (int i = 0; i < hashSize; i++) {
			printList(data[i]);
		}
	}
	cout << endl;
}
/**
 * returns the distribution of data across the table
 */
void Table::hashStats(ostream &out) const {
	out << " \n number of buckets: " << hashSize << endl;
	out << " number of entries: " << numEntries() << endl;
	out << " number of non empty buckets: " << numNonEmptyBuckets() << endl;
	out << " longest chain: " << longestChain() << endl;
}


/**
 * returns the number of nun empty buckets in the table
 */
int Table::numNonEmptyBuckets() const {
	int numNonEmptyBuckets = 0;
	if (sizeof(data) == 0) {
		cout << "\n There are no elements in the table. ";
	} else {
		for (int i = 0; i < hashSize; i++) {
			ListType p = data[i];
			if (p != NULL) {
				numNonEmptyBuckets += 1;
			}
		}
	}
	return numNonEmptyBuckets;
}

/**
 * This helper method returns the longest chain
 * in the linked list
 */
int longestList(int &longestChain, ListType & list) {
	ListType p = list;
	int newLongestChain = 0;
	if (p != NULL) {
		while (p->next != NULL) {
			newLongestChain += 1;
			p = p->next;
		}
		newLongestChain += 1;
	}
	if (newLongestChain > longestChain) {
		longestChain = newLongestChain;
	}
	return longestChain;
}
/**
 *  returns the longest chain in the table
 */
int Table::longestChain() const {
	int longestChain = 0;
	if (sizeof(data) == 0) {
		cout << "\n There are no elements in the table. ";
	} else {
		for (int i = 0; i < hashSize; i++) {
			longestList(longestChain, data[i]);
		}
	}
	return longestChain;
}

