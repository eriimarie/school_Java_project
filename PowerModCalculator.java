/**************************************************************************
 *  interface PowerModCalculator defines a method computeModuloPower that
 *  computes base^exponent%modulus for given long integers base, exponent
 *  and modulus
 * 
 *  @author James Benham
 ****************************************************************************/

package powermod;

public interface PowerModCalculator {
	
	/**
	 * Computes base^exponent%modulus
	 * 
	 * @param base
	 * @param exponent
	 * @param modulus
	 * @return base^exponent%modulus
	 * <p>
	 * <b>pre-conditions:</b> base > 0, modulus > 0, exponent >= 0
	 */
    
	public long computeModuloPower(
			long base,
			long exponent,
			long modulus);
}
