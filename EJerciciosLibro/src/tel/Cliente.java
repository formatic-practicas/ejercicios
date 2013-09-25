package tel;

import java.io.*;
import java.net.*;

public class Cliente {
	public static int PORT = 4567;
	//public static String HOST = "127.0.0.1";
	public static String HOST = "192.168.0.6";
	public static void main(String[] args) {
		System.err.println("Intentando conectar con la telefonista");
		Socket skt = null;
		try {
			skt = new Socket(HOST, PORT);
		}
		catch (Exception ex) {
			System.err.println("La telefonista no está en línea");
			System.exit(-1);
		}
		int tel;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			ObjectOutputStream datos = new ObjectOutputStream(
					skt.getOutputStream());
			System.err.println("Nos conectamos con la telefonista:" + HOST
					+ ":" + PORT);
			System.err.println("Ingrese números -1 termina");
			while (true) {
				if ((tel = leerInt(teclado)) == -1) {
					System.err.println("Programa terminado");
					datos.writeInt(-1);
					datos.flush();
					datos.close();
					skt.close();
					System.exit(0);
				} else {
					datos.writeInt(tel);
					datos.flush();
				}
			}
		} catch (IOException ex1) {
			ex1.printStackTrace(System.err);
		}
	}
	public static int leerInt(BufferedReader buff) {
		int lee = 0;
		boolean error;
		do {
			error = false;
			try {
				lee = Integer.parseInt(buff.readLine());
			}catch (NumberFormatException ex) {
				System.err.println("Entrada erronea, repetir:?");
				error = true;
			}catch (Exception ex) {
				ex.printStackTrace(System.err);
			}
		} while (error);
		return lee;
	}
}
