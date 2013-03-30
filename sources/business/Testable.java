// uncomment the line below when in proper directory tree
package business; // because it will only be used by Business Classes
import java.io.*;
// Testable.java - Testable interface
/**
   Implementing this interface in EVERY new Business Class 
   would force all programmers to use the design/testing methodoloy
   I use in my own work.  Specifically, I create a formatDisplay() and
   update() instance methods and a getData() static method (but 
   static methods like getData() do not form part of an interface).
*/
public interface Testable
{
  // the public instance methods in the interface
  public int formatDisplay(StringBuffer sbuf);
  
  public int update(StringBuffer sb);
  // uncomment the following (which no class implements) to force a compile error 
  // public void imaginary(String ghost);
  // uncomment the following line to see the error generated relating to statics
  // public int getData(StringBuffer sb, BufferedReader br, String separators);
} // end of interface Testable
