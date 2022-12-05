package criptografia;

import java.util.HashSet;
import java.util.Random;

public class CriptografiaJoseff {
	
	String[] grupoSoma7  = {"q", "e", "t", "u", "o", "l"};
	String[] grupoSoma12 = {"w", "r", "y", "i", "p", "k"};
	String[] grupoSoma20 = {"j", "g", "d", "a", "x", "v", "n"};
	String[] grupoSoma9  = {"h", "f", "s", "z", "c", "b", "m"};
	String[] alfabeto = {"w", "r", "y", "i", "p", "k", "h", "f", "s", "z", "c", "b", "m", "q", "e", "t", "u", "o", "l", "j", "g", "d", "a", "x", "v", "n"};
	HashSet<String> setSoma7 = new HashSet<>();
	HashSet<String> setSoma12 = new HashSet<>();
	HashSet<String> setSoma20 = new HashSet<>();
	HashSet<String> setSoma9 = new HashSet<>();
	Random gerador = new Random();
	
	public CriptografiaJoseff() {
		for (String letra : grupoSoma7) {
			setSoma7.add(letra);
		}
		for (String letra : grupoSoma12) {
			setSoma12.add(letra);
		}
		for (String letra : grupoSoma20) {
			setSoma20.add(letra);
		}
		for (String letra : grupoSoma9) {
			setSoma9.add(letra);
		}
	}
	
	public String quebrar(String senha) {
		String senhaInvertida = "";
		String retorno = "";
		int soma;
		char[] letras = senha.toCharArray();
		for (int i = letras.length - 1; i > -1 ; i--) {
			senhaInvertida += letras[i];
		}

		for (char letra : senhaInvertida.toCharArray()) {
			String letraAleatoria = alfabeto[gerador.nextInt(26)];
			if (setSoma7.contains(letraAleatoria)) { 
				soma = 7;
			} else if (setSoma12.contains(letraAleatoria)) {
				soma = 12;
			} else if (setSoma20.contains(letraAleatoria)) {
				soma = 20;
			} else {
				soma = 9;
			}

			String hexadecimal = String.format("%x", ((int) letra + soma));
			retorno += hexadecimal + letraAleatoria;
		}
		return retorno;
	}
	
	public String montar(String criptografia) {
    	String retorno = "";
		String criptoInvertida = "";
		int soma = 0;
		
		for (int i = 0; i < criptografia.length(); i += 3) {
			String hexadecimal = "" + criptografia.charAt(i) + criptografia.charAt(i + 1);
			String chave = "" + criptografia.charAt(i + 2);
			
			if (setSoma12.contains(chave)) {
				soma = 12;
			} else if (setSoma20.contains(chave) ) {
				soma = 20;
			} else if (setSoma7.contains(chave)) {
				soma = 7;
			} else if (setSoma9.contains(chave)) {
				soma = 9;
			}
			
			int decimal = Integer.parseInt(hexadecimal, 16) - soma;
			retorno += (char) decimal;
		}
		
		for (int i = retorno.length() - 1; i > -1; i--) {
			criptoInvertida += "" + retorno.charAt(i);
		}
		
		return criptoInvertida;
    }
}
