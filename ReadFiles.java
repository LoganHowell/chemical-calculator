import java.io.*; // Required for the File class. 
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer; 

/** 
 * Reads a csv file as input data and converts it to a reference array. 
 * The created array will be referenced by the Driver program for various 
 * functions. 
 * 
 * @author Logan Howell - 445281 
 * @version 20-4-2018 
 * 
 */  

public class ReadFiles { 
    
    /*
     * VARIABLES BELOW ARE REQUIRED TO BE MODIFIED APPROPRIATELY BY THE 
     * USER AS PER THE ATTACHED DOCUMENTATION.
     */ 
    final static String CSV_FILE_LOCATION = "/Users/LoganHowell/Desktop/FormatPeriod.csv"; // The location of the file. 
    final int NUMCOL = 6; // The number of columns within the CSV file.
    
    
    static File file = new File(CSV_FILE_LOCATION); // Initialises the file's directory. 
    
    public static String[] [] table; // The reference periodic table. 
    int rowNum = 0; // Number of rows within the CSV file. 
    
    /** Checks if the input file csvFile is a valid destination. */ 
    public static boolean checkIsFile() { 
        return file.isFile(); 
    }
    
    
    /** 
     * Determines the number of rows within the CSV data file.
     * Eliminates the possibility that a shorter or longer periodic table source
     * is used as input data - number of rows would then not be constant.
     * Only allows an array to be of the correct size when initialised.
     * @return: The number of rows within the CSV file. 
     */ 
    public static int findRowNum() { 
        int rowNum = 0; // Resets the row count if the method is called twice. 
        
        // Checks to see if the file is valid.
        if (checkIsFile()) { 
            try { 
                BufferedReader reader = new BufferedReader(new FileReader (file)); 
                
                // Reads each line of the file and increments the row count. 
                while(reader.readLine() != null) {
                    rowNum++; 
                }
            } catch (Exception ex) { 
                System.err.println(ex); 
                System.err.println("Row is out of bounds."); 
            } 
        } else {
            System.err.println("Invalid source file."); 
            System.err.println("Please check that the file's location is valid."); 
        }
        return rowNum; 
    } 
    
    
    /** 
     * Converts the CSV file into an array. 
     * Array serves as the source for any references to 
     * chemical data. 
     */ 
    public void accessCSV() { 
        int rowCount = 0; // Count of rows within the array. 
        
        table = new String[findRowNum()][NUMCOL]; // 2D array with rowNum rows and 6 columns. 
        
        try { 
            BufferedReader reader = new BufferedReader(new FileReader (file)); 
            String line = null; // The current line being read. 
            
            // Iterates over the entire file until a null value is found.
            while ((line = reader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(line, ","); 
                
                // While there are more tokens available within the tokenizer's input...
                while (token.hasMoreTokens()) { 
                    // Enters each tokenized element into the array as per format of the csv input. 
                    for (int i = 0; i < NUMCOL; i++) { 
                        table [rowCount][i] = token.nextToken();  
                    }
                    rowCount++; 
                } 
            }
            
        } catch (Exception e) { 
            System.out.println(e); 
        } 
    } 
    
    
    /** 
     * Displays all elements within the 'table' array for fault finding purposes. 
     */ 
    public void printArray() { 
        
        //Nested for loop accesses both rows and columns of the 'table' array. 
        for (int r = 0; r<table.length; r++) { 
            for (int c =0; c<table[r].length; c++) {
                System.out.print(table[r][c]);
                System.out.print(" "); // Empty string for formatting. 
            }
            System.out.println(""); 
        } 
    } 

}
