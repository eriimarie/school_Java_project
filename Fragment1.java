/***********************************************************
*
*  class Fragment1 implements the runFragment method to 
*   run fragment 1 from the algorithm analysis exercise
*
*  @author   James W Benham
*
*************************************************************/

class Fragment1 implements CodeFragment
{
  public long executeFragment(long n)
  {
     long sum = 0;
     
     for (long i = 0; i < n*n; i++)
       for (long j = 0; j*j < i ; j++)
         sum++;
         
     return sum;
  }  //executeFragment
} // fragment1
