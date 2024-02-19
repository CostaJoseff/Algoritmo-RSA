package criptografia;

import java.math.BigInteger;

/**
 * Classe que serve como HUB para gerar chaves e revertere Criptografias
 * 
 */
public class RSA {
	
	private ChavePrivada cPriv;
	private String txtCripto, msg;
	
	RSA() {
		cPriv = new ChavePrivada();
	}
	
	RSA(BigInteger p, BigInteger q, BigInteger n, BigInteger funcTotiente, BigInteger d, BigInteger e) {
		cPriv = new ChavePrivada(p, q, n, funcTotiente, d, e);
	}
	
	public ChavePublica getCopiaPublica() {
		return new ChavePublica(cPriv.getPublicNumbers());
	}
	
	public void ReverterCriptografia(String txt) {
		txtCripto = txt;
		msg = cPriv.RSAStringDecrypt(txt);
	}
}
