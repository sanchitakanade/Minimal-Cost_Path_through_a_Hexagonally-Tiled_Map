/*
 * Name: Sanchita Kanade
 * Class: CS560 Algorithm
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * This class is an implementation of Dijkstra algorithm.
 * It contains dijkstra(Graph g, int sourceNode, int startDistance), 
 * printPath(int[] path, int source, int destination, int[] cost),
 * addToGraph(int[][] vertexPosition, int[][] weight, Graph graph) methods 
 * to find minimal cost path from source vertex to destination vertex, to print that path 
 * into a text file, and to add vertices to graph respectively.
 * 
 * @author Sanchita Kanade
 */
public class FindMinimalCostPath {
	private final static int infinity = 999999999;
/**
 * This method finds minimal cost path from source vertex to destination vertex.
 * @param graph represents a graph
 * @param sourceNode represents source vertex's position
 * @param startDistance represents weight on source vertex
 */
	public void dijkstra(Graph graph, int sourceNode, int startDistance){
		final int maxVertices = graph.no_of_vertices;
		int destinationNode = 8;
		int i;
		int u, v, weightOnNode_v, minimumDistance;
		LinkedList<EdgeNode> listOfNodes;
		EdgeNode node;
		boolean[] intree = new boolean[maxVertices + 1];
		int[] distance = new int[maxVertices + 1];
		int[] parent = new int[maxVertices + 1];
		
		//Initializing intree, distance and parent matrices
		for(i = 1; i <= graph.no_of_vertices; i++ ){
			intree[i] = false;
			distance[i] = infinity;
			parent[i] = -1;	
		}
		
		distance[sourceNode] = startDistance;
		u = sourceNode;
		
		//Updates distance[v] and parent[v], and visits a next node having minimum distance value.
		while(!intree[u]){
			intree[u] = true;
			listOfNodes = graph.arrayOfEdgeNodeList[u];
			if(listOfNodes.size() != 0){
				node = listOfNodes.getFirst();
				for(i = 1; i <= listOfNodes.size(); i++){ 
					if(node.weight != -1){
						v = node.vertexPosition;
						weightOnNode_v = node.weight;
						if(distance[v]> distance[u]+ weightOnNode_v) {
							distance[v] = distance[u] + weightOnNode_v;
							parent[v] = u;
						}
						if(i < listOfNodes.size())
							node = listOfNodes.listIterator(i).next();
					}
					else if(i < listOfNodes.size())
						node = listOfNodes.listIterator(i).next();
					else break;
				}
			}
			u = 1;
			minimumDistance = infinity;
			for(i = 1; i <= graph.no_of_vertices; i++)
				if(!intree[i] && minimumDistance > distance[i])
				{
					minimumDistance = distance[i];
					u = i;
				}
		}
		try{
			printPath(parent, sourceNode, destinationNode,distance);
		}catch (IOException ioe){
			System.out.println("Caught IOException: " + ioe.getMessage());
		}
	}
	
/**
 * This method prints minimal-cost path from source vertex to destination vertex into a text file 
 * @param path represents an array which contains a minimal-cost path for each vertex in the graph
 * @param source represents source vertex
 * @param destination represents destination vertex
 * @param cost represents an array which contains minimal-cost for each vertex in the graph
 * @throws IOException This method throws exception if there is a failure to create, to write 
 * or to close a file 'MinimumCostPath.txt'
 */
	public void printPath(int[] path, int source, int destination, int[] cost)throws IOException {
		FileWriter writer = new FileWriter("MinimumCostPath.txt");
		int[] minimalCostPath = new int[path.length];
		minimalCostPath[0] = destination;
		int temp = path[destination];
		for(int i = 1; i < minimalCostPath.length; i++ ){
			if (temp == -1) {
				break;
			}
			else{
				minimalCostPath[i] = temp;
				temp = path[temp];
			}
		}
		for(int i = minimalCostPath.length-1; i >= 0; i--){
			if(minimalCostPath[i]!= 0)
				writer.write(minimalCostPath[i] +"\n");
		}
		writer.write("MINIMAL-COST PATH COSTS: " + cost[destination]);
		writer.close();
	}
	
/**
 * This method is adding vertices along with their weights to an empty graph from vertexPosition and weight arrays 
 * @param vertexPosition represents an array which contains positions of all vertices 
 * @param weight represents an array which contains weights on all vertices  
 * @param graph represents an empty graph
 */
	public void addToGraph(int[][] vertexPosition, int[][] weight, Graph graph) {
		final int Maxrows = vertexPosition.length;
		final int Maxcols = vertexPosition[0].length;
		for(int r = 0; r < Maxrows-1; r++){
			for(int c = 1; c < Maxcols; c++ ){

				if(c%2 == 0 && vertexPosition[r][c] != -1){

					if(c-1 > 0)graph.addEdge(vertexPosition[r][c], vertexPosition[r][c-1], weight[r][c-1]);
					if(c+1 < Maxcols) graph.addEdge(vertexPosition[r][c], vertexPosition[r][c+1], weight[r][c+1]);
					if(r-1 >= 0)graph.addEdge(vertexPosition[r][c], vertexPosition[r-1][c], weight[r-1][c]);
					if(r+1 < Maxrows && vertexPosition[r+1][c] != -1) graph.addEdge(vertexPosition[r][c], vertexPosition[r+1][c], weight[r+1][c]);
					if(r+1 < Maxrows && c-1 > 0) graph.addEdge(vertexPosition[r][c], vertexPosition[r+1][c-1], weight[r+1][c-1]);
					if(r+1 < Maxrows && c+1 < Maxcols) graph.addEdge(vertexPosition[r][c], vertexPosition[r+1][c+1], weight[r+1][c+1]);

				}
				if(c%2 != 0 ){
					if(r-1 >= 0) graph.addEdge(vertexPosition[r][c], vertexPosition[r-1][c], weight[r-1][c]);
					if(r-1 >= 0 && c-1 > 0 )	graph.addEdge(vertexPosition[r][c], vertexPosition[r-1][c-1], weight[r-1][c-1]);
					if(r-1 >= 0 && c+1 < Maxcols) graph.addEdge(vertexPosition[r][c], vertexPosition[r-1][c+1], weight[r-1][c+1]);
					if(c-1 > 0 && vertexPosition[r][c-1] != -1) graph.addEdge(vertexPosition[r][c], vertexPosition[r][c-1], weight[r][c-1]);
					if(c+1 < Maxcols && vertexPosition[r][c+1] != -1) graph.addEdge(vertexPosition[r][c], vertexPosition[r][c+1], weight[r][c+1]);
					if(r+1 < Maxrows && vertexPosition[r+1][c] != 0) graph.addEdge(vertexPosition[r][c], vertexPosition[r+1][c], weight[r+1][c]);

				}
			}

		}
	}
}
