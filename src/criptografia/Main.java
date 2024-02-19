package criptografia;

import java.util.Scanner;
import java.math.BigInteger;

public class Main {

	public static void main(String args[]) {
		RSA rsa = new RSA();
		ChavePublica cPub = rsa.getCopiaPublica();
		System.out.println("Informe a mensagem: ");
		Scanner sc = new Scanner(System.in);
		String msg = "Sushi de morango";//sc.nextLine();
		System.out.println(msg);
		String criptografia = cPub.RSAStringEncrypt(msg);
		rsa.ReverterCriptografia(criptografia);
		sc.close();
		
	}
}
