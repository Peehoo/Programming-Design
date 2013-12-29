#include "listFuncs.h"

#include <cstddef>
#include <iostream>
using namespace std;
/*
struct Node {

	int data;

	Node *next;

	Node(int item);

	Node(int item, Node *n);
};
 */
/*
Node::Node(int theData) {
	data = theData;
	next = NULL;
}

Node::Node(int theData, Node *n) {
	data = theData;
	next = n;
}

void insertFront(Node * & list, int val){
	list = new Node(val, list);
}

ListType insert(Node * & list, int val){
	list = new Node(val, list);
	return list;
}

void printList(Node *list){
	if(list==NULL){
		cout<< "<empty>";
		return;
	}
	else {
		ListType p =list;
		while(p!=NULL){
			cout<< p->data<<" ";
			p = p->next;
		}
		cout<<endl;
	}
}

void clearList(Node *&list){
ListType p=list;
while(list!=NULL){
	list=list->next;
	delete p;
	p=list;
}
}
 */
ListType buildSequence(int low, int high) {
	if(low>high){
		return NULL;
	}
	else if(low==high){
		return new Node(low);
	}
	else {
		ListType list = NULL;
		for(int i=high; i>=low; i--){
			insertFront(list,i);
		}
		return list;
	}
}

void bufferWithZeroes(ListType & list) {
	if(list==NULL){
		return;
	}
	else if(list->next==NULL){
		return;
	}
	else {
		ListType p = list;
		while(p!=NULL && p->next!=NULL){
			p->next = new Node(0,p->next);
			p=p->next;
			if(p!=NULL){
				p=p->next;
			}
		}
	}
}

int numElements(ListType & list){
	int numElements = 0;
	if(list!=NULL){
		ListType p = list;
		while(p!=NULL){
			numElements+=1;
			p=p->next;
		}
	}
	return numElements;
}

void split(ListType & list, int loc, ListType &part1, ListType &part2){
	int n =numElements(list);
	cout << "split called"<< endl;
	if(list==NULL){
		part1 = NULL;
		part2 = NULL;
		list = NULL;
		return;
	}


	if(loc<=0){

		part1 = NULL;
		part2 = list;
		list = NULL;
		return;
	}

	if((loc>0)&&(loc<n)){
		ListType p = list;
		part1 =list;
		for(int i=0; i<loc-1; i++){
			if(p!=NULL){
				p=p->next;
			}
		}
		part2 = p->next;
		p->next = NULL;
		list = NULL;
		return;
	}

	if(loc>=n){
		part1 = list;
		part2 = NULL;
		list = NULL;
	}
}

void removeRunAtLoc(ListType & list, int loc) {
	int n =numElements(list);
	if(list==NULL){
		cout<< "list is null";
		return;		// no change made to the list
	}

	if((loc>=n)||(loc<0)){
		cout<< "\n location value out of valid range";
		return;		// location value out of valid range
	}

	ListType p = list;
	ListType q =list;
	if(loc==0){
		while((p!=NULL)&&(p->next!=NULL)){
			if(p->data!= p->next->data){
				list = p->next;
				return;
			}
			if(p->data == p->next->data){
				p = p->next;
			}
		}
		list = p->next;
		return;
	}

	for(int i=0; i<loc; i++){
		if(p!=NULL){
			p=p->next;
			if((i<loc-1)&&(q!=NULL)){
				q=q->next;
			}
		}
	}
	ListType r = p;
	while((p!=NULL)&&(p->next!=NULL)){
		if(p->data != p->next->data){
			q->next = p->next;
			delete r;
			return;
		}
		if(p->data == p->next->data){
			p = p->next;
			delete r;
			r = p;
		}
	}
	q->next = p->next;
	delete r;
	return;
}


