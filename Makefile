#
# A simple makefile to clean up all class files
#

it :
	@ echo ""
	@ echo "Valid targets are 'clean'"
	@ echo "                  'pathFinder'"
	@ echo "                  'experimentTools'"
	@ echo ""

pathFinder :
	javac TestTool.java

experimentTools :
	mkdir expData
	mkdir expData/nodeExp
	mkdir expData/nodeExp/graphs
	mkdir expData/nodeExp/waypoints
	mkdir expData/edgeExp
	mkdir expData/edgeExp/graphs
	mkdir expData/edgeExp/waypoints
	mkdir expData/waypointExp
	mkdir expData/waypointExp/graphs
	mkdir expData/waypointExp/waypoints
	chmod a+x ExpTool
	chmod a+x ExpTools/GraphGen
	chmod a+x ExpTools/SpawnEGraphs
	chmod a+x ExpTools/SpawnNGraphs
	chmod a+x ExpTools/SpawnNWayPoints
	chmod a+x ExpTools/SpawnPlan
	chmod a+x ExpTools/SpawnWayPoints
	chmod a+x ExpTools/wayPointGen

all:
	make pathFinder
	make experimentTools

clean : 
	- rm -f *.class
	- rm -rf expData
