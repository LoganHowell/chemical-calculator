import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** 
 * A organiser class for the methods involving
 * the Element data class utilised within 
 * the ChemCalcDriver program. 
 * 
 * @author Logan Howell - 445281
 * @version 8-5-2018
 */

public class ElementOrganiser  { 
    
    private Scanner sc = new Scanner(System.in); // For reading input from the user. 
    private final int ELEMENT_CAPACITY = 100; // The number of elements able to be added to the data array. 
    private int numElements = 0; // The initial number of elements within the array. 
    
    Element data [ ] = new Element[ELEMENT_CAPACITY]; // Array of elements.
    
    /** 
     * Reads the next character the user enters. 
     */
    public char nextChar() {
        return sc.next().charAt(0);
    }
    
    
    /** 
     * Converts an input molecular formula into individual sub-strings of 
     * element symbols using regular expressions. 
     * @return: The number of Elements within the 'data' array. 
     */ 
    public int parseFormula() { 
        int elementCount; // The number of elements per capture group. 
        String symbol; // The element's symbol
        String typeNumber; // The number of elements of type 'symbol'. 
        int number; // Conversion case for typeNumber. 
        String formula; // The formula to be parsed. 
        
        System.out.println("Please enter a molecular formula:");
        formula = sc.next(); 
        
        Pattern pattern = Pattern.compile("([A-Z][a-z]?)(\\d*)");
        
        Matcher match = pattern.matcher(formula);
        
        // Iterates while valid groups are found.
        while (match.find()) {
            
            // Converts the captured groups to a string. 
            symbol = match.group(1);
            typeNumber = match.group(2);
            
            if (!typeNumber.equals("")) { // Check out isEmpty method. 
                number = Integer.parseInt(typeNumber); 
            } else {
                number = 1;
            }
            
            // Converts each symbol group into elements based on their frequency. 
            for (int i = 0; i < number; i++) { 
                if (ElementTable.isElement(symbol)) {
                    data[numElements] = ElementTable.getElement(symbol);
                    numElements ++;
                    
                } else {
                    System.err.println("The input formula contains an error.");
                    System.err.println(symbol + " is not a valid element.");  
                    System.err.println(symbol + "has not been added to the molecule."); 
                }
            }
        }
        return numElements;
    }

    
    /** 
     * Allows the user to search for elements to add to their molecule. 
     * Displays relevent data for each input element. 
     */ 
    public void elementSearch() { 
        String search; // The search element input from user. 
        char action; // A y/n prompt - response. 
        
        System.out.println("ELEMENT SEARCH"); 
        do { 
            System.out.println("Please enter an element's chemical symbol:"); 
            search = sc.next(); 
            
            if (ElementTable.isElement(search)) {
                Element outputElement;
                outputElement = ElementTable.getElement(search);
                
                System.out.println("Element name: " + outputElement.getName()); 
                System.out.println("Atomic number: " + outputElement.getAtomicNumber());
                System.out.println("Atomic mass: " + outputElement.getAtomicMass() + " AMU");
                System.out.println("Electronegativity: " + outputElement.getElectroneg());
                System.out.println(""); // Empty line for formatting. 
                
                System.out.println("Add this element to your molecule? (y/n)"); 
                action = nextChar(); 
                
                if (action == 'y') {
                    
                    if (numElements < data.length) { 
                        data[numElements] = outputElement; 
                        numElements++;    
                        System.out.println("There are currently " + numElements + " element(s) in the molecule.");  
                        System.out.println(""); // Empty string for formatting. 
                    } 
                }
                
            } else { 
                System.err.println("INVALID ELEMENT INPUT.");
                System.err.println("PLEASE TRY AGAIN"); 
            }
            
        } while (!ElementTable.isElement(search));
        
    } 
    
    
    /** 
     * Prints the elements within the molecule to provide a visualisation
     * of current molecular configuration. 
     * Printed from first to last added. 
     */ 
    public void printElements() { 
        System.out.print("In order of addition - "); 
        System.out.println("Current molecular configuration is:");
        for (int i = 0; i < numElements; i++) { 
            System.out.print((data[i]).getSymbol()); 
        } 
        System.out.println(""); // Empty line for formatting.    
    } 
    
    
    /** 
     * Determines the total molecular mass of the molecule.
     * @return: The molecular mass of the current molecule. 
     */ 
    public double getMolecularMass() {
        double molecMass = 0; // The molecular mass of the compoumd. 
        
        for (int i = 0; i < numElements; i++) {
            molecMass += data[i].getAtomicMass(); 
        }
        return molecMass; 
    }
    
    
    /** 
     * Deletes the last element within the molecule. 
     * @return: The new number of elements within the array. 
     */ 
    public int deleteElement() { 
        data[numElements] = null; 
        numElements = numElements - 1; 
        return numElements;  
    }
    
    
    /** 
     * Clears all elements within the molecule.
     * @return: The new number of elements within the array.
     */ 
    public int clearMolecule() {
        for (int i = 0; i < numElements; i++) { 
            data[i] = null; 
        }
        numElements = 0; 
        return numElements; 
    }
    
    
    /**
     * @return: The number of elements within the array.
     */ 
    public int returnNumElements() { 
        return numElements;
    }
}