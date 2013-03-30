package applications;

import business.*;
import useful.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class TestInventoryItemImpl
{
  //===================static data ===============================================================
  // none

  //==================instance data ======================================================================
  /**
   * In which to store args from command line
   */
  public DataReaderImpl_1 dri;
  //Hashtable<String, String>[] ht = (Hashtable<String, String>[]) new Hashtable<?, ?>[10]; // 78
  //Hashtable<String, String>[] ht = (Hashtable<String, String>[]) new Hashtable[10]; // 78
  //Hashtable<String, String>[] ht = new Hashtable[10]; // 78
  Hashtable[] ht = new Hashtable[10]; // 78

  //private Hashtable[] ht = new Hashtable[10]; // 78
  //private Hashtable[] ht; // 78
  //private Hashtable<String, String>[] ht =  new Hashtable[10]; // 78
  //ht[0] = new Hashtable<String, String>();
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

    // =================== 1) create a hash map =============================================
    // Hashtable <String, String> ht = new Hashtable<String, String>(); // = hash table
    //Hashtable <String, String> ht = new Hashtable<String, String>(); // ht = hash table
    Enumeration htk;
    Enumeration htik; // htik = hashtable (inflated) keys 
    String htiks; // htiks = hashtable (inflated) keys as strings
    //BigDecimal bd;
    int htai = 0; // hash-table's array's index
    // for tokenizing 
    String sep;
    String temp;
    StringTokenizer st;


    try // try 2 start
    {

      // ht[0] = new Hashtable<String, String>();
      //==================================================================================================
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
            + "\nTesting method 5/12: getData(csv,dri,\"~\"). \nResult: csv (non-tokenized): " + csv
            + hr);

          /*
           //=============== creating a hashtable start ============================================================

           sep = String.valueOf(csv.charAt(0)); // extract the first character, which is the delimiter tilda
           st = new StringTokenizer(csv.toString(), sep); // tokenize $csv 



           try
           {
           //========= fill a hashtable start ======================================================================
           ht[htai] = new Hashtable(); // 78
           System.out.println(" hash-table n has been created");

           temp = st.nextToken(); // 24
           if (!temp.equals("\t"))
           {
           ht[htai].
           put("Inventory ID",
           new String(temp)); // 26
           }

           temp = st.nextToken();
           if (!temp.equals("\t"))
           {
           ht[htai].put("Description", new String(temp));
           }

           temp = st.nextToken();
           if (!temp.equals("\t"))
           {
           ht[htai].put("Packaging", new String(temp));
           }

           temp = st.nextToken();
           if (!temp.equals("\t"))
           {
           //ht.put("Qty in stock", new Integer(quantityInStock));
           ht[htai].put("Qty in stock", new String(String.valueOf(temp))); // 27
           }

           temp = st.nextToken();
           if (!temp.equals("\t"))
           {
           //unitPrice = Double.parseDouble(temp);
           //ht.put("Unit price", new Double(unitPrice));
           //bd = new BigDecimal(unitPrice);
           ht[htai].put("Unit price", new String(String.valueOf(temp))); // 27

           //ht.put("Unit price", new String(String.valueOf(new BigDecimal(unitPrice)))); // 
           }

           temp = st.nextToken();
           if (!temp.equals("\t"))
           {
           //reorderPoint = Integer.parseInt(temp);
           ht[htai].put("Reorder point", new String(String.valueOf(temp)));
           }

           temp = st.nextToken();
           if (!temp.equals("\t"))
           {
           //reorderQuantity = Integer.parseInt(temp);
           ht[htai].put("Reorder qty", new String(String.valueOf(temp)));
           }

           temp = st.nextToken();
           if (!temp.equals("\t"))
           {
           //totalOrdered = Integer.parseInt(temp);
           ht[htai].put("Total ordered", new String(String.valueOf(temp)));
           }

           temp = st.nextToken();
           if (!temp.equals("\t"))
           {
           //totalSalesOrders = Integer.parseInt(temp);
           ht[htai].put("Tot.Sal.Orders", new String(String.valueOf(temp)));
           }

           String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //yyyy-M-dd_HH:mm:ss
           //lastUpdated = timeStamp;

           ht[htai].put("Last updated", new String(timeStamp));

           System.out.println(" hash-table n has been filled");

           //========= fill a hashtable finish ====================================================================


           }
           catch (Exception e)
           {
           System.out.println("Caught exception in wrap(): " + e.getMessage());
           System.out.println("... stack trace follows ...");
           e.printStackTrace(System.out);
           }
           //================creating a hashtable finish ===========================================================
           */








          nemo = new InventoryItemImpl();     // 67        
          System.out.println("\nAn object created.");


          



          //returned = nemo.getData(csv, dri, "~"); // 61 
          //System.out.println("csv: "+csv);
          System.out.println(hr + "\nTesting method 12/12: update(sb). \nResult: err: "
            + nemo.update(csv));

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
            ht[htai] = new Hashtable(); // 78
            System.out.println(" hash-table n has been created");
            String ok = nemo.getPrimaryKey(); // ok = object key

            ht[htai].put(ok, nemo);

            System.out.println(" hash-table n has been filled");

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
          System.out.println(hr + "\nTesting method 8/12: placeReplenishmentOrder(). "
            + "\nResult: Message: "
            + nemo.placeReplenishmentOrder() + "." + hr);





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
   * Performs one-time cleanup just before the application ends. <p>closes input and/output files or
   * database(s)</p> <p>closes network connections</p>
   */
  private int wrap()
  {

    int retval = 0;

    //===== hashtable map (start) === 
    Enumeration htik;
    StringBuffer _sb = new StringBuffer();
    StringBuffer fd = new StringBuffer(); //fd=formatData
    String htiks = "";
    InventoryItemImpl htiv;


    //===== hashtable map (finish) === 

    //int ctr = 0;

    //==========================================



    /*
     // === deflating the hashtable start ==============================================
     try
     {
     FileOutputStream fos = new FileOutputStream("../data/Assign1.ser"); // 29
     ObjectOutputStream oos = new ObjectOutputStream(fos); // 30 

     oos.writeObject(ht);  // 31 
     System.out.println("\n--- ht has been serialized/deflated.");
     oos.close(); // 32
     fos.close();

     }
     catch (Exception e)
     {
     System.out.println("Caught exception in wrap(): " + e.getMessage());
     System.out.println("... stack trace follows ...");
     e.printStackTrace(System.out);

     }

     // === deflating the hashtable finish ==============================================

     */

    // === deflating the hashtable start ==============================================
    try
    {
      FileOutputStream fos = new FileOutputStream("../data/Assign1.ser"); // 29
      ObjectOutputStream oos = new ObjectOutputStream(fos); // 30 

      oos.writeObject(ht);  // 31 
      System.out.println("\n--- ht has been serialized/deflated.");
      oos.close(); // 32
      fos.close();

    }
    catch (Exception e)
    {
      System.out.println("Caught exception in wrap(): " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);

    }

    // ============================ deflating the hashtable finish ==============================================


    // =============================== inflate/deserialize the hashtable start ===================================

    //Hashtable hti = null; // 33 - ht inflated
    Hashtable[] hti = null; // 33 - ht inflated

    try
    {
      //FileInputStream fis = new FileInputStream("Assign1.ser"); // 34
      FileInputStream fis = new FileInputStream("../data/Assign1.ser"); // 
      ObjectInputStream ois = new ObjectInputStream(fis);

      hti = (Hashtable[]) ois.readObject(); // 35

      ois.close();
      fis.close();

    }
    catch (Exception e)
    {
      System.out.println("Caught exception in wrap(): " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    }
    // =============================== inflate/deserialize the hashtable finish ===================================



    System.out.println("\n --- ht has been deserialized/inflated...");


    /*
     // === print out the contents of the inflated hashtable (start) =================================    

     _sb.append("--- the deserialized/inflated hashtable's contents\n");
    
     for (int i = 0; i < 8; i++)
     {
     htik = hti[i].keys(); // 36 
      
     // 71 

     while (htik.hasMoreElements()) // 37
     {
     htiks = (String) htik.nextElement(); // 38
     _sb.append((String) htiks + "\t: " + hti[i].get(htiks)); // 39    

     _sb.append("\n");
     }
     }


     printNumLoose();
     printNumClose();
     System.out.println("");

     System.out.println(_sb);

    

     System.out.println("");
     printNumClose();
     printNumLoose();
    
     // === print out the contents of the inflated hashtable (finish) =================================
     */


    // === print out the contents of the inflated hashtable (start) =================================    

    _sb.append("--- the deserialized/inflated hashtable's contents\n");

    for (int i = 0; i < 8; i++)
    {
      htik = hti[i].keys(); // 36 

      // 71 

      while (htik.hasMoreElements()) // 37
      {
    
        // ======== option 1 start ============================================================        
        htiks = (String) htik.nextElement(); // 38        
        _sb.append((String) htiks + "\t: " + hti[i].get(htiks)); // output BAD: returns address of item object        
        // ======== option 1 finish ============================================================
    
        
       /*
        //========= option 2 start ======================================================================       
        htiks = (String) htik.nextElement(); // 38   
        InventoryItemImpl nemo = new InventoryItemImpl(hti[i].get(htiks)); //ERROR: constructor can't be applied to given types        
        nemo.formatReportData(fd); // ERROR: can't find the symbol
        _sb.append(fd);
        //========= option 2 finish ======================================================================
        */
        
        /*
        //========= option 3 start ======================================================================
        htiks = (String) htik.nextElement(); // 38           
        InventoryItemImpl nemo = hti[i].get(htiks); // ERROR: incompatible types       
        nemo.formatReportData(fd);
        _sb.append(fd);
        //========= option 3 finish ======================================================================
        */

        /*
        //========= option 4 start ======================================================================
        InventoryItemImpl nemo = hti[i].get(htik); // ERROR: incompatible types
        nemo.formatReportData(fd);
        _sb.append(fd);
        //========= option 4 finish ======================================================================
        */


        _sb.append("\n");
      }
    }


    printNumLoose();
    printNumClose();
    System.out.println("");

    System.out.println(_sb);

    System.out.println("");
    printNumClose();
    printNumLoose();

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
