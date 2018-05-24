/** 
 * Provides data summaries of elements with a name, symbol,
 * atomic mass, electronegativity and atomic number .
 * 
 * @author Logan Howell - 445281 
 * @version 24-4-2018 
 * 
 */  

public class Element { 
    
    // The types of bond polarities. 
    public enum Polarity { NON_POLAR, POLAR, IONIC };
    
    private String name; // The name of the element. 
    private String symbol; // The chemical symbol of the element. 
    private int atomicNumber; // The element's atomic number. 
    private double atomicMass; // The atomic mass of the element.  
    private double electroNeg; // The electro-negativity of the element. 
    
    Element(String n, String s, int z, double m, double e) {
        name = n; 
        symbol = s;
        atomicNumber = z; 
        atomicMass = m; 
        electroNeg = e;
    } 
    
    // Getters and setters for the above constructor. 
    
    /** Returns the element's name. */ 
    public String getName() { 
        return name; 
    }
    
    
    /** Gets the element's symbol. */
    public String getSymbol() { 
        return symbol; 
    }
    
    
    /** Gets the element's atomic number. */
    public int getAtomicNumber() {
        return atomicNumber; 
    }
    
    
    /** Gets the element's atomic mass. */
    public double getAtomicMass() { 
        return atomicMass; 
    }
    
    /** Gets the element's electronegativity. */
    public double getElectroneg() { 
        return electroNeg; 
    }
    
} 