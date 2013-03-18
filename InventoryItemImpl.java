
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
  
  // unique identifier for product/package combination, e.g. A1 
  private String inventoryId;
  
  // description of product e.g. "chocolate bar"
  private String description;
  
  // given product's packaging description 
  private String pack;
  
  // the amount currently in stock for this inventoryId, e.g. "1000"
  private int quantityInStock;
  
  // the selling price of one unit of this inventoryId, e.g. $"5.00"
  private double unitPrice;
  
  // quantity at which replenishment should be made, e.g. "100"
  private int reorderPoint;
  
  //the quantity normally ordered for replenishment e.g. "2000"
  private int reorderQuantity;
  
  // total of outstanding orders for replenishment, e.g. not delivered yet, pending e.g. "300"
  private int totalOrdered;
  
  //total outstanding sales orders for this inventoryId e.g. "100"
  private int totalSalesOrders;
  
  //date and time of previous update to this object ???
  private String lastUpdated;

  //========= CONSTRUCTORS
  // Class InventoryItemImpl has only the following constructor:
  
  public InventoryItemImpl()
  {
       //System.out.print("(i10)");
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
        //System.out.print("(i20)");

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
        //System.out.print("(i30)");

    String message = "";
    double a = 0;    
    
        //System.out.print("(i40)");
    // Decreases the quantityInStock by the amount passed as parameter 1.  
    quantityInStock -= decAmount;
    
        //System.out.print("(i50)");

    
    //Substitutes data values for [decAmount] and [inventoryId]. ????

    // If the calculation of “quantityInStock plus totalOrdered less totalSalesOrders” 
    // is now less than value of the reorderPoint
            //System.out.print("(i60)");
        
    a = quantityInStock + (totalOrdered - totalSalesOrders);
    
        //System.out.print("(i70)");
    
    if (a < reorderPoint)
    {
          //System.out.print("(i80)");
      // then  place an order for replenishment (call placeReplenishmentOrder())  with repeats as necessary. 
      placeReplenishmentOrder();
      
          //System.out.print("(i90)");
      
      // Store the current date and time in lastUpdated.
      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
      
          //System.out.print("(i100)");
      
      lastUpdated = timeStamp;

        // Note that the strings that may be returned from placeReplenishmentOrder 
        //should appear appended as part of the message to be returned by this method.  
        // Those messages should appear after the “Decreased stock...” text 
        // and there should be a single space separating each of the strings concerned.
    }
    
        //System.out.print("(i110)");
    //Returns the message “Decreased stock of [inventoryId] by [decAmount].”.
    message = "Decreased stock of " + inventoryId + " by " + decAmount;
    
        //System.out.print("(i120)");
    
    return message;
  }

    // --------- Method 2. formatDisplay() -------------------------------------------------------

  public int formatDisplay(StringBuffer sb)
  {
    int retval = 0;
    sb.append("\n"+"+++++++ formatDisplay() ++++++++");
    // Append the data from one object to the StringBuffer passed as parameter 1.  
    // This method will be used primarily for testing/checking purposes 
    // so that it is desirable that it be in a format that is easy for YOU to read, 
    // with every instance item displayed.
    sb.append("\n"+"inventoryId     : "+ inventoryId);
    sb.append("\n"+"description     : "+ description);
    sb.append("\n"+"pack            : "+ pack);
    sb.append("\n"+"quantityInStock : "+ quantityInStock);
    sb.append("\n"+"unitPrice       : "+ unitPrice);
    sb.append("\n"+"reorderPoint    : "+ reorderPoint);
    sb.append("\n"+"reorderQuantity : "+ reorderQuantity);
    sb.append("\n"+"totalOrdered    : "+ totalOrdered);
    sb.append("\n"+"totalSalesOrders: "+ totalSalesOrders);
    sb.append("\n"+"+++++++ formatDisplay() ++++++++");
    return retval;
  }

  // -------- Method 3.  formatReportData_1()------------------------------------------------------------
  
  public int formatReportData_1(StringBuffer sb)
  {
    
    // Append  the data from one object, formatted for Report 1, to the StringBuffer passed as parameter 1.  
    // The format of Report 1 is described in an accompanying document.
    int retval = 0;
    
    // String EOL = new String(System.getProperty("line.separator"));
    // String tab = new String("\t");
    

    
    sb.append(inventoryId + "\t");
    sb.append(description + "\t");
    sb.append(pack +"\n"+lastUpdated);
    sb.append(reorderPoint + "\n" + totalOrdered);
    sb.append(reorderQuantity + "\n" + quantityInStock);
    sb.append(unitPrice + "\n" + totalSalesOrders);
    sb.append("\n");
    
    return retval;    
  }

  // -------- Method 4. formatReportHeadings_1()
  
  public static int formatReportHeadings_1(StringBuffer sb)
  {
    int retval = 0;
    // Append  the headings, formatted for Report 1, to the StringBuffer passed as parameter 1.  
    // The format of Report 1 is described on an accompanying document.
    
    sb.append("\n");
    sb.append("--------------------------------------------------------------");
    sb.append("Inv Id   Description    Package          Ro-Pt Ro-Qty U-Price");
    sb.append("                        Last-Upd        RepOrd In Stk  SalOrd");
    sb.append("--------------------------------------------------------------");
    sb.append("\n");
    
    return retval;
  }

  // -------- Method 5. getData() -----------------------------------------
  
  public static int getData(StringBuffer sb, DataReaderImpl_1 br, String separator)
  {
    String temp = "";
    int err = 0;
    // The first character stored in the StringBuffer must be the separator character passed as parameter 3
    sb.append(separator);
        
    // This method uses the library method getData(...)  in package useful to do this work. ???
    
    for (int k=0; k<prompts.length; k++)
    {
      // Display prompts: output prompts - leave cursor on same line
      System.err.print(prompts[k]);
      // ensure that the data appears
      System.err.flush();
      //...and get data for each instance data item using the DataReaderImpl_1 object passed as parameter 2. 
      
      temp = br.readLine();
      
      // check 1: if ... reached the end of file
      if (temp == null)
      {        
        err = -1;
        break;
      }
      // check 2: if data entered contains a separator char
      if (temp.indexOf(separator) >=0)
      {
        err = -2;
        break;
      }
      // check 3: if input has empty line
      if (temp.equals(""))
      {
        // assign "tab" to indicate empty field
        temp = "\t";
      }
      // ... then each data item followed by a separator character.   
      // It stores (in CSV format)  the data items input in the StringBuffer passed as parameter 1.  
      // -- data item is OK so add it to sb:StringBuffer
      sb.append(temp + separator);
      
    }
    
    return err;
  }

  // -------- Method 6. getPrimaryKey()
  public String getPrimaryKey()
  {
    String pk = "";   
      
    // The primary key data should NOT include any spaces.
    // check: does "inventoryId" contain spaces?
    if (!inventoryId.contains(" "))
    {
      pk = inventoryId;      
    }
    // Returns the value of inventoryId 
    // (to be used as the key for a Hashtable or database primary key or similar purpose).
    return pk;
  }

  // --------Method 7. increaseStock()
  public String increaseStock(int incAmount)
  {
    String message = "";
    // Increases the quantityInStock by the amount passed as parameter 1.   
    quantityInStock += incAmount;   
    
    // Returns the message “Increased stock of [inventoryId] by [incAmount].”  
    // (substitutes data values for [incAmount] and [inventoryId]). 
    // Stores the current date and time in lastUpdated
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    lastUpdated = timeStamp;
    message = "Increased stock of "+inventoryId + " by " +incAmount;
    return message;
  }

  // ----------- Method 8. placeReplenishmentOrder()
  
  private String placeReplenishmentOrder()
  {
    String message = "";
    // Adds the reorderQuantity to the totalOrdered and returns the message “Replenishment [reorderQuantity] ordered for [inventoryId].”  
    totalOrdered += reorderQuantity;
    // (substitutes data values for [reorderQuantity] and [inventoryId]).  
    // Stores the current date and time in lastUpdated.  
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    lastUpdated = timeStamp;
    // (Note: to test this method, at first you must make it public  , 
    // but you should then change it to private  , 
    // and recompile your code before you submit your work
    // This method should definitely NOT be callable from code in other classes
    message = "Replenishment "+ reorderQuantity + " ordered for " + inventoryId;
    return message;
  }
  // Method 9. placeSalesOrder()

  public String placeSalesOrder(int qtyOrdered)
  {
    String message = "";
    // Adds the qtyOrdered to totalSalesOrders 
    totalSalesOrders += qtyOrdered;
    // and returns the message “Sales Order of [qtyOrdered] for [inventoryId].”  
    // (substitutes data values for [qtyOdered] and [inventoryId]).  
    // Stores the current date and time in lastUpdated. 
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    lastUpdated = timeStamp;
    
    message = "Sales Order of " + qtyOrdered + " for "+ inventoryId;
    return message;
  }

  // ---------- Method 10. receiveReplenishment()----------------------------------------------------
  
  public String receiveReplenishment(int qtyReceived)
  {
    String message = "";
    // Reduces the totalOrdered  by the qtyReceived  
    totalOrdered -= qtyReceived;
    // then call increaseStock(qtyReceived).
    String x = increaseStock(qtyReceived);
    
    // Stores the current date and time in lastUpdated.
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    lastUpdated = timeStamp;
    
    // Return the message “Replenishment received of [qtyReceived] for [inventoryId].” 
    // followed by the messages returned by the call to the increaseStock() method.  
    // (substitutes data values for [qtyReceived] and [inventoryId]).  
    message = "Replenishment received of " + qtyReceived+" for "+ inventoryId + ". " + x;
    return message;
    
  }

  // --------- Method 11. shipSalesOrder() ---------------------------------------------------------
  
  public String shipSalesOrder(int qtyShipped)
  {
    String message = "";
    
    // Reduces totalSalesOrders by the qtyShipped
    totalSalesOrders -= qtyShipped;
    
    // ... then calls decreaseStock(qtyShipped).  
    String x = decreaseStock(qtyShipped);
    // Returns the message “Goods [qtyShipped] items shipped for [inventoryId].” 
    // followed by the message(s) returned by  the call to the decreaseStock() method. /
    // (substitutes data values for [qtyShipped] and [inventoryId]).  
    // Stores the current date and time in lastUpdated.
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    lastUpdated = timeStamp;
        
    message = "Goods "+qtyShipped + " items shipped for " +inventoryId + x;
    return message;
  }

  // ----------------- Method 12. update()
  public int update(StringBuffer sb)
  {
    // to indicate status (normal = 0)
    int err = 0;
    // separator value used by CSV format data
    String sep = "";
    // to extract data
    StringTokenizer tk;
    String temp = "";
    //int tempInt = 0;
    //double tempDbl = 0;
    
    
    // The sb:StringBuffer passed as parameter 1 is in CSV format and was produced by getData() above.  
    
    //Creates a StringTokenizer [to extract data items] 
    // using the first character of the StringBuffer as the separator. 
    tk = new StringTokenizer(sb.toString(),sep);
    //Extracts each token and uses the data to update the instance data items, 
    // converting the data as necessary. 
    temp = tk.nextToken();
    
    // series of checks
    // Check 1: is it anything but a tab?
    if (!temp.equals("\t"))
    {
      // then don't convert, just assign
      inventoryId = new String(temp);
    }
    
    if (!temp.equals("\t"))
    {
      // then don't convert, just assign
      description = new String(temp);
    }
    
    if (!temp.equals("\t"))
    {
      // then don't convert, just assign
      pack = new String(temp);
    }
    
    if (!temp.equals("\t"))
    {
      // pass value of temp to String, create new object:String, parse it into int and assign
      quantityInStock = Integer.parseInt(temp);
    }
    
    if (!temp.equals("\t"))
    {
      // then don't convert, just assign
      inventoryId = new String(temp);
    }
    
    if (!temp.equals("\t"))
    {
      // then don't convert, just assign
      unitPrice = Double.parseDouble(temp);
    }
    
    if (!temp.equals("\t"))
    {
      // then don't convert, just assign
      reorderPoint = Integer.parseInt(temp);
    }
    
    if (!temp.equals("\t"))
    {
      // then don't convert, just assign
      reorderQuantity = Integer.parseInt(temp);
    }
    
    if (!temp.equals("\t"))
    {
      // then don't convert, just assign
      totalOrdered = Integer.parseInt(temp);
    }
    
    if (!temp.equals("\t"))
    {
      // then don't convert, just assign
      totalSalesOrders = Integer.parseInt(temp);
    }
    
    
    
    // Stores the current date and time in lastUpdated.
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    lastUpdated = timeStamp;
    
    // Note that several of the methods above return text messages 
    // which your test application should display.  
    // The messages should appear exactly as specified (correct spelling, case  and punctuation with a single space between each word)  
    // except that data values replace the variable names shown above in []. 
    // No [] should appear in the messages you output.
    return err;

  } 
  
// ================ END 12 METHODS    
  
  
} // end class InventoryItemImpl