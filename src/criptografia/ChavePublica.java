package criptografia;

import java.math.BigInteger;

/**
 * Classe que deve ser distribuída para o público.
 * Essa chave criptograva o texto
 * 
 */
public class ChavePublica {

	private BigInteger e;
	private BigInteger n;

	/*
	 * Construtor único, recebe os 2 valores que representam a chave.
	 * @param en Uma lista no tipo 'e, n'
	 * 
	 */
	ChavePublica(BigInteger[] en) {
		this.e = en[0];
		this.n = en[1];
	}
	
	/*
	 * Criptograva uma string qualquer
	 * @param text texto que será criptografado
	 * @return Texto ciptografado
	 * 
	 */
	public String RSAStringEncrypt(String text) {
		return new BigInteger(text.getBytes()).modPow(e, n).toString();
	}
}
