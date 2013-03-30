package useful;

import java.io.*;
//import useful.*;

public class DataReaderImpl_1 implements DataReader
{

  // -----------instance data-------------------
  private String marker;
  private BufferedReader theReader;

  // ======================== constructor (only one) =======================================================
  
  public DataReaderImpl_1()
  {
    theReader = new BufferedReader(new InputStreamReader(System.in));    
    marker = "#"; // 42
  }
  
  // ============= readLine() ================================================================================
  
  public String readLine()
  // throws IOException
  {
    String data = null; // 44

////debug
//    System.out.println("THIS IS MARKER:   " + marker);

    if (marker == null) 
    {
      try
      {
        data = theReader.readLine(); // 45

        if (data == null) // 46
        {
          return data;
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    else // 40
    {
      try
      {
        data = theReader.readLine(); // 45

        if (data == null) // 46
        {
          return data; // 47
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

      while (data.startsWith(marker)) // 48
      {
        if (data != null)
        {
          try
          {
            data = theReader.readLine(); // 45

            if (data == null)
            {
              return data;
            }
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }
        }
      }
      
      if (data != null) // 41
      {
        int x = data.indexOf(marker); // 49
        if (x >= 0) // 50
        {
          data = data.substring(0, x); // 51
          data = data.trim(); // 52
        }
      } // end if
    } // end else
    return data;
  }

  // =============== resetMarker() ============================================================================
  
  public String resetMarker(String mkr)
  {
    String ocmt = new String(marker); // 43

    if (mkr.equals("0")) // 53
    {
      marker = null;
    }
    else // 54
    {
      marker = mkr; // 55
    }
    return ocmt;
  }
  
  // =============== close () ================================================================================

  public void close() //  throws IOException
  {
    try
    {
      theReader.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}// end of class