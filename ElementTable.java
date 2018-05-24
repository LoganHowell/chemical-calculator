/** 
 * A manager for all methods relating to requests for 
 * reference Element data. 
 * 
 * Reduces the amount of parsing required to 
 * create Element objects when compared to previous
 * versions. 
 * 
 * @author Logan Howell - 445281
 * @version 8-5-2018
 */

public class ElementTable {
    
    private final static int ROW_NUM = ReadFiles.findRowNum() - 1; 
    final static Element REFERENCE [ ] = new Element [ROW_NUM];
    
    
    /** 
     * Initialises a reference table of Element objects.
     * Any calls to access reference data will be directed to
     * this method rather than the CSV file to limit parsing from 
     * String to other data types. 
     */
    public static void initialise() { 
        final int SYMBOL_ROW = 0;
        final int NAME_ROW = 1;
        final int ATOMIC_NUM_ROW = 2;
        final int ATOMIC_MASS_ROW = 3;
        final int ELECTRONEG_ROW = 4;
        
        Element newElement; 
        
        for (int i = 0; i < ROW_NUM; i++) {
            String symbol = ReadFiles.table[i][SYMBOL_ROW]; // The chemical symbol of the element. 
            String name = ReadFiles.table[i][NAME_ROW]; // The name of the element. 
            int atomicNum = Integer.parseInt(ReadFiles.table[i][ATOMIC_NUM_ROW]); // The element's atomic number. 
            double atomicMass = Double.parseDouble(ReadFiles.table[i][ATOMIC_MASS_ROW]); // The atomic mass of the element. 
            double electroNeg = Double.parseDouble(ReadFiles.table[i][ELECTRONEG_ROW]);
            
            newElement = new Element(name, symbol, atomicNum, atomicMass, electroNeg);       
            REFERENCE[i] = newElement;              
        }
    }
    
    /**
     * Determines if an element is valid.
     * @param symbol: The chemical symbol being analysed.
     */ 
    public static boolean isElement(String symbol) { 
        for (int i = 0; i< ROW_NUM; i++) {
            if (REFERENCE[i].getSymbol().equals(symbol)) {
                return true; 
            }
        }
        return false;  
    }
    
    
    /**
     * Determines the index in the reference table for
     * a requested Element. 
     * @param symbol: The chemical symbol being analysed.
     * @returnL The index of the requested Element within the reference array. 
     */
    public static int getElementIndex(String symbol) {
        int index = 0; // The position of the element within the array. 
        
        if (isElement(symbol)) { 
            for (int i = 0; i< REFERENCE.length; i++) {
                if (symbol.equals(REFERENCE[i].getSymbol())) { 
                    index = i; 
                }   
            } return index; 
        } 
        else { 
            System.err.println("INVALID ELEMENT INPUT."); 
            System.err.println("Please try again.");
            return 0; 
        }
    } 
    
    
    /**
     * Returns the data for a requested Element. 
     * @param symbol: The chemical symbol being analysed.
     * @return: Data requested for a specific Element. 
     */ 
    public static Element getElement(String element) {
        Element newElement;
        int index; // The array position of the requested Element. 
        index = getElementIndex(element);
        
        newElement = REFERENCE[index];
        return newElement; 
    }
    
    /**
     * Returns the electronegativity for a requested
     * Element. 
     * @param symbol: The chemical symbol being analysed.
     */
    public static double assignElectroneg(String element) {
        int index; // The location of the Element within the array. 
        double electroneg; // The electronegativity of the Element. 
        
        index = getElementIndex(element);
        electroneg = REFERENCE[index].getElectroneg();
        
        return electroneg;
    }
}

