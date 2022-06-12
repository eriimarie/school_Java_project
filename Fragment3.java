/***********************************************************
*
*  class Fragment3 implements the runFragment method to 
*   run fragment 3 from the algorithm analysis exercise
*
*  @author   James W Benham
*
*************************************************************/

class Fragment3 implements CodeFragment
{
  public long executeFragment(long n)
  {
     long sum = 0;
     
     for (long i = 1; i <= n; i *= 3)
     {
       sum++;
     }
          
     return sum; 
  }  //executeFragment
} // fragment3
