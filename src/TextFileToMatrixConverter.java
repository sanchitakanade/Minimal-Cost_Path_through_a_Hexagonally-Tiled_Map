/*
 * Name: Sanchita Kanade
 * Class: CS560 Algorithm
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class converts an input text file into two matrices 
 * @author Sanchita Kanade
 */
public class TextFileToMatrixConverter {

	/**
	 * This method reads input text file and fills all values into two matrices, positionArray and valueArray 
	 * then adds values from these matrices into a graph and 
	 * finds minimal-cost path from source vertex to destination vertex 
	 * using an object of FindMinimalCostPath class
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		FileReader fileReader = null;
		BufferedReader bufferReader = null;
		try{
			//reading an input text file
			fileReader = new FileReader("input.txt");
			bufferReader = new BufferedReader(fileReader);

			int[][] positionArray = new int[17][16];
			int[][] valueArray = new int[17][16];

			int i = 0;
			int row = 1;
			String[] tokens = new String[2];

			// adds values from the input text file into matrices, positionArray and valueArray
			while(i < 17 && row < 33) {

				if(row % 2 != 0) {
					for (int j = 1; j < 16;) { 
						tokens = bufferReader.readLine().split(" ");
						int position = Integer.valueOf(tokens[0]);
						int value = Integer.valueOf(tokens[1]);
						positionArray[i][j] = position;
						valueArray[i][j] = value;
						j = j+2;
					}		
				}
				else {
					for (int j = 2; j < 16;) {
						String line;
						int position = -1;
						int value = 0;
						if((line = bufferReader.readLine()) != null && !line.trim().equals("")) {
							tokens = line.split(" ");
							position = Integer.valueOf(tokens[0]);
							value = Integer.valueOf(tokens[1]);
						}
						positionArray[i][j] = position;
						valueArray[i][j] = value;
						j = j+2;
					}
					i++;
				}
				row++;
			}

			/* 
			 * finding minimal-cost path from source node 226 to destination node
			 * using FindMinimalCostPath class
			 */
			FindMinimalCostPath findPath = new FindMinimalCostPath();
			Graph graph = new Graph(233);
			findPath.addToGraph(positionArray, valueArray, graph);
			findPath.dijkstra(graph, 226, valueArray[15][1]); 

		} catch(IOException ioe) {
			System.out.println("Caught IOException: " + ioe.getMessage());
			ioe.printStackTrace();

		} finally {

			if(fileReader != null && bufferReader != null) {	
				try{	
					bufferReader.close();
					fileReader.close();

				} catch(IOException e){
					System.out.println("Caught IOException: " + e.getMessage());
					e.printStackTrace();
				}
			}	
		}

		long end = System.currentTimeMillis();
		System.out.println("Total Time: "+ (end - startTime) +" ms");
	}
}
