// Person.java - an early version ... to be improved soon

package business;

import java.io.*;        // needed for MyDataReader (for IOException)
import java.util.*;      // needed for StringTokenizer

import useful.*;         // for MyDataReader


/**
* Represents a real Person (expects to be used as a base for
* several other classes so has minimal information)
*/
public class Person  implements Serializable
{
  private String  familyName;
  private String  givenName;
  private String  gender;
  private String  height;
  
  private Phone homePhone;
  private Address homeAddress;
  
   //----static data----------------------
   
   private static String[] prompts =
   {
     "familyName",
     "givenName",
     "gender",
     "height"
   };
   
  //---- constructors----------------------
   
  public Person()
  {
    // make sure there are no null references in here
    familyName  = "";
    givenName   = "";
    gender      = "";
    height      = "";
    homePhone   = new Phone();
    homeAddress = new Address();
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
     temp.append("\n" + "+++++++++Person+++++++++");     
     temp.append("\n" + "familyName : " + familyName);
     temp.append("\n" + "givenName  : " + givenName);
     temp.append("\n" + "gender     : " + gender);
     temp.append("\n" + "height     : " + height);
     homePhone.formatDisplay(temp);
     homeAddress.formatDisplay(temp);
     temp.append("\n" + "+++++end Person+++++++++");     
	   return retval;
   }

  //----------------------------------------------------------------------getData()
  /** getData(...)  supplies a CSV with data to udate() the business object 
  */
  public static int getData(StringBuffer sb, MyDataReader br, String sep)
    throws IOException
  {
    int retval = 0;
      
    retval = Useful.getData(prompts, sb, br, sep);
    
    

    // now get the Phone data by calling class Phone getData() 
    
    Phone.getData(sb, br, "!");
    sb.append(sep);              // add the PersonSeparator
   
    // now get the Address data by calling class Address getData() 
    
    Address.getData(sb, br, "!");
    sb.append(sep);              // add the PersonSeparator
    
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
      familyName = new String(temp);
    }

    temp = tk.nextToken();
    if (! temp.equals("\t"))
    {
      // String, use as is
      givenName = new String(temp);
    }

    temp = tk.nextToken();
    if (! temp.equals("\t"))
    {
      // String, use as is
      gender = new String(temp);
    }

    temp = tk.nextToken();
    if (! temp.equals("\t"))
    {
      // String, use as is
      height = new String(temp);
    }
    
    // deal with Phone CSV as a token withiin Person CSV
    temp = tk.nextToken();
    if (! temp.equals("\t"))
    {
      // is a CSV String for a class Phone object
      homePhone.update(new StringBuffer(temp));
    }


    // deal with Address CSV as a token withiin Person CSV
    temp = tk.nextToken();
    if (! temp.equals("\t"))
    {
      // is a CSV String for a class Address object
      homeAddress.update(new StringBuffer(temp));
    }

    return retval; // indicate OK (i.e. no errors)
  } // end of update
}

