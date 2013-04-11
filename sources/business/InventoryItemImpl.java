package business;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import useful.*;

/**
 * An object of this class - InventoryItemImpl - represents one kind of item that a company keeps in
 * stock that are ready for sale, for raw materials or partially manufactured goods. * The classes
 * methods make it possible to: <ul> <li>obtain data from an external file </li> <li>increase and
 * decrease the stock</li> <li>place sales orders</li> <li>place and receive replenishment
 * orders</li> <li>monitor ship sales orders</li> <li>produce a report of current stock
 * condition</li> </ul>
 *
 * @author Igor Entaltsev
 */
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

  /**
   * Decreases the quantityInStock by the amount passed as parameter 1. Returns the message
   * “Decreased stock of [inventoryId] by [decAmount].”.
   *
   * @param decAmount
   * @return outMsg
   */
  public String decreaseStock(int decAmount)
  {
    String outMsg = "";
    try
    {
      if (quantityInStock > 0) // 72
      {
        if ((quantityInStock - decAmount) < 0) // 73
        {
          outMsg = "You can't take away " + decAmount + ", you only have " + quantityInStock + ".";
        }
        else // 74 
        {
          quantityInStock -= decAmount;
          outMsg = "Decreased stock of " + inventoryId + " by " + decAmount + ".";
        }
      }

      while ((quantityInStock + totalOrdered) < reorderPoint) // 75
      {
        System.out.println("Uh-oh, you are below the reorder point. I will do replenishment.");
        outMsg += "\n" + placeReplenishmentOrder() + ".";
      }

      String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //
      lastUpdated = timeStamp;

    }
    catch (Exception e)
    {
      System.out.println("Caught exception in decreaseStock() : " + e.getMessage() + ".");
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
    }

    return outMsg;
  }

  // ========= METHOD 2. formatDisplay() ======================================================
  /**
   * Appends the data from one object to the StringBuffer passed as parameter 1. <p>This method is
   * used primarily for testing/checking purposes </p>
   *
   * @param sb
   * @return retval
   */
  public int formatDisplay(StringBuffer sb)
  {

    int retval = 0;
    sb.append("\n" + "--------------------- formatDisplay() start ---------------------");
    sb.append(String.format("\n" + "%-9s%-15s%-16s%6s%7s%8s", "Inv Id", "Description", "Package", "Ro-Pt", "Ro-Qty", "U-Price"));
    sb.append(String.format("\n" + "%32s%14s%7s%8s", "Last-Upd", "RepOrd", "In Stk", "SalOrd"));
    sb.append("\n" + "-----------------------------------------------------------------");
    sb.append(String.format("\n" + "%-9s%-15s%-16s%6d%7d%8.2f", inventoryId, description, pack, reorderPoint, reorderQuantity, unitPrice));
    sb.append(String.format("\n" + "%34s%12d%7d%5d", lastUpdated, totalOrdered, quantityInStock, totalSalesOrders));
    sb.append("\n" + "--------------------- formatDisplay() finish ---------------------");

    return retval;
  }

  // ======-- METHOD 3.  formatReportData_1()============================================================
  /**
   * Appends the data from one object, formatted for Report 1, to the StringBuffer passed as
   * parameter 1.
   *
   * @param sb
   * @return retval
   */
  public int formatReportData_1(StringBuffer sb)
  {
    int retval = 0;

    try
    {
      sb.append(String.format("\n" + "%-9s%-15s%-16s%6d%7d%8.2f", inventoryId, description, pack, reorderPoint, reorderQuantity, unitPrice));
      sb.append(String.format("\n" + "%34s%12d%7d%5d", lastUpdated, totalOrdered, quantityInStock, totalSalesOrders));
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
  /**
   * Appends the headings, formatted for Report 1, to the StringBuffer passed as parameter 1.
   *
   * @param sb
   * @return retval
   */
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
      sb.append(String.format("\n" + "%-9s%-15s%-16s%6s%7s%8s", "Inv Id", "Description", "Package", "Ro-Pt", "Ro-Qty", "U-Price"));
      sb.append(String.format("\n" + "%32s%14s%7s%8s", "Last-Upd", "RepOrd", "In Stk", "SalOrd"));
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
  /**
   * Displays prompts and gets data for each instance data item using the DataReaderImpl_1 object
   * passed as parameter 2. It stores (in CSV format) the data items input in the StringBuffer
   * passed as parameter 1.
   *
   * @param sb
   * @param br
   * @param separator
   * @return err
   */
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
  /**
   * Returns the value of inventoryId (to be used as the key for a Hashtable or database primary key
   * or similar purpose).
   *
   * @return pk
   */
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
  /**
   * Increases the quantityInStock by the amount passed as parameter 1. Returns the message
   * “Increased stock of [inventoryId] by [incAmount].”
   *
   * @param incAmount
   * @return outMag
   */
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
        outMsg = "Increased stock of " + inventoryId + " by " + incAmount + ".";

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
  /**
   * Adds the reorderQuantity to the totalOrdered and returns the message “Replenishment
   * [reorderQuantity] ordered for [inventoryId].” Stores the current date and time in lastUpdated.
   *
   * @return outMsg
   */
  //private
  private String placeReplenishmentOrder()
  {
    String outMsg = "";

    try
    {
      totalOrdered += reorderQuantity; // 14
      String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //yyyy-MM-dd_HHmmss
      lastUpdated = timeStamp;
      outMsg = "Replenishment " + reorderQuantity + " ordered for " + inventoryId + ".";
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

  /**
   * Adds the qtyOrdered to totalSalesOrders and returns the message “Sales Order of [qtyOrdered]
   * for [inventoryId].” Stores the current date and time in lastUpdated.
   *
   * @param qtyOrdered
   * @return outMsg
   */
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
        outMsg = "Sales Order of " + qtyOrdered + " for " + inventoryId + ".";
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
  /**
   * Reduces the totalOrdered by the qtyReceived then call increaseStock(qtyReceived). Returns the
   * message “Replenishment received of [qtyReceived] for [inventoryId].” followed by the messages
   * returned by the call to the increaseStock() method. Stores the current date and time in
   * lastUpdated.
   *
   * @param qtyReceived
   * @return outMsg
   */
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

        outMsg = "Replenishment received of " + qtyReceived + " for " + inventoryId + ". " + x + "."; // 19
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
  /**
   * Reduces totalSalesOrders by the qtyShipped then calls decreaseStock(qtyShipped). Returns the
   * message “Goods [qtyShipped] items shipped for [inventoryId].” followed by the message(s)
   * returned by the call to the decreaseStock() method. Stores the current date and time in
   * lastUpdated.
   *
   * @param qtyShipped
   * @return outMsg:String
   */
  public String shipSalesOrder(int qtyShipped)
  {
    String outMsg = "";

    try
    {
      if (qtyShipped > 0)
      {
        totalSalesOrders -= qtyShipped;
        String x = decreaseStock(qtyShipped);
        outMsg = "Goods " + qtyShipped + " items shipped for " + inventoryId + ". " + x + ".";
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
  /**
   * The StringBuffer passed as parameter 1 is in CSV format and was produced by getData() above.
   * Creates a StringTokenizer using the first character of the StringBuffer as the separator.
   * Extracts each token and uses the data to update the instance data items, converting the data as
   * necessary. Stores the current date and time in lastUpdated.
   *
   * @param sb
   * @return err:int
   */
  public int update(StringBuffer sb) // 20 
  {
    int err = 0; // 21
    String sep = "";
    StringTokenizer st;
    String temp = "";

    try // try 1
    {
      sep = String.valueOf(sb.charAt(0)); // 22   

      st = new StringTokenizer(sb.toString(), sep); // 23 

      temp = st.nextToken(); // 24
      if (!temp.equals("\t"))
      {
        inventoryId = new String(temp); // 25
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        description = new String(temp);
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        pack = new String(temp);
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        quantityInStock = Integer.parseInt(temp);
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        unitPrice = Double.parseDouble(temp);
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        reorderPoint = Integer.parseInt(temp);
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        reorderQuantity = Integer.parseInt(temp);
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        totalOrdered = Integer.parseInt(temp);
      }

      temp = st.nextToken();
      if (!temp.equals("\t"))
      {
        totalSalesOrders = Integer.parseInt(temp);
      }

      String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //yyyy-M-dd_HH:mm:ss
      lastUpdated = timeStamp;
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
} // end class InventoryItemImpl