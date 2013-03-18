// TestInventoryItemImpl.java - application class
// package applications
// import useful.*;
import java.io.*;
import java.util.*;

/**
 * Application which displays a welcome message on standard output. <p>This program illustrates a
 * "divide and conquer' strategy to make the program more Object Oriented </p>
 * 
* <p>The basic design of this application will be used for most applications in this and the
 * following Java course(s). This design is no accident! It is based on he idea that things have a
 * "correct" place, which the experience of many professional programmers have confirmed has many
 * advantages</p>
 * 
* <p>The biggest single advantage of this is the way the main() int(), run() and wrap() to organize
 * the rest of the program. It sets the practice that will be extended later. There is no need for
 * any other instructions to be added to main() so that code will NEVER have to be written again
 * (for the rest of your programming life!).</p>
 *
 * <p>For the time being you are asked to take that as true and follow this example so that the
 * advantages can be revealed in due course.</p>
 * 
* @version 1.0
 * @author Brian Perry
 */
public class TestInventoryItemImpl
{
  //===================static data
  // none

  //==================instance data
  /**
   * In which to store args from command line
   */
  // to store command-line args  as instance var(s)
  public DataReaderImpl_1 dri;

  //==================constructors
  // none defined ( :. default constructor provided by compiler )
  //===================methods
  //===========(alphabetic by method name)
  // ----------Method printNumLoose() 
  // prints 0      1      2      3...
  private void printNumLoose()
  {
    for (int i = 0; i < 6; i++)
    {
      System.out.print(i);
      for (int j = 0; j < 9; j++)
      {
        System.out.print(" ");
        j++;
      }
      i++;
    }
  }

  // ----------Method printNumClose()
  // prints 1234567890123....
  private void printNumClose()
  {
    for (int k = 0; k < 6; k++)
    {
      for (int m = 0; m < 10; m++)
      {
        System.out.print(m);
      }
    }
  }

  //-------------------init()
  /**
   * Performs one-time initialization at start of application typically the following...
   * <p>processes the command-line arguments.</p> <p>opens input and/output files or database(s)</p>
   * <p>opens network connections</p>
   *
   * @param args arguments from command-line
   */
  private String init(String[] args)
  {


    String temp = "";
    dri = new DataReaderImpl_1();


    return temp;
  } // end of init()

  //------------------------run()
  /**
   * Controls the major part of the application (typically in a loop which reads input file(s). But,
   * in this example, the task is trivial.
   */
  private void run()
  {
    int counter = 0;
    //empty string for getData()/update()
    StringBuffer csv = new StringBuffer(128);
    InventoryItemImpl nemo;
    int returned;
    // empty string for displaying
    StringBuffer sb = new StringBuffer(128);
    
    System.out.println("\n------------------ Test "+ ++counter + "----------------");
    
    // get first set of data
    returned = InventoryItemImpl.getData(csv,dri,"~");
    
    // check: is array "prompts" empty? (special case: -3)
    if (returned == -3)
    {
      // make object of target class
      nemo = new InventoryItemImpl();
      
      // there maybe instance vars initialized by the constructor
      nemo.formatDisplay(sb);
      System.out.println(sb);
      
    }
    // if array "prompts" is not empty
    else
    {
      while (returned == 0)
      {
        System.out.println("\n --- csv:StringBuffer after getData() is: " + csv);
        
        nemo = new InventoryItemImpl();
        System.out.println("nemo, immdiately after construction, follows");
        sb.setLength(0);
        returned = nemo.formatDisplay(sb);
        System.out.println(sb);

        nemo.update(csv);
        System.out.println("nemo, immdiately after update(), follows");
        sb.setLength(0);
        returned = nemo.formatDisplay(sb);
        System.out.println(sb);

        System.out.println("+++++++++ end of Test " + counter + "+++++++++++");
        System.out.println("\n+++++++++++++++ Test " + ++counter + "++++++++++++");

        // get next set of data
        csv.setLength(0);
        returned = InventoryItemImpl.getData(csv, dri, "~");

      }
    }
  }  // end of run()

  //-------------------------usage()
  /**
   * Displays a help message for how to use this application
   */
  private void usage()
  {
    System.err.println("USAGE IS: " + "java TestApp # any args ignored");
  } // end of usage()

  //--------------------------------------------wrap()
  /**
   * Performs one-time cleanup just before the application ends. <p>closes input and/output files or
   * database(s)</p> <p>closes network connections</p>
   */
  private int wrap()
  {
    int retval = 0;
    printNumLoose();
    printNumClose();




    printNumClose();
    printNumLoose();

    dri.close();

    return retval;
  }  // end of wrap()

  //-------------------------main()
  /**
   * This is the first method called. It makes an object or its own class then calls methods init(),
   * run() and wrap() in that sequence. Thus it clearly and completely controls the application (but
   * the major work is delegated to be done in the init(), run() and wrap() methods).
   */
  public static void main(String[] args)
    throws IOException
  {
    TestInventoryItemImpl theApp = new TestInventoryItemImpl(); // make object of own class

    theApp.init(args);                        // then call its methods
    theApp.run();
    theApp.wrap();


  } // end of main()
} // end of class
