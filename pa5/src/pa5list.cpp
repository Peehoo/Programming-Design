/*
 * pa5list.cpp
 *
 *  Created on: Nov 26, 2013
 *      Author: joe
 */
#include "Table.h"
#include <iostream>

using namespace std;

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

Table::Table() {
	numEntries();
	hashSize = HASH_SIZE;
	data = new ListType[hashSize];
}


Table::Table(unsigned int hSize) {
	numEntries();
	hashSize = hSize;
	data = new ListType[hashSize];
}

bool Table::insert(const string &key, int value) {
	if(data[hashCode(key)] == NULL){
		data[hashCode(key)]= new Node(key,value);
	}
	else {
		ListType p = data[hashCode(key)];
		while (p->next != NULL) {
			if(p->key == key){
				return false;
			}
			p = p->next;
		}
		p->next = new Node(key,value);
	}
	return true;
}

int main(){
	Table test;
	bool inserted = test.insert("a",2);
	cout<< "Value inserted : " << inserted;
}



