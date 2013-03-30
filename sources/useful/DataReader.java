package useful;

import java.io.*;


public interface DataReader
{
  
  
  public String readLine();

  
  public String resetMarker(String mkr);

  
  public void close();
}