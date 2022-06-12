/******************************************************************************
 * class RecursivePowerModCalculator implements PowerModCalculator using the
 * recursive definition
 * 
 * base^exponent%modulus = 1 if exponent == 0
 * base^exponent%modulus = base^(exponent-1)*base%modulus if exponent > 1
 * 
 * @author James Benham
 *******************************************************************************/
 
package powermod;

public class RecursivePowerModCalculator implements PowerModCalculator {
	
	public long computeModuloPower(long base, long exponent, long modulus) {
		
		if (exponent == 0)
			return 1;
		else
			return computeModuloPower(base,exponent-1,modulus)*base%modulus;
		
	} // computeModulorPower

} // RecursivePowerModCalculator
