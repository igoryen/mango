
import java.text.SimpleDateFormat;
import java.util.*;

// package business
public class InventoryItemImpl implements InventoryItem, Testable
// implements multiple interfaces
{
  // object:InventoryItemImpl = company.stock.item.kind
  // what it will not do:
  // 1) tracking individual replenishments
  // 2) tracking sales orders 
  //      exclusion: tracks sales orders when they affect the quantity of stock

  //============STATIC DATA
  // The text used for each instance item (except for lastUpdated) 
  // to prompt the user for data input.  
  // For now, at least,  the prompt for each variable 
  // should be the name of the variable concerned 
  // and should be in the following sequence, 
  // which is common in the industry. 
  private static String[] prompts =
  {
    "inventoryId      : ",
    "description      : ",
    "pack             : ",
    "quantityInStock  : ",
    "unitPrice        : ",
    "reorderPoint     : ",
    "reorderQuantity  : ",
    "totalOrdered     : ",
    "totalSalesOrders : ",
  };
  //==========INSTANCE DATA
   
  private String inventoryId; // unique identifier for product/package combination, e.g. A1  
  private String description; // description of product e.g. "chocolate bar"  
  private String pack; // given product's packaging description   
  private int quantityInStock; // the amount currently in stock for this inventoryId, e.g. "1000"  
  private double unitPrice; // the selling price of one unit of this inventoryId, e.g. $"5.00"  
  private int reorderPoint; // quantity at which replenishment should be made, e.g. "100"  
  private int reorderQuantity; //the quantity normally ordered for replenishment e.g. "2000"  
  private int totalOrdered; // total of outstanding orders for replenishment, e.g. not delivered yet, pending e.g. "300"  
  private int totalSalesOrders; //total outstanding sales orders for this inventoryId e.g. "100"  
  private String lastUpdated; //date and time of previous update to this object ???

  //========= CONSTRUCTORS
  // Class InventoryItemImpl has only the following constructor:
  public InventoryItemImpl()
  {
       
    //It should initialize all non-primitive data items to sensible values.  
    //The primitive should all have their default values.
    inventoryId = "";
    description = "";
    pack = "";
    quantityInStock = 0;
    unitPrice = 0;
    reorderPoint = 0;
    reorderQuantity = 0;
    totalOrdered = 0;
    totalSalesOrders = 0;
       
  }

  //------------------------------------------------------
  //produce formatted output
  // formatDisplay()
  //------------------------------------------------------
  // methods to enable report of a more business-oriented nature
  // bfr() // business-format report
  //============== 11 METHODS=====================
  // ---------Method 1. decreaseStock()
  public String decreaseStock(int decAmount)
  {
    

    String outMsg = "";

    

    try
    {
      // case # : if you have NOT, then do nothing

      if (quantityInStock > 0) // case# you HAVE
      {
        if ((quantityInStock - decAmount) < 0) // case# : what you have is not enough
        {
          outMsg = "quantityInStock is less than decAmount";
        }
        else // case # : what you have is enough
        {
          quantityInStock -= decAmount;
          outMsg = "Decreased stock of " + inventoryId + " by " + decAmount;
        }
      }


      while ((quantityInStock + totalOrdered) < reorderPoint) // Next: check if the present amount together with what is being ordered is still less than the threshhold when you have to re-order
      {
        System.out.println("Despite ordering more, you are still below the reorder point. I will do replenishment."); // notify of the problem

        outMsg += "\n" + placeReplenishmentOrder(); // do replenishment, receive the replenishment result, and append to the outMsg
      }

      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); // Store the current date and time in lastUpdated.
      lastUpdated = timeStamp;

    }
    catch (Exception e)
    {
      System.out.println("Caught exception in decreaseStock() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    }
    
    return outMsg;
  }

 
  // --------- Method 2. formatDisplay() -------------------------------------------------------
  public int formatDisplay(StringBuffer sb)
  {
    
    int retval = 0;
    sb.append("\n" + "+++++++ formatDisplay() start ++++++++");
    
    sb.append("\n" + "inventoryId     : " + inventoryId);
    sb.append("\n" + "description     : " + description);
    sb.append("\n" + "pack            : " + pack);
    sb.append("\n" + "quantityInStock : " + quantityInStock);
    sb.append("\n" + "unitPrice       : " + unitPrice);
    sb.append("\n" + "reorderPoint    : " + reorderPoint);
    sb.append("\n" + "reorderQuantity : " + reorderQuantity);
    sb.append("\n" + "totalOrdered    : " + totalOrdered);
    sb.append("\n" + "totalSalesOrders: " + totalSalesOrders);
    sb.append("\n" + "+++++++ formatDisplay() finish ++++++++");

    return retval;
  }

  // -------- Method 3.  formatReportData_1()------------------------------------------------------------
  public int formatReportData_1(StringBuffer sb)
  {


   
    int retval = 0;

    // String EOL = new String(System.getProperty("line.separator"));
    // String tab = new String("\t");
    try
    {
      sb.append(inventoryId + "\t" + description + "\t" + pack + "\t" + reorderPoint + "\t" + reorderQuantity + "\t" + unitPrice);
      sb.append(lastUpdated + "\t" + totalOrdered + "\t" + quantityInStock + "\t" + totalSalesOrders);

    }
    catch (Exception e)
    {
      System.out.println("Caught exception in formatReportData_1 : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    }
    return retval;
  }

  // -------- Method 4. formatReportHeadings_1()
  public static int formatReportHeadings_1(StringBuffer sb)
  {    
    int retval = 0;
    
    try
    {
      String lineOfDashes = "";
      for (int i = 0; i < 60; i++)
      {
        lineOfDashes += "-";
      }
      
      sb.append("\n"+ lineOfDashes + "\n");
      sb.append("--------------------------------------------------------------\n");
      sb.append("Inv Id   Description    Package          Ro-Pt Ro-Qty U-Price\n");
      sb.append("                        Last-Upd        RepOrd In Stk  SalOrd\n");
      sb.append("--------------------------------------------------------------\n");
      sb.append("\n"+ lineOfDashes);
      
    }
    catch (Exception e)
    {
      System.out.println("Caught exception in formatReportHeadings_1() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    }
    return retval;
  }

  // -------- Method 5. getData() -----------------------------------------
  // obtain input data from user/data file and store in sb:StringBuffer
  public static int getData(StringBuffer sb, DataReaderImpl_1 br, String separator) // sb<csv, br<dri, separator<"~"
    //throws IOException
  {
    
    int err = 0; // error code
    try
    {
      String temp = "";
      sb.append(separator); // The first character stored in the StringBuffer must be the separator character passed as parameter 3. This method uses the library method getData(...)  in package useful to do this work. ???

      for (int k = 0; k < prompts.length; k++) // for as long as you have available prompts
      {
        System.err.print("\n"+prompts[k]); // 1) Display prompt[k]: output prompt - leave cursor on same line        
        System.err.flush(); // ensure that the data appears    
        temp = br.readLine(); // 2) get user input for prompt[k] using br (<dri)

              if (temp == null) // 3) case 1: user input was "null" , e.g. reached the end of file
              {
                err = -1;
                break;
              }
              // or
              if (temp.indexOf(separator) >= 0) // 4) check 2: if data entered contains a separator char "~"
              {
                err = -2;
                break;
              }
              // or
              if (temp.equals("")) // check 3: if input has empty line
              {
                temp = "\t"; // assign "tab" to indicate empty field
              }

        sb.append(temp + separator);    // ... then each data item followed by a separator character. It stores (in CSV format)  the data items input in the StringBuffer passed as parameter 1. -- data item is OK so add it to sb:StringBuffer
      }
    } // end of try{}
    catch (Exception e)
    {
      System.out.println("Caught exception in getData() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    } // end of catch{}

    return err; // returns an int to TestInventoryItemImpl.return
  }

  // -------- Method 6. getPrimaryKey() ---------------------------------------------
  public String getPrimaryKey()
  {
    String pk = "";

    if (!inventoryId.contains(" ")) // The primary key data should NOT include any spaces. check: does "inventoryId" contain spaces?
    {
      pk = inventoryId;
    }
    return pk; // Returns the value of inventoryId (to be used as the key for a Hashtable or database primary key or similar purpose).
  }

  // --------Method 7. increaseStock()------------------------------------------------------
  public String increaseStock(int incAmount)
  {
    String outMsg = "";
    try
    {
      // check 1
      if (incAmount <= 0) // the user is trying to increase stock by nothing, or by negative amount
      {
        outMsg = "Bad input: incAmount is <= 0";
      }

      // check 2
      if (quantityInStock >= 0) // the user is increasing by a good number
      {
        quantityInStock += incAmount; // Increases the quantityInStock by the amount passed as parameter 1. 
        outMsg = "Increased stock of " + inventoryId + " by " + incAmount;
        // (substitutes data values for [incAmount] and [inventoryId]). ??

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        lastUpdated = timeStamp; // Stores the current date and time in lastUpdated
      }
    }
    catch (Exception e)
    {
      System.out.println("Caught exception in increaseStock() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    } // end of catch{}

    return outMsg;
  }

  // ----------- Method 8. placeReplenishmentOrder()------------------------------------------
  private String placeReplenishmentOrder()
  {
    String outMsg = "";

    try
    {
      // Adds the reorderQuantity to the totalOrdered and returns the outMsg “Replenishment [reorderQuantity] ordered for [inventoryId].”  
      totalOrdered += reorderQuantity;
      // (substitutes data values for [reorderQuantity] and [inventoryId]).  
      // Stores the current date and time in lastUpdated.  
      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
      lastUpdated = timeStamp;
      // (Note: to test this method, at first you must make it public  , 
      // but you should then change it to private  , 
      // and recompile your code before you submit your work
      // This method should definitely NOT be callable from code in other classes
      outMsg = "Replenishment " + reorderQuantity + " ordered for " + inventoryId;
    }
    catch (Exception e)
    {
      System.out.println("Caught exception in placeReplenishmentOrder() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    } // end of catch{}
    return outMsg;
  }
  // ------------------ Method 9. placeSalesOrder() -----------------------------------

  public String placeSalesOrder(int qtyOrdered)
  {
    String outMsg = "";
    try
    {
      // check 1
      if (qtyOrdered > 0) // user's quantity ordered is a number greater than zero
      {
        totalSalesOrders += qtyOrdered; // Adds the qtyOrdered to totalSalesOrders// and returns the outMsg “Sales Order of [qtyOrdered] for [inventoryId].”  
        // (substitutes data values for [qtyOdered] and [inventoryId]).  
        // Stores the current date and time in lastUpdated. 
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        lastUpdated = timeStamp;
        outMsg = "Sales Order of " + qtyOrdered + " for " + inventoryId;
      }
    }
    catch (Exception e)
    {
      System.out.println("Caught exception in placeSalesOrder() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    } // end of catch{}
    return outMsg;
  }

  // ---------- Method 10. receiveReplenishment()----------------------------------------------------
  public String receiveReplenishment(int qtyReceived)
  {
    String outMsg = "";

    try
    {
      // check
      if (qtyReceived > 0) // in case you got something 
      {
        totalOrdered -= qtyReceived; // Reduces the totalOrdered  by the qtyReceived  

        String x = increaseStock(qtyReceived); // then call increaseStock(qtyReceived).

        // Stores the current date and time in lastUpdated.
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        lastUpdated = timeStamp;

        // Return the outMsg “Replenishment received of [qtyReceived] for [inventoryId].” 
        // followed by the outMsgs returned by the call to the increaseStock() method.  
        // (substitutes data values for [qtyReceived] and [inventoryId]).  
        outMsg = "Replenishment received of " + qtyReceived + " for " + inventoryId + ". " + x;
      }

    }
    catch (Exception e)
    {
      System.out.println("Caught exception in receiveReplenishment() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    } // end of catch{}


    return outMsg;

  }

  // --------- Method 11. shipSalesOrder() ---------------------------------------------------------
  
  public String shipSalesOrder(int qtyShipped)
  {
    
    String outMsg = "";

    try
    {
      // check
      if (qtyShipped > 0) // in case you're going to ship SOMEthing
      {
        totalSalesOrders -= qtyShipped; // Reduces totalSalesOrders by the qtyShipped
        String x = decreaseStock(qtyShipped); // ... then calls decreaseStock(qtyShipped).
        outMsg = "Goods " + qtyShipped + " items shipped for " + inventoryId + x;
        // Returns the outMsg “Goods [qtyShipped] items shipped for [inventoryId].” 
        // followed by the outMsg(s) returned by  the call to the decreaseStock() method. /
        // (substitutes data values for [qtyShipped] and [inventoryId]).  
        // Stores the current date and time in lastUpdated.
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        lastUpdated = timeStamp;
      }

    }
    catch (Exception e)
    {
      System.out.println("Caught exception in shipSalesOrder() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    } // end of catch{}

    return outMsg;
  }

  // ----------------- Method 12. update() -------------------------------------------------------
  
  public int update(StringBuffer sb) // sb < csv
  {
    int err = 0; // to indicate status (normal = 0)
    String sep = "";    
    StringTokenizer tk; // to extract data
    String temp = ""; // to store extracted data
    //int tempInt = 0;
    //double tempDbl = 0;

    try
    {
      // The sb:StringBuffer passed as parameter 1 is in CSV format and was produced by getData() above.  
      sep = String.valueOf(sb.charAt(0)); // separator value used by CSV format data     
              
      tk = new StringTokenizer(sb.toString(), sep); //Creates a StringTokenizer [to extract data items] using the first character of the StringBuffer as the separator.       


      // series of checks
      // Check 1: is it anything but a tab?
      temp = tk.nextToken(); //Extracts each token and uses the data to update the instance data items, converting the data as necessary.
      if (!temp.equals("\t"))
      {
        inventoryId = new String(temp); // then don't convert, just assign
      }

      temp = tk.nextToken(); //Extracts each token and uses the data to update the instance data items, converting the data as necessary.
      if (!temp.equals("\t"))
      {
        description = new String(temp);  // then don't convert, just assign
      }

      temp = tk.nextToken(); //Extracts each token and uses the data to update the instance data items, converting the data as necessary.
      if (!temp.equals("\t"))
      {
        pack = new String(temp); // then don't convert, just assign
      }

      temp = tk.nextToken(); //Extracts each token and uses the data to update the instance data items, converting the data as necessary.
      if (!temp.equals("\t"))
      {
        quantityInStock = Integer.parseInt(temp); // pass value of temp to String, create new object:String, parse it into int and assign
      }

      temp = tk.nextToken(); //Extracts each token and uses the data to update the instance data items, converting the data as necessary.
      if (!temp.equals("\t"))
      {
        unitPrice = Double.parseDouble(temp);  // then don't convert, just assign
      }

      temp = tk.nextToken(); //Extracts each token and uses the data to update the instance data items, converting the data as necessary.
      if (!temp.equals("\t"))
      {
        reorderPoint = Integer.parseInt(temp); // then don't convert, just assign
      }

      temp = tk.nextToken(); //Extracts each token and uses the data to update the instance data items, converting the data as necessary.
      if (!temp.equals("\t"))
      {
        reorderQuantity = Integer.parseInt(temp);  // then don't convert, just assign
      }

      temp = tk.nextToken(); //Extracts each token and uses the data to update the instance data items, converting the data as necessary.
      if (!temp.equals("\t"))
      {
        totalOrdered = Integer.parseInt(temp);  // then don't convert, just assign
      }

      temp = tk.nextToken(); //Extracts each token and uses the data to update the instance data items, converting the data as necessary.
      if (!temp.equals("\t"))
      {
        totalSalesOrders = Integer.parseInt(temp);  // then don't convert, just assign
      }



      // Stores the current date and time in lastUpdated.
      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
      lastUpdated = timeStamp;

      // Note that several of the methods above return text outMsgs 
      // which your test application should display.  
      // The outMsgs should appear exactly as specified (correct spelling, case  and punctuation with a single space between each word)  
      // except that data values replace the variable names shown above in []. 
      // No [] should appear in the outMsgs you output.

    }
    catch (Exception e)
    {
      System.out.println("Caught exception in update() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    } // end of catch{}



    return err;

  }
// ================ END 12 METHODS    

  public static String[] persistData(Hashtable<Integer, InventoryItemImpl> table, String keyValue)
  {
    String[] retval = new String[3];
    InventoryItemImpl item;

    try
    {

      int key = Integer.parseInt(keyValue);
      item = table.get(key); // 

      if (item != null) // in case # item HAS something
      {
        retval = new String[3]; // create an array
        retval[0] = "1";
        retval[1] = "";

        for (int i = 0; i < prompts.length; i++)
        {
          if (prompts[i].indexOf(":") > 0)
          {
            prompts[i] = prompts[i].substring(0, prompts[i].indexOf(":")).trim();
          }
          retval[1] += "~" + prompts[i];
        }
        retval[1] += "~lastUpdated~";
        retval[2] = "~" + item.inventoryId + "~" + item.description + "~" + item.pack + "~"
          + item.quantityInStock + "~" + item.unitPrice + "~" + item.reorderPoint + "~"
          + item.reorderQuantity + "~" + item.totalOrdered + "~" + item.totalSalesOrders + "~"
          + item.lastUpdated + "~";
      }
      else // case # : object DOES NOT exist, item has NOT anything
      {
        retval[0] = "0";
        retval[1] = "<p>0 records selected for : " + keyValue + "</p>";
      }
    }
    catch (Exception e)
    {
      System.out.println("Esception caught in abc() " + e.getMessage() + e.getCause());
    }
    return retval;
  }
} // end class InventoryItemImpl