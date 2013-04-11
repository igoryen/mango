// Useful.java - a class to contain mostly static methods
  
 package useful;
 
 import java.io.*;
 
 public class Useful
 {
  
   //---------------------------------------------------------------------getData()
   /**
     * <p>This is the library version of getData(...) called from the getData(...)
     * method of all Business Classes. </p>
     * <p>Reads the prompts (param #1) in a loop.
     * Gets data from the MyReader (param #3)and appends it to a 
     * StringBuffer (param #2).  The appended data is in CSV format:
     * <pre>
     * ~Field1~Field2~ ... Fieldn~
     * </pre>
     * where '~' is a marker for the separator (param#4)
     * 
     * </p>
    */
    public static int getData(String[] prompts, StringBuffer sb, DataReaderImpl_1 br, String sep)
      throws IOException
    {
      String   buf = ""; // to read into
      int      err = 0;  // used for return value
      int      k = 0;    // loop control

      sb.append(sep);  // start StringBuffer with separator char

      for (k = 0; k < prompts.length; k++)
      {
        System.err.print(prompts[k]);  // output prompt - leave cursor on same line
        System.err.flush();            // ensure that the data appears
        
        buf = br.readLine();           // read the input/response
        
        // ================= do a series of checks ==========================================
        if (buf == null)
        {
           // at end of file
           err = -1;
           break;
        }
        //--- data entered may not contain a separator character
        if (buf.indexOf(sep) >= 0  )
        {
          err = -2;                     // set return code
          break;                       // finish - break out of loop
        }
        //--- deal with empty input field 
        if ( buf.equals("") )
        {
          buf = "\t";                   // tab to indicate empty field
        }
        //=============== finish doing a series of checks =====================================
        
        //--- data item is OK so add it to StringBuffer
        sb.append(buf + sep);          // append separator
      } // end for()

      return err;                      // return result/status code
    } // endof getData()
  
   //---------------------------------------------------------------isValidNumeric()
   /**
     SA244
     Tests the value of parameter data to see if all the characters are numeric.
     Return true if that is so, otherwise return false.
   */
   public static boolean isValidNumeric(String data)
   {
     boolean retval = true;   // assume true (OK) to begin
     
     // examine data, char-by-char until non-numeric is found or until end of String
     for (int k = 0; k < data.length(); k++)
     {
       if ( (data.charAt(k) < '0' ) || (data.charAt(k) > '9') )
       {
         retval = false;
         break;
       }
     }
     return retval;
   } // end of method

   //------------------------------------------------------------isValidDecimal()
   /**
     SA244  
     Tests the value of parameter data to see it represents a decimal number.
     Return true if that is so, otherwise return false.
     
     The String is examined in four parts as follows:
     1. an optional sign, if present must be '-' or '+'
     2. a whole-number part (must be at least one digit)
     3. an optional decimal point, if present must not be the last character in the String.
     4. an optional decimal value (there must be at least one character following the
        decimal point if a decimal point is present)
   */
   public static boolean isValidDecimal(String data)
   {
     boolean retval              = true;   // assume true (OK) to begin
     String  temp                = "";     // used to split data into parts
     String  tempWhole           = "";     // will contain the whole-number part
     String  tempDecimal         = "";     // will contain the decimal part
     
     
     // look for sign
     // if there is valid sign it is identified otherwise the first character
     // data is treated as part of the number, which is moved into String temp
     // if the first character was intended to be a sign but was mis-keyed
     // that will be show later as an invalid numeric digit
     // d0 ... while so as to be able to break when invalid is known
     do
     { 
        if ( (data.charAt(0) == '-') || (data.charAt(0) == '+') )
        {
          temp = new String(data.substring(1));
        }
        else
        {
          temp = new String(data);
        }
        
        // examine the whole number part of the string
        // and note decimal part if any
        int k = data.indexOf(".");
        
        // special case - whole-number part must exist
        if (k == 0)
        {
          retval = false;
          break;
        }
        if ( k < 0 )
        {
          tempWhole = new String(temp);
        }
        else
        {
          tempWhole   = new String(temp.substring(0, k));
          tempDecimal = new String(temp.substring(k)); 
        }
        retval = isValidNumeric(tempWhole);
        if (retval == false)
        {
          break;
        }
        // temp decimal is now empty or contains the decimal point
        // followed by the decimal value
        // only a decimal point it the number is invalid
        if (tempDecimal.length() == 0)
        {
          break;
        }
        if (tempDecimal.length() == 1)
        {
          retval = false;
          break;
        }
        // test decimal part
        retval = isValidNumeric(tempDecimal.substring(1));
     } while (false);  // ensure once only         
      
     return retval;
   } // end of method
  
  } // end class Useful

