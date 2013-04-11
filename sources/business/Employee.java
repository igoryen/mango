// Employee.java - an early version ... to be improved soon
package business;

import java.io.*;        // needed for MyDataReader (for IOException)
import java.text.SimpleDateFormat;
import java.util.*;      // needed for StringTokenizer

import useful.*;         // for MyDataReader

/**
 * Represents an real-world Employee. Could have methods to calculate pay to be used for a Payroll application.
 * 
*/
public class Employee implements Serializable, Testable
{

  private String employeeId;
  private String jobTitle;
  private String salary;
  private String payType;
  //---- new vars for A3 (top) ------------------
  //---- this pay period -------------------
  private double grossThisPay;
  private double taxThisPay;
  private double pensionThisPay;
  private double unionThisPay;
  private double netThisPay;
  //---- Year To Date ---------------------
  private double grossYTD;
  private double taxYTD;
  private double pensionYTD;
  private double unionYTD;
  private double netYTD;
  //-----new vars for A3 (bottom) ------------------
//========================= static data (top) ======================================================================
  private static String[] prompts =
  {
    "employeeId     : ",
    "jobTitle       : ",
    "salary         : ",
    "payType        : ",
  };
//========================= static data (bottom) ======================================================================

  // ===================== constructors (top) ===========================================================
  public Employee()
  {
    //super();   // call the base class constructor

    // make sure there are no null references in here
    employeeId = "";
    jobTitle = "";
    salary = "";
    payType = "";

    grossThisPay = 0.00;

    taxThisPay = 0.00;
    pensionThisPay = 0.00;
    unionThisPay = 2.00;
    netThisPay = 0.00;

    grossYTD = 0.00;

    taxYTD = 0.00;
    pensionYTD = 0.00;
    unionYTD = 2.00;
    netYTD = 0.00;

  }
  // ===================== constructors (bottom) ===========================================================

  //======================== M.E.T.H.O.D.S. (top) ================================================================
  // ===================== METHOD calculateSalary() (top) ===========================================================
  public void calculateSalary()
  {
    //System.out.println("(payType                    : " + payType + ")");
    //System.out.println("(salary                     : " + salary + ")");

    if (payType.equalsIgnoreCase("S4"))
    {
      // for employee with payType S4

      double grossCurrentPay = 0.00; // gross pay for the current pay period
      //grossCurrentPay = Double.parseDouble(salary) / (double) 13;
      grossCurrentPay = Double.parseDouble(salary) / 13; // obtaining month salary by dividing annual salary by 13 months

      grossThisPay += grossCurrentPay; // initializing 
      grossYTD += grossCurrentPay;    // initializing
      //System.out.println("(grossThisPay               : " + grossThisPay + ")");


      pensionThisPay = pensionYTD = grossThisPay * 0.06; //calculating company pension plan deductions

     // System.out.println("(netThisPay before pension  : " + netThisPay + ")");
      netThisPay = netYTD = grossThisPay - pensionThisPay; // setting the net sum
      System.out.println("(netThisPay after pension   : " + netThisPay + ")");

    }
    else
    {
      System.out.println("... not applicable since this is not a salaried worker.");
    }



  } // end of calculateSalary
  // ===================== METHOD calculateSalary() (bottom) ===========================================================

  // ===================== METHOD calculateTax() (top) ===========================================================
  public void calculateTax()
  {
    //double taxCurrentPay = 0.00; // tax for the current pay period
    //System.out.println("(grossThisPay           : " + grossThisPay + ")");
    //taxCurrentPay = grossThisPay * 0.10;
    taxYTD = taxThisPay = grossThisPay * 0.10;
    //System.out.println("(Employee.calculateTax().taxCurrentPay: " + taxCurrentPay + ")");
    //System.out.println("(taxThisPay             : " + taxThisPay + ")");
    //taxThisPay = grossThisPay - taxCurrentPay;
    //System.out.println("(Employee.calculateTax().taxThisPay   : " + taxThisPay + ")");
    //taxYTD = grossThisPay - taxCurrentPay;

    //System.out.println("(netThisPay before tax  : " + netThisPay + ")");
    netThisPay -= taxThisPay;
    netYTD -= taxYTD;
    System.out.println("(netThisPay after tax   : " + netThisPay + ")");


  }
  // ===================== METHOD calculateTax() (bottom) ===========================================================

  // ===================== METHOD calculateUnion() (top) =========================================================
  public void calculateUnion()
  {
    if (payType.equalsIgnoreCase("HW"))
    {
      //unionThisPay = 2.00; // union dues for the current pay period

      //System.out.println("(netThisPay before union   : " + netThisPay + ")");
      netThisPay -= unionThisPay;
      netYTD -= unionYTD;
      System.out.println("(netThisPay after union    : " + netThisPay + ")");

    }
    else
    {
      System.out.println("... not applicable since this is not a union member.");
    }
  }
  // ===================== METHOD calculateUnion() (bottom) =========================================================

  // ===================== METHOD calculateWages() (top) ===========================================================
  public String calculateWages(double hoursWorked)
  {
    String retval = "";
    if (payType.equalsIgnoreCase("HW"))
    {
      // for employee with payType HW
      //System.out.println("(Employee.calculateWages().grossThisPay : " + grossThisPay + ")");
      double grossCurrentPay; // gross pay for the current pay period
      grossCurrentPay = Double.parseDouble(salary) * hoursWorked;
      grossThisPay += grossCurrentPay;
      grossYTD += grossCurrentPay;
      //System.out.println("(grossThisPay              : " + grossThisPay + ")");

      pensionThisPay = pensionYTD = grossThisPay * 0.06;

      //System.out.println("(netThisPay before pension : " + netThisPay + ")");
      netThisPay = netYTD = grossThisPay - pensionThisPay;
      System.out.println("(netThisPay after pension  : " + netThisPay + ")");

    }
    else
    {
      System.out.println("... not applicable since this is not a wage-worker.");
    }


    return retval;
  }
  // ===================== METHOD calculateWages() (bottom) =========================================================

  // ===================== METHOD formatDisplay() (top) ===========================================================
  //-----------------------------------------------------------------formatDisplay()
  /**
   * In Order to test a class properly it is essential to be able to display the status of objects (ALL the data within the object). <p>Formats the
   * contents of the object into a String so that when displayed (by the application) it will make a "pretty" display.</p>
   */
  public int formatDisplay(StringBuffer temp)
  {
    int retval = 0;
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //yyyy-MM-dd_HHmmss
    

    temp.append("\n" + "--------------------- formatDisplay() ---------------------");

    temp.append(String.format("\n" + "%-22s%15s%27s", "Seneca College Payslip", "Period 01", timeStamp)); //yyyy-MM-dd_HHmmss));
    temp.append("\n" + "---- =======================================--------- =========="); // 39= 10- 10=
    temp.append(String.format("\n" + "%4s%1s%-37s%2s", employeeId, " ", jobTitle, payType));
    temp.append("\n");
    temp.append(String.format("\n" + "%5s%-37s%11s%11s", " ", "INCOME", "ThisPay", "YearToDate"));
    temp.append(String.format("\n" + "%7s%-35s%11.2f%11.2f", " ", "Gross Pay", grossThisPay, grossYTD));
    temp.append(String.format("\n" + "%5s%-60s", " ", "DEDUCTIONS"));
    temp.append(String.format("\n" + "%7s%-35s%11.2f%11.2f", " ", "Income Tax", taxThisPay, taxYTD));
    temp.append(String.format("\n" + "%7s%-35s%11.2f%11.2f", " ", "Pension", pensionThisPay, pensionYTD));

    if (payType.equalsIgnoreCase("HW"))
    {
      temp.append(String.format("\n" + "%7s%-35s%11.2f%11.2f", " ", "Union Dues", unionThisPay, unionYTD));
    }
    if (payType.equalsIgnoreCase("S4"))
    {
      temp.append(String.format("\n" + "%7s%-35s", " ", "Union Dues"));
    }


    temp.append("\n");
    temp.append(String.format("\n" + "%5s%-37s%11.2f%11.2f", " ", "NET PAY", netThisPay, netYTD));
    temp.append(String.format("\n" + "%5s%-37s%11.2f", " ", "PAID IN TO BANK", netThisPay));

    temp.append("\n" + "--------------------- finish formatDisplay() ---------------------");
    return retval;
  }
// =====================METHOD formatDisplay() (bottom) ===========================================================
  // ===================== METHOD formatHeadings() (top) =========================================================

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
  // ===================== METHOD formatHeadings() (bottom) =========================================================

  // ===================== METHOD formatPaySlip() (top) ==========================================================
  public int formatPaySlip(StringBuffer sb) // append data from employee
  {
    int err = 0;

    try
    {
//      sb.append(String.format("\n" + "%-22s%15s%27s", "Seneca College Payslip", "Period 01", "yyyy-mm-dd"));
//      sb.append("\n" + "---- =======================================--------- =========="); // 39= 10- 10=
      sb.append(String.format("%4s%1s%-37s%2s", employeeId, " ", jobTitle, payType));
      sb.append("\n");
      sb.append(String.format("\n" + "%5s%-37s%11s%11s", " ", "INCOME", "ThisPay", "YearToDate"));
      sb.append(String.format("\n" + "%7s%-35s%11.2f%11.2f", " ", "Gross Pay", grossThisPay, grossYTD));
      sb.append(String.format("\n" + "%5s%-60s", " ", "DEDUCTIONS"));
      sb.append(String.format("\n" + "%7s%-35s%11.2f%11.2f", " ", "Income Tax", taxThisPay, taxYTD));
      sb.append(String.format("\n" + "%7s%-35s%11.2f%11.2f", " ", "Pension", pensionThisPay, pensionYTD));
      if (payType.equalsIgnoreCase("HW"))
      {
        sb.append(String.format("\n" + "%7s%-35s%11.2f%11.2f", " ", "Union Dues", unionThisPay, unionYTD));
      }
      if (payType.equalsIgnoreCase("S4"))
      {
        sb.append(String.format("\n" + "%7s%-35s", " ", "Union Dues"));
      }
      sb.append("\n");
      sb.append(String.format("\n" + "%5s%-37s%11.2f%11.2f", " ", "NET PAY", netThisPay, netYTD));
      sb.append(String.format("\n" + "%5s%-37s%11.2f", " ", "PAID IN TO BANK", netThisPay));
      sb.append("\n");

    }
    catch (Exception e)
    {
      System.out.println("******************************************************************");

      System.out.println("Caught exception in formatReportData_1 : " + e.getMessage());
      System.out.println("... stack trace follows ...");
      e.printStackTrace(System.out);
      System.out.println("******************************************************************");

    }

    return err;
  }
  // ===================== METHOD formatPaySlip() (bottom) ==========================================================

  // =====================METHOD getData() (top) ===========================================================
  //----------------------------------------------------------------------getData()
  /**
   * getData(...) supplies a CSV with data to udate() the business object
   */
  public static int getData(StringBuffer sb, DataReaderImpl_1 dri, String sep)
    throws IOException
  {
    int retval = 0;

    retval = Useful.getData(prompts, sb, dri, sep);
    // deal with base-class object
    //Person.getData(sb, br, "%");
    sb.append(sep);
    return retval;
  }
  // end of method
  // =====================METHOD getData() (bottom) ===========================================================

  // =====================METHOD getPrimaryKey() (top) ===========================================================
  /**
   * Supplies a primary key value for possible use with Hashtable or relational database storage applications.
   */
  public String getPrimaryKey()
  {
    //System.out.println("(entered getPK())");

    String pk = "";

    if (!employeeId.contains(" "))
    {
      pk = employeeId;
    }
    //System.out.println("(exiting getPK())");
    return pk;
  }
  // =====================METHOD getPrimaryKey() (bottom) ===========================================================

  // =====================METHOD update() (top) ===========================================================
  /**
   * update() - inserts data from the StringBuffer, (param #1) into an empty object. The format of the StringBuffer is that provided by getData(...)
   * in CSV format ... separator is first character of StringBuffer
   */
  public int update(StringBuffer sb)
  {
    //System.out.println("(entered Employee.update)");
    int retval = 0;  // used to indicate status (0 = normal)
    String sep;      // separator value used by CSV format data
    StringTokenizer tk;       // to extract data
    String temp;    // for extracted data

    //--- get separator (value is first character of StringBuffer)
    sep = String.valueOf(sb.charAt(0));
    //--- create tokenizer (to extract data items)
    tk = new StringTokenizer(sb.toString(), sep);

    //--- now get tokens and update instance variables
    //    only make change if data is not equal to a single tab 

    temp = tk.nextToken();
    if (!temp.equals("\t"))
    {
      // String, use as is
      employeeId = new String(temp);
    }

    //System.out.println("(Employee.update().employeeId: " + employeeId + ")");

    temp = tk.nextToken();
    if (!temp.equals("\t"))
    {
      // String, use as is
      jobTitle = new String(temp);
    }

    //System.out.println("(Employee.update().jobTitle: " + jobTitle + ")");


    temp = tk.nextToken();
    if (!temp.equals("\t"))
    {
      // String, use as is
      salary = new String(temp);
    }

    //System.out.println("(Employee.update().salary: " + salary + ")");


    temp = tk.nextToken();
    if (!temp.equals("\t"))
    {
      // String, use as is
      payType = new String(temp);
    }

    //System.out.println("(Employee.update().payType: " + payType + ")");


    //System.out.println("(exiting Employee.update())");
    return retval; // indicate OK (i.e. no errors)
  } // end of update    
  // =====================METHOD update() (bottom) ===========================================================
  //========================M.E.T.H.O.D.S. (bottom) =================================================================
}
