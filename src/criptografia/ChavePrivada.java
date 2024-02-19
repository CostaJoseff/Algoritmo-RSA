package criptografia;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * chave principal que gera os numeros gerais e os numeros que vão compor a chave publica além de 
 * reverter a mensagen criptografada.
 * 
 */
public class ChavePrivada {
	
	private BigInteger p, q, n, funcTotiente, d, e;
	private int bitLength;
	
	/*
	 * Construtor padrão que gera tudo. Não precisa de parametros
	 * 
	 */
	ChavePrivada() {
		int bitLength = 512;
		p = new BigInteger(bitLength, 100, new SecureRandom());
		q = new BigInteger(bitLength, 100, new SecureRandom());
		generateNumbers(bitLength);
	}
	
	/*
	 * Construtor utilizado para quando já possui os 2 numeros primos e um valor ded bitLength.
	 * Eu utilizo 512 como bitLength.
	 * 
	 */
	ChavePrivada(BigInteger p, BigInteger q, int bitLength) {
		this.p = p;
		this.q = q;
		generateNumbers(bitLength);
	}
	
	/*
	 * Construtor utitlizado quando exisite a necessidade de utilizar os seus numeros.
	 * 
	 */
	ChavePrivada(BigInteger p, BigInteger q, BigInteger n, BigInteger funcTotiente, BigInteger d, BigInteger e) {
		this.p = p;
		this.q = q;
		this.n = n;
		this.funcTotiente = funcTotiente;
		this.d = d;
		this.e = e;
	}
	
	/*
	 * Reverte a criptografia que foi gerada pela chave criada com os números dessa class.
	 * 
	 * @param brokenText texto resultado da criptografia
	 * @return Mensagem original criptografada pela chave pública 
	 * 
	 */
	public String RSAStringDecrypt(String brokenText) {
		return new String(new BigInteger(brokenText).modPow(d, n).toByteArray());
	}
	
	/*
	 * Gera os números necessários para compor as chaves primaia (a class atual) e as públicas (geradas pelos numeros criados aqui)
	 * 
	 */
	private void generateNumbers(int bitLength) {
		this.bitLength = bitLength;
		
		n = p.multiply(q);
		funcTotiente = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		d = new BigInteger(funcTotiente.divide(new BigInteger(bitLength, 10, new SecureRandom())).toString());
		while (funcTotiente.gcd(d).intValue() != 1) {d = d.subtract(BigInteger.ONE);}
		e = d.modInverse(funcTotiente);
	}
	
	/*
	 * Entrega os 2 números que as chaves públicas precisam para serem criadas
	 * 
	 * @return Array de BigIntegers na ordem 'e, n'
	 * 
	 */
	public BigInteger[] getPublicNumbers() {
		return new BigInteger[] {e, n};
	}
	
	/*
	 * Entrega todos os números utilizados nessa classe
	 * 
	 * @return Array de BigIntegers na ordem 'p, q, n, funcaoTotiente, d, e'
	 * 
	 */
	public BigInteger[] getAllNumbers() {
		return new BigInteger[] {p, q, n, funcTotiente, d, e};
	}
	
	/*
	 * Entrega o bitLength utilizado nessa classe
	 * 
	 * @return Valor do BitLenth
	 * 
	 */
	public int getBitLength() {
		return this.bitLength;
	}
}
