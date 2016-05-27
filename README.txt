Mathieu Belzile-Ha					      1/6/2016

				README


GRAPH TRAVERSAL & EXPERIMENT TOOLS

---------------------------------------------------------------------

INSTALLATION:

	The Makefile provided serves as an installer. It has five
	valid targets:

	-it:
		Displays all valid targets.

	-pathFinder:
		Compiles all java classes related to graph traversal.

	-experimentTools:
		Instals tools that were used in performing experiments
		discussed in the experiment report, if one might want
		to reproduce them.

	-all:
		Installs both pathFinder and experimentTools.

	-clean:
		removes all class files.

PATHFINDER:

	run prototype:	java TestTool [graph file] [waypoint file] 
			[num iterations](optional)

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

		-num iterations: optionally, the number of times the
				 TestTool repeats the tasked assigned
				 to it can be specified.

				 If this is made more than 1, the
				 calculated run time will be an average
				 made up of these iterations, which
				 allows one to have a more accurate
				 measurement of this.

EXPERIMENT TOOLS:

	The experiment tools are the set of the following programs:

		-GraphGen
		-SpawnEGraphs
		-SpawnNGraphs

		-wayPointGen
		-SpawnWayPoints
		-SpawnNWayPoints

		-SpawnPlan

		-ExpTool

		-experiment

	Note: This are all found in the ExpTools/ directory, except ExpTool,
	which is in the root directory.

	GraphGen:

		This program is used to generate valid graph files of
		specified node and edge count. The edges are generated
		at random at the file is saved at the specified path.

		run prototype: 	GraphGen [numnodes] [numedges] 
				[filepath]

	SpawnEGraphs/SpawnNGraphs:

		Incidently, SpawnEGraphs and SpawnNGraphs are just 
		wrapper programs used to mass-produce graph files 
		according to the specific needs of this project's
		experiments. 

		SpawnEGraphs is used to generated a series of graphs 
		with increasing amounts of Edges.

		SpawnNGraphs is used to generated  series of graphs 
		with increasing amounts of Nodes.

		run prototype: 	SpawnEGraphs [startNumEdges] 
				[endNumEdges] [numNodes] [directory] 
				[numFiles]

		run prototype: 	SpawnNGraphs [startNumNodes] 
				[endNumNodes] [numEdges] [directory]
				[numFiles]

	wayPointGen:

		Just the same as GraphGen, this generates a valid
		waypoint file.

		run prototype:	wayPointGen [numNodes] [numWaypoints]
				[filePath] [separationType]

		(separationType is included because a waypoint file
		can, alternatively, have its waypoints separated by
		new lines instead of white spaces)

	SpawnWayPoints/SpawnNWayPoints:

		Same as what SpawnEGraphs/SpawnNGraphs, are to
		GraphGen, only SpawnWayPoints generates waypoint
		files with increasingly many waypoints and
		SpawnNWayPoints generates waypoint files with
		increasingly many nodes.

		run prototype:	SpawnWayPoints [numNodes] 
				[startNumWaypoints] [endNumWaypoints]
				[directory] [separationType] [numFiles]

		run prototype:	SpawnNWayPoints [startNumNodes] 
				[endNumNodes] [numWaypoints]
				[directory] [separationType] [numFiles]

	ExpTool:

		This program calls TestTool on all graphs contained
		in a given directory and all waypoints contained in
		another given directory. The output is then recorded
		in two different ways on two different files, ".raw" 
		and ".data".

		".raw" file:

			This file records the path of the waypoint and
			graph file and the raw TestTool output. It
			is useful for observing specific input/output
			interactions.

		".data" file:

			Each line of this file is composed of two
			tokens, the file of interest and the resulting
			run time, seperated by a comma.

			This has for advantage that it is easy to load
			this file into programs such as Excel and
			obtain charts so to visually represent them.

		run prototype: 	ExpTool [graphDirectory] [waypointDirectory]
				[fileName] [fileOfInterest] [numIterations]

			fileOfInterest has to possible values, "graph"
			or "waypoint". This determines which file name
			will be displayed next to runtime in the .data
			file.

	SpawnPlan:

		WARNING: long to run.

		Essentially just a list of variou Spawn program calls
		specific to what I needed to generate for my experiment.

		run prototype: SpawnPlan [mode]

			-[mode] has two valid values:

				-exec: 	Generates all files needed to the
					experiment.

				-clear:	removes all generated files

	experiment:

		WARNING: long to run. (Spawn Plan must also be run
		before running this as the experiment relies on
		files generated by the latter program.)

		Same as SpawnPlan only for ExpTool.

		run prototype: experiment [mode]

                        -[mode] has two valid values:

                                -exec:  runs all TestTool experiments.
					Result files of type .raw and .data
					will be found in: expData/nodeExp/
								  edgeExp/
								  waypointExp/

                                -clear: removes all experiment result files.
					(.raw and .data files).


		Note: For the Node Experiment, I had to hardcode it 
		after all as there were some unforseen complications 
		with using ExpTool. This is further discussed in the 
		experimental methods section of the report.

CREDITS:

	"make clean" and "make it" - Dr. Hamilton Wright
