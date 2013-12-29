/*
 * ectest.cpp
 *
 *  Created on: Dec 7, 2013
 *      Author: joe
 */


// ectest.cpp
// CSCI 455 Fall 2013, Extra Credit assignment

// Note: this uses separate compilation.  You put your code in listFuncs.cpp
// Code in this file calls those funcs.
// You do not need to modify or submit this file.


#include <iostream>
#include <string>

// for istringstream
#include <sstream>

#include "listFuncs.h"

using namespace std;


//*************************************************************************


/*
 * promptForInt: Prompts for and reads an integer.
 */
int promptForInt (string prompt)
{
    int value;

    cout << prompt << ": ";
    cin >> value;
    return value;
}



// build the list in forward order
// old value of theList is destroyed.
// (needs to be O(n))

// Note: this code uses istringstream: this is the analogous feature
//     to using a Scanner on a String in Java.
void buildList(Node * & theList) {

  string lineStr;

  getline(cin, lineStr);  // consume rest of previous line

  getline(cin, lineStr);

  if (lineStr.empty()) {
    theList = NULL;
    return;
  }

  istringstream istr(lineStr);

  Node *last = NULL;
  int i;

  istr >> i;   // first one is a special case
  theList = new Node(i);
  last = theList;

  while (istr >> i) { // comes out false if we reach end of string (i.e., eoln)
    last->next = new Node(i);
    last = last->next;
  }

}


void doHelp() {
  cout << "Valid commands are" << endl;
  cout << "         b(build new list), i(insert), u(build sequence), " << endl;
  cout << "         z(buffer with Zeroes), s(split), " << endl;
  cout << "         r(remove run), p(print), q(quit), h(elp)." << endl;
}


void doSplit(Node * &list) {
  ListType part1;
  ListType part2;
  int loc = promptForInt("Location at which to split list");
  cout<< "splitting<<endl";
  split(list, loc, part1, part2);
  cout << "Part 1: ";
  printList(part1);
  cout << "Part 2: ";
  printList(part2);
}


// execute the correct action based on command char.
// that action possibly updates theList.
// returns false iff the command entered was 'q'
bool dispatchCommand(char cmd, ListType & theList) {

  int  val;
  int low;
  int high;

    switch (cmd) {
    case 'b':
      clearList(theList);
      cout << "Please enter a sequence of ints followed by <nl> (e.g:1 2 3): ";
      buildList(theList);
      break;
    case 'u':
      clearList(theList);
      low = promptForInt ("Lower bound for sequence");
      high= promptForInt ("Upper bound for sequence");
      theList = buildSequence(low, high);
      break;
    case 'i':
      val = promptForInt ("Value to insert");
      insertFront (theList, val);
      break;
    case 'z':
      bufferWithZeroes(theList);
      break;
    case 's':
      doSplit(theList);
      break;
    case 'r':
      val = promptForInt ("Location at which to remove run");
      removeRunAtLoc(theList, val);
      break;
    case 'p':
      cout << "Print list: " << endl;
      printList(theList); cout << endl;
      break;
    case 'q':
      return false;
      break;
    default:
      doHelp();
      break;
    }

    return true;

}


/* a little test program */

int main ()
{

  char cmd;
  bool keepGoing = true;
  ListType theList = NULL;

  cout << "Enter 'h' for a description of the commands." << endl;

  do {
    cout << "\nPlease enter a command [b, i, u, e, z, s, r, p, q, h]: ";
    cin >> cmd;

    keepGoing = dispatchCommand(cmd, theList);

    cout << "The list: ";
    printList (theList);

  } while (keepGoing);

  return 0;
}


//*****************************************************************
// utility list funcs and Node methods

void insertFront(Node *&list, int val) {
  list = new Node(val, list);
}


void printList(Node *list) {

  if (list == NULL) {
    cout << "<empty>";
  }

  Node *p = list;
  while (p != NULL) {
    cout << p->data << " ";
    p = p->next;
  }
  cout << endl;
}


void clearList(Node *&list) {

  Node *rest = list;

  while (list != NULL) {
    rest = list->next;  // rest is all but the first element
    delete list;  // reclaims one node only
    list = rest;
  }

}

Node::Node(int item) {
  data = item;
  next = NULL;
}

Node::Node(int item, Node *n) {
  data = item;
  next = n;
}

