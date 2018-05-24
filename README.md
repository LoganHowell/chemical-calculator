# chemical-calculator
Undertakes chemical calculations on a collection of user built molecules.

1.	Within the class ReadFiles, the String csvFileLocation must be modified to reflect the current position of the file on the user’s computer.
	2.	Also within the class ReadFiles(): The final int NUMCOL must be altered if a CSV file other than that which was included in the GitHub package is utilised. While the addition of an extra data column within the CSV file to provide extra functionality is supported, the dimensions of the created reference array must reflect this change. 
	3.	Within the ChemCalcDriver class, the final ints ELEMENT_CAPACITY and MOLECULE_CAPACITY must be changed to reflect the maximum number of elements allowed within a single molecule and the maximum number of molecules that can be analysed . During development, these integers were assigned a value of 50 and this proved fine for simple organic molecule modelling, however if larger molecules such as cholesterol were being modelled, this value must be increased to avoid the array becoming full. 
	4.	If a different csv file were used as a source of data for the program the structure of the data (i.e. the positions of rows with respect to each other) can be determined by calling printArray() from the main() method. 
