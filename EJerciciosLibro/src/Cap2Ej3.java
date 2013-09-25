import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Cap2Ej3 {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nombre = "";
		float acumulador = 0;
		boolean error;
		try {
			System.out.println("Nombre:?");
			nombre = br.readLine();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		}
		for (int i = 0; i < 10; i++) {
			//error = false;
			do {
				error = false;
				try {
					System.out.print("Examen " + (i + 1) + "? ");
					acumulador += Float.parseFloat(br.readLine());
				} catch (NumberFormatException ex) {
					System.out.println("Error, ingresar denuevo");
					error = true;
				}

				catch (IOException ex) {
					ex.printStackTrace();
					System.exit(-1);
				}

			} while (error);
		}
		acumulador /= 10;
		acumulador = (float) Math.round(acumulador * 100) / 100;
		System.out.println(nombre + ", tu promedio es de: " + acumulador);
		System.exit(0);
	}
}
