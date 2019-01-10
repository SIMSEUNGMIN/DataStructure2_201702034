package equivalenceClasses;

import app.AppView;
import graph.AdjacencyGraph;
import graph.Edge;
import list.List;
import list.LinkedList;
import list.Stack;
import list.ArrayStack;
import list.Iterator;

public class EquivalenceClasses<E extends Edge> {
	//상수
	private static final boolean DEBUG_MODE = true;
	
	//디버그 메세지
	private static void showDebugMessage(String aMessage){
		if(EquivalenceClasses.DEBUG_MODE){
			AppView.outputDebugMessage(aMessage);
		}
	}
	
	//비공개 인스턴스 변수
	private AdjacencyGraph<E> _graph;
	private boolean[] _found;
	private Stack<Integer> _sameClassMembers;
	private List<List<Integer>> _equivalenceClassList;
	
	//getter/setter
	private AdjacencyGraph<E> graph(){
		return this._graph;
	}
	
	private void setGraph(AdjacencyGraph<E> newGraph){
		this._graph = newGraph;
	}
	
	private boolean[] found(){
		return this._found;
	}
	
	private void setFound(boolean[] newFound){
		this._found = newFound;
	}
	
	private Stack<Integer> sameClassMember(){
		return this._sameClassMembers;
	}
	
	private void setSameClassMemvbers(Stack<Integer> newSameClassMembers){
		this._sameClassMembers = newSameClassMembers;
	}
	
	public List<List<Integer>> equivalenceClassList(){
		return this._equivalenceClassList;
	}
	
	private void setEquivalenceClassList(List<List<Integer>> newEquivalenceClassList){
		this._equivalenceClassList = newEquivalenceClassList;
	}
	
	//생성자
	public EquivalenceClasses(){
		this.setGraph(null);
		this.setFound(null);
		this.setSameClassMemvbers(null);
		this.setEquivalenceClassList(null);
	}
	
	//공개함수
	public boolean solve(AdjacencyGraph<E> aGraph){
		this.setGraph(aGraph);
		if(this.graph().numberOfVertices() < 1){ //no vertices.
			return false;
		}
		this.setFound(new boolean[this.graph().numberOfVertices()]);
		this.setEquivalenceClassList(new LinkedList<List<Integer>>());
		this.setSameClassMemvbers(new ArrayStack<Integer>());
		EquivalenceClasses.showDebugMessage("\n");
		for(int vertex = 0; vertex < this.graph().numberOfVertices(); vertex++){
			if(!this.found()[vertex]){
				EquivalenceClasses.showDebugMessage("[Debug] 새로운 동등 클래스: {");
				List<Integer> newEquivalenceClass = new LinkedList<Integer>();
				this.equivalenceClassList().add(newEquivalenceClass);
				
				this.found()[vertex] = true;
				newEquivalenceClass.add(vertex);
				EquivalenceClasses.showDebugMessage(" " + vertex);
				this.sameClassMember().push(vertex);
				while(! this.sameClassMember().isEmpty()){
					int tailVertex = this.sameClassMember().pop();
					Iterator<E> iterator = this.graph().neighborIteratorOf(tailVertex);
					while(iterator.hasNext()){
						E edge = iterator.next();
						int headVertex = edge.headVertex();
						if(! this.found()[headVertex]){
							this.found()[headVertex] = true;
							newEquivalenceClass.add(headVertex);
							EquivalenceClasses.showDebugMessage(", " + headVertex);
							this.sameClassMember().push(headVertex);
						}
					}
				}
				EquivalenceClasses.showDebugMessage(" }\n");
			}
		}
		return true;
	}
	
}
