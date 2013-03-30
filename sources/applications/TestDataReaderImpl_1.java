package applications;

//C:\\java\\JAC444_ientaltsev2_A1\\sources\\applications
//  TestDataReader.java (the test app)
import useful.*;
import java.io.*;
import java.util.*;

/**
 * <p>This is application class
 * <mark>TestDataReader</mark>. It makes and
 * uses an object of class DataReaderImpl_1
 * that implements interface DataReader and
 * works by calling its methods as often as
 * necessary. TestDataReader is in package
 * applications.
 *
 * @author Igor Entaltsev
 */
public class TestDataReaderImpl_1
{
  //===================static data
  // none

  //==================instance data
  /**
   * In which to store args from command line
   */
  // to store command-line args  as instance var(s)
  // private String[] names;
  public DataReaderImpl_1 dri;

  //==================constructors
  // none defined ( :. default constructor provided by compiler )
  //===================methods
  //===========(alphabetic by method name)
  //-------------------init()
  /**
   * <p>Creates dri:DataReaderImpl_1.</p>
   *
   * @return temp:String
   */
  private String init()
  {
    System.out.println("\n-------program start-------\n");
    String temp = "";

    // couldn't find class DataReaderImpl_1 
    // because...
    dri = new DataReaderImpl_1();
    //new InputStreamReader(System.in));

    return temp;
  }

  //------------------------run()
  /**
   * <p>Reads the first line of test data
   * file
   *
   * @throws IOException
   */
  private void run()
    throws IOException
  {
    //System.out.println("Hello World!");

    // object to store old comment marker text
    String ocmt = "";
    // new comment marker text
    String ncmt = "";
    String buf = "";

    // new object to store raw line
    String raw = dri.readLine();
    ncmt = raw.substring(0, 1);

    if (ncmt.equals("/"))
    {
      ncmt = raw.substring(0, 2);
    }
    //System.out.println("This is NCMT: " + ncmt);
    // if the first line in TDF = 1
    // TDF = test data file
    if (ncmt.equals("1"))
    {
      System.out.println(
        "The first char in the line is 1, "
        + "so marker is default.\n");
    }
    else if (ncmt.equals("0"))
    {
      ocmt = dri.resetMarker(ncmt);
      System.out.println(
        "The first char in line is 0"
        + " so marker is: " + ncmt);
    }
    else // in other cases set cmt to user's value
    {
      ocmt = dri.resetMarker(ncmt);
      System.out.println("The marker is: "
        + ncmt);
    }

    do
    {
      buf = dri.readLine();

      // if the line is not empty
      if (buf != null)
      {
        System.out.println("The line is: "
          + buf);
      }
      if (buf == null) // if the line is empty
      {
        break;
      }
    }
    while // while there's smth in the line
      (buf != null);
  }  // end of run()

  //--------------------------------------------wrap()
  /**
   * <p>Calls method close() on object
   * dri:DataReaderImpl_1</p>
   *
   * @return retval
   */
  private int wrap()
  {
    int retval = 0;

    dri.close();

    System.out.println("\n-------program end-------");
    return retval;
  }  // end of wrap()

  //-------------------------main()
  /**
   * This is the first method called. It
   * makes an object or its own class then
   * calls methods init(), run() and wrap()
   * in that sequence. Thus it clearly and
   * completely controls the application (but
   * the major work is delegated to be done
   * in the init(), run() and wrap()
   * methods).
   */
  public static void main(String[] args)
    throws IOException
  {
    TestDataReader theApp = new TestDataReader();

    theApp.init();   // then call its methods
    theApp.run();
    theApp.wrap();


  } // end of main()
} // end of class
