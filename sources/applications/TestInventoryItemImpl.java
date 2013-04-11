package applications;

import business.*;
import useful.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

@SuppressWarnings("unchecked")
/**
 * This is a test application class. It is used to: <ul> <li>instantiate class
 * InventoryItemImpl</li> <li>test its methods </li> <li>save its instances in a hashtable</li>
 * <li>Serialize and deserialize the hashtable</li> <li>produce a report using the data from the
 * instances of class InventoryItemImpl</li>
 *
 * </ul>
 *
 * @author Igor Entaltsev
 */
public class TestInventoryItemImpl
{
  //===================static data ===============================================================
  // none

  //==================instance data ======================================================================
  /**
   * In which to store args from command line
   */
  public DataReaderImpl_1 dri;
  private Hashtable ht = new Hashtable(); // ht = hashtable. used for serialization
  private Hashtable hti = new Hashtable(); // hti= hashtable inflated. Used for deserialization.
  private InventoryItemImpl _nemo; //to be used at deserialization  

  //==================constructors ====================================
  // none defined ( :. default constructor provided by compiler )
  //=================METHOD init() =====================================
  /**
   * Performs one-time initialization at start of application typically the following...
   * <p>processes the command-line arguments.</p> <p>opens input and/output files or database(s)</p>
   * <p>opens network connections</p>
   *
   * @param args arguments from command-line
   */
  private String init(String[] args)
  {
    String temp = "";
    dri = new DataReaderImpl_1(); // 56
    return temp;
  } // end of init()

  // ========== method printNumClose() start ==================================================================
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
  // ========== method printNumClose() finish ==============================================================
  // ========== method printNumLoose() start  ==============================================================

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
  // ========== method printNumLoose() finish ==============================================================
  // ========================== METHOD run() ==============================================================

  /**
   * This method tests the methods of every instance of class InventoryItemImpl, and creates a
   * hashtable of the instances.
   */
  private void run()
  {
    //===========================================================================================================
    int counter = 0;
    int ctr = 0;
    StringBuffer csv = new StringBuffer(128); // 57
    InventoryItemImpl nemo; // 58
    int returned; // 59
    StringBuffer sb = new StringBuffer(128); // 60
    String hr = "";

    // =================== 1) create a hash map =============================================

    Enumeration htk;
    Enumeration htik; // htik = hashtable (inflated) keys

    String htiks; // htiks = hashtable (inflated) keys as strings

    int htai = 0; // hash-table's array's index
    // for tokenizing 
    String sep;
    String temp;
    StringTokenizer st;


    String line = new String();
    for (int x = 0; x < 25; x++)
    {
      line += "=";
    }

    try // try 2 start
    {
      System.out.println("\n" + line + " Test " + ++counter + " started " + line);

      //===========================================================================================================

      returned = InventoryItemImpl.getData(csv, dri, "~"); // 61 

      if (returned == -3)              // 62
      {
        nemo = new InventoryItemImpl(); // 63   
        nemo.formatDisplay(sb);        // 64
        System.out.println(sb);
      } //===========================================================================================================
      else // 65
      {
        while (returned == 0) // 66
        {
          System.out.println(hr
            + "\nTesting method 5/12: getData(csv,dri,\"~\"). \nResult: csv (non-tokenized): " + csv
            + hr);
          nemo = new InventoryItemImpl();     // 67        
          //System.out.println("\nAn object created.");

          System.out.println(hr + "\nTesting method 12/12: update(sb). \nResult: err: "
            + nemo.update(csv));

          //=======================================================================================================
          //sb.setLength(0);                 // 68
          returned = nemo.formatDisplay(sb);  // 69
          //=======================================================================================================

          sb.setLength(0); //   68
          returned = nemo.formatDisplay(sb);
          System.out.println(hr + "\nTesting 2 method 2/12: formatDisplay(sb). \nResult: sb: " + sb
            + hr);
          //=======================================================================================================
          //nemo = new InventoryItemImpl();
          sb.setLength(0);
          //=======================================================================================================
          nemo.formatReportHeadings_1(sb);
          System.out.println(hr
            + "\nTesting method 4/12: formatReportHeadings_1(sb). \nResult: sb: "
            + sb);
          //=======================================================================================================
          sb.setLength(0);
          nemo.formatReportData_1(sb);
          System.out.println(hr + "\nTesting method 3/12: formatReportData_1(sb). \nResult: sb: "
            + sb);
          //=======================================================================================================
          System.out.println(hr + "\nTesting method 6/12: getPrimaryKey(). \nResult: PK: "
            + nemo.getPrimaryKey() + "." + hr);
          //=============== creating a hashtable start ============================================================
          try
          {
            //========= fill a hashtable start ======================================================================
            ht.put(nemo.getPrimaryKey(), nemo);
            //========= fill a hashtable finish ====================================================================
          }
          catch (Exception e)
          {
            System.out.println("Caught exception in wrap(): " + e.getMessage());
            System.out.println("... stack trace follows ...");
            e.printStackTrace(System.out);
          }
          //================creating a hashtable finish ===========================================================

          //=======================================================================================================
          System.out.println(hr + "\nTesting method 7/12: increaseStock(55). \nResult: Message: "
            + nemo.increaseStock(55) + "." + hr);
          //=======================================================================================================
          //System.out.println(hr + "\nTesting method 8/12: placeReplenishmentOrder(). "
          //  + "\nResult: Message: "
          //  + nemo.placeReplenishmentOrder() + "." + hr);
          //=======================================================================================================
          System.out.println(hr + "\nTesting method 9/12: placeSalesOrder(44). \nResult: Message: "
            + nemo.placeSalesOrder(44) + "." + hr);
          //=======================================================================================================
          System.out.println(hr
            + "\nTesting method 10/12: receiveReplenishment(33). \nResult: Message: "
            + nemo.receiveReplenishment(33) + "." + hr);
          //=======================================================================================================
          System.out.println(hr + "\nTesting method 11/12: shipSalesOrder(22). \nResult: Message: "
            + nemo.shipSalesOrder(22) + "." + hr);
          //=======================================================================================================
          System.out.println(hr + "\nTesting method 1/12: decreaseStock(11). \nResult: Message: "
            + nemo.decreaseStock(11) + "." + hr);
          //=======================================================================================================
          System.out.println("\nAfter testing the methods, the data for <" + nemo.getPrimaryKey()
            + "> are as follows:");
          //=======================================================================================================
          sb.setLength(0);
          nemo.formatReportHeadings_1(sb);
          System.out.println(sb);
          //=======================================================================================================
          sb.setLength(0);
          nemo.formatReportData_1(sb);
          System.out.println(sb);

          // ======= deflating $sb (finish) =======================================================================

          System.out.println("\n" + line + " Test " + counter + " finished " + line);
          System.out.println("\n" + line + " Test " + ++counter + " started " + line);
          //======================================================================================================

          csv.setLength(0); // 77        
          returned = InventoryItemImpl.getData(csv, dri, "~"); // 76 

          htai++; // the end of loop, so increment htai
        } // while loop finish
      } // else finish

    } // try 2 finish
    catch (Exception e)
    {
      System.out.println("Caught exception in wrap(): " + e.getMessage());
    }
  }  // end of run()

  //===================== METHOD usage() =============================================================
  /**
   * Displays a help message for how to use this application
   */
  private void usage()
  {
    System.err.println("USAGE IS: " + "java TestApp # any args ignored");
  } // end of usage()
  //===================== METHOD usage() end =============================================================

  //===================== METHOD wrap() ======================================================================
  /**
   * This method serializes and deserializes the hashtable created in run() and outputs the contents
   * of the hashtable.
   *  
   */
  private int wrap()
  {
    int retval = 0;
    //================= hashtable map (start) ===================================================
    Enumeration htik;
    Object htiko; // hash table inflated. key as Object
    StringBuffer _sb = new StringBuffer();
    StringBuffer fd = new StringBuffer(); //fd=formatData
    String htiks = "";
    InventoryItemImpl htiv;
    //================= hashtable map (finish) ==================================================

    // === deflating/serializing the hashtable start ==============================================
    try
    {
      FileOutputStream fos = new FileOutputStream("../data/Assign1.ser"); // 29
      ObjectOutputStream oos = new ObjectOutputStream(fos); // 30 

      oos.writeObject(ht);  // 31 
      //System.out.println("\n--- ht has been serialized/deflated.");
      oos.close(); // 32
      fos.close();
    }
    catch (Exception e)
    {
      System.out.println("Caught exception in wrap(): " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    }

    // =============== deflating/serializing the hashtable finish ==============================================

    // ================ inflate/deserialize the hashtable start ===================================

    //Hashtable[] hti = null; // 33 - ht inflated
    try
    {
      FileInputStream fis = new FileInputStream("../data/Assign1.ser"); // 
      ObjectInputStream ois = new ObjectInputStream(fis);

      hti = (Hashtable) ois.readObject(); // 35

      ois.close();
      fis.close();

      //System.out.println("hti size is : " + hti.size());
    }
    catch (Exception e)
    {
      System.out.println("Caught exception in wrap(): " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    }
    // =============================== inflate/deserialize the hashtable finish ===================================
    //System.out.println("\n --- ht has been deserialized/inflated...");
    // ==================== print out the contents of the inflated hashtable (start) =============================    

    //_sb.append("--- the deserialized/inflated hashtable's contents\n");

    //System.out.println("hti.length: "+hti.length);
    System.out.println("");
    printNumLoose();
    printNumClose();
    //System.out.println("");

    InventoryItemImpl.formatReportHeadings_1(_sb);
    System.out.println(_sb);

    for (htik = hti.keys(); htik.hasMoreElements();)
    {
      _sb.setLength(0);
      htiko = htik.nextElement(); //         
      //_sb.append("key: " + htiko + "\n");
      _nemo = (InventoryItemImpl) hti.get(htiko);
      _nemo.formatReportData_1(_sb);
      System.out.println(_sb);
      //_sb.append(_sb);
      //_sb.append("\n");
    }
    //System.out.println(_sb);

    System.out.println("");
    printNumClose();
    printNumLoose();
    String equals = new String();
    for (int x = 0; x < 65; x++)
    {
      equals += "=";
    }
    System.out.println(equals);

    // === print out the contents of the inflated hashtable (finish) =================================

    dri.close();

    return retval;
  }  // end of wrap()
  // ===================== METHOD wrap() finish=============================================================

  //===================== METHOD main() ===========================================================
  /**
   * This is the first method called. It makes an object or its own class then calls methods init(),
   * run() and wrap() in that sequence. Thus it clearly and completely controls the application (but
   * the major work is delegated to be done in the init(), run() and wrap() methods).
   */
  public static void main(String[] args)
    throws IOException
  {

    TestInventoryItemImpl theApp = new TestInventoryItemImpl(); // make object of own class

    theApp.init(args);                        // then call its methods
    theApp.run();
    theApp.wrap();


  } // end of main()
} // end of class
