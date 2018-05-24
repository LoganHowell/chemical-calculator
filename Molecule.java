import java.io.IOException;
import java.util.regex.*;

/** 
 * Provides data summaries of molecules with chemical formulal and
 * molecular mass.
 * 
 * @author Logan Howell - 445281 
 * @version 5-5-2018 
 * 
 */  


public class Molecule { 
    
    private String formula; // The formula for the molecule. 
    private double molecularMass; // The molecular mass of the molecule. 
    
    public Molecule(String f, double m) {
        
        formula = f;
        molecularMass = m; 
        
    } 
    
    
    /** 
     * Gets the molecule's formula.
     */ 
    public String getFormula() { 
        return formula; 
    }
    
    
    /** 
     * Sets the molecule's formula. 
     */ 
    public void setFormula(String f) { 
        formula = f; 
    } 
    
    
    /** 
     * Gets the molecule's atomic mass.
     */
    public double getMolecularMass() { 
        return molecularMass; 
    }
    
    
    /** 
     * Sets the molecule's atomic mass.
     */ 
    public void setMolecularMass(double m) { 
        molecularMass = m; 
    }

    
    /**
     * Converts the Molecule's fomula String to
     * a formatted, subscripted non-structural
     * formula. 
     */ 
    public String toString() { 
        
        StringBuilder builder = new StringBuilder();
        ElementOrganiser organiser = new ElementOrganiser(); // Provides access to the isElement() method. 
        
        String[] elements = formula.split("(?=[A-Z])"); // Splits the String formula into an array as per regex. 
        
        String formatFormula = null; // The formula which is to be built. 
        boolean check = true; // Check condition for each of the split elements. 
        String symbol; // Comparison case.  
        int counter = 1;
        
        // Checks that all elements within the array are valid.
        for (int i = 0; i < elements.length; i++) {
            symbol = elements[i];
            
            // Case if an invalid element has been split. 
            if (!ElementTable.isElement(symbol))
                check = false;
        } 
        
        if (check == true) {
            for (String element : elements) {
                if (!element.equals(formatFormula)) {
                    if (formatFormula != null) {
                        builder.append(formatFormula);
                        if(counter > 1) {
                            builder.append(counter);
                        }
                    }
                    formatFormula = element;
                    counter = 1;
                } else {
                    counter++;
                }
            }
            
            // Adds the final element to the appended String.  
            builder.append(formatFormula);
            if (counter > 1) {
                builder.append(counter);
            }
            
        } else {
            builder.append(""); // Appends an empty string. 
            System.err.println("ERROR");
            System.err.println("FORMULA CONTAINS INVALID ELEMENT");
        } 
        return builder.toString(); // Returns the appended formula. 
    } 
}