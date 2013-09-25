

import java.io.*;

public class Cap2Ej1 {
	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nombre = "";
		int edad = 0;
		char categoria = 'A';
		boolean repetir = true;
		while (repetir) {
			try {
				System.out.println("Nombre:?");
				nombre = br.readLine();
				System.out.print("Edad:?");
				edad = Integer.parseInt(br.readLine());
				repetir = false;
			} catch (Exception ex) {
				System.out.println("Teclee bien!");
				//ex.printStackTrace(System.err);
				//System.exit(-1);
			}
		}
		if (edad > 25) {
			categoria = 'B';
		}
		edad += 10;
		System.out.println("El usuario " + nombre + " de categoría "
				+ categoria + " en una década tendrá " + edad + " años");
		System.exit(0);
	}
}
