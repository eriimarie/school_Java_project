/******************************************************************************
*
*  FragmentTimer contains a timeFragment method that runs the specified
*   fragment with a range of different input values. 
*   It determines the actual run times, and compares them to the calculated 
*   time complexity.  
* 
*   To calculate the time complexities, it uses a formula of the form n^k
*    or (n^k) * log(n), where the exponent k and whether to multiply by log(n)
*    are parameters to the constructor (an int and a boolean)
*
*   @author James W Benham
*
********************************************************************************/

import java.util.*;
  
class FragmentTimer
{
   private CodeFragment fragment;     // the fragment being tested  
   
  // instance variables for the time complexity computation
  // assumes time complexity is of the form (n^k) or (n^k * log(n)) 
   private int k;                       // the exponent on n
   private boolean isLog;               // true if n^k is multiplied by log(n)
     
  // Constructor
   public FragmentTimer(CodeFragment fragment, int exponent, boolean isLog)
   {
      this.fragment = fragment;
      this.k = exponent;
      this.isLog = isLog;
   }

  // Time the fragement for all values in the range and report result  
   public ArrayList<TimingDataRow> createTable(long lowLimit, long highLimit,
                                               long increment, boolean isFactor)
   {
      ArrayList<TimingDataRow> outputTable = new ArrayList<TimingDataRow>();
      
      long n = lowLimit;
      
      while (n <= highLimit)
      {
        // Create a row and add to table
         long runTime = measureRunTime(n);
         double timeComplexity = computeTimeComplexity(n,k,isLog);
         double ratio = runTime / timeComplexity;
         TimingDataRow row = new TimingDataRow(n,runTime,timeComplexity,ratio);
         outputTable.add(row); 
         
        // Compute next input value
         if (isFactor)
           n *= increment;
         else
           n += increment;
      } //while
      
      return outputTable;   
   }
 
  // Measures the actual run time for the given fragment with input n 
   private long measureRunTime(long n)
   {
     // Run fragment and record run time
      long startTime = System.nanoTime();
      long result = fragment.executeFragment(n);
      long stopTime = System.nanoTime();
      long runTime = stopTime - startTime;
      return runTime;
    }

   // Computes time complexity using formula parameters
    private double computeTimeComplexity(long n, int k, boolean isLog)
    {
       double timeComplexity = 1;
       
       for (int i = 0; i < k; i++)
         timeComplexity *= n;
         
       if (isLog)
         timeComplexity *= Math.log(n);
         
       return timeComplexity;
    }
}                            