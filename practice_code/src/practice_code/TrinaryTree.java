package practice_code;

/**
*Insertion and Deletion in Trinary Tree. Trinary tree is similar to binary tree
*where the values less are to the left of node, values greater are to the right
*of the node and values equal to the node are the middle child of node 
*/
public class TrinaryTree<T extends Comparable<T>>{
	private Node<T> root;

	public TrinaryTree(T data){
		root = new Node<T>(data);
	}

	public TrinaryTree(){
	}


	public static void main(String args[]){
		TrinaryTree<Integer> tree = new TrinaryTree<Integer>();
		tree.insert(5);
		tree.insert(4);
		tree.insert(9);
		tree.insert(5);
		tree.insert(7);
		tree.insert(6);
		tree.insert(2);
		tree.insert(2);
		System.out.println(tree.toString());
		tree.remove(tree.root);
		System.out.println(tree.toString());
		tree.remove(tree.root.right);
		System.out.println(tree.toString());
		tree.remove(tree.root.left);
		System.out.println(tree.toString());
	}

	public void insert(T data){
		if(root==null){
			root = new Node<T>(data);
			return;
		}
		insert(data,root);
	}

	private void insert(T data, Node<T> root){

		if(data.compareTo(root.data)<0){
			if(root.left==null){
				root.left = new Node<T>(data);
			}
			else{
				insert(data,root.left);
			}
		}

		if(data.compareTo(root.data)==0){
			if(root.middle==null){
				root.middle = new Node<T>(data);
			}
			else{
				insert(data,root.middle);
			}

		}

		if(data.compareTo(root.data)>0){
			if(root.right==null){
				root.right = new Node<T>(data);
			}
			else{
				insert(data,root.right);
			}
		}
	}

	/**
	 * PRE : node is not null
	 * @param node
	 * @return
	 */
	public boolean remove(Node<T> node){
		Node<T> parent = findParent(node);
		if(parent==null && !(root.equals(node))){
			return false;
		}

		if(isLeafNode(node)){          // It is a leaf node
			return removeChild(parent, node);
		}

		Node<T> onlyChild = hasOnlyOneChild(node);


		if(onlyChild!=null){
			if(root.equals(node)){
				root = onlyChild;
				return true;
			}
			if(parent.left.equals(node)){
				parent.left = onlyChild;
			}
			else if(parent.right.equals(node)){
				parent.right = onlyChild;
			}
			else if(parent.middle.equals(node)){
				parent.middle = onlyChild;
			}
			return true;
		}
		else {
			Node<T> middleChild = hasMiddleChild(node);

			if(middleChild!=null){
				if(root.equals(node)){
					root = middleChild;
				}
				middleChild.left = node.left;
				middleChild.right = node.right;
			}
			else {
				Node<T> minNode = findMinimumInTree(node.right);
				Node<T> parentOfMinNode = findParent(minNode);
				parentOfMinNode.left = minNode.right;
				if(root.equals(node)){
					root = minNode;
				}
				minNode.left = node.left;
				minNode.right = node.right;
			}	
			return true;
		}
	}


	private Node<T> findMinimumInTree(Node<T> node){
		if(node.left!=null){
			return findMinimumInTree(node.left);
		}
		return node;
	}

	private boolean isLeafNode(Node<T> node){
		if((node.left==null)&&(node.right==null)&&(node.middle==null)){
			return true;
		}
		else return false;
	}

	private Node<T> hasMiddleChild(Node<T> node){
		if(node.middle!=null){
			return node.middle;
		}
		return null;
	}

	private Node<T> hasOnlyOneChild(Node<T> node){
		if((node.left==null)&&(node.right==null)&&(node.middle!=null)){
			return node.middle;
		}
		if((node.left!=null)&&(node.right==null)&&(node.middle==null)){
			return node.left;
		}
		if((node.left==null)&&(node.right!=null)&&(node.middle==null)){
			return node.right;
		}
		return null;
	}

	private Node<T> findParent(Node<T> node){
		if(root==null){
			return null;
		}
		if(root.data == node.data){
			return null;
		}
		else
			return findParent(root,node);
	}

	private Node<T> findParent(Node<T> root, Node<T> node){
		if(root==null){
			return null;
		}
		if(root.left!=null){
			if(node.data.compareTo(root.left.data)==0){
				if(node.equals(root.left)){
					return root;
				}
				else return findParent(root.left, node);
			}
		}
		if(root.right!=null){
			if(node.data.compareTo(root.right.data)==0){
				if(node.equals(root.right)){
					return root;
				}
				else return findParent(root.right, node);
			}
		}
		if(root.middle!=null){
			if(node.data.compareTo(root.middle.data)==0){
				if(node.equals(root.middle)){
					return root;
				}
				else return findParent(root.middle, node);
			}
		}	


		if(node.data.compareTo(root.data)<0){
			return findParent(root.left, node);
		}
		else if(node.data.compareTo(root.data)==0){
			return findParent(root.middle, node);
		}
		else 
			return findParent(root.right, node);
	}


	private boolean removeChild(Node<T> parent, Node<T> child){
		if(root.equals(child)){
			root = null;
			return true;
		}
		else {
			if(parent.left.equals(child)){
				parent.left=null;
				return true;
			}
			if(parent.right.equals(child)){
				parent.right=null;
				return true;
			}
			if(parent.middle.equals(child)){
				parent.middle=null;
				return true;
			}
			else return false;
		}
	}


	private class Node<T> {
		private T data;
		private Node<T> left, middle, right;

		public Node(T data, Node<T> l, Node<T> m,Node<T> r) {
			left = l;
			middle = m;
			right = r;
			this.data = data;
		}

		public Node(T data) {
			this(data, null,null, null);
		}
	} 
	
	public String toString() {
	    StringBuilder string = new StringBuilder("[");
	    helpToString(root, string);
	    string.append("]");
	    return string.toString();
	}

	/**
	 * Recursive help method for toString. 
	 * 
	 * @param node
	 * @param string
	 */
	private void helpToString(Node<T> node, StringBuilder string) {
	    if (node == null)
	        return; // Tree is empty, so leave.

	    if (node.left != null) {
	        helpToString(node.left, string);
	        string.append(", ");
	    }

	    string.append(node.data);

	    if (node.middle != null) {
	        string.append(" ");
	        helpToString(node.middle, string);
	    }
	    
	    if (node.right != null) {
	        string.append(", ");
	        helpToString(node.right, string);
	    }
	}

	
	
	
}
