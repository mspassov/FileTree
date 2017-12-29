/**
 * Martin Spassov
 * 250901340
 * mspassov@uwo.ca
 */

import java.util.Iterator;
import java.util.ArrayList;

public class FileTree {
	
	public TreeNode<FileObject> firstNode;		
	public ArrayList<String> fileTypeStorage;
	public String targetFile;
	ArrayList<String> duplicateStorage;
	public int count;
	
	/**
	 * Constructor creates a tree based on an initial parameter
	 * @param fileObjectName
	 * @throws FileObjectException
	 * @author Martin
	 */
	
	
	public FileTree (String fileObjectName) throws FileObjectException{
		
		FileObject firstFile= new FileObject(fileObjectName);
		count=1;
		
		
		//Here the root is created and its parent is set to null
		firstNode= new TreeNode<FileObject>(firstFile, null); 		
		exploreRecursive(firstNode);
		
	} 
	
	/**
	 * Recursive method which creates the tree of files and folders 
	 * @param r
	 * @author Martin
	 */
	
	private void exploreRecursive(TreeNode<FileObject> r){
		
		//if the node is a folder, an iterator of its children is created.
		if(r.getData().isDirectory()){
			FileObject f=r.getData();
			Iterator<FileObject> iter= f.directoryFiles();
			
			//Each child is subsequently explored recursively
			while(iter.hasNext()){
				FileObject tempObj= iter.next();
				
				//For every child, a new node is created, and its parent is set to the current node
				TreeNode<FileObject> n= new TreeNode<FileObject>(tempObj, r);
				r.addChild(n);
				n.setData(tempObj);
				n.setParent(r);
				count++;
				exploreRecursive(n);
			}
		}	
	}
	
	/**
	 * Method returns the root node
	 * @return firstNode
	 * @author Martin
	 */
	
	public TreeNode<FileObject> getRoot(){
		return firstNode;
	}
	
	/**
	 * Method finds all the files of a certain type
	 * Returns an iterator with all the files of the same type
	 * @param type
	 * @return iterable
	 * @author Martin
	 */
	
	public Iterator<String> filesOfType(String type){
		
		
		fileTypeStorage= new ArrayList<String>();
		iterateType(firstNode, type);
		
		Iterator<String> iterable= fileTypeStorage.iterator();
		
		return iterable;
	}
	
	/**
	 * Recursive method which compares every node to the target file to see if the extension is the same
	 * @param r
	 * @param filetype
	 * @author Martin
	 */
	
	private void iterateType(TreeNode<FileObject> r, String filetype){
		
		
	
		if(r.getData().isFile()){
			FileObject f= r.getData();
			
			String absolutePath= f.getLongName();
			
			//if the node is a file, we check to see if it contains an extension
			//if it does, then the string is split at the dot and then compared to the parameter. 
			if(absolutePath.contains(".")){
				String [] pathStorage= absolutePath.split("\\.");
				String fullFileType="."+pathStorage[1];
				
				//if they are the same, then it is added to the ArrayList
				if(fullFileType.equals(filetype)){
					fileTypeStorage.add(absolutePath);
				}
			}
		}
		
		//if the node is a folder, then it is explored recursively
		
		if(r.getData().isDirectory()){
			
			Iterator<TreeNode<FileObject>> iter= r.getChildren();
			while(iter.hasNext()){
				
				TreeNode<FileObject> n= iter.next();
				iterateType(n, filetype); 
				
			}
				
		}	
	}
	
	/**
	 * Method finds a target file, if it exists
	 * @param name
	 * @return targetFile
	 * @author Martin
	 */
	
	public String findFile(String name){
		
		//initial target is set to null, and if it remains null then an empty string is returned
		targetFile=null;
		findHelper(firstNode, name);
		if(targetFile==null){
			targetFile="";
			return targetFile;
		}
		
		else{
			return targetFile;
		}
			
	}
	
	/**
	 * Recursive method which compares the name of the target file with every node.
	 * If it finds it, it returns the file's absolute path
	 * @param current
	 * @param filename
	 * @author Martin
	 */
	
	private void findHelper(TreeNode<FileObject> current , String filename){
		
		//if the node is a file, its name is compared to the parameter and if they are the same, then 
		//its long name is set to the targetFile
		if(current.getData().isFile()){
			if(current.getData().getName().equals(filename)){
				targetFile=current.getData().getLongName();
			}
			
			
		}
		
		//It the node is a directory, it is explored recursively
		
		if(current.getData().isDirectory()){
			
			Iterator<TreeNode<FileObject>> iter2= current.getChildren();
			while(iter2.hasNext()){
				
				TreeNode<FileObject> temp= iter2.next();
				findHelper(temp, filename);
			}
			
		}
		
	}
	
	/**
	 * Method finds all the files which are duplicated and returns an iterator containing those files
	 * @return duplicate
	 * @author Martin
	 */
	
	public Iterator<String> duplicatedFiles(){
		
		duplicateStorage = new ArrayList<String>();
		TreeNode<FileObject> currentNode= firstNode;
		FileObject fileTarget= firstNode.getData();
		
		Iterator<TreeNode<FileObject>> iter= firstNode.getChildren();
		while(iter.hasNext()){
			duplicateFinder(iter.next(), fileTarget);
			fileTarget=iter.next().getData();
		}
		
		Iterator<String> duplicate= duplicateStorage.iterator();
	
		return duplicate;
	}
	/**
	 * Helper method which finds duplicate files and stores them in an ArrayList
	 * @param current
	 * @param target
	 */
	
	
	private void duplicateFinder(TreeNode<FileObject> current, FileObject target){
		
		if(current.getData().isFile()){
			if(current.getData().equals(target)){
				duplicateStorage.add(current.getData().getLongName());
			}
		}
		
		if(current.getData().isDirectory()){
			Iterator<TreeNode<FileObject>> tempIter= current.getChildren();
			while(tempIter.hasNext()){
				TreeNode<FileObject> tempObj= tempIter.next();
				duplicateFinder(tempObj, target);
			}
		}
		
	}
	
	public Iterator<String> duplicatedFolders(){
		return null;
	}
	

}
