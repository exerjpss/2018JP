Key things in mind
1 - size of data in total not known. Probably huge, but specification is only commenting
on size of 50 trades
2 - Historical data not commented on. Should history be kept?
3 - Valid data checking. e.g. numbers should be positive only ?
4 - Product names, case insensitive? doesn't matter
5 - Input for this exercise is a file
6 - Data format has been chosen for ease
7 - message 1 is message 2 but without QTY and QTY is defaulted to 1
8 - message 3 has to have a sale with it (as per spec'd)
9 - limited external jars to mockito for testing. As per spec 1 jar.


Chosen to use a BDD/TDD approach. Also limiting code to the specification only, rather than 
making assumptions. Would clarify limits and details and implement.

Overall design
	Main calls mainloop;
		Main takes one filename parameter from the command line.
	mainLoop will open the file and read lines in using a message object
		Each valid line will increase the count.
		Valid line has to have parameters consistent with the spec. 
		Contents of parameters are not checked here.
	A hashmap stores the product name and a pointer to a header node (a bucket holding
	the transactions).
	A transaction can be a sale or an adjustment.
	The system is coded to interfaces to allow internal changes. Currently for convenience array ists 		are used to store the transactions
	Output is to the console:
	Adjustments are calculated at report time.
	Adjustments are added to each sale, so the sale has the original sale plus a list of adjustments
		This might not be efficient, but it retains history and prevents calculations happening 
		during input
		Jar is in the bin subdirectory
		run as java -jar jptrade.jar filename
		Project is an eclipse project.