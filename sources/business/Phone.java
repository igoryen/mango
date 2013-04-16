// Phone.java - an early version ... to be improved soon

package business;

import java.io.*;        // needed for MyDataReader (for IOException)
import java.util.*;      // needed for StringTokenizer

import useful.*;         // for MyDataReader

/**
* Represents a real phone number (instance variable names are as in North
* America although would be usable in other parts of the world)
*
*/
public class Phone implements Serializable
{
  private String  area;
  private String  exchange;
  private String  local;
  private String  extension;
   //----static data----------------------
   
   private static String[] prompts =
   {
     "area",
     "exchange",
     "local",
     "extension"
   };
   
  //---- constructors----------------------
 
  /**
  * Initializes the the reference variables to sensible
  * not-null values.
  */  
  public Phone()
  {
    // make sure there are no null references in here
    area  = "";
    exchange   = "";
    local      = "";
    extension  = "";
  }
  

   //-----------------------------------------------------------------formatDisplay()
   /**
   In Order to test a class properly it is essential to be able to
   display the status of objects (ALL the data within the object).
    <p>Formats the contents of the object into a String so that when
       displayed (by the application) it will make a "pretty" display.</p>
   */
   public int formatDisplay(StringBuffer temp)
   {
     int retval = 0;
     temp.append("\n" + "+++++++++Phone+++++++++");     
     temp.append("\n" + "area      : " + area);
     temp.append("\n" + "exchange  : " + exchange);
     temp.append("\n" + "local     : " + local);
     temp.append("\n" + "extension : " + extension);
     temp.append("\n" + "+++++end Phone+++++++++");     
	   return retval;
   }

  //----------------------------------------------------------------------getData()
  /** getData(...)  supplies a CSV with data to udate() the business object 
  */
  public static int getData(StringBuffer sb, DataReaderImpl_1 br, String sep)
    throws IOException
  {
    int retval = 0;
      
    retval = Useful.getData(prompts, sb, br, sep);
    return retval;
  }
  // end of method
  
  //----------------------------------------------------------------------update()
  /**
   * update() -  inserts data from the StringBuffer, (param #1) into an empty 
   * object.  The format of the StringBuffer is that provided by getData(...)
   * in CSV format ... separator is first character of StringBuffer
   */
  public int update(StringBuffer sb)
  {
    int              retval = 0;  // used to indicate status (0 = normal)
    String           sep;      // separator value used by CSV format data
    StringTokenizer  tk;       // to extract data
    String           temp;    // for extracted data

    //--- get separator (value is first character of StringBuffer)
    sep = String.valueOf(sb.charAt(0));
    //--- create tokenizer (to extract data items)
    tk = new StringTokenizer(sb.toString(), sep);

    //--- now get tokens and update instance variables
    //    only make change if data is not equal to a single tab 

    temp = tk.nextToken();
    if (! temp.equals("\t"))
    {
      // String, use as is
      area = new String(temp);
    }

    temp = tk.nextToken();
    if (! temp.equals("\t"))
    {
      // String, use as is
      exchange = new String(temp);
    }

    temp = tk.nextToken();
    if (! temp.equals("\t"))
    {
      // String, use as is
      local = new String(temp);
    }

    temp = tk.nextToken();
    if (! temp.equals("\t"))
    {
      // String, use as is
      extension = new String(temp);
    }


    return retval; // indicate OK (i.e. no errors)
  } // end of update
}

