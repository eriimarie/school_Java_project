/***********************************************************
*
*  class Fragment4 implements the runFragment method to 
*   run fragment 4 from the algorithm analysis exercise
*
*  @author   James W Benham
*
*************************************************************/

class Fragment4 implements CodeFragment
{
  public long executeFragment(long n)
  {
     long sum = 0;
     
     for (long i = 1; i <= n*n; i += 3)
       for (long j = i; j >= 1; j /= 4)
         sum ++;
         
     return sum;
  }  //executeFragment
} // fragment4
