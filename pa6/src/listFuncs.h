// listFuncs.h
// CSCI 455 Fall 2013, Extra Credit assignment
// You do not need to modify or submit this file.

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H



//*************************************************************************
// Node type used for lists
struct Node {
  int data;
  Node *next;

  Node(int item);
  Node(int item, Node *n);

};


typedef Node * ListType;

//*************************************************************************
// Utility functions used by the test driver code

// inserts val at the front of list
// PRE: list is a well-formed list
void insertFront(Node *&list, int val);

// makes list into an empty list
//   reclaiming memory used by nodes
// PRE: list is a well-formed list
// POST: list' is NULL
void clearList(Node * &list);

// prints list elements, space separated, ending with '\n'
// prints empty list as "<empty>"
// PRE: list is a well-formed list
void printList(Node *list);



//*************************************************************************
// Functions you need to write for this assignment:
//   (implementations go in listFuncs.cpp)


/*
 * buildSequence
 *
 * returns a well-formed list of the increasing sequence of values low
 * through high, in that order.  If that's an empty set, returns an
 * empty list.
 *
 *   Example1: low = 2,  high = 5     returns (2 3 4 5)
 *   Example2: low = -3, high = -3    returns (-3)
 *   Example3: low = 5,  high = 4     returns ()
 */
ListType buildSequence(int low, int high);



/*
 * bufferWithZeroes
 *
 * PRE: list is a well-formed list
 * POST: modifies list so that it's the same as before, except that
 *         a zero appears between every two elements from the old list
 *
 * Examples:
 *  list before call     list after call
 *    ()                   ()
 *    (3)                  (3)
 *    (3 7 4)              (3 0 7 0 4)
 *    (3 0 4)              (3 0 0 0 4)
 */
void bufferWithZeroes(ListType & list);



/*
 * split
 *
 * splits its linked list argument, list, at position loc into two
 * parts, part1 and part2.  If we interpret loc as an index counting
 * from zero (as in an array) the element at loc will be the first
 * element in part2.
 * loc <= 0 means the split is before the first element, and
 * loc >= the number of elements in the list, means the split is after the
 * last element.
 *
 * PRE: list is a well-formed list
 * POST: list is NULL, and part1 and part2 are well-formed lists with
 *       the values described.
 *
 * Examples:
 *  list (before the call)   loc          part1           part2
 *
 *  (2 6 7 5)                1            (2)             (6 7 5)
 *  (2 6 7 5)                0            ()              (2 6 7 5)
 *  (2 6 7 5)                2            (2 6)           (7 5)
 *  (2 6 7 5)                3            (2 6 7)         (5)
 *  (2 6 7 5)                100          (2 6 7 5)       ()
 *  ()                       any          ()              ()
 */
void split(ListType & list, int loc, ListType & part1, ListType & part2);



/*
 * removeRunAtLoc
 *
 * removes the run of one or more elements starting at location loc.  A
 * run is a sequence of elements that are all the same value.  Elements are
 * numbered as in an array: e.g., the first element is at location
 * zero.  If the location is out of range, no change is made to the
 * list.
 *
 * NOTE: this doesn't always remove a complete run, because it only
 * removes the element at that location and to the right of it (not
 * left).  The last example below illustrates this.
 *
 * Examples:
 *   list
 *   before call   loc    list after call
 *    ()           any      ()
 *    (4 7 4)       -1      (4 7 4)
 *    (4 7 4)        3      (4 7 4)
 *    (4 7 4)        2      (4 7)
 *    (4 7 4)        0      (7 4)
 *    (4 7 7 7 4)    1      (4 4)
 *    (4 4 4 7 4)    0      (7 4)
 *    (4 4 4 7 4)    1      (4 7 4)
 */
void removeRunAtLoc(ListType & list, int loc);


//*************************************************************************

#endif
