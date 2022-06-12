/****************************************************************************
 * class EfficientRecursivePowerModCalculator computes a power mod m using
 * the following recursion
 * 
 * b^n = 1 if n == 0
 * b^n = (b^(n/2))^2 if n > 0 and n is even
 * b^n = (b^((n-1)/2)^2 *b if n > 0 and n is odd
 * 
 * Note that for Java integer division, if n is odd (n-1)/2 == n/2
 * 
 * @author James Benham
 */
package powermod;

public class EfficientRecursivePowerModCalculator implements PowerModCalculator {

	@Override
	public long computeModuloPower(long base, long exponent, long modulus) {
		if (exponent==0)
			return 1;
		else {
			long halfPower = computeModuloPower(base,exponent/2,modulus);
			if (exponent%2 == 0) // exponent is even
				return halfPower*halfPower%modulus;
			else // exponent is odd
				return halfPower*halfPower*base%modulus;
		} // outer else
			
	} // computeModuloPower

} // EfficientPowerModCalculator
