package criptografia;

import java.math.BigInteger;

public class InverseRSA {

	private BigInteger e;
	private BigInteger n;
	
	InverseRSA(BigInteger e, BigInteger n) {
		this.e = e;
		this.n = n;
	}
	
	InverseRSA(BigInteger[] en) {
		this.e = en[0];
		this.n = en[1];
	}
	
	public String RSAStringEncrypt(String text) {
		return new BigInteger(text.getBytes()).modPow(e, n).toString();
	}
}
