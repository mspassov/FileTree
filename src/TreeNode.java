/**
 * Martin Spassov
 * 250901340
 * mspassov@uwo.ca
 * 
 * @author Martin
 * @param <T>
 * 
 */


import java.util.Comparator;
import java.util.Iterator;




public class TreeNode<T> {
	
	private TreeNode<T> parent;
	private ListNodes<TreeNode<T>> children;
	private T data;
	
	/**
	 * The constructor which initializes the variables 
	 * 
	 * @author Martin
	 */
	
	
	public TreeNode(){
		parent= null;
		data=null;
		children=new ListNodes<TreeNode<T>>();
	}
	
	
	/**
	 * Second constructor which takes as argument a tree node, and a datum
	 * @param d
	 * @param p
	 * @author Martin
	 */
	
	public TreeNode(T d, TreeNode<T> p){
		children=new ListNodes<TreeNode<T>>();
		data=d;
		parent=p;
		
	}
	
	/**
	 * Method sets the parent to a node
	 * @param p
	 * @author Martin
	 */
	
	public void setParent(TreeNode<T> p){
		parent=p;
	}
	
	/**
	 * Method gets the parent of a node
	 * @return parent node
	 * @author Martin
	 */
	
	public TreeNode<T> getParent(){
		return parent;
	}
	
	/**
	 * A node is added to another node as part of the tree
	 * @param c
	 * @author Martin
	 */
	
	public void addChild(TreeNode<T> c){
		children.add(c);
	}
	
	/**
	 * Method returns an iterator of the tree
	 * @return TreeNode iterator
	 * @author Martin
	 */
	
	public Iterator<TreeNode<T>> getChildren(){
		return children.getList();
	}
	
	/**
	 * Method returns a sorted iterator of the children
	 * @param sorter
	 * @return sorted TreeNode iterator
	 * @author Martin
	 */
	
	
	public Iterator<TreeNode<T>> getChildren(Comparator<TreeNode<T>> sorter){
		return children.sortedList(sorter);
	
		
	/**
	 * Method returns the data within the node
	 * @return data
	 * @author Martin
	 */
		
	}
	
	public T getData(){
		return data;
	}
	
	/**
	 * Method sets the data in a node
	 * @param d
	 * @author Martin
	 */
	
	public void setData(T d){
		data=d;
	}
	

}
