// TestInventoryItemImpl.java - application class
// package applications
// import useful.*;
import java.io.*;
import java.util.*;


public class TestInventoryItemImpl
{
  //===================static data
  // none

  //==================instance data
  /**
   * In which to store args from command line
   */

  public DataReaderImpl_1 dri;

  //==================constructors
  // none defined ( :. default constructor provided by compiler )
  
  //===================methods
  //===========(alphabetic by method name)
 
  private void printNumLoose()  // prints 0      1      2      3...
  {
    for (int i = 0; i < 5; i++)
    {
      System.out.print(i);
      for (int j = 0; j < 9; j++)
      {
        System.out.print(" ");        
      }       
    }
    System.out.print("\n");
  }
  private void printNumClose() // prints 1234567890123....
  {
    for (int k = 0; k < 5; k++)
    {
      for (int m = 0; m < 10; m++)
      {
        System.out.print(m);
      }
    }
    System.out.print("\n");
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
       System.out.println("[Entered init()]");
    String temp = "";
       
    dri = new DataReaderImpl_1(); // create rto:DataReaderImpl_1
       System.out.println("[in init(). created dri.]");
       System.out.println("[Exited init()]");
    return temp;
  } // end of init()

  //------------------------run()
  /**
   * Controls the major part of the application (typically in a loop which reads input file(s). But,
   * in this example, the task is trivial.
   */
  private void run()
  {
       System.out.println("[Entered run()]");
    int counter = 0;    
    StringBuffer csv = new StringBuffer(128); //"comma separated values" - empty string for getData()/update()
    InventoryItemImpl nemo;
    int returned; // for values returned by methods which this test app calls 
    StringBuffer sb = new StringBuffer(128); // empty string for displaying
    
    System.out.println("\n------------------ Test "+ ++counter + " started ----------------");    
    //System.out.println("in run() BEFORE getData(), csv: ("+csv+")");
    returned = InventoryItemImpl.getData(csv,dri,"~"); // load csv with first input (items separated by tilde) 
    //System.out.println("in run() AFTER getData(), csv: ("+csv+")");
    
          if (returned == -3) // if iii.getData() returned err=-3 (which is special case for array "prompts" empty
          {      
            nemo = new InventoryItemImpl(); // instantialize :InventoryItemImpl      
            nemo.formatDisplay(sb); // there may still be instance vars initialized by the constructor
            System.out.println(sb);  
               //System.out.println("there are no prompts");
          }    
          
          else // if iii.getData() returned err=0, i.e. if array "prompts" is not empty
          {
            while (returned == 0) // loop as long as getData() works fine
            {                //System.out.println("\n --- csv:StringBuffer after III.getData() is: " + csv);        
              nemo = new InventoryItemImpl(); // instantiate III
                             //System.out.println("nemo, immdiately after construction, follows: (" + nemo+")");
              
              sb.setLength(0); // empty the sb:StringBuffer used for displaying
              returned = nemo.formatDisplay(sb);  // pass empty sb to formatDisplay(); fill sb with values. returned=0 (if normal)
              
              //System.out.println("[run() prints sb]");
              //System.out.println(sb);           // print out the loaded sb
              //               System.out.println("in run(), csv: ("+csv+")");
              
              nemo.update(csv);                // pass the csv loaded above to update()
                             //System.out.println("nemo, immdiately after III.update(), follows(" + nemo+")");
              
              sb.setLength(0);
              
              returned = nemo.formatDisplay(sb);
              System.out.println(sb);
              
              //returned = nemo.persistData(sb);
              
              System.out.println(  "--------------- Test " + counter + " finished -------------");
              System.out.println("\n--------------- Test " + ++counter + " started -------------");        
              csv.setLength(0); // get next set of data
              returned = InventoryItemImpl.getData(csv, dri, "~");
              
              
              
              
              
            }
            
              
          }
          System.out.println("[Exited run()]");
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
    System.out.println("[Entered wrap()]");
    int retval = 0;
    printNumLoose();
    printNumClose();
    
    
    
    




    printNumClose();
    printNumLoose();

    dri.close();
    System.out.println("[did dri.close()]");
    System.out.println("[Exited wrap()]");

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
       System.out.println("[entered main()");
    TestInventoryItemImpl theApp = new TestInventoryItemImpl(); // make object of own class

    theApp.init(args);                        // then call its methods
    theApp.run();
    theApp.wrap();
       System.out.println("[exited main()");

  } // end of main()
} // end of class
