// Address.java

package business;

import java.io.*;
import java.util.*;
import useful.*;

/**
* Represents a real Address object
  <p>Used as a learning tool, just a few variables.</p>
 @author B. Perry
 @author --add your name here ---
 @version 1 - 2007-05-22
*/
public class Address implements Serializable
{
   //----static data
  private static String[] prompts =
  {
    "number",
    "street",
    "suite", 
    "city",
    "province",
    "postcode",
    "country",
  };


      
	 //----instance data
   
   /** explanation number */
   private String number;
   /** explanation street */
   private String street;
   /** explanation suite */
   private String suite;
   /** explanation city */
   private String city;
   /** explanation province */
   private String province;
   /** explanation postcode */
   private String postcode;
   /** explanation country */
   private String country;

   //---constructors
   
   /**
   * No param constuctor.  Should initalize all references to avoid
   * NullPointerException later.  Allows primitive variables to
   * get default values.  Expects most instance variables
   * to be populated by an update() method.
   */
   public Address()
   {
     number="";
     street="";
     suite="";
     city="";
     province="";
     postcode="";
     country="";
     // any primitives get default initialization
   }  // end contructor
   
   //---methods (in sequence by method name)
   
   //-------------------------------------------------------formatDisplay()
   /**
   In Order to test a class properly it is essential to be able to
   display the status of objects (ALL the data within the object).
    <p>Formats the contents of the object into a String so that when
       displayed (by the application) it will make a "pretty" display.</p>
   */
   public int formatDisplay(StringBuffer temp)
   {
     int retval = 0;
     temp.append("\n" + "+++++++++Address+++++++++");     
     temp.append("\n" + "number   : " + number);
     temp.append("\n" + "street   : " + street);
     temp.append("\n" + "suite    : " + suite);
     temp.append("\n" + "city     : " + city);
     temp.append("\n" + "province : " + province);
     temp.append("\n" + "postcode : " + postcode);
     temp.append("\n" + "country  : " + country);
     temp.append("\n" + "+++++end Address+++++++++");     
	   return retval;
   } // end method

   //------------------------------------------------------formatEnvelope()
   /**
    <p>Formats the contents of the object into a String so that when
       displayed (by the application) it will as a comma separated
       list if the instance varible values.</p>
   */
   public int formatEnvelope(StringBuffer temp, String sendersCountry)
   {
     int retval = 0;
     if ( (sendersCountry == null) ||  (! sendersCountry.equals("") ) )
     {
       temp.append("\n" + number);
       temp.append(", " + street);
       temp.append("  " + suite);
       temp.append("\n" + city);
       temp.append(", " + province);
       temp.append("\n" + postcode);
       if (! sendersCountry.equals(country))
       {
         temp.append("\n" + country);
       }
     }
     else
     {
       retval = 3;
     }
	   return retval;
   } // end method

   //----------------------------------------------------------formatList()
   /**
   In Order to test a class properly it is essential to be able to
   display the status of objects (ALL the data within the object).
    <p>Formats the contents of the object into a String so that when
       displayed (by the application) it will make a "pretty" display.</p>
   */
   public int formatList(StringBuffer temp, String sendersCountry)
   {
     int retval = 0;
     if ( (sendersCountry != null) || (! sendersCountry.equals("") ) )
     {
       temp.append("\n" + number);
       temp.append(", " + street);
       temp.append(", " + suite);
       temp.append(", " + city);
       temp.append(", " + province);
       temp.append(", " + postcode);
       if (! sendersCountry.equals(country))
       {
         temp.append(", " + country);
       }
     }
     else
     {
       retval = 3;
     }
	   return retval;
   } // end method

  //--------------------------------------------------------------getData()
  /**
   * <p> Class the libray version of getData(...) to achieve the following.</p>
   * <p>
   * Gets data from the BufferedReader (param #2)and appends it to a 
   * StringBuffer (param #1).  The appended data is in the format:
   * <pre>
   * ~Field1~Field2~ ... Fieldn~
   * </pre>
   * where '~' is a marker for the separator (param#3)
   * 
   * </p>
   */
  public static int getData(StringBuffer sb, DataReaderImpl_1 br, String sep)
    throws IOException
  {
    int      err = 0;  // used for return value

    err = Useful.getData(prompts, sb, br, sep); // library (static) method 
    return err;                      // return result/status code
  } // end of getData()

  //---------------------------------------------------------------update()
  /**
   * update() -  inserts data from the StringBuffer, (param #1) into an empty 
   * object.  The format of the StringBuffer is that provided by getData(...)
   * in CSV format ... separator is first character of StringBuffer
   */
  public int update(StringBuffer sb)
  {
    int             err = 0;  // used to indicate status (0 = normal)
    String          sep;      // separator value used by CSV format data
    StringTokenizer tk;       // to extract data
    String          temp;    // for extracted data

    //--- get separator (value is first character of StringBuffer)
    sep = String.valueOf(sb.charAt(0));
    //--- create tokenizer (to extract data items)
    tk = new StringTokenizer(sb.toString(), sep);

    //--- now get tokens and update instance variables
    //    only make change if data is not equal to a single tab 

    temp = tk.nextToken();              
    if (! temp.equals("\t"))            
    {                                        
      // is String - no conversion needed    
      number = new String(temp);          
    }                                        
                                             
    temp = tk.nextToken();               
    if (! temp.equals("\t"))            
    {                                        
      // is String - no conversion needed    
      street = new String(temp);          
    }                                        
                                             
    temp = tk.nextToken();               
    if (! temp.equals("\t"))            
    {                                        
      // is String - no conversion needed    
      suite = new String(temp);          
    }                                        
                                             
    temp = tk.nextToken();               
    if (! temp.equals("\t"))            
    {                                        
      // is String - no conversion needed    
      city = new String(temp);          
    }                                        
                                             
    temp = tk.nextToken();               
    if (! temp.equals("\t"))            
    {                                        
      // is String - no conversion needed    
      province = new String(temp);          
    }                                        
                                             
    temp = tk.nextToken();               
    if (! temp.equals("\t"))            
    {                                        
      // is String - no conversion needed    
      postcode = new String(temp);          
    }                                        
                                             
    temp = tk.nextToken();               
    if (! temp.equals("\t"))            
    {                                        
      // is String - no conversion needed    
      country = new String(temp);          
    }                                        
                                             
    return err; // indicate OK (i.e. no errors)
  } // end of update

} // end class
