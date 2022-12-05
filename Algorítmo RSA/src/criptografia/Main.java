package criptografia;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;

public class Main {

	static Path path;
	static BigInteger[] numbers = new BigInteger[6];
	static Scanner sc = new Scanner(System.in);
	static RSA privateKey;
	
	public static void main(String args[]) {
		firstReadFile();
		
		while (true) {
			menu();
			String option = sc.nextLine();
			command(option);
		}
	}
	
	private static void menu() {
		System.out.println("1 - Read the file again\n"
				+ "2 - Set a new Path\n"
				+ "3 - Create a new private Key\n"
				+ "4 - ReCreate a private Key\n"
				+ "5 - Save the numbers\n"
				+ "6 - StringDecrypt\n"
				+ "7 - Exit\n\n"
				+ "Opton:");
	}
	
	private static void command(String option) {
		switch (option) {
		case "1": {
			if (Files.exists(path)) {
				try {
					readNumbers();
					System.out.println("Numbers normally loaded");
				} catch (Exception e) {
					System.out.println("Failed to read file --- ~Try read again~");
				}
			} else {
				System.out.println("File don't exist");
			}
			break;
		}
		case "2": {
			path = Path.of(sc.nextLine());
			break;
		}
		case "3": {
			privateKey = new RSA(512, 100);
			privateKey.storeNumbers(path);
			break;
		}
		case "4": {
			privateKey = new RSA(numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], numbers[5]);
			break;
		}
		case "5": {
			privateKey.storeNumbers(path);
			break;
		}
		case "6": {
			stringDecript();
		}
		case "7": {
			System.exit(0);
		}
		default:
			System.out.println("Invalid option...");
		}
	}

	private static void stringDecript() {
		String code = sc.nextLine();
		System.out.println(privateKey.RSAStringDecrypt(code));
	}

	private static void firstReadFile() {
		System.out.println("Set the file path");
		path = Path.of(sc.nextLine());
		if (Files.exists(path)) {
			try {
				readNumbers();
				System.out.println("Numbers normally loaded");
			} catch (Exception e) {
				System.out.println("Failed to read file --- ~Try read again~");
			}
		} else {
			System.out.println("File don't exist");
		}
	}

	public static void readNumbers() throws IOException {
		List<String> strNumbers = Files.readAllLines(path);
		for (int i = 0; i < 6; i++) {
			numbers[i] = new BigInteger(strNumbers.get(i));
		}
	}

}
