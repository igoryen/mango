// uncoment the  line below when in proper environment
// package business; ////(in package business in file InventoryItem.java)

interface InventoryItem
{
//An object of class InventoryItemImpl represents one kind of item that a company keeps in stock 
  //that are ready for sale, for raw materials or partially manufactured goods.  
  // Class InventoryItemImpl implements this interface 
  //as well as the previously provided interface Testable.  
  // InventoryItem lists the following methods.

  //---------------------------------------String  decreaseStock(int decAmount)
  /**
   * Decrease the quantityInStock by the
   * amount passed as parameter 1. Returns
   * the message “Decreased stock of
   * [inventoryId] by [decAmount].”.
   */
  public String decreaseStock(int decAmount);

  //_____________________________int formatReportData_1(StringBuffer sb)
  /**
   * Appends the data from one object,
   * formatted for Report 1, to the
   * StringBuffer passed as parameter 1.
   * Returns a possible error indicator (zero
   * means “no errors”.
   */
  public int formatReportData_1(StringBuffer sb);

  //-------------------------------------String getPrimaryKey()
  /**
   * Returns the value of inventoryId (to be
   * used as the key for a Hashtable or
   * database primary key or similar
   * purpose).
   */
  public String getPrimaryKey();
  //-------------------------------------String  increaseStock(int incAmount)

  /**
   * Increases the quantityInStock by the
   * amount passed as parameter 1. Returns
   * the message “Increased stock of
   * [inventoryId] by [incAmount].”
   */
  public String increaseStock(int incAmount);
  //-------------------------------------String  placeSalesOrder(int qtyOrdered)

  /**
   * Adds the qtyOrdered to totalSalesOrders
   * and returns the message “Sales Order of
   * [qtyOrdered] for [inventoryId].”
   */
  public String placeSalesOrder(int qtyOrdered);
  //--------------------------------String receiveReplenishment(int qtyReceived)

  /**
   * Reduces the totalOrdered by the
   * qtyReceived then calls
   * increaseStock(qtyReceived).
   */
  public String receiveReplenishment(int qtyReceived);
  //--------------------------------------String shipSalesOrder(int qtyShipped)

  /**
   * Reduces totalSalesOrders by the
   * qtyShipped then calls
   * decreaseStock(qtyShipped).
   */
  public String shipSalesOrder(int qtyShipped);
}