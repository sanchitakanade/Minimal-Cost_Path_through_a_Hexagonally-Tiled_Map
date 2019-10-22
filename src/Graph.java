/*
 * Name: Sanchita Kanade
 * Class: CS560 Algorithm
 */

import java.util.LinkedList;

/**
 * This class is a representation of a graph, 
 * it contains addEdge(int u, int v, int w) and toString() methods
 * to add vertices u and v into the graph and to print the graph respectively
 * 
 * @author Sanchita Kanade
 */
public class Graph {
	
	LinkedList<EdgeNode>[] arrayOfEdgeNodeList;
	int no_of_vertices;
	int no_of_edges;

	/**
	 * Parameterized constructor initializing field no_of_vertices to  a value given by a programmer
	 * creates an array of EdgeNode list and initializes each array element to new EdgeNode linked list
	 * @param n represents total number of vertices in the graph
	 */
	public Graph(int n){
		this.no_of_vertices = n;
		arrayOfEdgeNodeList = new LinkedList[n+1];
		for(int i = 1; i < arrayOfEdgeNodeList.length; i++) {
			arrayOfEdgeNodeList[i] = new LinkedList<EdgeNode>();
		}		
	}
	
	/**
	 * This method adds vertices u and v into the graph
	 * @param u represents position of starting vertex 
	 * @param v represents position of end vertex
	 * @param w represents weight on end vertex
	 */
	public void addEdge(int u, int v, int w){
		arrayOfEdgeNodeList[u].add(0,new EdgeNode(v,w));
	}
		
	/**
	 * This method overrides Object's toString method and
	 * returns a String representation of the graph.
	 */ 
	@Override
	public String toString(){
		String result = "";
		for(int i = 1; i < arrayOfEdgeNodeList.length; i++)
			result += i +"=>" + arrayOfEdgeNodeList[i]+"\n";
		return result;
		
	}
	
	
}
