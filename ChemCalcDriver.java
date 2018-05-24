import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** 
 * Driver program for the Chemistry Calculator. 
 * Utilises data accessed through the ReadFiles class to
 * create and enter Element objects into an array. 
 * 
 * Arrays of Elements can be converted to Molecule objects
 * with the data associated with each Molecule being able 
 * to be analysed with calculations performed based on the 
 * structure and the composition of the object. 
 * 
 * @author Logan Howell - 445281
 * @version 5-5-2018 
 * 
 */  
public class ChemCalcDriver { 
    
    /** 
     * Reads the next character the user enters. 
     * @param sc: The scanner device to read user input. 
     * @return: The first character input. 
     */
    public static char nextChar(Scanner sc) {
        return sc.next().charAt(0);
    }
    
    
    /**
     * Determines the bond polarity of the bond between two
     * elements that the user inputs and returns it. 
     * @param sc: The scanner device to read user input. 
     * @param organiser: The class which provides ability to manipulate collections of
     * Elements. 
     * @return: The Polarity of the bond. 
     */ 
    public static Element.Polarity getPolarity(Scanner sc) { 
        Element.Polarity bondPolarity = Element.Polarity.NON_POLAR; // The enum output. 
        String atom1, atom2; // The two element comparison cases. 
        double polarity = 0; // The polarity of the bond between the two elements. 
        double electro1, electro2; // The electronegativity of the two elements.
        
        System.out.println("Please enter the FIRST element."); 
        atom1 = sc.next(); 
        System.out.println("Please enter the SECOND element."); 
        atom2 = sc.next();
        
        if (ElementTable.isElement(atom1) && ElementTable.isElement(atom2)) { 
            
            // Assigns the electronegativity for each input element. 
            electro1 =  ElementTable.assignElectroneg(atom1);
            electro2 =  ElementTable.assignElectroneg(atom2);
            
            polarity = electro1 - electro2; 
            polarity = Math.abs(polarity); // Ensures the polarity is positive.
            
            if (polarity <= 0.5) {
                bondPolarity = Element.Polarity.NON_POLAR; 
            } else { 
                if (polarity > 0.5 && polarity < 2.0) { 
                    bondPolarity = Element.Polarity.POLAR; 
                } else {
                    bondPolarity = Element.Polarity.IONIC; 
                }
            }
        } else { 
            System.err.println("INVALID ELEMENT INPUT"); 
            System.out.println("PLEASE TRY AGAIN"); 
        } 
        
        return bondPolarity; 
    } 
    
    
    /** Determines the mass of the molecule required
      * for a number of moles. 
      * @param sc: The scanner device to read user input. 
      * @param data: The array containing Element objects. 
      * @param numMolecules: The number of molecules currently within the array.
      */
    public static void substanceMass(Scanner sc, Molecule [] entries, int numMolecules) {
        double substanceMass; // The required mass of substance.
        double molecMass; // The molecular mass of the substance.
        double numMoles; // The number of moles of substance. 
        
        System.out.println("Enter the required number of moles of substance.");    
        numMoles = sc.nextDouble(); 
        
        System.out.println("You require:");
        for (int i = 0; i < numMolecules; i++) { 
            molecMass = entries[i].getMolecularMass();    
            substanceMass = numMoles * molecMass; 
            
            System.out.print("Molecule " + (i+1) + ": ");
            System.out.format("%.2f", substanceMass);
            System.out.println(" grams."); 
        }
        
        System.out.println(""); // Empty line for formatting. 
    }
    
    
    /** 
     * Determines the mass of substance required for a
     * solution of given molarity. 
     * @param sc: The scanner device to read user input. 
     * @param data: The array containing Element objects.
     * @param numMolecules: The number of molecules currently within the array.
     */
    public static void solutionMass(Scanner sc, Molecule [] entries, int numMolecules) {
        double conc; // The concentration of the solution.  
        double volume; // The volume of the solution. 
        double substanceMass; // The required mass of substance.
        double molecMass; // The molecular mass of the substance.
        
        System.out.println("Please enter the required concentration of solution (M):"); 
        conc = sc.nextDouble(); 
        
        System.out.println("Please enter the volume of the solution (L).");
        volume = sc.nextDouble(); 
        
        for (int i = 0; i < numMolecules; i++ ) { 
            molecMass = entries[i].getMolecularMass();    
            substanceMass = conc * volume * molecMass; 
            
            System.out.print("Molecule " + (i+1) + ": ");
            System.out.format("%.2f", substanceMass);
            System.out.println(" grams."); 
        }
        System.out.println(""); // Empty line for formatting. 
    }
    
    
    /** 
     * Determines the pressure produced in a container for
     * a given amount of substance, temperature and volume.
     * Outputs the partial pressure in kPa. 
     * @param sc: The scanner device to read user input. 
     * @param data: The array containing Element objects.
     * @param numMolecules: The number of molecules currently within the array.
     */ 
    public static void partialPressure(Scanner sc, Molecule [] entries, int numMolecules) { 
        final double GAS_CONSTANT = 8.31; // The ideal gas constant. 
        double volume; // The volume of the pressure vessel. 
        double substanceMass; // The required mass of substance.
        double temp; // The temperature of the atmosphere in Celsius. 
        double molecMass; // The molecular mass of the substance. 
        double pressure; // The partial pressure produced. 
        
        System.out.println("Please enter the volume of the vessel (L):");
        volume = sc.nextDouble(); 
        
        System.out.println("Please enter the mass of the sample (g):");
        substanceMass = sc.nextDouble(); 
        
        System.out.println("Please enter the temperature of the atmosphere (Celsius):"); 
        temp = sc.nextDouble(); 
        temp = temp + 273.15; // Converts the temp from Celsius to Kelvin. 
        
        for (int i = 0; i < numMolecules; i++ ) { 
            molecMass = entries[i].getMolecularMass();    
            
            pressure = (substanceMass * GAS_CONSTANT * temp) / (molecMass * volume);
            System.out.print("Molecule " + (i+1) + ": ");
            System.out.format("%.2f", pressure);
            System.out.println(" kPa."); 
        }
    }
    
    
    /**
     * Prints the molecular formulas of all initialised
     * molecules so the user can make comparisons between
     * structure and chemical composition. 
     * @param entries: The array containing the completed Molecule objects.
     * @param numMolecules: The number of molecules within the array. 
     */ 
    public static void printMolecules(Molecule [] entries, int numMolecules) {
        for (int i = 0; i < numMolecules; i++) {
            System.out.println("Molecule " + (i+1) + " :" + entries[i].toString()); 
        }
        
    }
    
    
    /**
     * Creates a new molecule to be analysed using various 
     * methods. The molecule created in this step cannot be 
     * altered in any way. Note that the molecule is not converted
     * into a subscript form. This is due to the minimal information 
     * this provides about the structure of the molecule in this form.
     * @param data: The array containing Element objects. 
     * @param entries: The array containing the completed Molecule objects.
     * @param numMolecules: The number of molecules within the array. 
     * @param organiser: The class which provides ability to manipulate collections of
     * Elements. 
     * @return: The number of molecules within the 'entries' array. 
     */ 
    public static int createNewMolecule(Molecule [] entries, int numMolecules, ElementOrganiser organiser) { 
        String formula = ""; // The formula of the molecule. 
        double molecularMass; // The molecular mass of the molecule. 
        int numElements; // The number of elements in the molecule being built. 
        int count; // Subscript count for elements. 
        Molecule newMolecule; 
        
        numElements = organiser.returnNumElements();
        
        if (numElements > 0) {
            for (int i = 0; i < numElements; i++) {
                formula = organiser.data[i].getSymbol() + formula;   
            }
            
            molecularMass = organiser.getMolecularMass(); 
            newMolecule = new Molecule(formula, molecularMass);
            entries[numMolecules] = newMolecule;
            numMolecules++; 
            
        } else { 
            System.out.println("Please enter AT LEAST one element."); 
        }
        
        return numMolecules; 
    } 
    
    /**
     * Determines and displays the current molecular massess for each
     * molecule created. 
     * @param entries: The array containing the completed Molecule objects.
     * @param numMolecules: The number of molecules within the array. 
     */
    public static void showMolecularMass(Molecule [] entries, int numMolecules) {   
        double molecularMass; // The molecular mass for each molecule. 
        
        for (int i = 0; i < numMolecules; i++) {
            molecularMass = entries[i].getMolecularMass();
            System.out.print("Molecule " + (i+1) + ": ");
            System.out.format("%.2f", molecularMass);
            System.out.print(" g/mol.");    
            System.out.println();
        }
    }
    
    
    /** 
     * Initial menu visible at the start of the program. 
     * Allows users to add individual elements or 
     * enter a molecular formula. 
     * @param sc: The scanner device to read user input. 
     * @param data: The array containing Element objects. 
     * @param entries: The array containing the completed Molecule objects.
     * @param numMolecules: The number of molecules within the array.
     * @param organiser: The class which provides ability to manipulate collections of
     * Elements. 
     */ 
    public static void startMenu(Scanner sc, Molecule [] entries, int numMolecules, ElementOrganiser organiser) {   
        int choice; // The user's selection for the menu. 
        
        do {
            // Menu options for below switch statement. 
            System.out.println("MENU:"); 
            System.out.println("1: Enter molecular formula.");
            System.out.println("2: Search for element data."); 
            System.out.println("3: Display the current molecular configuration.");  
            System.out.println("4: Delete the last element.");
            System.out.println("5: Clear the molecule's current structure.");
            System.out.println("6: Complete the molecule."); 
            
            choice = sc.nextInt(); 
            
            switch (choice) {
                
                case 1: organiser.parseFormula(); 
                startMenu(sc, entries, numMolecules, organiser);
                break; 
                
                case 2: organiser.elementSearch(); 
                startMenu(sc, entries, numMolecules, organiser);
                break;
                
                case 3: organiser.printElements(); 
                startMenu(sc, entries, numMolecules, organiser);
                break;
                
                case 4: organiser.deleteElement(); 
                startMenu(sc, entries, numMolecules, organiser);
                break;
                
                case 5: organiser.clearMolecule();
                startMenu(sc, entries, numMolecules, organiser);
                break;
                
                case 6: numMolecules = createNewMolecule(entries, numMolecules, organiser);  
                organiser.clearMolecule(); 
                functionMenu(sc, entries, numMolecules, organiser); 
                break;
            }
            
        } while (organiser.returnNumElements() < 1); 
 
    } 
    
    
    /** 
     * The selection menu where the user chooses functions.
     * Only able to be entered when there is an element within the
     * array of atoms. 
     * @param sc: The scanner device to read user input. 
     * @param data: The array containing Element objects. 
     * @param entries: The array containing the completed Molecule objects.
     * @param numMolecules: The number of molecules within the array. 
     * @param organiser: The class which provides ability to manipulate collections of
     * Elements. 
     */ 
    public static void functionMenu (Scanner sc, Molecule [] entries, int numMolecules, ElementOrganiser organiser) { 
        int select; // The user's selection input. 
        int choice; // The user's choice for the molecule. 
        Molecule currentMolecule; // The user's current selected molecule. 
        
        do {
            // Menu options for below switch statement. 
            System.out.println("PLEASE SELECT AN ACTION:");  
            System.out.println("1: Build a new molecule."); 
            System.out.println("2: Display the current molecular structures.");
            System.out.println("3: Calculate the current molecular masses.");
            System.out.println("4: Determine required substance mass (for number of moles).");
            System.out.println("5: Determine bond type.");
            System.out.println("6: Determine required solute mass.");
            System.out.println("7: Determine partial pressure (for substance mass).");
            System.out.println("8: QUIT.");
            
            select = sc.nextInt(); 
            
            switch(select) { 
                
                case 1: startMenu(sc, entries, numMolecules, organiser);
                break; 
                
                case 2: printMolecules(entries, numMolecules);
                break;
                
                case 3: showMolecularMass(entries, numMolecules); 
                break;
                
                case 4: substanceMass(sc, entries, numMolecules);
                break; 
                
                case 5: System.out.println("Bond type is " + getPolarity(sc)); 
                break; 
                
                case 6: solutionMass(sc, entries, numMolecules); 
                break;
                
                case 7: partialPressure(sc, entries, numMolecules);
                break; 
                
            } 
        } while (select != 8);
        
    } 
    
    
    public static void main(String [] args) {  
        Scanner sc = new Scanner(System.in); 
        ElementOrganiser organiser = new ElementOrganiser(); 
        ReadFiles read = new ReadFiles(); 
        
        int MOLECULE_CAPACITY = 20; // The maximum number of molecules that can be analysed. 
        Molecule entries [ ] = new Molecule[MOLECULE_CAPACITY]; // Array of molecules. 
        int numMolecules = 0; // The number of molecules within the array. 
        
        read.accessCSV(); // Accessess the CSV file.
        ElementTable.initialise(); // Initialises the reference array. 
        
        System.out.println("~~~ WELCOME TO THE CHEMICAL CALCULATOR ~~~"); 
        startMenu(sc, entries, numMolecules, organiser);
        
    } 
} 
