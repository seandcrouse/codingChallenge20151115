# codingChallenge20151115

-------------------------------------------------------------------------------

To test part 1, run the main method in TestGraphWalker.  The first argument 
must be a file path.  To test part 2, run the main method TestPaths.  In both 
cases, the file should represent a tree of nodes.  Each level of the tree is 
defined by the number of leading tab characters.  For example:

Animal
   |-- Dog
   |    |-- Chihuahua
   |    |-- Poodle
   |
   |-- Cat
        |-- Lion
        |-- House
              |-- Tabby
              |-- Siamese
              
In the txt file, it would be represented as:

Animal
	Dog
		Chihuahua
		Poodle
	Cat
		Lion
		House
			Tabby
			Siamese
			
When printing the nodes, the toString method prints the name and children of 
each node.  To print just the name of the nodes, and not the children, use the 
VM argument -DnamesOnly=true.

The file humanTaxonomy.txt is provided as a sample input file.

-------------------------------------------------------------------------------

To test part 3, run the shell script countInstancesOfEachWord.sh.  The first 
argument must be a text file.  A word is defined as a series of alphanumeric 
characters. Each word is separated by one or more non-alphanumeric characters.
It is case-sensitive, so the word 'and' is not the same as 'And'.

The file pg11.txt is provided as a sample input file.