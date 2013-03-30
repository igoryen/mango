package applications;

import business.*;
import useful.*;
import java.io.*;
import java.util.*;

public class TestInventoryItemImpl
{
  //===================static data ===============================================================
  // none

  //==================instance data ======================================================================
  /**
   * In which to store args from command line
   */
  public DataReaderImpl_1 dri;

  //==================constructors
  // none defined ( :. default constructor provided by compiler )
  //-------------------init()
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
//     private void hasht(String sb)
//     {
//     Hashtable ht = new Hashtable();
//     // == 1) create a hash map
//     Enumeration htk;
//     String htv;
//     // == 2) create the hashtable

  // ========== method printNumLoose() finish ==============================================================
  // ========================== METHOD run() ==============================================================
  /**
   * Controls the major part of the application (typically in a loop which reads input file(s). But,
   * in this example, the task is trivial.
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
      //+ "\n________________________________________________________________";
    //============================================================================================================

    System.out.println("\n========================================== Test "
      + ++counter + " started ==========================================");

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
          + "\nTesting method 5/12: getData(csv,dri,\"~\"). \nResult: csv (non-tokenized): " + csv + hr);

        nemo = new InventoryItemImpl();     // 67        
        System.out.println("\nAn object created."); 
              //returned = nemo.getData(csv, dri, "~"); // 61 
              //System.out.println("csv: "+csv);
        System.out.println(hr + "\nTesting method 12/12: update(sb). \nResult: err: " + nemo.update(csv)); 

        //=======================================================================================================
               
        //sb.setLength(0);                 // 68
        
        returned = nemo.formatDisplay(sb);  // 69
        
        //System.out.println(hr + "\nTesting 1 method 2/12: formatDisplay(sb). \nsb: " + sb + hr);

        //=======================================================================================================

        //nemo.update(csv);                // 70
        //System.out.println("\nobject after $csv was tokenized in update(): ");
              //System.out.println(hr + "\nTesting method 12/12: update(sb). \nResult: err: " + nemo.update(csv)); 

        //=======================================================================================================

        sb.setLength(0); //   68
        returned = nemo.formatDisplay(sb);
        System.out.println(hr + "\nTesting 2 method 2/12: formatDisplay(sb). \nResult: sb: " + sb + hr);
        //=======================================================================================================
        //nemo = new InventoryItemImpl();
        sb.setLength(0);
        //=======================================================================================================
        nemo.formatReportHeadings_1(sb);
        System.out.println(hr + "\nTesting method 4/12: formatReportHeadings_1(sb). \nResult: sb: " + sb);
        //=======================================================================================================
        sb.setLength(0);
        nemo.formatReportData_1(sb);
        System.out.println(hr + "\nTesting method 3/12: formatReportData_1(sb). \nResult: sb: " + sb);
        //=======================================================================================================
        System.out.println(hr + "\nTesting method 6/12: getPrimaryKey(). \nResult: PK: "
          + nemo.getPrimaryKey() + "." + hr);
        //=======================================================================================================
        System.out.println(hr + "\nTesting method 7/12: increaseStock(55). \nResult: Message: "
          + nemo.increaseStock(55) + "." + hr);

        
        //=======================================================================================================
        System.out.println(hr + "\nTesting method 8/12: placeReplenishmentOrder(). " + "\nResult: Message: "
          + nemo.placeReplenishmentOrder() + "." + hr);



       

        //=======================================================================================================
        System.out.println(hr + "\nTesting method 9/12: placeSalesOrder(44). \nResult: Message: "
          + nemo.placeSalesOrder(44) + "." + hr);
        //=======================================================================================================
        System.out.println(hr + "\nTesting method 10/12: receiveReplenishment(33). \nResult: Message: "
          + nemo.receiveReplenishment(33) + "." + hr);
        //=======================================================================================================
        System.out.println(hr + "\nTesting method 11/12: shipSalesOrder(22). \nResult: Message: "
          + nemo.shipSalesOrder(22) + "." + hr);
        //=======================================================================================================
        System.out.println(hr + "\nTesting method 1/12: decreaseStock(11). \nResult: Message: "
          + nemo.decreaseStock(11) + "." + hr);
        //=======================================================================================================

        System.out.println("\nAfter modifying values, the data are as follows:");
        //=======================================================================================================
        sb.setLength(0);
        nemo.formatReportHeadings_1(sb);
        System.out.println(sb);
        //=======================================================================================================
        sb.setLength(0);
        nemo.formatReportData_1(sb);
        System.out.println(sb);
        
        
        /*
         // ============================  get output
         sb.setLength(0);
         returned = nemo.formatDisplay(sb);
         System.out.println(hr + "\ntesting :"+sb);        
         //==========================================
         */

        /*
         System.out.println("---------- Test " + ctr +   " began ----------");
         System.out.println("---------- Test " + ++ctr + " ended ----------");
        
         */

        // ====================== get next set of data =================
        //csv.setLength(0);
        //returned = InventoryItemImpl.getData(csv, dri, "~");
        //System.out.println("\n$returned: " + returned);





        // ==================================================================
        // System.out.println(hr + "Testing method 2/12:\nformatDisplay(sb). \nsb: "+ nemo.formatDisplay(sb) + "." + hr);

        // ==================================================================
        //System.out.println(hr + "\nTesting method 5/12: getData(sb, dri, \"~\"). \nsb: "+ nemo.getData(sb, dri, "~") + "." + hr);


//        // ==================================================================
//        System.out.println(hr+"Testing method 12/12:\nupdate(sb). \nsb: "+ nemo.update(sb) + "." + hr);
//        // ====================================================================================

        // ======== deflating $sb (start) ======================================================================= 
        /*
         try
         {

         FileOutputStream fos = new FileOutputStream("iii_" + ++ctr + ".ser"); // 1. create a file 

         // :ObjectOutputStream 
         // It is a high level stream.      
         // It has method writeObject(Object x)
         // It serializes an Object to a file and gives it extension .ser  
         // and sends it to the output stream. 
         // Similarly, the ObjectInputStream class contains the following method for deserializing an object:
         ObjectOutputStream oos = new ObjectOutputStream(fos); // 2. create a serializer

         oos.writeObject(sb);  // serialize the instance            3. serialize the object
         oos.close();
         fos.close();
         }
         catch (Exception e)
         {
         System.out.println("Esception caught in run() " + e.getMessage() + e.getCause());
         }
         */
        // ======= deflating $sb (finish) =======================================================================

        //returned = nemo.persistData(sb);

        System.out.println("\n========================================== Test "
          + counter + " finished ==========================================");
        System.out.println("\n========================================== Test "
          + ++counter + " started ==========================================");
        //======================================================================================================
        csv.setLength(0); // 77        
        returned = InventoryItemImpl.getData(csv, dri, "~"); // 76 
      }
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
   * Performs one-time cleanup just before the application ends. <p>closes input and/output files or
   * database(s)</p> <p>closes network connections</p>
   */
  private int wrap()
  {

    int retval = 0;

    //===== hashtable map (start) === 
    Enumeration htik;
    StringBuffer _sb = new StringBuffer();
    String htiks = "";


    //===== hashtable map (finish) === 

    //int ctr = 0;

    //==========================================




    // =================================== inflate the hashtable ===========================================

    Hashtable hti = null; // 33 - ht inflated

    try
    {
      FileInputStream fis = new FileInputStream("Assign1.ser"); // 34
      ObjectInputStream ois = new ObjectInputStream(fis);
      hti = (Hashtable) ois.readObject(); // 35
      ois.close();
      fis.close();

    }
    catch (Exception e)
    {
      System.out.println("Caught exception in wrap(): " + e.getMessage());
    }



    System.out.println("\n --- ht has been deserialized/inflated...");

    // === print out the contents of the hashtable =================================


    htik = hti.keys(); // 36 
    _sb.append("--- the deserialized/inflated hashtable's contents\n");
    // 71 

    while (htik.hasMoreElements()) // 37
    {
      htiks = (String) htik.nextElement(); // 38
      _sb.append((String) htiks + "\t: " + hti.get(htiks)); // 39    

      _sb.append("\n");
    }

    printNumLoose();
    printNumClose();
    System.out.println("");

    System.out.println(_sb);

    // =================== work with deflated hashtable ==================

    System.out.println("");
    printNumClose();
    printNumLoose();


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
