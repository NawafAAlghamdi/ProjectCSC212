
import java.security.spec.EncodedKeySpec;

public class BST<T> {
    BSTNode<T> root, current;
	
	
	public BST() {
		root = current = null;
	}
	public boolean empty() {
		return root == null;
	}
	public boolean full() {
		return false;
	}
	public T retrieve () {
		return current.data;
	}
    public boolean find(Relative rel){
		switch (rel) {
		   case Root:	// Easy case
			current = root;
			return true;
		   case Parent:
			if(current == root)
                return false;
			current = findparent(current, root);
			return true;
		   case LeftChild:
			if(current.left == null)
                return false;
			current = current.left;
			return true;
		   case RightChild:
			if(current.right == null)
                return false;
			current = current.right;
			return true;
		   default:
			return false;
		}
	}
    private BSTNode<T> findparent(BSTNode<T> p, BSTNode<T> t) {
		if(t == null)
			return null;	// empty tree
		if(t.right == null && t.left == null)
			return null;
		else if(t.right == p || t.left == p)
			return t;	// parent is t
		else {
			BSTNode<T> q = findparent(p, t.left);
			if (q != null)
				return q;
			else
				return findparent(p, t.right);
		}
	}
    public boolean findkey(int tkey) {
		BSTNode<T> p = root, q = root;	
		if(empty())
			return false;	
		while(p != null) {
			q = p;
			if(p.key == tkey) {
				current = p;
				return true;
			}
			else if(tkey < p.key)
				p = p.left;
			else
				p = p.right;
		}	
		current = q;
		return false;
	}
    public boolean insert(int k, T val) {
		BSTNode<T> p, q = current;
		if(findkey(k)) {
			current = q;  // findkey() modified current
			return false; // key already in the BST
		}
		p = new BSTNode<T>(k, val);
		if (empty()) {
			root = current = p;
			return true;
		}
		else {
			// current is pointing to parent of the new key
			if (k < current.key)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}
    public void inOrder(){
            if(root==null)
            System.out.println("Empty list");
            else inOrder(root);
    }
    private void inOrder(BSTNode node){
            if(node==null) return;
            inOrder(node.left);
                System.out.println("key= " + node.key);
                System.out.println(node.data);
            inOrder(node.right);
    }
    
}
