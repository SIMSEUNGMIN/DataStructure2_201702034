package kruskal;

public class PairwiseDisjointSets {
	private static final int INITIAL_SINGLETON_SET_SIZE = 1;
	
	private int _numberOfElements;
	private int[] _parentTree;
	
	public PairwiseDisjointSets(int givenNumberOfElements){
		this.setNumberOfElement(givenNumberOfElements);
		this.setParentTree(new int[this.numberOfElements()]);
		for(int rootOfSingletonSet = 0;
				rootOfSingletonSet < this.numberOfElements(); rootOfSingletonSet++){
			this.setSizeOfSetFor(rootOfSingletonSet, INITIAL_SINGLETON_SET_SIZE);
		}
	}
	
	private int numberOfElements(){
		return this._numberOfElements;
	}
	
	private void setNumberOfElement(int newNumberOfElements){
		this._numberOfElements = newNumberOfElements;
	}
	
	private int[] parentTree(){
		return this._parentTree;
	}
	
	private void setParentTree(int[] newParentTree){
		this._parentTree = newParentTree;
	}
	
	private int parentOf(int aMember){
		return this.parentTree()[aMember];
	}
	
	private void setParentOf(int aChildMember, int newParentMember){
		this.parentTree()[aChildMember] = newParentMember;
	}
	
	private boolean parentDoesExist(int aMember){
		return (this.parentOf(aMember) >= 0);
	}
	
	private int sizeOfSetFor(int aRoot){
		return -this.parentOf(aRoot);
	}
	
	private void setSizeOfSetFor(int aRoot, int newSize){
		this.setParentOf(aRoot, -newSize);
	}
	
	public int find(int aMember){
		int rootCandidate = aMember;
		while(this.parentDoesExist(rootCandidate)){
			rootCandidate = this.parentOf(rootCandidate);
		}
		int root = rootCandidate;
		
		//Apply Collapsing Rule
		int child = aMember;
		int parent = this.parentOf(child);
		if(parent >= 0){
			while(parent != root){
				this.setParentOf(child, root);
				child = parent;
				parent = this.parentOf(child);
			}
		}
		return root;
	}
	
	public void union(int aMemberA, int aMemberB){
		int rootOfSetA = find(aMemberA);
		int rootOfSetB = find(aMemberB);
		
		int sizeOfSetA = this.sizeOfSetFor(rootOfSetA);
		int sizeOfSetB = this.sizeOfSetFor(rootOfSetB);
		
		if(sizeOfSetA < sizeOfSetB){
			this.setParentOf(rootOfSetA, rootOfSetB);
			this.setSizeOfSetFor(rootOfSetB, (rootOfSetA + rootOfSetB));
		}
		else{
			this.setParentOf(rootOfSetB, rootOfSetA);
			this.setSizeOfSetFor(rootOfSetA, (rootOfSetA + rootOfSetB));
		}
	}
}
