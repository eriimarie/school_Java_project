/***********************************************************
*
*  class Fragment0 implements the runFragment method to 
*   run fragment 0 - the code fragment discussed in class
*
*  @author   James W Benham
*
*************************************************************/

class Fragment0 implements CodeFragment
{
  public long executeFragment(long n)
  {
     long sum = 0;
     
     for (long i = 1; i < n*n*n; i++)
       for(long j = 1; j < i*i; j++)
         sum++;
         
     return sum;
  }  //executeFragment
} // fragment0
