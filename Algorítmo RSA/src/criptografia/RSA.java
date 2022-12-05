package criptografia;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;

public class RSA {
	
	private BigInteger p, q, n, funcTotiente, d, e;
	private int bitLength;
	
	RSA() {
		int bitLength = 512;
		p = new BigInteger(bitLength, 100, new SecureRandom());
		q = new BigInteger(bitLength, 100, new SecureRandom());
		generateNumbers(bitLength);
	}
	
	RSA(BigInteger p, BigInteger q, int bitLength) {
		this.p = p;
		this.q = q;
		generateNumbers(bitLength);
	}
	
	RSA(int bitLength, int expoentLength) {
		p = new BigInteger(bitLength, expoentLength, new SecureRandom());
		q = new BigInteger(bitLength, expoentLength, new SecureRandom());
		generateNumbers(bitLength);
	}
	
	RSA(BigInteger p, BigInteger q, BigInteger n, BigInteger funcTotiente, BigInteger d, BigInteger e) {
		this.p = p;
		this.q = q;
		this.n = n;
		this.funcTotiente = funcTotiente;
		this.d = d;
		this.e = e;
	}
	
	public String RSAStringDecrypt(String brokenText) {
		return new String(new BigInteger(brokenText).modPow(d, n).toByteArray());
	}
	
	public void storeNumbers(Path path){
		try {
			Files.deleteIfExists(path);
			Files.createFile(path);
			String text = p + "\n" +
							q + "\n" +
							n + "\n" +
							funcTotiente + "\n" +
							d + "\n" +
							e;
			Files.writeString(path, text);
		} catch (Exception e) {
			System.out.println("Error storing numbers");
		}
	}
	
	private void generateNumbers(int bitLength) {
		this.bitLength = bitLength;
		
		n = p.multiply(q);
		funcTotiente = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		d = new BigInteger(funcTotiente.divide(new BigInteger(bitLength, 10, new SecureRandom())).toString());
		while (funcTotiente.gcd(d).intValue() != 1) {d = d.subtract(BigInteger.ONE);}
		e = d.modInverse(funcTotiente);
	}
	
	public BigInteger[] getPublicNumbers() {
		return new BigInteger[] {e, n};
	}
	
	public BigInteger[] getAllNumbers() {
		return new BigInteger[] {p, q, n, funcTotiente, d, e};
	}
	
	public int getBitLength() {
		return this.bitLength;
	}
}
