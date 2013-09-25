package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class IO {
	public static float pideFloat(String titulo, float desde, float hasta) {
		float ret = 0.0f;
		do {
			System.out.println(titulo);
			try {
				ret = Float.parseFloat(br.readLine());
				if(ret < desde || ret > hasta){
					System.out.println("Rango erroneo");
					continue;
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("Formato erroneo");
			} catch (IOException e) {
				System.out.println("Error de teclado");
			}
		} while (true);
		return ret;
	}
	public static int pideInt(String titulo, int desde, int hasta){
		return pideEntero(titulo, desde, hasta);
	}
	public static int pideEntero(String titulo, int desde, int hasta) {
		int ret = 0;
		do {
			System.out.println(titulo);
			try {
				ret = Integer.parseInt(br.readLine());
				if(ret < desde || ret > hasta){
					System.out.println("Rango erroneo");
					continue;
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("Formato erroneo");
			} catch (IOException e) {
				System.out.println("Error de teclado");
			}
		} while (true);
		return ret;
	}

	public static BufferedReader br = new BufferedReader(new InputStreamReader(
	System.in));

	public static String pideCadena(String titulo) {
		String ret = "";
		do {
			System.out.println(titulo);
			try {
				ret = br.readLine();
				break;
			} catch (IOException e) {
				System.out.println("Error de teclado");
			}
		} while (true);
		return ret;
	}

}
