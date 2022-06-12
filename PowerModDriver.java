/***********************************************************************
 * class PowerModDriver is a driver to raise a given base to a given
 *  exponent modulo a given modulus.  It also determines the time
 *  needed to perform the calculation (in nanoseconds).
 *  
 *  The base, exponent, and modulus are given by run-time arguments.
 *  
 *  @author James Benham
 ***********************************************************************/

package powermod;

public class PowerModDriver {

	public static void main(String[] args) {
   
		long base = Long.parseLong(args[0]);
		long exponent = Long.parseLong(args[1]);
		long modulus = Long.parseLong(args[2]);
		
		PowerModCalculator calculator
		  // = new RecursivePowerModCalculator();
		  // = new IterativePowerModCalculator();
		  = new RecursivePowerModCalculator();
		
		long startTime = System.nanoTime();
		long modPower = calculator.computeModuloPower(base, exponent, modulus);
		long endTime = System.nanoTime();
		long runTime = endTime - startTime;
		
		System.out.println(base	+ "^" + exponent + "%" + modulus + " = " + modPower);
		System.out.println("The run time was " + runTime + " nanoseconds.");
	}

}
