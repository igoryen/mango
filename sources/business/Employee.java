// Employee.java - an early version ... to be improved soon
package business;

import java.io.*;        // needed for MyDataReader (for IOException)
import java.text.SimpleDateFormat;
import java.util.*;      // needed for StringTokenizer

import useful.*;         // for MyDataReader

/**
 * Class Employee represents a real-world Employee. Could have methods to calculate pay to be used for a Payroll application. <p>Run instructions:
 * <pre>
 * java applications.Assignment3 [&lt; ../data/Employee.txt]
 * </pre> <ol> <li>private String employeeId;<i></i></li> <li>private String jobTitle;<i></i></li> <li>private String salary;<i> // either the hourly
 * rate for wage workers or the annual salary for salaried workers</i></li> <li>private String payType;<i></i> // payment type - by the hour or
 * salary</li> <li>private double grossThisPay;<i></i> // gross pay this payment period</li> <li>private double taxThisPay;<i> // tax deduction for
 * this payment period</i></li> <li>private double pensionThisPay;<i></i> // pension deduction for this payment period</li> <li>private double
 * unionThisPay;<i></i> // union fee deduction for this payment period</li> <li>private double netThisPay;<i></i> // net pay for this payment
 * period</li> <li>private double grossYTD;<i></i> // gross for year-to-date</li> <li>private double taxYTD;<i></i></li> <li>private double
 * pensionYTD;<i></i></li> <li>private double unionYTD;<i></i></li> <li>private double netYTD;<i></i></li> <li>private static String[] prompts<i></i>
 * // the prompts that are displayed for the user to enter data</li> </ol> </p>
 *
 * @version 1.0
 * @author Igor Entaltsev
 * 
*/
public class Employee extends Person implements Serializable, Testable
{
  
  private String employeeId;
  private String jobTitle;
  private String salary;
  private String payType;
  //---- start new vars for A3 ------------------
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
  //-----finish new vars for A3  ------------------
//========================= start declaring static data ======================================================================
  private static String[] prompts =
  {
    "employeeId: ",
    "jobTitle: ",
    "salary: ",
    "payType: ",
  };
//========================= finish declaring static data (bottom) ======================================================================

  // ===================== start constructors ===========================================================
  /**
   * The application has only one constructor.
   */
  public Employee()
  {
    super();   // calling the constructor of class Person (the base class)

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
  // ===================== finish constructors  ===========================================================

  //======================== START METHODS ================================================================
  // alphabetically by method name
  
  //===================== start METHOD calculatePension() ============================================================
  /**
   * This method:
   * <ol>
   * <li>Calculates the pension for the current period as 6% of the grossPayThisPeriod.</li>  
   * <li>Subtract the calculated amount from both the taxThisPay and taxYTD instance variables.</li>
</li>
   * </ol>
   */ 
  
  public void calculatePension()
  {
    pensionThisPay = pensionYTD = grossThisPay * 0.06; //calculating company pension plan deductions

    // System.out.println("(netThisPay before pension  : " + netThisPay + ")");
    netThisPay = netYTD = grossThisPay - pensionThisPay; // setting the net sum
    //System.out.println("(netThisPay after the 6% pension (" + grossThisPay * 0.06 + ") is: " + netThisPay + ")");

    //System.out.println("(netThisPay before pension : " + netThisPay + ")");
    netThisPay = netYTD = grossThisPay - pensionThisPay;
    //System.out.println("(netThisPay (" + grossThisPay + ") after the 6% pension (" + grossThisPay * 0.06 + ") is: " + netThisPay + ")");    
    
  }

  //===================== finish METHOD calculatePension() ============================================================
  // ===================== start METHOD calculateSalary() ===========================================================
  /**
   * This method will only be called for objects for salaried employees with payType S4). <ol> <li>It calculates the gross
   * pay for the current pay period by [?] for salaried employees (payType = S4) by dividing the value of the salary variable by thirteen. </li>
   * <li>Adds the calculated amount to both the grossThisPay and grossYTD instance variables.</li> </ol>
   */
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
      //System.out.println("(grossThisPay: " + grossThisPay + ")");

      /*
       pensionThisPay = pensionYTD = grossThisPay * 0.06; //calculating company pension plan deductions

       // System.out.println("(netThisPay before pension  : " + netThisPay + ")");
       netThisPay = netYTD = grossThisPay - pensionThisPay; // setting the net sum
       System.out.println("(netThisPay after the 6% pension (" + grossThisPay * 0.06 + ") is: " + netThisPay + ")");      
       */      
    }
    else
    {
      System.out.println("... calculateSalary() is not applicable since this is not a salaried worker.");
    }
  }
  // ===================== finish METHOD calculateSalary()  ===========================================================

  // ===================== start METHOD calculateTax()  ===========================================================
  /**
   * This method: <ol> <li>Calculates the tax for the current period as 10% of the grossPayThisPeriod.</li> <li>Subtracts the calculated amount from
   * both the taxThisPay and taxYTD instance variables.</li> </ol>
   */
  public void calculateTax()
  {
    //double taxCurrentPay = 0.00; // tax for the current pay period
    //System.out.println("(grossThisPay           : " + grossThisPay + ")");
    //taxCurrentPay = grossThisPay * 0.10;
    taxYTD = taxThisPay = grossThisPay * 0.10; //taxYTD +=
    //System.out.println("(Employee.calculateTax().taxCurrentPay: " + taxCurrentPay + ")");
    //System.out.println("(taxThisPay             : " + taxThisPay + ")");
    //taxThisPay = grossThisPay - taxCurrentPay;
    //System.out.println("(Employee.calculateTax().taxThisPay   : " + taxThisPay + ")");
    //taxYTD = grossThisPay - taxCurrentPay;

    //System.out.println("(netThisPay before tax  : " + netThisPay + ")");
    netThisPay -= taxThisPay;
    netYTD -= taxYTD;
    //System.out.println("(netThisPay after the 10% tax (" + grossThisPay * 0.10 + ") is: " + netThisPay + ")");
  }
  // ===================== finish METHOD calculateTax()  ===========================================================

  // ===================== start METHOD calculateUnion()  =========================================================
  /**
   * This method: <ol> <li>Calculate the union dues for current pay period as a fixed rate $2,00.</li>
   * <li>Subtract the calculated amount from both the netThisPay and netYTD instance variables[.]</li> </ol>
   */
  public void calculateUnion()
  {
    if (payType.equalsIgnoreCase("HW"))
    {
      //unionThisPay = 2.00; // union dues for the current pay period

      //System.out.println("(netThisPay before union   : " + netThisPay + ")");
      netThisPay -= unionThisPay;
      netYTD -= unionYTD;
      //System.out.println("(netThisPay after union (2.00) is: " + netThisPay + ")");
    }
    else
    {
      System.out.println("... calculateUnion() is not applicable since this is not a union member.");
    }
  }
  // ===================== finish METHOD calculateUnion()  =========================================================

  // ===================== start METHOD calculateWages()  ===========================================================
  /**
   * This method will only be called for objects for hourly paid employees with payType HW) <ol> <li>Calculates
   * the gross pay for the current pay period for hourly rate employees (payType HW ) by multiplying the value of the salary variable by the value
   * of hoursWorked (parameter 1).</li> <li>Adds the calculated amount to both the grossThisPay and grossYTD instance variables.</li> </ol>
   *
   * @param hoursWorked the amount of hours the employee has worked in 4 weeks
   */
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
      //System.out.println("(grossThisPay: " + grossThisPay + ")");

      /*
       pensionThisPay = pensionYTD = grossThisPay * 0.06;

       //System.out.println("(netThisPay before pension : " + netThisPay + ")");
       netThisPay = netYTD = grossThisPay - pensionThisPay;
       System.out.println("(netThisPay (" + grossThisPay + ") after the 6% pension (" + grossThisPay * 0.06 + ") is: " + netThisPay + ")");
      
       */
    }
    else
    {
      System.out.println("... calculateWages() is not applicable since this is not a wage-worker.");
    }
    
    return retval;
  }
  // ===================== finish METHOD calculateWages() =========================================================

  // ===================== start METHOD formatDisplay() ===========================================================
  /**
   * In Order to test a class properly it is essential to be able to display the status of objects (ALL the data within the object). <p>Formats the
   * contents of the object into a String so that when displayed (by the application) it will make a "pretty" display.</p>
   */
  public int formatDisplay(StringBuffer temp)
  {
    int retval = 0;
    
    temp.append("\n" + "+++++++++Employee+++++++++");
    temp.append("\n" + "employeeId : " + employeeId);
    temp.append("\n" + "jobTitle   : " + jobTitle);
    temp.append("\n" + "salary     : " + salary);    
    temp.append("\n" + "payType    : " + payType);
    super.formatDisplay(temp);
    temp.append("\n" + "+++++end Employee+++++++++");


    /*
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
     */
    return retval;
  }
// =====================finish METHOD formatDisplay()  ===========================================================

  // ================== start METHOD formatPaySlip()  ==========================================================
  /**
   * Appends the data from one object, formatted as a payslip, to the StringBuffer (parameter 1).
   *
   * @param sb
   * @return int err (the error status)
   */
  public int formatPaySlip(StringBuffer sb) // append data from employee
  {
    int err = 0;
    
    try
    {
      
      // if {sb != null OR sb.lenth=0
      //{ print header
      // else
      // don't print header
      
//      sb.append(String.format("\n" + "%-22s%15s%27s", "Seneca College Payslip", "Period 01", "yyyy-mm-dd"));
//      sb.append("\n" + "---- =======================================--------- =========="); // 39= 10- 10=
      sb.append(String.format("\n" + "%4s%1s%-37s%2s", employeeId, " ", jobTitle, payType));
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
  // ===================== finish METHOD formatPaySlip() ==========================================================

  // =====================start METHOD getData()  ===========================================================
  /**
   *
   * This method supplies a CSV with data to udate() the business object.
   *
   * @param sb
   * @param dri
   * @param sep
   * @return int err (the error status)
   * @throws IOException
   */
  public static int getData(StringBuffer sb, DataReaderImpl_1 dri, String sep) throws IOException
  {
    int err = 0;
    
    err = Useful.getData(prompts, sb, dri, sep);
    // deal with base-class object
    err = Person.getData(sb, dri, "%");
    
    sb.append(sep);
    
    return err;
  }
  // =====================finish METHOD getData() ===========================================================

  // =====================start METHOD getPrimaryKey()  ===========================================================
  /**
   * Supplies a primary key value for possible use with Hashtable or relational database storage applications.
   *
   * @return pk - the value of the primary key
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
  // ===================== finish METHOD getPrimaryKey() ===========================================================

  // =====================start METHOD update()  ===========================================================
  /**
   * inserts data from the StringBuffer, (param #1) into an empty object. The format of the StringBuffer is that provided by getData(...) in CSV
   * format ... separator is first character of StringBuffer.
   *
   * @param sb
   * @return int err - the error status
   */
  public int update(StringBuffer sb)
  {
    //System.out.println("(entered Employee.update)");
    int err = 0;  // used to indicate status (0 = normal)
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
      //employeeId = Integer.parseInt(temp);
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
    
    temp = tk.nextToken();
    if (!temp.equals("\t"))
    {
      super.update(new StringBuffer(temp));
    }

    //System.out.println("(Employee.update().payType: " + payType + ")");

    //System.out.println("(exiting Employee.update())");
    return err; // indicate OK (i.e. no errors)
  }
  // =====================finish METHOD update()  =========================================================
  //======================finish METHODS =================================================================
}
