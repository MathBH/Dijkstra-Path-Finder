#
# A simple makefile to clean up all class files
#

it :
	@ echo ""
	@ echo "Valid targets are 'clean'"
	@ echo "                  'pathFinder'"
	@ echo ""

pathFinder :
	javac TestTool.java

clean : 
	- rm -f *.class
