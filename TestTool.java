import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class TestTool {
	
	private static Graph graph;
	private static int[] waypoints;
	private static int numIterations;
	private static long runTime;
	
	public static void main(String[] args) throws IOException{
		
		//Load data
		
		graph = new Graph(args[0]);
		
		loadWayPoints(args[1]);
		
		//Determine number of Iterations

		numIterations = 1;
		
		if (args.length > 2){
			numIterations = Integer.valueOf(args[2]);
		}
		
		//Initialize PathFinder and runTime
		
		PathFinder pf = graph.getPathFinder();
		runTime = 0;
		
		//Execute all tests
		
		for (int i = 0; i < numIterations; i++){
			
			Date startTime;
			Date endTime;
			
			startTime = new Date();
			
			pf.multiPath(waypoints);
			
			endTime = new Date();
			
			//If this is the first iteration, print the path
			if (i == 0){
				pf.printPath();
			}
			
			pf.resetPath();

			runTime += (endTime.getTime() - startTime.getTime());

		}
		
		//print time taken
		System.out.println("Run Time: " + ((float)runTime/(float)numIterations));
		
	}
	
	protected static void loadWayPoints(String path) throws IOException{
		File file = new File(path);
		String linebuff = null;
		int nWaypoints = 0;
		waypoints = null;
		
		int lineNum = 0;
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			while((linebuff = reader.readLine()) != null) {
				
				if(linebuff.length() == 0) {
					System.err.println("Error: Invalid File Type: Empty line at pos: "+lineNum);
					throw new IOException();
				}
				
				//If lineNum is 0 then it is the number of waypoints
				if (lineNum == 0){
					nWaypoints = Integer.valueOf(linebuff);
					waypoints = new int[nWaypoints];
					
				//Otherwise, if it is 1 and there are spaces it is the list of waypoints
				} else if (lineNum == 1 && linebuff.contains(" ")){
					String[] textWaypoints = linebuff.split(" +");
					for (int i = 0; i < nWaypoints; i++){
						waypoints[i] = Integer.valueOf(textWaypoints[i]);
				}
					
				//If none of the above, the file is separated by new line chars.
				} else {
					int i = lineNum - 1;
					waypoints[i] = Integer.valueOf(linebuff);		
				}
				
				lineNum ++;
				}
				
		} catch (FileNotFoundException e) {
			System.err.print("Error: File not found\n");
		}
		
	}
	
	protected int[] getWayPoints(){
		return waypoints;
	}
}
