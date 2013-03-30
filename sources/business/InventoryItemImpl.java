package business;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import useful.*;
import java.math.BigDecimal;

// igor
public class InventoryItemImpl implements InventoryItem, Testable, Serializable
{

  //============STATIC DATA ==========================================================
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
  //==========INSTANCE DATA ====================================================
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

  //========= CONSTRUCTORS ==============================================
  public InventoryItemImpl()
  {
    inventoryId = "(N/A)";
    description = "(N/A)";
    pack = "(N/A)";
    quantityInStock = 0;
    unitPrice = 0.00;
    reorderPoint = 0;
    reorderQuantity = 0;
    totalOrdered = 0;
    totalSalesOrders = 0;

  }
//private String a ="";
  //============== 11 METHODS==============================================================
  // =======================METHOD 1. decreaseStock() ============================================
  public String decreaseStock(int decAmount)
  {
    String outMsg = "";
    try
    {
      if (quantityInStock > 0) // 72
      {
        if ((quantityInStock - decAmount) < 0) // 73
        {
          outMsg = "You can't take away "+decAmount+", you only have "+quantityInStock +".";
        }
        else // 74 
        {
          quantityInStock -= decAmount;
          outMsg = "Decreased stock of " + inventoryId + " by " + decAmount+".";
        }
      }

      while ((quantityInStock + totalOrdered) < reorderPoint) // 75
      {
        System.out.println("Uh-oh, you are below the reorder point. I will do replenishment.");
        outMsg += "\n" + placeReplenishmentOrder()+".";
      }

      String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //
      lastUpdated = timeStamp;

    }
    catch (Exception e)
    {
      System.out.println("Caught exception in decreaseStock() : " + e.getMessage()+".");
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    }

    return outMsg;
  }

  // ========= METHOD 2. formatDisplay() ======================================================
  
  public int formatDisplay(StringBuffer sb)
  {

    int retval = 0;
    sb.append("\n" + "+++++++ formatDisplay() start ++++++++");
    sb.append(String.format("\n"+"%-9s%-15s%-16s%6s%7s%8s", "Inv Id", "Description", "Package", "Ro-Pt", "Ro-Qty", "U-Price"));
    sb.append(String.format("\n"+"%32s%14s%7s%8s","Last-Upd","RepOrd", "In Stk","SalOrd"));
    sb.append(String.format("\n"+"%-9s%-15s%-16s%6d%7d%8.2f", inventoryId, description, pack, reorderPoint, reorderQuantity, unitPrice));
    sb.append(String.format("\n"+"%34s%12d%7d%5d",lastUpdated,totalOrdered, quantityInStock,totalSalesOrders));
       

    //sb.append("\n" + "inventoryId: "+inventoryId+", description: "+description+", pack: "+pack+", quantityInStock: "+quantityInStock);
    //sb.append("\n"+"unitPrice: "+unitPrice+", reorderPoint: " + reorderPoint+", reorderQuantity: " + reorderQuantity+ ", totalOrdered: " + totalOrdered+", totalSalesOrders: " + totalSalesOrders+".");
    sb.append("\n" + "+++++++ formatDisplay() finish ++++++++");

    return retval;
  }

  // ======-- METHOD 3.  formatReportData_1()============================================================
  public int formatReportData_1(StringBuffer sb)
  {
    int retval = 0;

    try
    {
      sb.append(String.format("\n"+"%-9s%-15s%-16s%6d%7d%8.2f", inventoryId, description, pack, reorderPoint, reorderQuantity, unitPrice));
      sb.append(String.format("\n"+"%34s%12d%7d%5d",lastUpdated,totalOrdered, quantityInStock,totalSalesOrders));
      

    }
    catch (Exception e)
    {
      System.out.println("Caught exception in formatReportData_1 : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    }
    return retval;
  }

  // ====================   METHOD 4. formatReportHeadings_1() ===============================================
  
  public static int formatReportHeadings_1(StringBuffer sb)
  {
    int retval = 0;

    try
    {
      String lineOfDashes = "";
      for (int i = 0; i < 62; i++)
      {
        lineOfDashes += "-";
      }

      sb.append("\n" + lineOfDashes);      
      sb.append(String.format("\n"+"%-9s%-15s%-16s%6s%7s%8s", "Inv Id", "Description", "Package", "Ro-Pt", "Ro-Qty", "U-Price"));
      sb.append(String.format("\n"+"%32s%14s%7s%8s","Last-Upd","RepOrd", "In Stk","SalOrd"));
      sb.append("\n" + lineOfDashes);

    }
    catch (Exception e)
    {
      System.out.println("Caught exception in formatReportHeadings_1() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    }
    return retval;
  }

  // ============================ METHOD 5. getData() =======================================
  
  public static int getData(StringBuffer sb, DataReaderImpl_1 br, String separator) // #1
  //throws IOException
  {

    int err = 0; // #2

    try
    {
      String temp = "";
      sb.append(separator);

      for (int k = 0; k < prompts.length; k++) // #3
      {
        System.err.print(prompts[k]); // #4
        System.err.flush(); // 5

        temp = br.readLine(); // 6
        // 7
        if (temp == null) // 8
        {
          err = -1;
          break;
        }
        if (temp.indexOf(separator) >= 0) // 9
        {
          err = -2;
          break;
        }
        if (temp.equals("")) // 10
        {
          temp = "\t";
        }

        sb.append(temp + separator); // 11
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

  // ==================== METHOD 6. getPrimaryKey() ==========================================
  public String getPrimaryKey()
  {
    String pk = "";

    if (!inventoryId.contains(" "))
    {
      pk = inventoryId;
    }
    return pk;
  }

  // ==================== METHOD 7. increaseStock() ==============================================
  public String increaseStock(int incAmount)
  {
    String outMsg = "";
    try
    {
      if (incAmount <= 0)
      {
        outMsg = "Bad input: incAmount is <= 0.";
      }
      if (quantityInStock >= 0)
      {
        quantityInStock += incAmount; // 12 
        outMsg = "Increased stock of " + inventoryId + " by " + incAmount+".";
        

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //yyyy-MM-dd_HHmmss
        lastUpdated = timeStamp; // 13 
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

  // ========================  METHOD 8. placeReplenishmentOrder() =========================================
  //private
  public
    String placeReplenishmentOrder()
  {
    String outMsg = "";

    try
    {
      
      totalOrdered += reorderQuantity; // 14
      String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //yyyy-MM-dd_HHmmss
      lastUpdated = timeStamp;
      outMsg = "Replenishment " + reorderQuantity + " ordered for " + inventoryId+".";
    }
    catch (Exception e)
    {
      System.out.println("Caught exception in placeReplenishmentOrder() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    } // end of catch{}
    return outMsg;
  }
  // ===================== METHOD 9. placeSalesOrder() ===============================================

  public String placeSalesOrder(int qtyOrdered)
  {
    String outMsg = "";
    try
    {
      // check 1
      if (qtyOrdered > 0) // 15 
      {
        totalSalesOrders += qtyOrdered;

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //yyyy-MM-dd_HHmmss
        lastUpdated = timeStamp;
        outMsg = "Sales Order of " + qtyOrdered + " for " + inventoryId+".";
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

  // ================== METHOD 10. receiveReplenishment() ==============================================
  public String receiveReplenishment(int qtyReceived)
  {
    String outMsg = "";

    try
    {
      if (qtyReceived > 0) // 16
      {
        totalOrdered -= qtyReceived; // 17

        String x = increaseStock(qtyReceived); // 18

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //yyyy-MM-dd_HHmmss
        lastUpdated = timeStamp;

        outMsg = "Replenishment received of " + qtyReceived + " for " + inventoryId + ". " + x+"."; // 19
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

  // ============ METHOD 11. shipSalesOrder() ================================================================
  public String shipSalesOrder(int qtyShipped)
  {

    String outMsg = "";

    try
    {
      if (qtyShipped > 0)
      {
        totalSalesOrders -= qtyShipped;
        String x = decreaseStock(qtyShipped);
        outMsg = "Goods " + qtyShipped + " items shipped for " + inventoryId +". " + x+".";
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //yyyy-MM-dd_HHmmss
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

  // =================== METHOD 12. update() ============================================================
  
  public int update(StringBuffer sb) // 20 
  {
    int err = 0; // 21
    String sep = "";
    StringTokenizer st;
    String temp = "";
    StringBuffer _sb = new StringBuffer();

    // =================== 1) create a hash map =============================================
    
    // ====================== filling the hashtable with rows ================================

    try // try 1
    {

      sep = String.valueOf(sb.charAt(0)); // 22   

      st = new StringTokenizer(sb.toString(), sep); // 23 

      temp = st.nextToken(); // 24
      if (!temp.equals("\t"))
      {
        inventoryId = new String(temp); // 25
        //ht.put("Inventory ID", new String(inventoryId)); // 26
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        description = new String(temp);
        //ht.put("Description", new String(description));
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        pack = new String(temp);
        //ht.put("Packaging", new String(pack));
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        quantityInStock = Integer.parseInt(temp);
        //ht.put("Qty in stock", new Integer(quantityInStock));
        //ht.put("Qty in stock", new String(String.valueOf(quantityInStock))); // 27
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        unitPrice = Double.parseDouble(temp);
        //ht.put("Unit price", new Double(unitPrice));
        //bd = new BigDecimal(unitPrice);
        //ht.put("Unit price", new String(String.valueOf(unitPrice))); // 27
        
        //ht.put("Unit price", new String(String.valueOf(new BigDecimal(unitPrice)))); // 
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        reorderPoint = Integer.parseInt(temp);
        //ht.put("Reorder point", new String(String.valueOf(reorderPoint)));
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        reorderQuantity = Integer.parseInt(temp);
        //ht.put("Reorder qty", new String(String.valueOf(reorderQuantity)));
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        totalOrdered = Integer.parseInt(temp);
        //ht.put("Total ordered", new String(String.valueOf(totalOrdered)));
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        totalSalesOrders = Integer.parseInt(temp);
        //ht.put("Tot.Sal.Orders", new String(String.valueOf(totalSalesOrders)));
      }

      String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //yyyy-M-dd_HH:mm:ss
      lastUpdated = timeStamp;

      //ht.put("Last updated", new String(lastUpdated));
      
      //=== print out the contents of the hashtable =======================================
/*
      htk = ht.keys();
      System.out.println("\nHere come the hashtable contens");
      while (htk.hasMoreElements())
      {
        htks = (String) htk.nextElement();
        System.out.println(htks + "\t: " + ht.get(htks));
      }
      */
      // === deflating the hashtable start ==============================================
      
      // === deflating the hashtable finish ==============================================

      
      // === work with the deflated hashtable (start) ================================================
      
      // === work with the deflated hashtable (finish) ================================================


    } // finish fill ht with rows
    catch (Exception e)
    {
      System.out.println("Caught exception in update() : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    } // end of catch{}

    return err;
  }
// ================ END 12 METHODS 
  /*
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
  */
  
} // end class InventoryItemImpl