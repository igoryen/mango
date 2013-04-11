// Assignment3.java   ...... - Test class for Employee (with Hashtable and Serialization)
package applications;

import java.io.*;
import java.util.*;
import business.*;
import useful.*;


/**
 * Purpose is to test class Employee ... which involves. <ul> <li>making one or more objects of the class</li> <li>calling each constructor at least
 * once <p>examining the data in the object concerned</p> </li> <li>calling each method at least once <p>with various data as parameter(s)</p>
 * <p>examining the return value</p> <p>examining the data in the object concerned</p> </li> </ul>
 *
 * <p>This program creates a Hashtable and adds each Employee object to it as it is made. Before the end of each run the Hashtable is serialized into
 * file (serial.out). At the start of each run it allows for the possibility that there is an existing serialized file (serial.in) and will
 * deserialize if if found to populate the Hashtable. If there is no serialized input it will create an empty Hashtable. This application currently
 * does NOT update existing Hashtable objects, but will add new ones using data input in the normal manner.</p>
 *
 * <p> Run instructions...
 * <pre>
 * java TestEmployeeHS [ &lt; Employee.txt ]
 * </pre> </p>
 *
 */
public class Assignment3
{
  //=========static data===============
  //none

  //=========start declaring instance data==================================================================
  private DataReaderImpl_1 dri;  // reader based on standard input
  private Hashtable ht = new Hashtable(); //
  private Hashtable hti = new Hashtable();
  private Employee nemo_inf; //to be used at deserialization 
  //=========finish declaring instance data================================================================

  /**
   * Perform initialization tasks when the program starts. <ul> <li>process the command-line options (from param list)</li> <li>open file(s) as
   * necessary</li> <li>open network-connection(s) as necessary</li> </ul>
   *
   * @param args the command line arguments
   */
  // ========== start method printNumClose()  ==================================================================
  /**
   * The method is used to print a row of numbers without spaces.
   */
  private void printNumClose() // prints 1234567890123....
  {
    //System.out.print("12345679");
    for (int k = 0; k < 6; k++)
    {
      for (int m = 1; m < 10; m++)
      {
        System.out.print(m);
      }
      System.out.print("0");
    }
    System.out.print("12345\n");
  }
  // ========== finish method printNumClose()  ==============================================================
  // ========== start method printNumLoose()   ==============================================================

  /**
   * The method is used to print a row of numbers with spaces.
   */
  private void printNumLoose()  // prints      1      2      3...
  {
    for (int i = 1; i < 7; i++)
    {
      for (int j = 0; j < 9; j++)
      {
        System.out.print(" ");
      }
      System.out.print(i);
    }
    System.out.print("\n");
  }
  // ========== finish printNumLoose()  ==============================================================
  //=============start METHOD init() ===================================================================

  public void init(String[] args) throws IOException, ClassNotFoundException
  {
    // open a suitable input stream on standard input
    dri = new DataReaderImpl_1();
  } // end init()
  //=============finish METHOD init() ===================================================================

  //=============== start METHOD run() ==================================================================
  /**
   * Do major part of processing, probably in a loop. <ul> <li>possibly call static method(s) of class</li> <li>make object(s) of other classes (by
   * calling constructor(s))</li> <li>call instance method(s) of those classes <p>display return value for examination</p> <p>display contents of
   * class variables or objects affected</p> </li> </ul>
   */
  public void run() throws IOException
  {
    int counter = 0;                  // for nuimber of tests
    StringBuffer csv = new StringBuffer(128);  // empty string for getData()/update()
    Employee nemo;                         // object of class Employee
    int returned = 0;                 // value to be returned
    StringBuffer sb = new StringBuffer(128);   // empty string for displaying


    try
    {
      // get first set of data
      returned = Employee.getData(csv, dri, "~");

      while (returned == 0)
      {
        System.out.println("\n+++StringBuffer $csv after getData() is : " + csv);
        System.out.println("\n+++++++++++++++ Test " + ++counter + "++++++++++++");

        //================= initializing Employee ==================================================
        nemo = new Employee();
        //================= finish initializing Employee ==================================================


        //System.out.println("nemo, immediately after construction (no-param construc tor), follows");
        sb.setLength(0);

        
        //============= displaying empty form ==================================
        returned = nemo.formatDisplay(sb);
        //System.out.println("sb after formatDisplay (empty report form): ((("+ sb+")))"); // empty fields
        //============= finish displaying empty form ==================================


        //============= filling the form (partially) ========================================================================
        nemo.update(csv);
        sb.setLength(0);
        
        //System.out.println("sb after update and formatDisplay: ((("+sb+")))");
        //============= finish filling the form ========================================================================

        
        //================= testing payroll methods ====================================================================

        //================= testing payroll method calculateSalary() =====================================
        System.out.println("Testing method calculateSalary()...");
        nemo.calculateSalary();
        //=================================================================================================

        //================= testing payroll method calculateWages() =====================================
        System.out.println("\nTesting method calculateWages(40)...");
        nemo.calculateWages(40);
        //=================================================================================================

        
        //==================testing payroll method calculateTax() ================================
        System.out.println("\nTesting method calculateTax()...");
        nemo.calculateTax();
        // ===================================================================================
        
        //==================testing payroll method calculateUnion() ================================
        System.out.println("\nTesting method calculateUnion()...");
        nemo.calculateUnion();
        // ===================================================================================
        
        
        returned = nemo.formatDisplay(sb);
        System.out.println("\nAfter update() and formatDisplay(), sb: ((("+sb+")))");
        
        
        
        //================= finish testing payroll methods ====================================================================

        //=========== start adding the current object to hashtable "ht"  ===============================================
        try
        {
          // add the current Employee object to the Hastable
          // NOTE: using the counter as the key is NOT IDEAL (but we will address that later)
          // DONE ... use the (new) getPrimaryKey method

          ht.put(nemo.getPrimaryKey(), nemo);
        }
        catch (Exception e)
        {
          System.out.println("******************************************************************");

          System.out.println("Caught exception in wrap(): " + e.getMessage());
          System.out.println("... stack trace follows ...");
          e.printStackTrace(System.out);
          System.out.println("******************************************************************");

        }
        //=========== finish adding the current object to hashtable "ht"  ===============================================

        System.out.println("+++++++++ end of Test " + counter + "+++++++++++\n");

        //===================== get next set of data to set up the next (a) =====================================
        csv.setLength(0);
        returned = Employee.getData(csv, dri, "~");
        //===================== get next set of data to set up the next (z) =====================================

      } // end of while() loop

      System.out.println(">>>> data-entry loop ended with return code: " + returned);
    }
    catch (Exception e)
    {
      System.out.println("******************************************************************");

      System.out.println("Caught exception in run(): " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
      System.out.println("******************************************************************");

    }
  } // end method
  // ====================== finish METHOD run() ============================================================

  // ====================== start METHOD wrap() ===================================================================
  /**
   * Do any cleanup tasks just before the program ends. <ul> <li>close file(s) used</li> // standard input does not need closing !!! (but you could)
   * <li>close network-connections(s) used</li> <li>In this case serialize the Hashtable of Employee objects </ul>
   */
  public void wrap() throws IOException
  {
    //================= start hashtable map ===================================================
    Enumeration htik;
    Object htiko; // hash table inflated. key as Object
    StringBuffer sb = new StringBuffer();
    StringBuffer fd = new StringBuffer(); //fd=formatData
    String htiks = "";
    String lineOfDashes = "";
    for (int i = 0; i < 65; i++)
    {
      lineOfDashes += "-";
    }



    //InventoryItemImpl htiv;
    //================= finish hashtable map ==================================================


    // ========== start deflating (serializing) the hashtable  ==============================================
    try
    {
      // ============ start making a suitable output stream  ===================================
      FileOutputStream fos = new FileOutputStream("../data/Assign3.ser");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      // ============ finish making a suitable output stream  ===================================

      //================= start the serialization  =======================================
      oos.writeObject(ht);
      //================= finish the serialization  =======================================

      // =============== start flush and close the output file =============================
      oos.flush();
      oos.close();
      // =============== finish flush and close the output file =============================
    }
    catch (Exception e)
    {
      System.out.println("******************************************************************");

      System.out.println("Caught exception in wrap(): " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
      System.out.println("******************************************************************");

    }

    //=================== finish deflating (serializing) the hashtable  ========================================

    //=================== inflating (deserializing) the Hashtable =========================================
    try
    {
      FileInputStream fis = new FileInputStream("../data/Assign3.ser");
      ObjectInputStream ois = new ObjectInputStream(fis);

      //  read objects in the same sequence as they were written
      hti = (Hashtable) ois.readObject();
      ois.close();
    }
    catch (Exception e)
    {
      System.out.println("******************************************************************");

      System.out.println("Caught exception in wrap(): " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
      System.out.println("******************************************************************");

    }
    //=================== finish inflating (deserializing) the Hashtable =========================================
    //=================== printing out the contents of the inflated hashtable ==============================

    System.out.println("");
    printNumLoose();
    printNumClose();
    System.out.println(lineOfDashes);

    Employee.formatHeadings(sb);
    System.out.println(sb);

    for (htik = hti.keys(); htik.hasMoreElements();)
    {
      sb.setLength(0);
      htiko = htik.nextElement();
      nemo_inf = (Employee) hti.get(htiko);
      nemo_inf.formatPaySlip(sb);
      System.out.println(sb);

    }// end for()

    //System.out.println("");
    System.out.println(lineOfDashes);
    printNumClose();
    printNumLoose();
    //=================== finish printing out the contents of the inflated hashtable ==============================
  } // end method
  // ====================== finish METHOD wrap() ===================================================================

  /**
   * Makes an object of its own class then calls instance methods to perform the application tasks. This is the first method called when the program
   * starts.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException, ClassNotFoundException
  {
    Assignment3 theApp = new Assignment3();
    theApp.init(args);
    theApp.run();
    theApp.wrap();
  } // end main()
}  // end class

