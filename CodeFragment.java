/***************************************************************************
*
*  Fragment is an interface for a code fragment, with a method 
*   executeFragment(n) to run the fragment.  It can be used
*   to compare actual run times to big-O time complexity values
*   for differing input values to determine if the time complexity
*   is a tight estimate.
*
*  @ author James W. Benham
*
******************************************************************************/

interface CodeFragment
{
   public long executeFragment(long n);
}