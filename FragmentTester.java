/************************************************************************
*
*  FragmentTester runs a fragment with a specified range of input values
*  and displays timing results
*
*  @author James W Benham
*
*
***************************************************************************/

import java.util.*;
import java.io.*;

class FragmentTester
{
  public static void main(String[] args)
  {
    final int MAX_FRAG_NUMBER = 6;
    
    CodeFragment fragment = null; // code fragment to be tested
    
    int  fragNumber,     // number of fragment
         exponent;       // exponent for time complexity
    long lowInputValue,  // first input value to fragment
         highInputValue, // last input value to fragment
         incrOrFactor;   // increment or factor used to compute next input value
        
    boolean isFactor,   // true if a factor is used to compute next input value
            isLog;      // true if timeComplexity has the from n^k * log(n)
            
    ArrayList<TimingDataRow> outputTable = new ArrayList<TimingDataRow>();
    
    Scanner inputScan = new Scanner(System.in);
 
    System.out.println("This program runs and times a code fragment for " +
                       "a range of input values.");
    System.out.println("It compares the actual run times to the time complexities.\n");
    
    System.out.println("Enter the fragment number (0 to "
                       + MAX_FRAG_NUMBER + "): ");
    fragNumber = inputScan.nextInt();
    
    if (fragNumber >= 0 & fragNumber <= MAX_FRAG_NUMBER)
      switch (fragNumber)
      {
        case 0: fragment = new Fragment0();
                break;
        case 1: fragment = new Fragment1();
                break;
        case 2: fragment = new Fragment2();
                break;
        case 3: fragment = new Fragment3();
                break;
        case 4: fragment = new Fragment4();
                break;
        case 5: fragment = new Fragment5();
                break;
        case 6: fragment = new Fragment6();
                break;
      } // switch (and if)                
    else 
    {
      System.out.println("Invalid fragment number - exiting program.");
      System.exit(1);
    }
                          
    System.out.println("Enter the exponent for the time complexity: ");
    exponent = inputScan.nextInt();
    System.out.println("Is n^" + exponent + " to be multiplied by log(n)? (Y/N)");
    String isLogString = inputScan.next();
    if (isLogString.charAt(0) == 'Y' || isLogString.charAt(0) == 'y')
      isLog = true;
    else 
      isLog = false;
      
    System.out.println("Enter the first input value to be used:");
    lowInputValue = inputScan.nextLong();
    System.out.println("Enter the highest input value to be used:");
    highInputValue = inputScan.nextLong();
    System.out.println("The next input value can be obtained by adding an increment "
                       + "to the current value or by multiplying by a factor.");
    System.out.println("Enter I for increment or F for factor:");
    String isIncrementString = inputScan.next();
    if (isIncrementString.charAt(0) == 'I' || isIncrementString.charAt(0) == 'i')
    {
      isFactor = false;
      System.out.println("Enter the increment:");
      incrOrFactor = inputScan.nextLong();
    }
    else
    {
      isFactor = true;
      System.out.println("Enter the factor:");
      incrOrFactor = inputScan.nextLong();
    }
      
    FragmentTimer timer = new FragmentTimer(fragment, exponent, isLog);
   
    outputTable = timer.createTable(lowInputValue,highInputValue,
                                    incrOrFactor,isFactor);
    
    Iterator<TimingDataRow> rowIterator = outputTable.iterator();
    
    System.out.println("Input\t\tRun Time (nsec)\t\tTime Complexity\t\tRunTime/Complexity\n");
    
    while (rowIterator.hasNext())
    {
       TimingDataRow row = rowIterator.next();
       
       long inputValue = row.getInputValue();
       long runTime = row.getRunTime();
       double complexity = row.getComplexity();
       double ratio = row.getRatio();
    
       System.out.printf("%1$8d\t%2$,15d\t\t%3$12.4g\t\t%4$16.6g\n",
                         inputValue, runTime, complexity, ratio);
    }                        
  }
}   