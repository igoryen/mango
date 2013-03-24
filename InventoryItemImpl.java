
import java.text.SimpleDateFormat;
import java.util.*;

// package business
public class InventoryItemImpl implements InventoryItem, Testable

{

  //============STATIC DATA
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
   
  private String inventoryId; 
  private String description; 
  private String pack; 
  private int quantityInStock; 
  private double unitPrice; 
  private int reorderPoint; 
  private int reorderQuantity; 
  private int totalOrdered; 
  private int totalSalesOrders; 
  private String lastUpdated; 

  //========= CONSTRUCTORS

  public InventoryItemImpl()
  {
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

  //============== 11 METHODS=====================
  // ---------Method 1. decreaseStock()
  public String decreaseStock(int decAmount)
  {
    String outMsg = "";
    try
    {
      if (quantityInStock > 0) 
      {
        if ((quantityInStock - decAmount) < 0) 
        {
          outMsg = "quantityInStock is less than decAmount";
        }
        else 
        {
          quantityInStock -= decAmount;
          outMsg = "Decreased stock of " + inventoryId + " by " + decAmount;
        }
      }


      while ((quantityInStock + totalOrdered) < reorderPoint) 
      {
        System.out.println("Despite ordering more, you are still below the reorder point. I will do replenishment."); 

        outMsg += "\n" + placeReplenishmentOrder(); 
      }

      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
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

  public static int getData(StringBuffer sb, DataReaderImpl_1 br, String separator) // sb<csv, br<dri, separator<"~"
    //throws IOException
  {
    
    int err = 0; // error code
    try
    {
      String temp = "";
      sb.append(separator); 

      for (int k = 0; k < prompts.length; k++) 
      {
        System.err.print("\n"+prompts[k]);      
        System.err.flush();     
        temp = br.readLine(); 

              if (temp == null) 
              {
                err = -1;
                break;
              }              
              if (temp.indexOf(separator) >= 0)
              {
                err = -2;
                break;
              }
              if (temp.equals("")) 
              {
                temp = "\t"; 
              }

        sb.append(temp + separator);    
      }
    } // end of try{}
    catch (Exception e)
    {
      System.out.println("Caught exception in getData() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    } // end of catch{}

    return err; 
  }

  // -------- Method 6. getPrimaryKey() ---------------------------------------------
  public String getPrimaryKey()
  {
    String pk = "";

    if (!inventoryId.contains(" ")) 
    {
      pk = inventoryId;
    }
    return pk; 
  }

  // --------Method 7. increaseStock()------------------------------------------------------
  public String increaseStock(int incAmount)
  {
    String outMsg = "";
    try
    {
      if (incAmount <= 0) 
      {
        outMsg = "Bad input: incAmount is <= 0";
      }
      if (quantityInStock >= 0) 
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
        totalSalesOrders += qtyOrdered; 
        
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
      if (qtyReceived > 0) 
      {
        totalOrdered -= qtyReceived; 

        String x = increaseStock(qtyReceived);

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        lastUpdated = timeStamp;

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
      if (qtyShipped > 0) 
      {
        totalSalesOrders -= qtyShipped; 
        String x = decreaseStock(qtyShipped); 
        outMsg = "Goods " + qtyShipped + " items shipped for " + inventoryId + x;
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
    int err = 0; 
    String sep = "";    
    StringTokenizer tk; 
    String temp = ""; 
    //int tempInt = 0;
    //double tempDbl = 0;

    try
    {

      sep = String.valueOf(sb.charAt(0)); // separator value used by CSV format data     
              
      tk = new StringTokenizer(sb.toString(), sep); //Creates a StringTokenizer [to extract data items] using the first character of the StringBuffer as the separator.       


      temp = tk.nextToken(); 
      if (!temp.equals("\t"))
      {
        inventoryId = new String(temp); 
      }

      temp = tk.nextToken(); 
      if (!temp.equals("\t"))
      {
        description = new String(temp);  
      }

      temp = tk.nextToken(); 
      if (!temp.equals("\t"))
      {
        pack = new String(temp);
      }

      temp = tk.nextToken(); 
      if (!temp.equals("\t"))
      {
        quantityInStock = Integer.parseInt(temp); 
      }

      temp = tk.nextToken(); 
      if (!temp.equals("\t"))
      {
        unitPrice = Double.parseDouble(temp);  
      }

      temp = tk.nextToken(); 
      if (!temp.equals("\t"))
      {
        reorderPoint = Integer.parseInt(temp); 
      }

      temp = tk.nextToken(); 
      if (!temp.equals("\t"))
      {
        reorderQuantity = Integer.parseInt(temp);  
      }

      temp = tk.nextToken(); 
      if (!temp.equals("\t"))
      {
        totalOrdered = Integer.parseInt(temp);  
      }

      temp = tk.nextToken(); 
      if (!temp.equals("\t"))
      {
        totalSalesOrders = Integer.parseInt(temp);  
      }



      // Stores the current date and time in lastUpdated.
      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
      lastUpdated = timeStamp;

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