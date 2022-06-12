/**************************************************************************
 * class IterativePowerModCalculator implements computeModuloPower
 * using iteration.  It essentially uses a loop to implement the 
 * non-recursive definition of exponentiation: 
 * b^n = b*b*...*b, where there are n factors of b.
 * 
 * @author James Benham
 ************************************************************************/

package powermod;

public class IterativePowerModCalculator implements PowerModCalculator {

	@Override
	public long computeModuloPower(long base, long exponent, long modulus) {
		
		long result = 1;
		
		for(long i = 0; i < exponent; i++){
			result = result*base%modulus;
		} // loop
		
		return result;
	} //computeModuloPower
} // IterativePowerModCalculator
