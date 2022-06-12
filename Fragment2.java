/***********************************************************
*
*  class Fragment2 implements the runFragment method to 
*   run fragment 2 from the algorithm analysis exercise
*
*  @author   James W Benham
*
*************************************************************/

class Fragment2 implements CodeFragment
 {
  public long executeFragment(long n)
  {
     long sum = 0;
     
     for (long i = 0; i < n*n; i += 2)
       for (long j = 0; j < i; j++)
         for (long k = 0; k < j*j; k++)
           sum++;
         
     return sum;
  }
}
