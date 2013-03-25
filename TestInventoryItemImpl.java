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

    String temp = "";

    dri = new DataReaderImpl_1(); // create rto:DataReaderImpl_1

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
    int ctr = 0;
    StringBuffer csv = new StringBuffer(128); //"comma separated values" - empty string for getData()/update()
    InventoryItemImpl nemo;
    int returned; // for values returned by methods which this test app calls 
    StringBuffer sb = new StringBuffer(128); // empty string for displaying

    System.out.println("\n------------------ Test " + ++counter + " started ----------------");

    returned = InventoryItemImpl.getData(csv, dri, "~"); // load csv with first input (items separated by tilde) 


    if (returned == -3) // if iii.getData() returned err=-3 (which is special case for array "prompts" empty
    {
      nemo = new InventoryItemImpl(); // instantialize :InventoryItemImpl      
      nemo.formatDisplay(sb); // there may still be instance vars initialized by the constructor
      System.out.println(sb);

    }
    else // if iii.getData() returned err=0, i.e. if array "prompts" is not empty
    {
      while (returned == 0) // loop as long as getData() works fine
      {
        nemo = new InventoryItemImpl(); // instantiate III


        sb.setLength(0); // empty the sb:StringBuffer used for displaying
        returned = nemo.formatDisplay(sb);  // pass empty sb to formatDisplay(); fill sb with values. returned=0 (if normal)


        nemo.update(csv);                // pass the csv loaded above to update()

        sb.setLength(0);

        returned = nemo.formatDisplay(sb);
        System.out.println(sb);


        try
        {

          FileOutputStream fos = new FileOutputStream("iii_" + ++ctr + ".ser"); // 1. create a file 

          // :ObjectOutputStream 
          // It is a high level stream.      
          // It has method writeObject(Object x)
          // It serializes an Object to a file and gives it extension .ser  
          // and sends it to the output stream. 
          // Similarly, the ObjectInputStream class contains the following method for deserializing an object:
          ObjectOutputStream oos = new ObjectOutputStream(fos); // 2. create a serializer

          oos.writeObject(sb);  // serialize the instance            3. serialize the object
          oos.close();
          fos.close();






        }
        catch (Exception e)
        {
          System.out.println("Esception caught in run() " + e.getMessage() + e.getCause());
        }




        //returned = nemo.persistData(sb);

        System.out.println("--------------- Test " + counter + " finished -------------");
        System.out.println("\n--------------- Test " + ++counter + " started -------------");
        csv.setLength(0); // get next set of data
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

    //int ctr = 0;

    //==========================================


    try
    {
      for (int ctr = 0; ctr < 4; ctr++)
      {
        StringBuffer e = null;
        FileInputStream fis = new FileInputStream("iii_" + ++ctr + ".ser"); // Create a FileInputStream 
        ObjectInputStream ois = new ObjectInputStream(fis); // Class ObjectInputStream 
        e = (StringBuffer) ois.readObject();        //read (deserialize) an object 

        
        System.out.println("inflating the object ");
        printNumLoose();
        printNumClose();
        System.out.println(e);
        printNumClose();
        printNumLoose();

        
        ois.close();
        fis.close();


      }
    }
    catch (Exception e)
    {
      System.out.println("Esception caught in run() " + e.getMessage() + e.getCause());
    }


    //==========================================









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
