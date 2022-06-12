/*********************************************************************
*
*  TimingDataRow holds one row in a table of timing data for
*   running a code fragment with a given input.  Specifically,
*   it holds the input value n, the measured run time, the
*   time complexity, and the ration of run time to time complexity
*
*  @author James W Benham
*
***********************************************************************/

class TimingDataRow
{
   private long n;                  // input value
   private long runTime;           // measured run time (nanoseconds) 
   private double timeComplexity;  // computed time complexity
   private double ratio;           // run-time/time-complexity
   
   // Constructor
   public TimingDataRow(long n, long runTime, double timeComplexity, double ratio)
   {
     this.n = n;
     this.runTime = runTime;
     this.timeComplexity = timeComplexity;
     this.ratio = ratio;
   }
   
   // get methods
   public long getInputValue()    { return n;}
   public long getRunTime()       { return runTime;}
   public double getComplexity()  { return timeComplexity;}
   public double getRatio()       { return ratio;}
}
