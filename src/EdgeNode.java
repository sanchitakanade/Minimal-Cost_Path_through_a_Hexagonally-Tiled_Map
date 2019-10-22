/*
 * Name: Sanchita Kanade
 * Class: CS560 Algorithm
 */


/**
 * This class is a representation of a vertex in a graph
 * @author Sanchita Kanade
 *
 */
public class EdgeNode {
	int vertexPosition;
	int weight;

	/**
	 * Parameterized constructor initializing all fields to values given by a programmer
	 * @param v represents position of a vertex
	 * @param w represents weight on the vertex
	 */
	public EdgeNode(int v, int w){
		this.vertexPosition = v;
		this.weight = w;
	}

	/**
	 * This method overrides Object's toString method and
	 * returns a String representation of EdgeNode Object.
	 */
	@Override
	public String toString(){
		return "(" + vertexPosition + ", "+ weight + ")" ;	 
	} 
	
}
