Mathieu Belzile-Ha					      1/6/2016

				README


GRAPH TRAVERSAL

---------------------------------------------------------------------

INSTALLATION:

	The Makefile provided serves as an installer.

	-it:
		Displays all valid targets.

	-pathFinder:
		Compiles all java classes related to graph traversal.

	-clean:
		removes all class files.

USE:

	run prototype:	java TestTool [graph file] [waypoint file]

	arguments:

		-graph file: a text file representing a graph.

		       -acceptable format is as follows:

			8	 -line 0: contains the number of nodes (int)
			15 	 -line 1: contains the number of edges (int)
			3 4 0.23 -lines 2 and above: contains an edge:
			5 7 0.32	-an edge is represented by:
			6 8 0.01		-two integers, seperated by
						 white space(s), that represent
			.			 the nodes that compose the
			.			 edge.
			.			-a floating point number,
						 seperated from the two
			1 2 0.21		 nodes by white space(s),
			3 4 0.91		 representing the weight
						 of the edge.
		
		-waypoint file: a text file representing a series of
				waypoints we would like our robot to
				traverse.

			-acceptable format is as follows:

				The first line is the number of 
				waypoints, and the second line is a
				series of integers that represent
				these waypoints.

				Example:
				
				5
				0 7 4 10 3

CREDITS:

	"make clean" and "make it" - Dr. Hamilton Wright
	