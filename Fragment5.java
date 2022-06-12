/***********************************************************
*
*  class Fragment5 implements the runFragment method to 
*   run fragment 5 from the algorithm analysis exercise
*
*  @author   James W Benham
*
*************************************************************/

class Fragment5 implements CodeFragment
{
  public long executeFragment(long n)
  {
    long sum = 0;
     
    for (long i = 1; i <= n*n; i++) {
      if(i%5 == 0) {
         for (long j = i*i; j > 0; j--)
           sum ++;
      } // if
      else {
        for (long k = 0; k < i; k++)
          sum++;
      } // else
        
    } // outer loop
     
    return sum; 
  }  //executeFragment
} // fragment5
