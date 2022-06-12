/***********************************************************
*
*  class Fragment6 implements the runFragment method to 
*   run fragment 6 from the algorithm analysis exercise
*
*  @author   James W Benham
*
*************************************************************/

class Fragment6 implements CodeFragment
{
  public long executeFragment(long n)
  {
    long sum = 0;
     
    for (long i = n*n; i > 0; i--) {
      if (i%n == 0) {
        for(long j = 0; j < i; j++)
          sum++;
      } //if
      else
        sum ++;
    } // outer loop
     
    return sum; 
  }  //executeFragment
} // fragment6
