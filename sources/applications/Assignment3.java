// Assignment3.java   ...... - Test class for Employee (with Hashtable and Serialization)
package applications;

import java.io.*;
import java.util.*;
import business.*;
import java.text.SimpleDateFormat;
import useful.*;

/**
 * Purpose is to test class Employee ... which involves. <ul> <li>making one or more objects of the class</li> <li>calling each constructor at least
 * once <p>examining the data in the object concerned</p> </li> <li>calling each method at least once <p>with various data as parameter(s)</p>
 * <p>examining the return value</p> <p>examining the data in the object concerned</p> </li> </ul>
 *
 * <p>This program creates a Hashtable and adds each Employee object to it as it is made. Before the end of each run the Hashtable is serialized into
 * file (Assign3.ser).</p>
 * <p>
 * The private fields:
 * <ol>
 * <li>private DataReaderImpl_1 dri;  // reader based on standard input</li>
 * <li>private Hashtable ht = new Hashtable(); </li>
 * <li>private StringBuffer reportBag = new StringBuffer(); // to accumulate the data from each instance of class Employee</li>
 * </ol>
 * </p>
 *
 * <p> Run instructions...
* <pre>
 * java applications.Assignment3 [&lt; ../data/Employee.txt] 
 * </pre>
 * @author Igor Entaltsev
 * @version 1.0
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
  private StringBuffer reportBag = new StringBuffer();

  //=========finish declaring instance data================================================================
  
  //=================START METHODS DECLARATION===============================================================
  // alphabetically by method name
  
  //=============start METHOD init() ===================================================================
  /**
   * Perform initialization tasks when the program starts. 
   * <ul> 
   * <li>process the command-line options (from param list)</li> <li>open file(s) as
   * necessary</li> <li>open network-connection(s) as necessary</li> </ul>
   *
   * @param args the command line arguments
   */
  public void init(String[] args) throws IOException, ClassNotFoundException
  {
    // open a suitable input stream on standard input
    dri = new DataReaderImpl_1();
    int retval = 0;
    retval = formatHeadings(reportBag);
  } // end init()
  //=============finish METHOD init() ===================================================================
  
  

  // ============start METHOD formatHeadings() =========================================================
  /**
   * This method creates headings for the report with the current date.
   * @param sb
   * @return retval (the error status)
   */
  public static int formatHeadings(StringBuffer sb)
  {
    int retval = 0;
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //yyyy-MM-dd_HHmmss
    try
    {
      //sb.append("\n" + "--------------------- start formatHeadings() ---------------------");
      sb.append(String.format("\n" + "%-22s%15s%27s", "Seneca College Payslip", "Period 01", timeStamp + "\n"));
      sb.append("\n" + "---- =======================================--------- =========="); // 39= 10- 10=
      //sb.append("\n" + "--------------------- finish formatHeadings() ---------------------");
    }
    catch (Exception e)
    {
      System.out.println("******************************************************************");
      System.out.println("Caught exception in formatReportHeadings_1() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
      System.out.println("******************************************************************");
    }
    return retval;
  }
  // ===================== METHOD formatHeadings() (bottom) =====================================================
  // ========== start method printNumClose()  ==================================================================
  /**
   * The method is used to print a row of 65 numbers without spaces used as a sort of ruler.
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
   * The method is used to print a row of numbers with spaces equal to 10 numbers used as a sort of ruler.
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
  // ========== finish METHOD printNumLoose()  ==============================================================


  //=============== start METHOD run() ==================================================================
  /**
   * Does major part of processing in a while loop. 
   * <ul> 
   * <li>calls static method(s) of class Employee</li> 
   * <li>instantiates classes :Address, :Phone (by calling their constructor)</li> 
   * <li>calls instance method(s) of the classes 
   * <p>displays return value for examination</p> 
   * <p>displays contents of class variables or objects affected</p> 
   * </li> 
   * </ul>
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
        System.out.println("sb after formatDisplay (empty report form): ((("+ sb+")))"); // empty fields
        //============= finish displaying empty form ==================================


        //============= filling the form (partially) ========================================================================
        nemo.update(csv);
        sb.setLength(0);

        //System.out.println("sb after update and formatDisplay: ((("+sb+")))");
        //============= finish filling the form ========================================================================


        //================= testing payroll methods ====================================================================

        //================= testing payroll method calculateSalary() =====================================
        //System.out.println("Testing method calculateSalary()...");
        nemo.calculateSalary();
        //=================================================================================================

        //================= testing payroll method calculateWages() =====================================
        //System.out.println("\nTesting method calculateWages(40)...");
        nemo.calculateWages(40);
        //=================================================================================================
        
        
        
        //================= testing payroll method calculatePension() =====================================
        //System.out.println("\nTesting method calculatePension()...");
        nemo.calculatePension();
        //=================================================================================================
        
        


        //==================start testing payroll method calculateTax() ================================
        //System.out.println("\nTesting method calculateTax()...");
        nemo.calculateTax();
        //==================finish testing payroll method calculateTax() ================================
        
        

        //==================start testing payroll method calculateUnion() ================================
        //System.out.println("\nTesting method calculateUnion()...");
        nemo.calculateUnion();
        //================== finish testing payroll method calculateUnion() ================================
        
        

        //================== start testing method formatDisplay() =============================
        returned = nemo.formatDisplay(sb);
        System.out.println("\nAfter update() and formatDisplay(), sb: (((" + sb + ")))");
        //================== finish testing method formatDisplay() =============================
        
        

        //===================== start testing method formatPaySlip() ======================
        sb.setLength(0); // empty the $sb before filling it again
        returned = nemo.formatPaySlip(sb); // filling $sb again
        //System.out.println("\nAfter formatSlip(), sb: (((\n" + sb + ")))");
        //===================== finish testing method formatPaySlip() ======================
        
        

        // ================== start adding the data from this.formatPaySlip() into $reportBag ============
        //sb.setLength(0); 
        returned = nemo.formatPaySlip(reportBag);
        //System.out.println("\nAfter formatSlip(), reportBag: (((\n"+reportBag+")))"); 
        // ================== finish adding the data from this.formatPaySlip() into $reportBag =============
        
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
   * Does any cleanup tasks just before the program ends. 
   * <ul> 
   * <li>closes file(s) used</li> // standard input does not need closing !!! (but you could)
   * <li>Serializes the Hashtable of Employee objects </li>
   * <li>Prints out the data from every object:Employee</li>
   * </ul>
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
    //================= finish hashtable map ==================================================


    // ========== start deflating (serializing) the hashtable  =========================================================================================================
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

    //=================== finish deflating (serializing) the hashtable  ================================================================================================



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

    /*
     //=================== printing out the contents of the inflated hashtable ====================================
     System.out.println("Printint out the contents of the inflated hashtable");
     printNumLoose();
     printNumClose();
     System.out.println(lineOfDashes);

     formatHeadings(sb);
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
     */
     
    // ========================== start printing out the contents of $reportBag ===================================
    System.out.println("\n");
    printNumLoose();
    printNumClose();
    System.out.println(lineOfDashes);
    //reportBag.append("\n");
    System.out.println(reportBag);
    System.out.println(lineOfDashes);
    printNumClose();
    printNumLoose();
    // ========================== finish printing out the contents of $reportBag ===================================

  } // end method
  // ====================== finish METHOD wrap() ===================================================================

  //========================FINISH METHODS DECLARATION =======================================================
  
  //=======================start METHOD main() ==============================================================
  /**
   * This is the first method called when the program starts.<ol>
   * <li>Makes an object of its own class </li>
   * <li>Calls instance methods to perform the application tasks. </li>   
   * </ol>
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException, ClassNotFoundException
  {
    Assignment3 theApp = new Assignment3();
    theApp.init(args);
    theApp.run();
    theApp.wrap();
  } 
    //=======================finish METHOD main() ==============================================================

}  // end class

