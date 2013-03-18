//package useful;

import java.io.*;

//import useful.*;
public class DataReaderImpl_1 implements DataReader
{

  // -----------instance data
  private String marker;
  private BufferedReader theReader;

  // ----------------constructor (only one)
  public DataReaderImpl_1()
  {

    theReader = new BufferedReader(new InputStreamReader(System.in));
    // initialize the cmt to "#"
    marker = "#";

  }

  // ------- methods 
  public String readLine()
  // throws IOException
  {
    String data = null;

////debug
//    System.out.println("THIS IS MARKER:   " + marker);

    if (marker == null)
    {
      try
      {
        data = theReader.readLine();

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
    else // if marker:String DOES exist
    {
      try
      {
        data = theReader.readLine();

        if (data == null)
        {
          return data;
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

      while (data.startsWith(marker))
      {
        if (data != null)
        {
          try
          {
            data = theReader.readLine();

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



      if (data != null) // if data:Sting DOES exist
      {
        int x = data.indexOf(marker);
        if (x >= 0)
        {
          data = data.substring(0, x);
        }
      } // end if
    } // end else
    return data;
  }

  public String resetMarker(String mkr)
  {
    String ocmt = new String(marker);

    if (mkr.equals("0"))
    {
      marker = null;
    }
    else // if mkr has value other than "0"
    {
      marker = mkr;
    }
    return ocmt;
  }

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